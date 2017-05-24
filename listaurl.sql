-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 24 Maj 2017, 18:47
-- Wersja serwera: 10.1.19-MariaDB
-- Wersja PHP: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `listaurl`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `linki`
--

CREATE TABLE `linki` (
  `urlId` int(11) NOT NULL,
  `url` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `nazwa` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `folder` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `linki`
--

INSERT INTO `linki` (`urlId`, `url`, `nazwa`, `folder`) VALUES
(1, 'https://www.youtube.com/watch?v=99JAI24Zd24', 'Kurs MySql', '/home/youtube/kursMySql'),
(2, 'https://www.facebook.com/', 'facebook', '/home/facebook'),
(10, 'http://docs.oracle.com/javase/7/docs/technotes/guides/language/try-with-resources.html', 'try with statement', '/home/oracle/docs'),
(14, 'https://help.github.com/articles/inviting-collaborators-to-a-personal-repository/', 'Git', '/home/git');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `linki`
--
ALTER TABLE `linki`
  ADD PRIMARY KEY (`urlId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `linki`
--
ALTER TABLE `linki`
  MODIFY `urlId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
