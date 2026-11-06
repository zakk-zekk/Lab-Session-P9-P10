public class Main {
    public static void main(String[] args) {
        Dompet dompet = new Dompet("Febrian", 0);

        // 4 objek dari class turunan, disimpan dalam referensi superclass (runtime polymorphism)
        Transaksi[] daftar = {
            new Pemasukan("Gaji Freelance", 3000000, "01-11-2026", "Freelance"),
            new Pengeluaran("Makan Siang", 25000, "02-11-2026", "Makanan", true),
            new Pengeluaran("Tagihan Listrik", 350000, "05-11-2026", "Tagihan", false),
            new Tabungan("Dana Darurat", 500000, "03-11-2026", 5000000, 1500000)
        };

        System.out.println("===== PENCATAT KEUANGAN HARIAN =====\n");

        System.out.println("--- 1. Detail Transaksi (Polymorphism) ---");
        for (Transaksi t : daftar) {
            t.tampilkanInfo();
            System.out.println();
        }

        System.out.println("--- 2. Encapsulation: ubah data lewat setter ---");
        Transaksi makan = daftar[1];
        System.out.println("Jumlah awal  : " + Transaksi.formatRupiah(makan.getJumlah()));
        makan.setJumlah(30000);
        System.out.println("Jumlah baru  : " + Transaksi.formatRupiah(makan.getJumlah()));
        makan.setJumlah(-100); // ditolak oleh validasi setter
        System.out.println("Jumlah akhir : " + Transaksi.formatRupiah(makan.getJumlah()));
        System.out.println();

        System.out.println("--- 3. Catat Transaksi (Overriding hitungDampakSaldo) ---");
        for (Transaksi t : daftar) {
            dompet.tambahTransaksi(t);
        }
        System.out.println("Saldo saat ini : " + Transaksi.formatRupiah(dompet.getSaldo()));
        System.out.println();

        System.out.println("--- 4. Riwayat (Overloading ringkasan) ---");
        System.out.println("Tanpa tanggal : " + daftar[0].ringkasan());
        System.out.println("Dengan tanggal: " + daftar[0].ringkasan(true));
        dompet.cetakRiwayat();
        System.out.println();

        System.out.println("--- 5. Interface Pengingat ---");
        Pengingat[] pengingat = {(Pengingat) daftar[2], (Pengingat) daftar[3]};
        tampilkanPengingat(pengingat);
        System.out.println();

        System.out.println("--- 6. Bayar Tagihan ---");
        System.out.println("Saldo sebelum bayar : " + Transaksi.formatRupiah(dompet.getSaldo()));
        ((Pengeluaran) daftar[2]).bayar();
        System.out.println("Saldo setelah bayar : " + Transaksi.formatRupiah(dompet.getSaldo()));
        System.out.println("Pengingat tersisa:");
        tampilkanPengingat(pengingat);
        System.out.println();

        System.out.println("--- Ringkasan Akhir ---");
        System.out.println("Pemilik          : " + dompet.getPemilik());
        System.out.println("Jumlah transaksi : " + dompet.getJumlahTransaksi());
        System.out.println("Saldo akhir      : " + Transaksi.formatRupiah(dompet.getSaldo()));
    }

    private static void tampilkanPengingat(Pengingat[] daftar) {
        for (Pengingat p : daftar) {
            if (p.perluDiingatkan()) {
                System.out.println("  ! " + p.getPesanPengingat());
            }
        }
    }
}
