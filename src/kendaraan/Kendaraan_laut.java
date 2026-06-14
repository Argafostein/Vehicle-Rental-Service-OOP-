package kendaraan;

import java.time.LocalDate;

public abstract class Kendaraan_laut extends Kendaraan {

    protected double kapasitas;
    protected String jenismesin;
    protected String tipeLambung;
    protected static int counterKendaraanLaut = 0;

    public Kendaraan_laut(String merek, String pemilik, LocalDate tahunBuat,
                           String nomorKendaraan, Mesin mesin,
                           double kapasitas, String jenismesin, String tipeLambung,
                           double tarifPerHari) {
        super(merek, pemilik, tahunBuat, nomorKendaraan, mesin, tarifPerHari);
        this.kapasitas   = kapasitas;
        this.jenismesin  = jenismesin;
        this.tipeLambung = tipeLambung;
        counterKendaraanLaut++;
    }

    public Kendaraan_laut() { super(); }

    public void   setKapasitas(double v)    { this.kapasitas   = v; }
    public void   setJenisMesin(String v)   { this.jenismesin  = v; }
    public void   setTipeLambung(String v)  { this.tipeLambung = v; }
    public double getKapasitas()            { return kapasitas; }
    public String getJenisMesin()           { return jenismesin; }
    public String getTipeLambung()          { return tipeLambung; }

    public static int getCounterKendaraanLaut() { return counterKendaraanLaut; }

    @Override
    public void cetakInfo() {
        super.cetakInfo();
        System.out.printf("  Kapasitas     : %.1f Ton/m3%n", kapasitas);
        System.out.printf("  Jenis Mesin   : %s%n", jenismesin);
        System.out.printf("  Tipe Lambung  : %s%n", tipeLambung);
    }
}