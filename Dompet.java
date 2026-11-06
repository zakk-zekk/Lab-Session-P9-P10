import java.util.ArrayList;

/**
 * Class pengelola: menyimpan seluruh transaksi dan menghitung saldo.
 */
public class Dompet {
    private String pemilik;
    private double saldoAwal;
    private ArrayList<Transaksi> riwayat = new ArrayList<>();

    public Dompet(String pemilik, double saldoAwal) {
        this.pemilik = pemilik;
        this.saldoAwal = saldoAwal;
    }

    public String getPemilik() { return pemilik; }

    public int getJumlahTransaksi() { return riwayat.size(); }

    // Saldo selalu dihitung ulang dari riwayat sehingga selalu konsisten
    public double getSaldo() {
        double saldo = saldoAwal;
        for (Transaksi t : riwayat) {
            saldo += t.hitungDampakSaldo();   // polymorphism: rumus mengikuti tipe objek
        }
        return saldo;
    }

    public void tambahTransaksi(Transaksi t) {
        riwayat.add(t);
        System.out.println("  + " + t.ringkasan());
    }

    public void cetakRiwayat() {
        for (Transaksi t : riwayat) {
            System.out.println("  " + t.ringkasan(true));
        }
    }
}
