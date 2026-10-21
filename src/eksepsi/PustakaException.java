package eksepsi;

/** Induk semua kesalahan pada layanan perpustakaan. */
public class PustakaException extends Exception {
    public PustakaException(String pesan) {
        super(pesan);
    }
}
