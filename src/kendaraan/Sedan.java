package kendaraan;

import interfaces.Jarak;
import java.time.LocalDate;

public class Sedan extends Kendaraan_darat implements Jarak {

    private boolean isRoof;

    public Sedan(String merek, String pemilik, LocalDate tahunBuat,
                 String nomorKendaraan, Mesin mesin,
                 int jumlahRoda, String transmisi,
                 double tarifPerHari, boolean isRoof) {
        super(merek, pemilik, tahunBuat, nomorKendaraan, mesin, jumlahRoda, transmisi, tarifPerHari);
        this.isRoof = isRoof;
    }

    public Sedan() { super(); }

    public void    setIsRoof(boolean v) { this.isRoof = v; }
    public boolean getIsRoof()          { return isRoof; }

    @Override public void cetakInfo() {
        System.out.println("=== INFO SEDAN ===");
        super.cetakInfo();
        System.out.printf("  Sunroof       : %s%n", isRoof ? "Ada" : "Tidak Ada");
    }

    @Override public String bergerak()        { return "Sedan " + merek + " melaju mulus"; }
    @Override public String jenisbahanbakar() { return "Pertamax"; }
    @Override public double hitungJarak()     { return mesin.getIsiBBM() * 12.0; }

    public String toCsv() {
        return toCsvBase() + "," + isRoof;
    }
}