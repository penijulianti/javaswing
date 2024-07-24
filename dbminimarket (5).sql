-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 03 Jul 2024 pada 10.54
-- Versi server: 10.1.32-MariaDB
-- Versi PHP: 5.6.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbminimarket`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kd_barang` varchar(8) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `satuan` varchar(20) NOT NULL,
  `harga` int(20) NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kd_barang`, `nama_barang`, `satuan`, `harga`, `stok`) VALUES
('BRG00001', 'Laptop', 'Pcs', 3000000, 2),
('BRG00002', 'Handphone', 'Pcs', 1500000, 4),
('BRG00003', 'Mouse', 'Pcs', 70000, 5),
('BRG00004', 'Headset', 'Pcs', 500000, 4),
('BRG00005', 'SSD', 'Pcs', 1300000, 4),
('BRG00006', 'Sabun', 'Pcs', 15000, 6),
('BRG00007', 'Bantal', 'Pcs', 200000, 10);

-- --------------------------------------------------------

--
-- Struktur dari tabel `penjualan`
--

CREATE TABLE `penjualan` (
  `id_transaksi` int(20) NOT NULL,
  `no_faktur` varchar(8) NOT NULL,
  `tanggal` date NOT NULL,
  `kd_barang` varchar(8) NOT NULL,
  `id_user` varchar(8) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `sub_total` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `penjualan`
--

INSERT INTO `penjualan` (`id_transaksi`, `no_faktur`, `tanggal`, `kd_barang`, `id_user`, `jumlah`, `sub_total`) VALUES
(38, '', '2024-06-28', 'BRG00007', 'ID0001', 1, 200000),
(39, 'NFS00001', '2024-06-28', 'BRG00004', 'ID0001', 1, 500000);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `relasidatatransaksi`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `relasidatatransaksi` (
`no_faktur` varchar(8)
,`tanggal` date
,`id_user` varchar(8)
,`nama` varchar(25)
,`status` varchar(15)
,`username` varchar(20)
,`akses` varchar(20)
,`kd_barang` varchar(8)
,`nama_barang` varchar(50)
,`satuan` varchar(20)
,`harga` int(20)
,`stok` int(11)
,`jumlah` int(11)
,`sub_total` int(20)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `relasikeranjang`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `relasikeranjang` (
`no_faktur` varchar(8)
,`tanggal` date
,`id_user` varchar(8)
,`username` varchar(20)
,`akses` varchar(20)
,`kd_barang` varchar(8)
,`nama_barang` varchar(50)
,`harga` int(20)
,`jumlah` int(11)
,`sub_total` int(20)
);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` varchar(8) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `telepon` varchar(15) NOT NULL,
  `status` varchar(15) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `akses` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `nama`, `alamat`, `telepon`, `status`, `username`, `password`, `akses`) VALUES
('ID0001', 'Hudha', 'Jln Bengawan No.12', '08992801932', 'Aktif', 'Hudha112', 'Hudha123', 'Kasir'),
('ID0002', 'Ucok', 'Jln Ambon No.420', '0877180293', 'Aktif', 'Pacuok233', 'cuok9920', 'Kasir1'),
('ID0003', 'Kojek', 'Jln sumbawa no.09', '08992817283', 'Aktif', 'Jekjok1223', 'jekicou11', 'Kasir2'),
('ID0004', 'Handi', 'Jln Riau No.69', '083124120923', 'Aktif', 'handiopel22', 'hand2', 'Admin1'),
('ID0005', 'Sableng', 'Jln Maritim No.82', '08317928827', 'Aktif', 'Wiroo212', 'wirosab23', 'Kasir2'),
('ID0006', 'Adti', 'Jln Veteran No.009', '08783999291', 'Aktif', 'Adit roges', 'Adit223', 'Admin1');

-- --------------------------------------------------------

--
-- Struktur untuk view `relasidatatransaksi`
--
DROP TABLE IF EXISTS `relasidatatransaksi`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `relasidatatransaksi`  AS  select `penjualan`.`no_faktur` AS `no_faktur`,`penjualan`.`tanggal` AS `tanggal`,`user`.`id_user` AS `id_user`,`user`.`nama` AS `nama`,`user`.`status` AS `status`,`user`.`username` AS `username`,`user`.`akses` AS `akses`,`barang`.`kd_barang` AS `kd_barang`,`barang`.`nama_barang` AS `nama_barang`,`barang`.`satuan` AS `satuan`,`barang`.`harga` AS `harga`,`barang`.`stok` AS `stok`,`penjualan`.`jumlah` AS `jumlah`,`penjualan`.`sub_total` AS `sub_total` from ((`penjualan` join `user`) join `barang`) where ((`penjualan`.`id_user` = `user`.`id_user`) and (`penjualan`.`kd_barang` = `barang`.`kd_barang`)) group by `penjualan`.`no_faktur`,`penjualan`.`tanggal`,`penjualan`.`id_user`,`penjualan`.`kd_barang` ;

-- --------------------------------------------------------

--
-- Struktur untuk view `relasikeranjang`
--
DROP TABLE IF EXISTS `relasikeranjang`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `relasikeranjang`  AS  select `penjualan`.`no_faktur` AS `no_faktur`,`penjualan`.`tanggal` AS `tanggal`,`user`.`id_user` AS `id_user`,`user`.`username` AS `username`,`user`.`akses` AS `akses`,`barang`.`kd_barang` AS `kd_barang`,`barang`.`nama_barang` AS `nama_barang`,`barang`.`harga` AS `harga`,`penjualan`.`jumlah` AS `jumlah`,`penjualan`.`sub_total` AS `sub_total` from ((`penjualan` join `user`) join `barang`) where ((`penjualan`.`id_user` = `user`.`id_user`) and (`penjualan`.`kd_barang` = `barang`.`kd_barang`)) group by `penjualan`.`no_faktur`,`penjualan`.`tanggal`,`penjualan`.`id_user`,`penjualan`.`kd_barang` ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kd_barang`);

--
-- Indeks untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `kd_barang` (`kd_barang`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `id_transaksi` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`kd_barang`) REFERENCES `barang` (`kd_barang`),
  ADD CONSTRAINT `penjualan_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
