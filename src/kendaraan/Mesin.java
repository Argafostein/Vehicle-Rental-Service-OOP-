package kendaraan;

public class Mesin {

    private String tipeMesin;
    private int    tenagaKuda;
    private double kapasitasBBM;
    private int    isiBBM;

    public Mesin(String tipeMesin, int tenagaKuda, double kapasitasBBM, int isiBBM) {
        this.tipeMesin    = tipeMesin;
        this.tenagaKuda   = tenagaKuda;
        this.kapasitasBBM = kapasitasBBM;
        this.isiBBM       = isiBBM;
    }

    public Mesin() {
        this("Standar", 0, 0.0, 0);
    }

    // Setter
    public void setTipeMesin(String v)   { this.tipeMesin    = v; }
    public void setTenagaKuda(int v)     { this.tenagaKuda   = v; }
    public void setKapasitasBBM(double v){ this.kapasitasBBM = v; }
    public void setIsiBbm(int v)         { this.isiBBM       = v; }

    // Getter
    public String getTipeMesin()    { return tipeMesin; }
    public int    getTenagaKuda()   { return tenagaKuda; }
    public double getKapasitasBBM() { return kapasitasBBM; }
    public int    getIsiBBM()       { return isiBBM; }

    public double getPersentaseBBM() {
        if (kapasitasBBM == 0) return 0;
        return (isiBBM / kapasitasBBM) * 100.0;
    }

    // Overloading isiBBM — 3 variasi
    public void isiBBM(int jumlah) {
        this.isiBBM = Math.min(this.isiBBM + jumlah, (int) this.kapasitasBBM);
        System.out.printf("  BBM diisi %d liter. Total: %d liter.%n", jumlah, this.isiBBM);
    }

    public void isiBBM() {
        this.isiBBM = (int) this.kapasitasBBM;
        System.out.printf("  Tangki penuh! BBM: %d liter.%n", this.isiBBM);
    }

    public void isiBBM(int harga, int hargaPerLiter) {
        int liter = harga / hargaPerLiter;
        isiBBM(liter);
        System.out.printf("  Beli BBM Rp%,d → %d liter.%n", harga, liter);
    }

    public void cetakInfo() {
        System.out.printf("  Tipe Mesin    : %s%n",  tipeMesin);
        System.out.printf("  Tenaga Kuda   : %d HP%n", tenagaKuda);
        System.out.printf("  Kapasitas BBM : %.1f L%n", kapasitasBBM);
        System.out.printf("  Isi BBM       : %d L (%.1f%%)%n", isiBBM, getPersentaseBBM());
    }

    /**
     * Serialisasi ke CSV untuk persistensi.
     * Format: tipeMesin|tenagaKuda|kapasitasBBM|isiBBM
     */
    public String toCsv() {
        return tipeMesin + "|" + tenagaKuda + "|" + kapasitasBBM + "|" + isiBBM;
    }

    public static Mesin fromCsv(String csv) {
        String[] p = csv.split("\\|");
        return new Mesin(p[0], Integer.parseInt(p[1]),
                Double.parseDouble(p[2]), Integer.parseInt(p[3]));
    }
}