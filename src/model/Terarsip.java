package model;

/**
 * Kemampuan untuk koleksi yang memiliki lokasi fisik di rak.
 * Hanya diterapkan pada BukuCetak dan Majalah (BerkasDigital tidak).
 */
public interface Terarsip {
    String nomorRak();

    int lokasiLantai();

    default String ringkasanArsip() {
        return "Rak " + nomorRak() + ", Lantai " + lokasiLantai();
    }
}
