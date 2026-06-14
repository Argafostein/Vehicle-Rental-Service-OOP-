package repository;

import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class DatabasePersistensi {

    public static final String FILE_TRANSAKSI = "transaksi.csv";
    public static final String FILE_CUSTOMER  = "customer.csv";

    // =========================================
    // SIMPAN CSV
    // =========================================
    public static void simpanBaris(String namaFile, String baris) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(namaFile, true))) {
            bw.write(baris);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("[DB] Gagal simpan: " + e.getMessage());
        }
    }

    // =========================================
    // BACA FILE CSV
    // =========================================
    public static List<String> bacaSemuaBaris(String namaFile) {
        List<String> hasil = new ArrayList<>();
        File file = new File(namaFile);

        if (!file.exists()) return hasil;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) hasil.add(line);
            }
        } catch (IOException e) {
            System.err.println("[DB] Error baca file: " + e.getMessage());
        }

        return hasil;
    }

    // =========================================
    // MYSQL INSERT (TAMBAHAN BARU)
    // =========================================
 public static void insertTransaksiMySQL(
        String id,
        String kendaraanId,
        String customerId,
        String tglRental,
        String tglKembali,
        int hari,
        double totalBiaya,
        String status
) {

    System.out.println("INSERT MYSQL DIPANGGIL");

    String sql =
        "INSERT INTO transaksi " +
        "(id, kendaraan_id, customer_id, tgl_rental, tgl_kembali, hari, total_biaya, status) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        System.out.println("MYSQL CONNECT = " + (conn != null));

        ps.setString(1, id);
        ps.setString(2, kendaraanId);
        ps.setString(3, customerId);
        ps.setString(4, tglRental);
        ps.setString(5, tglKembali);
        ps.setInt(6, hari);
        ps.setDouble(7, totalBiaya);
        ps.setString(8, status);

        int rows = ps.executeUpdate();

        System.out.println("Rows inserted = " + rows);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    // =========================================
    // RIWAYAT TRANSAKSI CSV
    // =========================================
    public static void tampilkanRiwayatTransaksi() {

        List<String> data = bacaSemuaBaris(FILE_TRANSAKSI);

        System.out.println("\n==================== RIWAYAT TRANSAKSI ====================");

        if (data.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        System.out.printf(
                "%-10s %-12s %-15s %-12s %-6s %-12s %-10s%n",
                "ID", "KENDARAAN", "CUSTOMER", "TGL", "HARI", "TOTAL", "STATUS"
        );

        System.out.println("----------------------------------------------------------");

        for (String row : data) {

            String[] f = row.split(",");

            if (f.length < 7) continue;

            System.out.printf(
                    "%-10s %-12s %-15s %-12s %-6s Rp%-10s %-10s%n",
                    f[0],
                    f[4],
                    f[2],
                    f[8],
                    f[10],
                    formatAngka(f[11]),
                    f[12]
            );
        }

        System.out.println("==========================================================\n");
    }

    // =========================================
    // STATISTIK CSV
    // =========================================
    public static void tampilkanStatistik() {

        List<String> data = bacaSemuaBaris(FILE_TRANSAKSI);

        if (data.isEmpty()) {
            System.out.println("Belum ada data statistik.");
            return;
        }

        long total = data.size();
        long aktif = 0;
        long selesai = 0;
        long pendapatan = 0;

        for (String row : data) {

            String[] f = row.split(",");

            if (f.length < 13) continue;

            if (f[12].equalsIgnoreCase("AKTIF")) aktif++;
            if (f[12].equalsIgnoreCase("SELESAI")) selesai++;

            try {
                pendapatan += Long.parseLong(f[11]);
            } catch (Exception ignored) {}
        }

        System.out.println("\n==================== STATISTIK ====================");
        System.out.println("Total Transaksi   : " + total);
        System.out.println("Aktif             : " + aktif);
        System.out.println("Selesai           : " + selesai);
        System.out.println("Pendapatan        : Rp" + formatAngka(String.valueOf(pendapatan)));
        System.out.println("==================================================\n");
    }

    // =========================================
    // HELPER FORMAT
    // =========================================
    private static String formatAngka(String angka) {
        try {
            long n = Long.parseLong(angka);
            return String.format("%,d", n).replace(",", ".");
        } catch (Exception e) {
            return angka;
        }
    }

    // =========================================
    // CEK FILE
    // =========================================
    public static boolean fileAda(String namaFile) {
        return new File(namaFile).exists();
    }
}