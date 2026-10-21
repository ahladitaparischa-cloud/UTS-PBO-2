package model;

public class Majalah extends Koleksi implements Terarsip {
    private final int edisi;
    private final String bulanTerbit;
    private final String nomorRak;
    private final int lokasiLantai;
    private final boolean edisiTerbaru;

    public Majalah(String kode, String judul, int tahunTerbit,
                   int edisi, String bulanTerbit, String nomorRak, int lokasiLantai,
                   boolean edisiTerbaru) {
        super(kode, judul, tahunTerbit);
        this.edisi = edisi;
        this.bulanTerbit = bulanTerbit;
        this.nomorRak = nomorRak;
        this.lokasiLantai = lokasiLantai;
        this.edisiTerbaru = edisiTerbaru;
    }

    @Override
    public boolean layakDipinjam() {
        return !edisiTerbaru;   // edisi terbaru TIDAK layak dipinjam
    }

    @Override
    public String deskripsi() {
        return "Majalah | " + super.deskripsi() + " | edisi " + edisi + " | " + bulanTerbit;
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
        return "merupakan edisi terbaru";
    }
}
