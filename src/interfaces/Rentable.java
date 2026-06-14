package interfaces;

/* Interface Rentable - Kontrak untuk semua kendaraan yang bisa disewakan.
 */
public interface Rentable {
    double hitungBiayaSewa(int hariSewa);
    boolean cekKetersediaan();
    void setStatusSewa(boolean disewa);
}