package layanan;

import eksepsi.KoleksiSedangDipinjamException;
import eksepsi.KoleksiTidakLayakException;
import eksepsi.PustakaException;
import model.Koleksi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class KatalogPerpustakaan {
    // Satu daftar bertipe kelas induk
    private final List<Koleksi> daftar = new ArrayList<>();

    public void tambah(Koleksi koleksi) {
        daftar.add(Objects.requireNonNull(koleksi, "Koleksi tidak boleh kosong"));
    }

    public List<Koleksi> getDaftar() {
        return Collections.unmodifiableList(daftar);
    }

    /** Memproses seluruh koleksi secara polimorfik (tanpa percabangan tipe). */
    public void cetakStatus() {
        for (Koleksi koleksi : daftar) {
            System.out.println(koleksi.deskripsi());
            System.out.println("    Layak dipinjam : " + (koleksi.layakDipinjam() ? "Ya" : "Tidak"));
            System.out.println("    Status         : " + (koleksi.isSedangDipinjam() ? "Sedang dipinjam" : "Tersedia"));
            System.out.println("    Lokasi         : " + koleksi.infoLokasi());
        }
    }

    /** Aturan pengurutan ditentukan dari luar lewat Comparator. */
    public void urut(Comparator<? super Koleksi> pengurut) {
        daftar.sort(pengurut);
    }

    /** Meminjam koleksi berdasarkan kode. */
    public void pinjam(String kode) throws PustakaException {
        Koleksi koleksi = cariByKode(kode);
        if (koleksi == null) {
            throw new PustakaException("Maaf, koleksi dengan kode \"" + kode
                    + "\" tidak ditemukan di katalog. Periksa kembali kodenya.");
        }
        if (koleksi.isSedangDipinjam()) {
            throw new KoleksiSedangDipinjamException(koleksi);
        }
        if (!koleksi.layakDipinjam()) {
            throw new KoleksiTidakLayakException(koleksi);
        }
        koleksi.setSedangDipinjam(true);
    }

    private Koleksi cariByKode(String kode) {
        for (Koleksi koleksi : daftar) {
            if (koleksi.getKode().equalsIgnoreCase(kode)) {
                return koleksi;
            }
        }
        return null;
    }
}
