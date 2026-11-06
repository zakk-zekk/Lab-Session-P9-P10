/**
 * Interface: kontrak untuk transaksi yang perlu diingatkan ke pengguna
 * (pengeluaran belum dibayar, target tabungan belum tercapai).
 */
public interface Pengingat {
    boolean perluDiingatkan();

    String getPesanPengingat();
}