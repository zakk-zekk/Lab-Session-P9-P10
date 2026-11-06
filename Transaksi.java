import java.util.Locale;

/**
 * Abstract class (superclass) untuk semua transaksi keuangan.
 * Menerapkan Encapsulation: atribut private + getter/setter.
 */
public abstract class Transaksi {
    private String deskripsi;
    private double jumlah;
    private String tanggal;

    public Transaksi(String deskripsi, double jumlah, String tanggal) {
        this.deskripsi = deskripsi;
        this.jumlah = jumlah > 0 ? jumlah : 0;
        this.tanggal = tanggal;
    }

    // ===== Getter =====
    public String getDeskripsi() { return deskripsi; }
    public double getJumlah() { return jumlah; }
    public String getTanggal() { return tanggal; }

    // ===== Setter (dengan validasi) =====
    public void setDeskripsi(String deskripsi) {
        if (deskripsi != null && !deskripsi.isBlank()) {
            this.deskripsi = deskripsi;
        }
    }

    public void setJumlah(double jumlah) {
        if (jumlah > 0) {
            this.jumlah = jumlah;
        } else {
            System.out.println("  [!] Jumlah tidak valid, perubahan ditolak.");
        }
    }

    // ===== Abstract method =====
    // Positif = menambah saldo, negatif = mengurangi saldo
    public abstract double hitungDampakSaldo();

    // ===== Overloading: nama sama, parameter berbeda =====
    public String ringkasan() {
        double dampak = hitungDampakSaldo();
        String tanda = dampak > 0 ? "+" : (dampak < 0 ? "-" : "");
        return deskripsi + " : " + tanda + formatRupiah(Math.abs(dampak));
    }

    public String ringkasan(boolean denganTanggal) {
        return denganTanggal ? "[" + tanggal + "] " + ringkasan() : ringkasan();
    }

    public void tampilkanInfo() {
        System.out.println("Deskripsi : " + deskripsi);
        System.out.println("Jumlah    : " + formatRupiah(jumlah));
        System.out.println("Tanggal   : " + tanggal);
    }

    public static String formatRupiah(double nilai) {
        return "Rp" + String.format(Locale.US, "%,.0f", nilai).replace(',', '.');
    }
}
