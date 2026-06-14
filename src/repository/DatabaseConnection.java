package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/rental_kendaraan";

    private static final String USER = "root";
    private static final String PASS = "";

    private static Connection conn;

    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Database berhasil terkoneksi!");
        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}