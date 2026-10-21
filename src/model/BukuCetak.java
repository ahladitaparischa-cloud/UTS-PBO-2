package model;

public class BukuCetak extends Koleksi implements Terarsip {
    private final int jumlahHalaman;
    private final String nomorRak;
    private final int lokasiLantai;
    private final boolean sedangDiperbaiki;

    public BukuCetak(String kode, String judul, int tahunTerbit,
                     int jumlahHalaman, String nomorRak, int lokasiLantai,
                     boolean sedangDiperbaiki) {
        super(kode, judul, tahunTerbit);
        this.jumlahHalaman = jumlahHalaman;
        this.nomorRak = nomorRak;
        this.lokasiLantai = lokasiLantai;
        this.sedangDiperbaiki = sedangDiperbaiki;
    }

    @Override
    public boolean layakDipinjam() {
        return !sedangDiperbaiki;
    }

    @Override
    public String deskripsi() {
        return "Buku Cetak | " + super.deskripsi() + " | " + jumlahHalaman + " halaman";
    }

    @Override
    public String nomorRak() {
        return nomorRak;
    }

    @Override
    public int lokasiLantai() {
        return lokasiLantai;
    }

    @Override
    public String infoLokasi() {
        return ringkasanArsip();
    }

    @Override
    public String alasanTidakLayak() {
        return "sedang diperbaiki";
    }
}
