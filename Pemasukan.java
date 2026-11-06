/**
 * Subclass 1: Pemasukan (gaji, uang saku, hadiah, dll). Menambah saldo.
 */
public class Pemasukan extends Transaksi {
    private String sumber;

    public Pemasukan(String deskripsi, double jumlah, String tanggal, String sumber) {
        super(deskripsi, jumlah, tanggal);
        this.sumber = sumber;
    }

    public String getSumber() { return sumber; }

    @Override
    public double hitungDampakSaldo() {
        return getJumlah();
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis     : Pemasukan");
        super.tampilkanInfo();
        System.out.println("Sumber    : " + sumber);
    }
}
