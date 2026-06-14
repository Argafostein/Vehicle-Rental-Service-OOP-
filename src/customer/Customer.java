package customer;

/**
 * Customer — merepresentasikan penyewa kendaraan.
 */
public class Customer {

    private String idCustomer;
    private String nama;
    private String nomorHP;

    private static int counter = 1;

    public Customer(String nama, String nomorHP) {
        this.idCustomer = "CUS-" + counter++;
        this.nama       = nama;
        this.nomorHP    = nomorHP;
    }

    // Getter
    public String getIdCustomer() { return idCustomer; }
    public String getNama()       { return nama; }
    public String getNomorHP()    { return nomorHP; }

    public void cetakInfo() {
        System.out.printf("  ID Customer   : %s%n", idCustomer);
        System.out.printf("  Nama          : %s%n", nama);
        System.out.printf("  Nomor HP      : %s%n", nomorHP);
    }

    /** Untuk persistensi */
    public String toCsv() {
        return idCustomer + "," + nama + "," + nomorHP;
    }

    public static Customer fromCsv(String csv) {
        String[] p = csv.split(",", 3);
        Customer c = new Customer(p[1], p[2]);
        return c;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s)", idCustomer, nama, nomorHP);
    }
}