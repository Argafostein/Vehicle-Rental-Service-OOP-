package kendaraan;

import java.time.LocalDate;

public abstract class Kendaraan_darat extends Kendaraan {

    protected int    jumlahRoda;
    protected String transmisi;
    protected static int counterKendaraanDarat = 0;

    public Kendaraan_darat(String merek, String pemilik, LocalDate tahunBuat,
                            String nomorKendaraan, Mesin mesin,
                            int jumlahRoda, String transmisi, double tarifPerHari) {
        super(merek, pemilik, tahunBuat, nomorKendaraan, mesin, tarifPerHari);
        assert jumlahRoda > 1 : "Roda harus > 1!";
        this.jumlahRoda = jumlahRoda;
        this.transmisi  = transmisi;
        counterKendaraanDarat++;
    }

    public Kendaraan_darat() { super(); }

    public void   setJumlahRoda(int v)   { assert v > 1 : "Roda harus > 1!"; this.jumlahRoda = v; }
    public void   setTransmisi(String v) { this.transmisi = v; }
    public int    getJumlahRoda()        { return jumlahRoda; }
    public String getTransmisi()         { return transmisi; }

    public static int getCounterKendaraanDarat() { return counterKendaraanDarat; }

    @Override
    public void cetakInfo() {
        super.cetakInfo();
        System.out.printf("  Jumlah Roda   : %d%n",  jumlahRoda);
        System.out.printf("  Transmisi     : %s%n",  transmisi);
    }
}