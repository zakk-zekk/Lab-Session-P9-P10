# Pencatat Keuangan Harian (Java OOP)

Praktik Lab Session 3B — Pengembangan Program Berbasis Object-Oriented Programming (OOP)

| | |
|---|---|
| **Nama** | Febrian Zaki Hidayatulloh |
| **NIM** | L0325045 |
| **Program Studi** | Informatika PSDKU Kebumen, Kelas B |
| **Universitas** | Universitas Sebelas Maret |

## Deskripsi

Program konsol untuk **mencatat keuangan pribadi sehari-hari**: pemasukan, pengeluaran, dan tabungan. Program menghitung saldo secara otomatis, menampilkan riwayat transaksi, serta **mengingatkan** tagihan yang belum dibayar dan target tabungan yang belum tercapai. Cocok untuk mahasiswa atau siapa saja yang ingin uangnya lebih terkontrol.

## Struktur Project

```
pencatat-keuangan-oop/
├── src/
│   ├── Main.java         # Class utama, demo seluruh konsep OOP
│   ├── Transaksi.java    # Abstract class (superclass)
│   ├── Pemasukan.java    # Subclass
│   ├── Pengeluaran.java  # Subclass, implements Pengingat
│   ├── Tabungan.java     # Subclass, implements Pengingat
│   ├── Pengingat.java    # Interface
│   └── Dompet.java       # Pengelola transaksi dan saldo
├── .gitignore
└── README.md
```

## Diagram Hubungan Class

```
              Transaksi (abstract)
             /        |         \      (extends)
       Pemasukan  Pengeluaran  Tabungan
                       \          /
                   <<interface>> Pengingat
           (implements: Pengeluaran & Tabungan)

        Dompet ──── menyimpan banyak ────> Transaksi
```

## Penerapan Konsep OOP

### 1. Encapsulation
- Atribut `deskripsi`, `jumlah`, dan `tanggal` pada `Transaksi` bersifat **private**.
- Diakses lewat **getter** (`getJumlah()`, dst.) dan **setter** (`setJumlah()`, `setDeskripsi()`).
- `setJumlah()` menolak nilai ≤ 0.
- Status lunas pada `Pengeluaran` hanya bisa berubah lewat method `bayar()`.
- Saldo pada `Dompet` tidak disimpan langsung, tetapi dihitung dari riwayat lewat `getSaldo()`.

### 2. Inheritance
- `Pemasukan`, `Pengeluaran`, dan `Tabungan` menggunakan `extends Transaksi`.
- Setiap constructor subclass memanggil `super(deskripsi, jumlah, tanggal)`.

### 3. Polymorphism
- **Overriding**: `hitungDampakSaldo()` dan `tampilkanInfo()` di-override dengan `@Override` di setiap subclass.
- **Overloading**: `ringkasan()` dan `ringkasan(boolean denganTanggal)`.
- **Runtime polymorphism**: objek `Pemasukan`, `Pengeluaran`, dan `Tabungan` disimpan dalam array `Transaksi[]`, lalu method dipanggil sesuai tipe objek sebenarnya. `Dompet.getSaldo()` juga memanfaatkan hal ini.

### 4. Abstract Class
- `Transaksi` adalah `abstract class` dengan abstract method `hitungDampakSaldo()`.
- Diimplementasikan oleh tiap subclass:

| Subclass | Dampak ke saldo |
|---|---|
| Pemasukan | `+jumlah` |
| Pengeluaran | `-jumlah` jika sudah lunas, `0` jika belum |
| Tabungan | `-jumlah` (dipindahkan ke tabungan) |

### 5. Interface
- `Pengingat` mendeklarasikan `perluDiingatkan()` dan `getPesanPengingat()`.
- `Pengeluaran` (tagihan belum lunas) dan `Tabungan` (target belum tercapai) menggunakan `implements Pengingat` dan memenuhi kedua method tersebut.
- `Pemasukan` tidak membutuhkan pengingat sehingga tidak mengimplementasikannya.

## Cara Menjalankan

Pastikan JDK 11 atau lebih baru sudah terpasang.

```bash
git clone <url-repository-kamu>
cd pencatat-keuangan-oop
javac -d out src/*.java
java -cp out Main
```

## Contoh Output

```
===== PENCATAT KEUANGAN HARIAN =====

--- 1. Detail Transaksi (Polymorphism) ---
Jenis     : Pemasukan
Deskripsi : Gaji Freelance
Jumlah    : Rp3.000.000
Tanggal   : 01-11-2026
Sumber    : Freelance

Jenis     : Pengeluaran
Deskripsi : Makan Siang
Jumlah    : Rp25.000
Tanggal   : 02-11-2026
Kategori  : Makanan
Status    : Lunas

Jenis     : Pengeluaran
Deskripsi : Tagihan Listrik
Jumlah    : Rp350.000
Tanggal   : 05-11-2026
Kategori  : Tagihan
Status    : Belum Lunas

Jenis     : Tabungan
Deskripsi : Dana Darurat
Jumlah    : Rp500.000
Tanggal   : 03-11-2026
Target    : Rp5.000.000
Terkumpul : Rp2.000.000 (40%)

--- 2. Encapsulation: ubah data lewat setter ---
Jumlah awal  : Rp25.000
Jumlah baru  : Rp30.000
  [!] Jumlah tidak valid, perubahan ditolak.
Jumlah akhir : Rp30.000

--- 3. Catat Transaksi (Overriding hitungDampakSaldo) ---
  + Gaji Freelance : +Rp3.000.000
  + Makan Siang : -Rp30.000
  + Tagihan Listrik : Rp0
  + Dana Darurat : -Rp500.000
Saldo saat ini : Rp2.470.000

--- 4. Riwayat (Overloading ringkasan) ---
Tanpa tanggal : Gaji Freelance : +Rp3.000.000
Dengan tanggal: [01-11-2026] Gaji Freelance : +Rp3.000.000
  [01-11-2026] Gaji Freelance : +Rp3.000.000
  [02-11-2026] Makan Siang : -Rp30.000
  [05-11-2026] Tagihan Listrik : Rp0
  [03-11-2026] Dana Darurat : -Rp500.000

--- 5. Interface Pengingat ---
  ! Tagihan belum dibayar: Tagihan Listrik (Rp350.000)
  ! Dana Darurat baru 40% dari target Rp5.000.000

--- 6. Bayar Tagihan ---
Saldo sebelum bayar : Rp2.470.000
  [v] Tagihan Listrik telah dibayar.
Saldo setelah bayar : Rp2.120.000
Pengingat tersisa:
  ! Dana Darurat baru 40% dari target Rp5.000.000

--- Ringkasan Akhir ---
Pemilik          : Febrian
Jumlah transaksi : 4
Saldo akhir      : Rp2.120.000
```

## Ringkasan Pemenuhan Ketentuan

- [x] Minimal 4 class termasuk `Main` (7 class/interface)
- [x] Minimal 4 objek (Gaji, Makan Siang, Tagihan Listrik, Dana Darurat) ditambah 1 objek `Dompet`
- [x] 1 abstract class, 3 subclass, 1 interface
- [x] Encapsulation, Inheritance, Polymorphism, Abstract Class, Interface
