package model;

/**
 * Kelas induk (abstract) untuk seluruh koleksi perpustakaan.
 */
public abstract class Koleksi {
    private final String kode;
    private final String judul;
    private final int tahunTerbit;
    private boolean sedangDipinjam;

    protected Koleksi(String kode, String judul, int tahunTerbit) {
        this.kode = kode;
        this.judul = judul;
        this.tahunTerbit = tahunTerbit;
    }

    /** Aturan kelayakan pinjam, wajib ditentukan oleh setiap turunan. */
    public abstract boolean layakDipinjam();

    /** Deskripsi dasar; diperluas oleh turunan memakai super.deskripsi(). */
    public String deskripsi() {
        return kode + " | " + judul + " (" + tahunTerbit + ")";
    }

    /** Jenis koleksi, diambil dari nama kelasnya (BukuCetak, Majalah, dst). */
    public String jenis() {
        return getClass().getSimpleName();
    }

    /** Lokasi fisik. Default: tidak ada. Di-override oleh koleksi yang punya rak. */
    public String infoLokasi() {
        return "Tidak ada lokasi fisik";
    }

    /** Alasan koleksi tidak layak dipinjam (dipakai untuk pesan eksepsi). */
    public String alasanTidakLayak() {
        return "tidak memenuhi syarat peminjaman";
    }

    public String getKode() { return kode; }
    public String getJudul() { return judul; }
    public int getTahunTerbit() { return tahunTerbit; }
    public boolean isSedangDipinjam() { return sedangDipinjam; }
    public void setSedangDipinjam(boolean sedangDipinjam) { this.sedangDipinjam = sedangDipinjam; }
}
