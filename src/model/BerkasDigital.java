package model;

public class BerkasDigital extends Koleksi {
    private final int ukuranMB;
    private final String tautanUnduh;

    public BerkasDigital(String kode, String judul, int tahunTerbit,
                         int ukuranMB, String tautanUnduh) {
        super(kode, judul, tahunTerbit);
        this.ukuranMB = ukuranMB;
        this.tautanUnduh = tautanUnduh;
    }

    @Override
    public boolean layakDipinjam() {
        return true;   // selalu layak
    }

    @Override
    public String deskripsi() {
        return "Berkas Digital | " + super.deskripsi()
                + " | " + ukuranMB + " MB | " + tautanUnduh;
    }
}
