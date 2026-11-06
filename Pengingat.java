/**
 * Interface: kontrak untuk transaksi yang perlu diingatkan ke pengguna
 * (tagihan belum dibayar, target tabungan belum tercapai).
 */
public interface Pengingat {
    boolean perluDiingatkan();

    String getPesanPengingat();
}
