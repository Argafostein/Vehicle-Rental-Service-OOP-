package transaksi;

import customer.Customer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import kendaraan.Kendaraan;
import kendaraan.StatusKendaraan;

/**
 * TransaksiRental — satu record penyewaan kendaraan.
 * Disimpan ke file CSV oleh DatabasePersistensi.
 */
public class TransaksiRental {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private String     idTransaksi;
    private Customer   customer;
    private Kendaraan  kendaraan;
    private LocalDate  tanggalRental;
    private LocalDate  tanggalKembali;
    private int        lamaSewa;
    private double     totalBiaya;
    private String     status; // "AKTIF" | "SELESAI"

    private static int counter = 1;

    // ── Konstruktor ──
    public TransaksiRental(Customer customer, Kendaraan kendaraan,
                           LocalDate tanggalRental, LocalDate tanggalKembali) {
        this.idTransaksi    = "TRX-" + String.format("%04d", counter++);
        this.customer       = customer;
        this.kendaraan      = kendaraan;
        this.tanggalRental  = tanggalRental;
        this.tanggalKembali = tanggalKembali;
        this.status         = "AKTIF";

        hitungLamaSewa();
        hitungTotalBiaya();

        kendaraan.setStatusKendaraan(StatusKendaraan.DISEWA);
    }

    private void hitungLamaSewa() {
        lamaSewa = (int) ChronoUnit.DAYS.between(tanggalRental, tanggalKembali);
        if (lamaSewa <= 0) lamaSewa = 1;
    }

    private void hitungTotalBiaya() {
        totalBiaya = kendaraan.getTarifPerHari() * lamaSewa;
    }

    public void selesaiRental() {
        kendaraan.setStatusKendaraan(StatusKendaraan.TERSEDIA);
        this.status = "SELESAI";
        System.out.println("  Rental " + idTransaksi + " selesai.");
    }

    // ── Getter ──
    public String    getIdTransaksi()     { return idTransaksi; }
    public Customer  getCustomer()        { return customer; }
    public Kendaraan getKendaraan()       { return kendaraan; }
    public LocalDate getTanggalRental()   { return tanggalRental; }
    public LocalDate getTanggalKembali()  { return tanggalKembali; }
    public int       getLamaSewa()        { return lamaSewa; }
    public double    getTotalBiaya()      { return totalBiaya; }
    public String    getStatus()          { return status; }

    // ── Cetak ──
    public void cetakTransaksi() {
        System.out.println("\n===== DETAIL TRANSAKSI =====");
        System.out.printf("  ID Transaksi  : %s%n", idTransaksi);
        System.out.printf("  Status        : %s%n", status);
        System.out.printf("  Customer      : %s (%s)%n", customer.getNama(), customer.getIdCustomer());
        System.out.printf("  Kendaraan     : %s [%s]%n", kendaraan.getMerek(), kendaraan.getIdKendaraan());
        System.out.printf("  Nomor         : %s%n", kendaraan.getNomorkendaraan());
        System.out.printf("  Tgl Rental    : %s%n", tanggalRental.format(FMT));
        System.out.printf("  Tgl Kembali   : %s%n", tanggalKembali.format(FMT));
        System.out.printf("  Lama Sewa     : %d hari%n", lamaSewa);
        System.out.printf("  Total Biaya   : Rp%,.0f%n", totalBiaya);
        System.out.println("============================");
    }

    /**
     * Serialisasi ke CSV untuk persistensi.
     * Format: idTrx,idCustomer,namaCustomer,hpCustomer,idKendaraan,merekKendaraan,
     *         nomorKendaraan,tarifPerHari,tglRental,tglKembali,lamaSewa,total,status
     */
    public String toCsv() {
        return String.join(",",
            idTransaksi,
            customer.getIdCustomer(),
            customer.getNama(),
            customer.getNomorHP(),
            kendaraan.getIdKendaraan(),
            kendaraan.getMerek(),
            kendaraan.getNomorkendaraan(),
            String.valueOf((int) kendaraan.getTarifPerHari()),
            tanggalRental.format(FMT),
            tanggalKembali.format(FMT),
            String.valueOf(lamaSewa),
            String.valueOf((int) totalBiaya),
            status
        );
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | %d hari | Rp%,.0f | %s",
            idTransaksi, kendaraan.getMerek(), customer.getNama(),
            lamaSewa, totalBiaya, status);
    }
}