package kendaraan;

import interfaces.Jarak;
import java.time.LocalDate;

public class Mobil extends Kendaraan_darat implements Jarak {

    private int jumlahPintu;
    private int jumlahKursi;

    public Mobil(String merek, String pemilik, LocalDate tahunBuat,
                 String nomorKendaraan, Mesin mesin,
                 int jumlahRoda, String transmisi,
                 int jumlahPintu, int jumlahKursi, double tarifPerHari) {
        super(merek, pemilik, tahunBuat, nomorKendaraan, mesin, jumlahRoda, transmisi, tarifPerHari);
        assert jumlahPintu >= 2 : "Pintu minimal 2!";
        this.jumlahPintu = jumlahPintu;
        this.jumlahKursi = jumlahKursi;
    }

    public Mobil() { super(); }

    public void setJumlahPintu(int v) { assert v >= 2; this.jumlahPintu = v; }
    public int  getJumlahPintu()      { return jumlahPintu; }
    public void setJumlahKursi(int v) { this.jumlahKursi = v; }
    public int  getJumlahKursi()      { return jumlahKursi; }

    @Override public void cetakInfo() {
        System.out.println("=== INFO MOBIL ===");
        super.cetakInfo();
        System.out.printf("  Jumlah Pintu  : %d%n", jumlahPintu);
        System.out.printf("  Jumlah Kursi  : %d%n", jumlahKursi);
    }

    @Override public String bergerak()        { return "Mobil " + merek + " melaju di jalan raya"; }
    @Override public String jenisbahanbakar() { return "Bensin / Solar"; }
    @Override public double hitungJarak()     { return mesin.getIsiBBM() * 12.0; }

    /** Serialisasi lengkap untuk persistensi */
    public String toCsv() {
        return toCsvBase() + "," + jumlahPintu + "," + jumlahKursi;
    }
}