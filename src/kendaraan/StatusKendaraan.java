package kendaraan;
/* Enum StatusKendaraan - Merepresentasikan status kendaraan di sistem rental.
 * Enum adalah tipe data yang nilainya terbatas dan sudah pasti.
 */
public enum StatusKendaraan {
    TERSEDIA("Tersedia"),
    DISEWA("Sedang Disewa"),
    PERAWATAN("Dalam Perawatan"),
    TIDAK_AKTIF("Tidak Aktif");
 
    private final String label;
 
    StatusKendaraan(String label) {
        this.label = label;
    }
 
    public String getLabel() {
        return label;
    }
 
    @Override
    public String toString() {
        return label;
    }
}