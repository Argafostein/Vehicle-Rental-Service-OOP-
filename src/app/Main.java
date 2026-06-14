package app;

import customer.Customer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import kendaraan.Bus;
import kendaraan.KapalPesiar;
import kendaraan.Kendaraan;
import kendaraan.Mesin;
import kendaraan.Mobil;
import kendaraan.Motor;
import kendaraan.Sedan;
import repository.DatabaseConnection;
import repository.DatabasePersistensi;
import service.RentalManager;
import transaksi.TransaksiRental;


public class Main {

    public static void main(String[] args) {

        DatabaseConnection.connect();
        RentalManager rentalManager =
                new RentalManager();

        Scanner input =
                new Scanner(System.in);

        // =====================================
        // CUSTOMER
        // =====================================

        Customer c1 =
                new Customer(
                        "Budi Santoso",
                        "08111111111"
                );

        Customer c2 =
                new Customer(
                        "Lepi",
                        "08222222222"
                );

        rentalManager.tambahCustomer(c1);
        rentalManager.tambahCustomer(c2);

        // =====================================
        // COLLECTION KENDARAAN
        // =====================================

        List<Kendaraan> daftarKendaraan =
                new ArrayList<>();

        // =====================================
        // MOBIL
        // =====================================

        try {

            Mesin mesinMobil =
                    new Mesin(
                            "V6 Turbo VTEC",
                            300,
                            400,
                            50
                    );

            Mobil mobil1 =
                    new Mobil(
                            "Honda Avanza",
                            "PT Rental Maju",
                            LocalDate.of(1999, 1, 1),
                            "B1234",
                            mesinMobil,
                            4,
                            "Manual",
                            4,
                            5,
                            350000
                    );

            rentalManager.tambahKendaraan(mobil1);

            daftarKendaraan.add(mobil1);

        } catch (Exception e) {

            System.out.println(
                    "Gagal membuat mobil : "
                    + e.getMessage()
            );
        }

        // =====================================
        // KAPAL PESIAR
        // =====================================

        try {

            Mesin mesinKapal =
                    new Mesin(
                            "Diesel Turbo",
                            5000,
                            1000,
                            2000
                    );

            KapalPesiar kp1 =
                    new KapalPesiar(
                            "Norwegian Cruise Line",
                            "Gerard",
                            LocalDate.of(1995, 2, 12),
                            "23242",
                            mesinKapal,
                            2000,
                            "Otomatis",
                            "Pesiar",
                            5000000,
                            50,
                            "Sangat Mewah"
                    );

            rentalManager.tambahKendaraan(kp1);

            daftarKendaraan.add(kp1);

        } catch (Exception e) {

            System.out.println(
                    "Gagal membuat kapal : "
                    + e.getMessage()
            );
        }

        // =====================================
        // MOTOR
        // =====================================

        try {

            Mesin mesinMotor =
                    new Mesin(
                            "2-Tak",
                            10,
                            5,
                            50
                    );

            Motor motor1 =
                    new Motor(
                            "Vespa",
                            "Fauzan",
                            LocalDate.of(2022, 5, 20),
                            "H1234",
                            mesinMotor,
                            2,
                            "Manual",
                            "Klasik",
                            "Rantai",
                            80000
                    );

            rentalManager.tambahKendaraan(motor1);

            daftarKendaraan.add(motor1);

        } catch (Exception e) {

            System.out.println(
                    "Gagal membuat motor : "
                    + e.getMessage()
            );
        }

        // =====================================
        // BUS
        // =====================================

        try {

            Mesin mesinBus =
                    new Mesin(
                            "Diesel Turbo Intercooler",
                            350,
                            100,
                            500
                    );

            Bus bus1 =
                    new Bus(
                            "Mercedes-Benz OH 1626",
                            "PO Quinta",
                            LocalDate.of(2023, 5, 20),
                            "B7001",
                            mesinBus,
                            6,
                            "Semi-Automatic",
                            1500000,
                            45,
                            "Pariwisata"
                    );

            rentalManager.tambahKendaraan(bus1);

            daftarKendaraan.add(bus1);

        } catch (Exception e) {

            System.out.println(
                    "Gagal membuat bus : "
                    + e.getMessage()
            );
        }

        // =====================================
        // SEDAN
        // =====================================

        try {

            Mesin mesinSedan =
                    new Mesin(
                            "V6 VTEC",
                            200,
                            50,
                            75
                    );

            Sedan sedan1 =
                    new Sedan(
                            "Honda Civic",
                            "Arga",
                            LocalDate.of(2014, 1, 1),
                            "B9999",
                            mesinSedan,
                            4,
                            "CVT",
                            500000,
                            false
                    );

            rentalManager.tambahKendaraan(sedan1);

            daftarKendaraan.add(sedan1);

        } catch (Exception e) {

            System.out.println(
                    "Gagal membuat sedan : "
                    + e.getMessage()
            );
        }

        // =====================================
        // CLI SYSTEM
        // =====================================

        boolean running = true;

        while (running) {

            System.out.println(
                    "\n====================================="
            );

            System.out.println(
                    "   SISTEM RENTAL KENDARAAN"
            );

            System.out.println(
                    "====================================="
            );

            System.out.println(
                    "1. Lihat Semua Kendaraan"
            );

            System.out.println(
                    "2. Rental Kendaraan"
            );

            System.out.println(
                    "3. Lihat Semua Transaksi"
            );

            System.out.println(
                    "4. Lihat Kendaraan Tersedia"
            );

            System.out.println(
                    "5. Statistik Kendaraan"
            );

            System.out.println(
                    "6. Riwayat Persistensi"
            );

            System.out.println(
                    "7. Keluar"
            );

            System.out.print(
                    "\nPilih menu : "
            );

            int pilih =
                    input.nextInt();

            input.nextLine();

            switch (pilih) {

                // =================================
                // LIHAT KENDARAAN
                // =================================

                case 1:

                    System.out.println(
                            "\n===== DAFTAR KENDARAAN ====="
                    );

                    for (int i = 0;
                            i < daftarKendaraan.size();
                            i++) {

                        Kendaraan k =
                                daftarKendaraan.get(i);

                        System.out.printf(
                                "%d. %s | Rp%,.0f | %s%n",
                                (i + 1),
                                k.getMerek(),
                                k.getTarifPerHari(),
                                k.getStatusKendaraan()
                        );
                    }

                    break;

                // =================================
                // RENTAL
                // =================================

case 2:

    System.out.println("\n===== RENTAL KENDARAAN =====");

    for (int i = 0; i < daftarKendaraan.size(); i++) {

        Kendaraan k = daftarKendaraan.get(i);

        System.out.printf(
                "%d. %s | Rp%,.0f | %s%n",
                (i + 1),
                k.getMerek(),
                k.getTarifPerHari(),
                k.getStatusKendaraan()
        );
    }

    System.out.print("\nPilih kendaraan : ");
    int index = input.nextInt();
    input.nextLine();

    if (index < 1 || index > daftarKendaraan.size()) {
        System.out.println("Pilihan tidak valid!");
        break;
    }

    Kendaraan dipilih = daftarKendaraan.get(index - 1);

    System.out.println("\nPilih Customer");
    System.out.println("1. " + c1.getNama());
    System.out.println("2. " + c2.getNama());

    System.out.print("Masukkan pilihan : ");
    int pilihCustomer = input.nextInt();
    input.nextLine();

    Customer customerDipilih =
            (pilihCustomer == 1) ? c1 : c2;

    System.out.print("Berapa hari sewa : ");
    int hari = input.nextInt();
    input.nextLine();

    // ==================================================
    // INI BAGIAN YANG KAMU KURANG
    // ==================================================

    TransaksiRental transaksi =
            rentalManager.rentalKendaraan(
                    customerDipilih,
                    dipilih,
                    LocalDate.now(),
                    LocalDate.now().plusDays(hari)
            );

    if (transaksi != null) {
        System.out.println("\n===== TRANSAKSI BERHASIL =====");
        transaksi.cetakTransaksi();
    }

    break;
                case 3:

                    rentalManager
                            .tampilkanSemuaTransaksi();

                    break;

                // =================================
                // KENDARAAN TERSEDIA
                // =================================

                case 4:

                    rentalManager
                            .tampilkanKendaraanTersedia();

                    break;

                // =================================
                // STATISTIK
                // =================================

                case 5:

                    int totalMobil = 0;
                    int totalMotor = 0;
                    int totalBus = 0;
                    int totalSedan = 0;
                    int totalKapal = 0;

                    for (Kendaraan k : daftarKendaraan) {

                        if (k instanceof Sedan) {

                            totalSedan++;

                        } else if (k instanceof Mobil) {

                            totalMobil++;

                        } else if (k instanceof Motor) {

                            totalMotor++;

                        } else if (k instanceof Bus) {

                            totalBus++;

                        } else if (k instanceof KapalPesiar) {

                            totalKapal++;
                        }
                    }

                    System.out.println(
                            "\n===== STATISTIK ====="
                    );

                    System.out.println(
                            "Total kendaraan : "
                            + daftarKendaraan.size()
                    );

                    System.out.println(
                            "Total mobil : "
                            + totalMobil
                    );

                    System.out.println(
                            "Total motor : "
                            + totalMotor
                    );

                    System.out.println(
                            "Total bus : "
                            + totalBus
                    );

                    System.out.println(
                            "Total sedan : "
                            + totalSedan
                    );

                    System.out.println(
                            "Total kapal : "
                            + totalKapal
                    );

                    break;

                // =================================
                // PERSISTENSI
                // =================================

                case 6:

                    DatabasePersistensi
                            .tampilkanRiwayatTransaksi();

                    DatabasePersistensi
                            .tampilkanStatistik();

                    break;

                // =================================
                // EXIT
                // =================================

                case 7:

                    running = false;

                    System.out.println(
                            "\nProgram selesai."
                    );

                    break;

                default:

                    System.out.println(
                            "\nMenu tidak tersedia!"
                    );
            }
        }

        input.close();

        // =====================================
        // RINGKASAN
        // =====================================

        rentalManager.tampilkanRingkasan();
    }
}