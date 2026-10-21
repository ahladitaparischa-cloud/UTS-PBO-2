import eksepsi.PustakaException;
import layanan.EksporKatalog;
import layanan.KatalogPerpustakaan;
import layanan.UrutLayakLaluJudul;
import model.BerkasDigital;
import model.BukuCetak;
import model.Majalah;

import java.io.IOException;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        KatalogPerpustakaan katalog = new KatalogPerpustakaan();

        // Data uji wajib (nomor rak Majalah & lantai adalah asumsi karena tidak ada di soal)
        katalog.tambah(new BukuCetak("BK-001", "Java How to Program", 2022, 620, "A-12", 1, false));
        katalog.tambah(new BukuCetak("BK-002", "Basis Data Relasional", 2021, 410, "B-04", 2, true));
        katalog.tambah(new Majalah("MJ-001", "Info Teknologi Edisi 88", 2026, 88, "Agustus", "M-01", 1, true));
        katalog.tambah(new Majalah("MJ-002", "Info Teknologi Edisi 87", 2026, 87, "Juli", "M-02", 1, false));
        katalog.tambah(new BerkasDigital("BD-001", "Panduan Pemrograman Java", 2024, 15, "unduh.polmed.ac.id/bd001"));
        katalog.tambah(new BerkasDigital("BD-002", "Jurnal Rekayasa Perangkat", 2025, 8, "unduh.polmed.ac.id/bd002"));

        garis("1. STATUS SELURUH KOLEKSI (urutan awal)");
        katalog.cetakStatus();

        garis("2. URUT: LAYAK DULU, LALU JUDUL");
        katalog.urut(new UrutLayakLaluJudul());
        katalog.cetakStatus();

        garis("3. URUT: TAHUN TERBIT (comparator lain dari luar)");
        katalog.urut(Comparator.comparingInt(k -> k.getTahunTerbit()));
        katalog.cetakStatus();

        garis("4. PEMINJAMAN");
        coba(katalog, "BK-001");   // berhasil
        coba(katalog, "BK-001");   // sedang dipinjam
        coba(katalog, "BK-002");   // sedang diperbaiki
        coba(katalog, "MJ-001");   // edisi terbaru
        coba(katalog, "BD-001");   // berhasil (digital)
        coba(katalog, "XX-999");   // tidak ditemukan

        garis("5. EKSPOR KE " + EksporKatalog.NAMA_BERKAS);
        try {
            new EksporKatalog().ekspor(katalog.getDaftar());
            System.out.println("Berhasil menulis " + EksporKatalog.NAMA_BERKAS);
        } catch (IOException e) {
            System.out.println("Gagal menulis berkas: " + e.getMessage());
        }
    }

    private static void coba(KatalogPerpustakaan katalog, String kode) {
        try {
            katalog.pinjam(kode);
            System.out.println("[BERHASIL] " + kode + " berhasil dipinjam.");
        } catch (PustakaException e) {
            System.out.println("[GAGAL]    " + e.getMessage());
        }
    }

    private static void garis(String judul) {
        System.out.println();
        System.out.println("=== " + judul + " ===");
    }
}
