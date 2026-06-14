package kendaraan;

import interfaces.Jarak;
import java.time.LocalDate;

public class Bus extends Kendaraan_darat implements Jarak {

    private int    jumlahPenumpang;
    private String tipeBus;

    public Bus(String merek, String pemilik, LocalDate tahunBuat,
               String nomorKendaraan, Mesin mesin,
               int jumlahRoda, String transmisi,
               double tarifPerHari, int jumlahPenumpang, String tipeBus) {
        super(merek, pemilik, tahunBuat, nomorKendaraan, mesin, jumlahRoda, transmisi, tarifPerHari);
        assert jumlahPenumpang > 10 : "Penumpang bus harus > 10!";
        this.jumlahPenumpang = jumlahPenumpang;
        this.tipeBus         = tipeBus;
    }

    public Bus() { super(); }

    public void   setJumlahPenumpang(int v) { this.jumlahPenumpang = v; }
    public int    getJumlahPenumpang()      { return jumlahPenumpang; }
    public void   setTipeBus(String v)      { this.tipeBus = v; }
    public String getTipeBus()              { return tipeBus; }

    // Overloading setHargaTiket (polimorfisme statis)
    public void setTarifDiskon(double diskon) {
        this.tarifPerHari = tarifPerHari - (tarifPerHari * diskon);
    }

    @Override public void cetakInfo() {
        System.out.println("=== INFO BUS ===");
        super.cetakInfo();
        System.out.printf("  Jml Penumpang : %d%n", jumlahPenumpang);
        System.out.printf("  Tipe Bus      : %s%n", tipeBus);
    }

    @Override public String bergerak()        { return "Bus " + merek + " mengangkut penumpang"; }
    @Override public String jenisbahanbakar() { return "Solar"; }
    @Override public double hitungJarak()     { return mesin.getIsiBBM() * 8.0; }

    public String toCsv() {
        return toCsvBase() + "," + jumlahPenumpang + "," + tipeBus;
    }
}