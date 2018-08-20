-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Ago 20, 2018 alle 18:12
-- Versione del server: 5.7.22
-- Versione PHP: 7.1.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `androidapp`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `tournaments`
--

CREATE TABLE `tournaments` (
  `id_tournament` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `place` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `tournaments`
--

INSERT INTO `tournaments` (`id_tournament`, `name`, `place`) VALUES
(1, 'Don Salvi', 'Ovada'),
(2, 'Casaleggio', 'Casaleggio Boiro'),
(3, 'Silvano', 'Silvano d\'Orba');

-- --------------------------------------------------------

--
-- Struttura della tabella `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `surname` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(256) NOT NULL,
  `email` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `users`
--

INSERT INTO `users` (`id_user`, `name`, `surname`, `username`, `password`, `email`) VALUES
(2, 'filippo', 'cenonfolo', 'cenno', '$2y$10$lMHoi1XXll4vykDmp8LkMe6WArH62ufEqXNtaHihXNDaaLQgPHnSK', 'cenno@cenno.it'),
(9, 'riky', 'cenno', 'riky', '$2y$10$TsgD./MbQdOjek0vzoUyXeXXLUECjVBio4uFYmWr.fNCJdWfG0s4i', 'riky@libero.it'),
(10, 'riky', 'cenno', 'riky98', '$2y$10$SYl6jKXrgAmvJaeiWCgBLurvtygU6SsfvVXdhhTQ8GZ1H72rs.D8q', 'riky@libero.it'),
(11, 'riky', 'cenno', 'riky98', '$2y$10$4IUnyaOXJOomzsK8CbouMe/WeQ3peLXst1f3Yy0OeDpMb5u9mtPS.', 'riky@libero.it'),
(12, 'Simone', 'Gaggero', 'simogagge', '$2y$10$yfy.78tN2fF6gxAa7VCDNeO.Zt12B/5yi31mcysS8AZYmRelZRq9a', 'simogagge@gmail.com');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `tournaments`
--
ALTER TABLE `tournaments`
  ADD PRIMARY KEY (`id_tournament`);

--
-- Indici per le tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `tournaments`
--
ALTER TABLE `tournaments`
  MODIFY `id_tournament` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
