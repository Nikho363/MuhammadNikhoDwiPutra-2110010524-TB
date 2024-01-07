-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 07 Jan 2024 pada 12.40
-- Versi server: 10.4.27-MariaDB
-- Versi PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbtiketpesawat`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `penerbangan`
--

CREATE TABLE `penerbangan` (
  `id_penerbangan` varchar(6) NOT NULL,
  `maskapai` varchar(30) NOT NULL,
  `rute` varchar(50) NOT NULL,
  `jam_keberangkatan` varchar(10) NOT NULL,
  `jam_kedatangan` varchar(10) NOT NULL,
  `kelas` varchar(10) NOT NULL,
  `harga_tiket` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `penerbangan`
--

INSERT INTO `penerbangan` (`id_penerbangan`, `maskapai`, `rute`, `jam_keberangkatan`, `jam_kedatangan`, `kelas`, `harga_tiket`) VALUES
('PN0001', 'Garuda Indonesia', 'Banjarmasin, Jakarta', '08.00 AM', '09.50 AM', 'Ekonomi', 1000000),
('PN0002', 'Garuda Indonesia', 'Banjarmasin, Jakarta', '08.00 AM', '09.50 AM', 'Bisnis', 5000000),
('PN0003', 'Lion Air', 'Banjarmasin, Bali', '10.00 AM', '05.00 PM', 'Ekonomi', 2500000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `penumpang`
--

CREATE TABLE `penumpang` (
  `nomor_ktp` varchar(16) NOT NULL,
  `nama_penumpang` varchar(30) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `nomor_telepon` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `penumpang`
--

INSERT INTO `penumpang` (`nomor_ktp`, `nama_penumpang`, `jenis_kelamin`, `tanggal_lahir`, `alamat`, `nomor_telepon`) VALUES
('0220', 'Sarah', 'Perempuan', '2003-07-12', 'Jln. S. Parman 1 No. 020', '0870492821223'),
('0550', 'Budi', 'Laki-Laki', '2003-05-07', 'Jln. Manggis No. 008', '089570701232');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(6) NOT NULL,
  `nomor_ktp` varchar(16) NOT NULL,
  `nama_penumpang` varchar(6) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `maskapai` varchar(20) NOT NULL,
  `rute` varchar(30) NOT NULL,
  `kelas` varchar(10) NOT NULL,
  `tanggal_berangkat` date NOT NULL,
  `jam_keberangkatan` varchar(10) NOT NULL,
  `jam_kedatangan` varchar(10) NOT NULL,
  `harga_satuan` int(11) NOT NULL,
  `jumlah_tiket` int(3) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `nomor_ktp`, `nama_penumpang`, `tanggal_transaksi`, `maskapai`, `rute`, `kelas`, `tanggal_berangkat`, `jam_keberangkatan`, `jam_kedatangan`, `harga_satuan`, `jumlah_tiket`, `total`) VALUES
('TR0001', '0550', 'Budi', '2024-01-07', 'Garuda Indonesia', 'Banjarmasin, Jakarta', 'Bisnis', '2024-01-10', '08.00 AM', '09.50 AM', 5000000, 1, 5000000),
('TR0002', '0220', 'Sarah', '2024-01-07', 'Lion Air', 'Banjarmasin, Bali', 'Ekonomi', '2024-01-15', '10.00 AM', '05.00 PM', 2500000, 1, 2500000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `penerbangan`
--
ALTER TABLE `penerbangan`
  ADD PRIMARY KEY (`id_penerbangan`);

--
-- Indeks untuk tabel `penumpang`
--
ALTER TABLE `penumpang`
  ADD PRIMARY KEY (`nomor_ktp`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
