package eksepsi;

import model.Koleksi;

public class KoleksiTidakLayakException extends PustakaException {
    public KoleksiTidakLayakException(Koleksi koleksi) {
        super("Maaf, \"" + koleksi.getJudul() + "\" (" + koleksi.getKode()
                + ") belum bisa dipinjam karena " + koleksi.alasanTidakLayak() + ". "
                + "Silakan pilih koleksi lain.");
    }
}
