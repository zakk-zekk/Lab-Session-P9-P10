/**
 * Subclass 2: Pengeluaran (makan, transport, tagihan). Mengurangi saldo setelah lunas.
 * Mengimplementasikan Pengingat untuk tagihan yang belum dibayar.
 */
public class Pengeluaran extends Transaksi implements Pengingat {
    private String kategori;
    private boolean lunas;

    public Pengeluaran(String deskripsi, double jumlah, String tanggal,
                       String kategori, boolean lunas) {
        super(deskripsi, jumlah, tanggal);
        this.kategori = kategori;
        this.lunas = lunas;
    }

    public String getKategori() { return kategori; }

    public boolean isLunas() { return lunas; }

    // Status lunas hanya berubah lewat method ini
    public void bayar() {
        if (!lunas) {
            lunas = true;
            System.out.println("  [v] " + getDeskripsi() + " telah dibayar.");
        }
    }

    @Override
    public double hitungDampakSaldo() {
        return lunas ? -getJumlah() : 0;   // belum lunas belum memotong saldo
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis     : Pengeluaran");
        super.tampilkanInfo();
        System.out.println("Kategori  : " + kategori);
        System.out.println("Status    : " + (lunas ? "Lunas" : "Belum Lunas"));
    }

    @Override
    public boolean perluDiingatkan() {
        return !lunas;
    }

    @Override
    public String getPesanPengingat() {
        return "Tagihan belum dibayar: " + getDeskripsi() + " (" + formatRupiah(getJumlah()) + ")";
    }
}
