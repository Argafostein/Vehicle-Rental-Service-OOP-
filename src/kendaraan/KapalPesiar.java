package kendaraan;

import interfaces.Jarak;
import java.time.LocalDate;

public class KapalPesiar extends Kendaraan_laut implements Jarak {

    private int    jumlahKamar;
    private String fasilitas;

    public KapalPesiar(String merek, String pemilik, LocalDate tahunBuat,
                       String nomorKendaraan, Mesin mesin,
                       double kapasitas, String jenismesin, String tipeLambung,
                       double tarifPerHari, int jumlahKamar, String fasilitas) {
        super(merek, pemilik, tahunBuat, nomorKendaraan, mesin,
              kapasitas, jenismesin, tipeLambung, tarifPerHari);
        assert jumlahKamar >= 1 : "Kapal minimal 1 kamar!";
        this.jumlahKamar = jumlahKamar;
        this.fasilitas   = fasilitas;
    }

    public KapalPesiar() { super(); }

    public void   setJumlahKamar(int v)     { assert v >= 1; this.jumlahKamar = v; }
    public int    getJumlahKamar()          { return jumlahKamar; }
    public void   setFasilitas(String v)    { this.fasilitas = v; }
    public String getFasilitas()            { return fasilitas; }

    @Override public void cetakInfo() {
        System.out.println("=== INFO KAPAL PESIAR ===");
        super.cetakInfo();
        System.out.printf("  Jumlah Kamar  : %d%n",  jumlahKamar);
        System.out.printf("  Fasilitas     : %s%n",  fasilitas);
    }

    @Override public String bergerak()        { return "Kapal " + merek + " berlayar di lautan"; }
    @Override public String jenisbahanbakar() { return "HFO (Heavy Fuel Oil)"; }
    @Override public double hitungJarak()     { return (mesin.getTenagaKuda() / 100.0) * mesin.getIsiBBM(); }

    public String toCsv() {
        return toCsvBase() + "," + kapasitas + "," + jenismesin + "," + tipeLambung
               + "," + jumlahKamar + "," + fasilitas;
    }
}