package service;

import customer.Customer;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import kendaraan.Kendaraan;
import kendaraan.StatusKendaraan;
import repository.DatabasePersistensi;
import repository.Repo;
import transaksi.TransaksiRental;

/**
 * RentalManager — service layer yang mengorkestrasi semua operasi rental.
 *
 * GENERIC COLLECTION: menggunakan Repo<T> dengan type parameter berbeda-beda.
 * PERSISTENSI: setiap transaksi baru langsung disimpan ke file CSV.
 * LINTAS PARADIGMA: menggunakan Stream + lambda (cariSatu, cariSemua, petakan).
 */
public class RentalManager {
    

    // ── Generic Repository (Generik Koleksi) ──
    private final Repo<Kendaraan>       repoKendaraan  = new Repo<>();
    private final Repo<Customer>        repoCustomer   = new Repo<>();
    private final Repo<TransaksiRental> repoTransaksi  = new Repo<>();

    // ==========================================
    //  KENDARAAN
    // ==========================================

    public void tambahKendaraan(Kendaraan kendaraan) {
        repoKendaraan.tambah(kendaraan);
        System.out.printf("  [+] Kendaraan '%s' [%s] ditambahkan.%n",
                kendaraan.getMerek(), kendaraan.getIdKendaraan());
    }

    public void tampilkanSemuaKendaraan() {
        System.out.println("\n===== DAFTAR KENDARAAN =====");
        // Paradigma fungsional: forEach dengan method reference
        repoKendaraan.getSemua().forEach(k -> {
            k.cetakInfo();
            System.out.println("  " + "-".repeat(32));
        });
    }

    /**
     * Tampilkan hanya kendaraan yang TERSEDIA.
     * Menggunakan cariSemua(Predicate) — Stream + lambda.
     */
    public void tampilkanKendaraanTersedia() {
        System.out.println("\n=== KENDARAAN TERSEDIA ===");

        // Paradigma fungsional: filter dengan lambda
        List<Kendaraan> tersedia = repoKendaraan.cariSemua(
            k -> k.getStatusKendaraan() == StatusKendaraan.TERSEDIA
        );

        if (tersedia.isEmpty()) {
            System.out.println("  Tidak ada kendaraan tersedia saat ini.");
            return;
        }

        // Paradigma fungsional: forEach + method reference
        tersedia.forEach(k ->
            System.out.printf("  %-10s %-22s %-10s Rp%,.0f/hari%n",
                k.getIdKendaraan(), k.getMerek(),
                k.getNomorkendaraan(), k.getTarifPerHari())
        );
    }

    /**
     * Cari kendaraan by ID — menggunakan cariSatu(Predicate).
     */
    public Optional<Kendaraan> cariKendaraanById(String id) {
        return repoKendaraan.cariSatu(k -> k.getIdKendaraan().equals(id));
    }

    /**
     * Ambil semua merek kendaraan — petakan() dengan Function.
     * Contoh penggunaan paradigma fungsional (map/transform).
     */
    public List<String> getDaftarMerek() {
        return repoKendaraan.petakan(Kendaraan::getMerek);   // method reference
    }

    public long hitungKendaraanTersedia() {
        return repoKendaraan.hitung(k -> k.getStatusKendaraan() == StatusKendaraan.TERSEDIA);
    }

    public Repo<Kendaraan> getRepoKendaraan() { return repoKendaraan; }

    // ==========================================
    //  CUSTOMER
    // ==========================================

    public void tambahCustomer(Customer customer) {
        repoCustomer.tambah(customer);
        System.out.printf("  [+] Customer '%s' [%s] ditambahkan.%n",
                customer.getNama(), customer.getIdCustomer());

        // Persistensi: simpan customer ke file
        DatabasePersistensi.simpanBaris(
            DatabasePersistensi.FILE_CUSTOMER,
            customer.toCsv()
        );
    // ── MYSQL PERSISTENSI ──
        
    }

    public Optional<Customer> cariCustomerById(String id) {
        return repoCustomer.cariSatu(c -> c.getIdCustomer().equals(id));
    }

    public Repo<Customer> getRepoCustomer() { return repoCustomer; }

    // ==========================================
    //  TRANSAKSI / RENTAL
    // ==========================================

    /**
     * Proses penyewaan kendaraan.
     * Validasi → buat TransaksiRental → simpan ke Repo → PERSIST ke CSV.
     */
    public TransaksiRental rentalKendaraan(
            Customer customer, Kendaraan kendaraan,
            LocalDate tanggalRental, LocalDate tanggalKembali) {

        // Validasi status kendaraan
        if (kendaraan.getStatusKendaraan() == StatusKendaraan.DISEWA) {
            System.out.println("  [!] Kendaraan '" + kendaraan.getMerek() + "' sedang disewa!");
            return null;
        }

        // Buat transaksi
        TransaksiRental transaksi = new TransaksiRental(
            customer, kendaraan, tanggalRental, tanggalKembali
        );

        // Simpan ke repo (in-memory)
        repoTransaksi.tambah(transaksi);

        // gatau disuruh gpt
        DatabasePersistensi.insertTransaksiMySQL(
          transaksi.getIdTransaksi(),
          kendaraan.getIdKendaraan(),
          customer.getIdCustomer(),
          transaksi.getTanggalRental().toString(),
          transaksi.getTanggalKembali().toString(),
              (int) java.time.temporal.ChronoUnit.DAYS.between(
          transaksi.getTanggalRental(),
          transaksi.getTanggalKembali()
    ),
          transaksi.getTotalBiaya(),
          transaksi.getStatus()
         );

        // ── PERSISTENSI: simpan ke file CSV ──
        DatabasePersistensi.simpanBaris(
            DatabasePersistensi.FILE_TRANSAKSI,
            transaksi.toCsv()
        );

        System.out.printf("  [✔] Rental '%s' berhasil! Transaksi: %s | Total: Rp%,.0f%n",
            kendaraan.getMerek(), transaksi.getIdTransaksi(), transaksi.getTotalBiaya());

        return transaksi;
    }

    public void selesaiRental(TransaksiRental transaksi) {
        transaksi.selesaiRental();
        System.out.println("  [✔] Kendaraan '" + transaksi.getKendaraan().getMerek() + "' sudah dikembalikan.");
    }

    public void tampilkanSemuaTransaksi() {
        System.out.println("\n===== TRANSAKSI IN-MEMORY =====");
        if (repoTransaksi.kosong()) {
            System.out.println("  Belum ada transaksi.");
            return;
        }
        // Paradigma fungsional: forEach + method reference
        repoTransaksi.getSemua().forEach(TransaksiRental::cetakTransaksi);
    }

    /**
     * Ambil transaksi aktif — filter dengan lambda.
     */
    public List<TransaksiRental> getTransaksiAktif() {
        return repoTransaksi.cariSemua(t -> t.getStatus().equals("AKTIF"));
    }

    public Repo<TransaksiRental> getRepoTransaksi() { return repoTransaksi; }

    // ==========================================
    //  RINGKASAN
    // ==========================================

    public void tampilkanRingkasan() {
        String garis = "=".repeat(45);
        System.out.println("\n" + garis);
        System.out.println("       RINGKASAN SISTEM RENTAL");
        System.out.println(garis);
        System.out.printf("  Total Kendaraan Terdaftar : %d%n", repoKendaraan.jumlah());
        System.out.printf("  Kendaraan Tersedia        : %d%n", hitungKendaraanTersedia());
        System.out.printf("  Total Customer            : %d%n", repoCustomer.jumlah());
        System.out.printf("  Total Transaksi (sesi ini): %d%n", repoTransaksi.jumlah());
        System.out.printf("  Transaksi Aktif           : %d%n", getTransaksiAktif().size());

        // Hitung total pendapatan sesi ini — Stream + reduce
        double totalPendapatan = repoTransaksi.getSemua().stream()
            .mapToDouble(TransaksiRental::getTotalBiaya)   // method reference
            .sum();                                        // terminal operation
        System.out.printf("  Total Pendapatan (sesi)   : Rp%,.0f%n", totalPendapatan);
        System.out.println(garis + "\n");
    }
}