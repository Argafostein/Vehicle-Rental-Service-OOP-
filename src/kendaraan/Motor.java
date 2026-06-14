package kendaraan;

import interfaces.Jarak;
import java.time.LocalDate;

public class Motor extends Kendaraan_darat implements Jarak {

    private String jenisMotor;
    private String tipeRantai;

    public Motor(String merek, String pemilik, LocalDate tahunBuat,
                 String nomorKendaraan, Mesin mesin,
                 int jumlahRoda, String transmisi,
                 String jenisMotor, String tipeRantai, double tarifPerHari) {
        super(merek, pemilik, tahunBuat, nomorKendaraan, mesin, jumlahRoda, transmisi, tarifPerHari);
        this.jenisMotor = jenisMotor;
        this.tipeRantai = tipeRantai;
    }

    public Motor() { super(); }

    public void   setJenisMotor(String v) { this.jenisMotor = v; }
    public String getJenisMotor()         { return jenisMotor; }
    public void   setTipeRantai(String v) { this.tipeRantai = v; }
    public String getTipeRantai()         { return tipeRantai; }

    @Override public void cetakInfo() {
        System.out.println("=== INFO MOTOR ===");
        super.cetakInfo();
        System.out.printf("  Jenis Motor   : %s%n", jenisMotor);
        System.out.printf("  Tipe Rantai   : %s%n", tipeRantai);
    }

    @Override public String bergerak()        { return "Motor " + merek + " melaju dengan jenis " + jenisMotor; }
    @Override public String jenisbahanbakar() { return "Bensin"; }
    @Override public double hitungJarak()     { return mesin.getKapasitasBBM() * 40.0; }

    public String toCsv() {
        return toCsvBase() + "," + jenisMotor + "," + tipeRantai;
    }
}