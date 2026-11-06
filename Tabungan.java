/**
 * Subclass 3: Tabungan. Memindahkan uang dari saldo ke tabungan.
 * Mengimplementasikan Pengingat untuk target yang belum tercapai.
 */
public class Tabungan extends Transaksi implements Pengingat {
    private double target;
    private double terkumpul;

    public Tabungan(String deskripsi, double jumlah, String tanggal,
                    double target, double terkumpulSebelumnya) {
        super(deskripsi, jumlah, tanggal);
        this.target = target;
        this.terkumpul = terkumpulSebelumnya + getJumlah();
    }

    public double getTarget() { return target; }

    public double getTerkumpul() { return terkumpul; }

    public int getPersentase() {
        return (int) (terkumpul * 100 / target);
    }

    @Override
    public double hitungDampakSaldo() {
        return -getJumlah();
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Jenis     : Tabungan");
        super.tampilkanInfo();
        System.out.println("Target    : " + formatRupiah(target));
        System.out.println("Terkumpul : " + formatRupiah(terkumpul) + " (" + getPersentase() + "%)");
    }

    @Override
    public boolean perluDiingatkan() {
        return terkumpul < target;
    }

    @Override
    public String getPesanPengingat() {
        return getDeskripsi() + " baru " + getPersentase() + "% dari target " + formatRupiah(target);
    }
}
