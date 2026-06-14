package kendaraan;

import interfaces.Rentable;
import java.time.LocalDate;

public abstract class Kendaraan implements Rentable {

    // ── Atribut ──
    protected String          merek;
    protected String          pemilik;
    protected LocalDate       tahunBuat;
    protected String          nomorKendaraan;
    protected Mesin           mesin;
    protected double          tarifPerHari;
    protected StatusKendaraan statusKendaraan;
    protected String          idKendaraan;

    protected static int counterKendaraan      = 0;
    private   static int idCounter             = 1000;

    // ── Abstract ──
    public abstract String bergerak();
    public abstract String jenisbahanbakar();

    // ── Konstruktor ──
    public Kendaraan(String merek, String pemilik, LocalDate tahunBuat,
                     String nomorKendaraan, Mesin mesin, double tarifPerHari) {
        assert pemilik != null && !pemilik.isBlank()   : "Pemilik tidak boleh kosong!";
        assert nomorKendaraan != null && nomorKendaraan.length() <= 5
                                                        : "Nomor kendaraan maks 5 karakter!";
        assert tarifPerHari >= 0                        : "Tarif tidak boleh negatif!";

        this.merek           = merek;
        this.pemilik         = pemilik;
        this.tahunBuat       = tahunBuat;
        this.nomorKendaraan  = nomorKendaraan;
        this.mesin           = mesin;
        this.tarifPerHari    = tarifPerHari;
        this.statusKendaraan = StatusKendaraan.TERSEDIA;
        this.idKendaraan     = "KND-" + (idCounter++);
        counterKendaraan++;
    }

    public Kendaraan() {
        this.merek           = "";
        this.pemilik         = "";
        this.tahunBuat       = LocalDate.of(2000, 1, 1);
        this.nomorKendaraan  = "";
        this.mesin           = new Mesin();
        this.tarifPerHari    = 0;
        this.statusKendaraan = StatusKendaraan.TIDAK_AKTIF;
        this.idKendaraan     = "KND-" + (idCounter++);
    }

    // ── Setter ──
    public void setMerek(String v)                     { this.merek = v; }
    public void setPemilik(String v)                   { this.pemilik = v; }
    public void setTahunBuat(LocalDate v)              { this.tahunBuat = v; }
    public void setNomorkendaraan(String v)            { this.nomorKendaraan = v; }
    public void setTarifPerHari(double v)              { this.tarifPerHari = v; }
    public void setStatusKendaraan(StatusKendaraan v)  { this.statusKendaraan = v; }

    // ── Getter ──
    public String          getMerek()           { return merek; }
    public String          getPemilik()         { return pemilik; }
    public LocalDate       getTahunBuat()       { return tahunBuat; }
    public String          getNomorkendaraan()  { return nomorKendaraan; }
    public double          getTarifPerHari()    { return tarifPerHari; }
    public StatusKendaraan getStatusKendaraan() { return statusKendaraan; }
    public String          getIdKendaraan()     { return idKendaraan; }
    public Mesin           getMesin()           { return mesin; }

    public static int getCounterKendaraan() { return counterKendaraan; }

    // ── Rentable: implementasi default di base class ──

    /**
     * Hitung biaya sewa berdasarkan tarif dan jumlah hari.
     * Concrete method di Kendaraan → bisa dipanggil tanpa cast ke Rentable.
     */
    @Override
    public double hitungBiayaSewa(int hariSewa) {
        assert hariSewa > 0 : "Hari sewa harus > 0!";
        return tarifPerHari * hariSewa;
    }

    @Override
    public boolean cekKetersediaan() {
        return statusKendaraan == StatusKendaraan.TERSEDIA;
    }

    @Override
    public void setStatusSewa(boolean disewa) {
        this.statusKendaraan = disewa ? StatusKendaraan.DISEWA : StatusKendaraan.TERSEDIA;
    }

    // ── Cetak ──
    public void cetakInfo() {
        System.out.printf("  ID            : %s%n",     idKendaraan);
        System.out.printf("  Merek         : %s%n",     merek);
        System.out.printf("  Pemilik       : %s%n",     pemilik);
        System.out.printf("  Tahun Buat    : %d%n",     tahunBuat.getYear());
        System.out.printf("  Nomor         : %s%n",     nomorKendaraan);
        System.out.printf("  Tarif/Hari    : Rp%,.0f%n", tarifPerHari);
        System.out.printf("  Status        : %s%n",     statusKendaraan.getLabel());
        System.out.printf("  Bahan Bakar   : %s%n",     jenisbahanbakar());
    }

    /**
     * Serialisasi bidang dasar ke CSV untuk persistensi.
     * Format: idKendaraan,jenis,merek,pemilik,tahunBuat,nomor,tarif,status,mesinCsv
     */
    public String toCsvBase() {
        return idKendaraan + ","
             + getClass().getSimpleName() + ","
             + merek + ","
             + pemilik + ","
             + tahunBuat + ","
             + nomorKendaraan + ","
             + (int) tarifPerHari + ","
             + statusKendaraan.name() + ","
             + mesin.toCsv();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%s)",
                idKendaraan, merek, nomorKendaraan, statusKendaraan.getLabel());
    }
}