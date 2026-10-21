package layanan;

import model.Koleksi;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EksporKatalog {
    public static final String NAMA_BERKAS = "katalog.txt";

    private static final String[] HEADER =
            {"Kode", "Jenis", "Judul", "Tahun", "Layak", "Status", "Lokasi"};
    private static final String JARAK = "  ";

    /** Menulis seluruh koleksi ke katalog.txt dengan kolom sejajar. */
    public void ekspor(List<Koleksi> daftar) throws IOException {
        List<String[]> baris = new ArrayList<>();
        baris.add(HEADER);
        for (Koleksi k : daftar) {
            baris.add(new String[]{
                    k.getKode(),
                    k.jenis(),
                    k.getJudul(),
                    String.valueOf(k.getTahunTerbit()),
                    k.layakDipinjam() ? "Ya" : "Tidak",
                    k.isSedangDipinjam() ? "Dipinjam" : "Tersedia",
                    k.infoLokasi()
            });
        }

        // Lebar tiap kolom = teks terpanjang di kolom itu
        int[] lebar = new int[HEADER.length];
        for (String[] b : baris) {
            for (int i = 0; i < b.length; i++) {
                lebar[i] = Math.max(lebar[i], b[i].length());
            }
        }

        // try-with-resources: aliran otomatis ditutup
        try (BufferedWriter tulis = Files.newBufferedWriter(
                Paths.get(NAMA_BERKAS), StandardCharsets.UTF_8)) {
            tulis.write(formatBaris(baris.get(0), lebar));
            tulis.newLine();
            tulis.write(garis(lebar));
            tulis.newLine();
            for (int i = 1; i < baris.size(); i++) {
                tulis.write(formatBaris(baris.get(i), lebar));
                tulis.newLine();
            }
        }
    }

    private String formatBaris(String[] kolom, int[] lebar) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < kolom.length; i++) {
            if (i < kolom.length - 1) {
                sb.append(String.format("%-" + lebar[i] + "s", kolom[i])).append(JARAK);
            } else {
                sb.append(kolom[i]);   // kolom terakhir tanpa spasi berlebih
            }
        }
        return sb.toString();
    }

    private String garis(int[] lebar) {
        int total = JARAK.length() * (lebar.length - 1);
        for (int w : lebar) {
            total += w;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < total; i++) {
            sb.append('-');
        }
        return sb.toString();
    }
}
