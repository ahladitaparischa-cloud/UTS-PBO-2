package eksepsi;

import model.Koleksi;

public class KoleksiSedangDipinjamException extends PustakaException {
    public KoleksiSedangDipinjamException(Koleksi koleksi) {
        super("Maaf, \"" + koleksi.getJudul() + "\" (" + koleksi.getKode()
                + ") sedang dipinjam orang lain. "
                + "Silakan coba lagi nanti atau pilih koleksi lain.");
    }
}
