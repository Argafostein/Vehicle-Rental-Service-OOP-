package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Repo<T> {

    private final List<T> data = new ArrayList<>();

    // ── CRUD Dasar ──

    public void tambah(T item) {
        data.add(item);
    }

    public boolean hapus(T item) {
        return data.remove(item);
    }

    public List<T> getSemua() {
        // Kembalikan copy agar data internal tidak bisa dimodifikasi langsung
        return new ArrayList<>(data);
    }

    public int jumlah() {
        return data.size();
    }

    public boolean kosong() {
        return data.isEmpty();
    }

    // ── Pencarian dengan Predicate (Functional / Lintas Paradigma) ──

    /**
     * Cari satu item yang cocok dengan kondisi (lambda / method reference).
     *
     * Contoh: repo.cariSatu(k -> k.getIdKendaraan().equals("KND-1000"))
     *
     * @param kondisi  Predicate<T> — ekspresi lambda
     * @return Optional<T>
     */
    public Optional<T> cariSatu(Predicate<T> kondisi) {
        return data.stream()
                   .filter(kondisi)
                   .findFirst();
    }

    /**
     * Cari semua item yang cocok dengan kondisi.
     *
     * Contoh: repo.cariSemua(k -> k.getStatusKendaraan() == StatusKendaraan.TERSEDIA)
     */
    public List<T> cariSemua(Predicate<T> kondisi) {
        return data.stream()
                   .filter(kondisi)        // filter — paradigma fungsional
                   .collect(Collectors.toList());
    }

    /**
     * Petakan (map) setiap item ke bentuk lain.
     * Paradigma fungsional: transformasi tanpa mutasi.
     *
     * Contoh: repo.petakan(k -> k.getMerek())  → List<String> semua merek
     */
    public <R> List<R> petakan(Function<T, R> fungsi) {
        return data.stream()
                   .map(fungsi)            // map — paradigma fungsional
                   .collect(Collectors.toList());
    }

    /**
     * Hitung jumlah item yang cocok kondisi.
     */
    public long hitung(Predicate<T> kondisi) {
        return data.stream()
                   .filter(kondisi)
                   .count();
    }

    /**
     * Cek apakah ada item yang memenuhi kondisi.
     */
    public boolean ada(Predicate<T> kondisi) {
        return data.stream().anyMatch(kondisi);
    }
}