-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Ago 27, 2018 alle 14:35
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
-- Struttura della tabella `Finali`
--

CREATE TABLE `Finali` (
  `id_torneo` int(11) NOT NULL,
  `id_partita` int(11) NOT NULL,
  `nome_squadra_1` varchar(32) DEFAULT NULL,
  `nome_squadra_2` varchar(32) DEFAULT NULL,
  `gol_squadra_1` int(11) DEFAULT NULL,
  `gol_squadra_2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `Gironi`
--

CREATE TABLE `Gironi` (
  `id_torneo` int(11) NOT NULL,
  `id_girone` int(11) NOT NULL,
  `nome_squadra` varchar(32) NOT NULL,
  `punteggio_squadra` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `Ottavi`
--

CREATE TABLE `Ottavi` (
  `id_torneo` int(11) NOT NULL,
  `id_partita` int(11) NOT NULL,
  `nome_squadra_1` varchar(32) DEFAULT NULL,
  `nome_squadra_2` varchar(32) DEFAULT NULL,
  `gol_squadra_1` int(11) DEFAULT NULL,
  `gol_squadra_2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Trigger `Ottavi`
--
DELIMITER $$
CREATE TRIGGER `ottavi_quarti` AFTER UPDATE ON `Ottavi` FOR EACH ROW BEGIN	
	IF (NEW.gol_squadra_1 != -1 AND NEW.gol_squadra_2 != -1)
    THEN
	IF(NEW.id_partita = 0)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN INSERT INTO Quarti VALUES (NEW.id_torneo, 0, 							NEW.nome_squadra_1, null, -1, -1);
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN INSERT INTO Quarti VALUES (NEW.id_torneo, 0, 							NEW.nome_squadra_2, null, -1, -1);
    		END IF;
    END IF;
    
	IF(NEW.id_partita = 1)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN UPDATE Quarti SET Quarti.nome_squadra_2=NEW.nome_squadra_1 WHERE id_partita = 0;
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN UPDATE Quarti SET Quarti.nome_squadra_2=NEW.nome_squadra_2 WHERE id_partita = 0;
    		END IF;
    END IF;
    
	IF(NEW.id_partita = 2)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN INSERT INTO Quarti VALUES (NEW.id_torneo, 1, 							NEW.nome_squadra_1, null, -1, -1);
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN INSERT INTO Quarti VALUES (NEW.id_torneo, 1, 							NEW.nome_squadra_2, null , -1, -1);
    		END IF;
    END IF;
    
	IF(NEW.id_partita = 3)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN UPDATE Quarti SET Quarti.nome_squadra_2=NEW.nome_squadra_1 WHERE id_partita = 1;
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN UPDATE Quarti SET Quarti.nome_squadra_2=NEW.nome_squadra_2 WHERE id_partita = 1;
    		END IF;
    END IF;
    
	IF(NEW.id_partita = 4)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN INSERT INTO Quarti VALUES (NEW.id_torneo, 2, 							NEW.nome_squadra_1, null, -1, -1);
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN INSERT INTO Quarti VALUES (NEW.id_torneo, 2, 							NEW.nome_squadra_2, null , -1, -1);
    		END IF;
    END IF;
    
    
	IF(NEW.id_partita = 5)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN UPDATE Quarti SET Quarti.nome_squadra_2=NEW.nome_squadra_1 WHERE id_partita = 2;
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN UPDATE Quarti SET Quarti.nome_squadra_2=NEW.nome_squadra_2 WHERE id_partita = 2;
    		END IF;
    END IF;

	IF(NEW.id_partita = 6)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN INSERT INTO Quarti VALUES (NEW.id_torneo, 3, 							NEW.nome_squadra_1, null, -1, -1);
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN INSERT INTO Quarti VALUES (NEW.id_torneo, 3, 							NEW.nome_squadra_2, null , -1, -1);
    		END IF;
    END IF;
    
	IF(NEW.id_partita = 7)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN UPDATE Quarti SET Quarti.nome_squadra_2=NEW.nome_squadra_1 WHERE id_partita = 3;
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN UPDATE Quarti SET Quarti.nome_squadra_2=NEW.nome_squadra_2 WHERE id_partita = 3;
    		END IF;
    END IF;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Partite_girone`
--

CREATE TABLE `Partite_girone` (
  `id_torneo` int(11) NOT NULL,
  `id_girone` int(11) NOT NULL,
  `id_partita` int(11) NOT NULL,
  `nome_squadra_1` varchar(32) DEFAULT NULL,
  `nome_squadra_2` varchar(32) DEFAULT NULL,
  `gol_squadra_1` int(11) DEFAULT NULL,
  `gol_squadra_2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Trigger `Partite_girone`
--
DELIMITER $$
CREATE TRIGGER `aggiornamento` AFTER UPDATE ON `Partite_girone` FOR EACH ROW BEGIN

        	IF (NEW.gol_squadra_1 = NEW.gol_squadra_2)
           		THEN
                	UPDATE Gironi SET Gironi.punteggio_squadra = (Gironi.punteggio_squadra+1) WHERE ((Gironi.nome_squadra = NEW.nome_squadra_1 OR Gironi.nome_squadra = NEW.nome_squadra_2) AND (Gironi.id_torneo = NEW.id_torneo));
            END IF;
 
 IF (NEW.gol_squadra_1 > NEW.gol_squadra_2)
           		THEN
                	UPDATE Gironi SET Gironi.punteggio_squadra = (Gironi.punteggio_squadra+3) WHERE ((Gironi.nome_squadra = NEW.nome_squadra_1) AND (Gironi.id_torneo = NEW.id_torneo));
            END IF;
            IF (NEW.gol_squadra_1 < NEW.gol_squadra_2)
           		THEN
                	UPDATE Gironi SET Gironi.punteggio_squadra = (Gironi.punteggio_squadra+3) WHERE ((Gironi.nome_squadra = NEW.nome_squadra_2) AND (Gironi.id_torneo = NEW.id_torneo));
            END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Quarti`
--

CREATE TABLE `Quarti` (
  `id_torneo` int(11) NOT NULL,
  `id_partita` int(11) NOT NULL,
  `nome_squadra_1` varchar(32) DEFAULT NULL,
  `nome_squadra_2` varchar(32) DEFAULT NULL,
  `gol_squadra_1` int(11) DEFAULT NULL,
  `gol_squadra_2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Trigger `Quarti`
--
DELIMITER $$
CREATE TRIGGER `quarti_semifinali` AFTER UPDATE ON `Quarti` FOR EACH ROW BEGIN	
	IF (NEW.gol_squadra_1 != -1 AND NEW.gol_squadra_2 != -1)
    THEN
	IF(NEW.id_partita = 0)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN INSERT INTO Semifinali VALUES (NEW.id_torneo, 0, 							NEW.nome_squadra_1, null, -1, -1);
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN INSERT INTO Semifinali VALUES (NEW.id_torneo, 0, 							NEW.nome_squadra_2, null, -1, -1);
    		END IF;
    END IF;
    
	IF(NEW.id_partita = 1)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN UPDATE Semifinali SET Semifinali.nome_squadra_2=NEW.nome_squadra_1 WHERE id_partita = 0;
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN UPDATE Semifinali SET Semifinali.nome_squadra_2=NEW.nome_squadra_2 WHERE id_partita = 0;
    		END IF;
    END IF;
    
	IF(NEW.id_partita = 2)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN INSERT INTO Semifinali VALUES (NEW.id_torneo, 1, 							NEW.nome_squadra_1, null, -1, -1);
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN INSERT INTO Semifinali VALUES (NEW.id_torneo, 1, 							NEW.nome_squadra_2, null , -1, -1);
    		END IF;
    END IF;
    
	IF(NEW.id_partita = 3)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN UPDATE Semifinali SET Semifinali.nome_squadra_2=NEW.nome_squadra_1 WHERE id_partita = 1;
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN UPDATE Semifinali SET Semifinali.nome_squadra_2=NEW.nome_squadra_2 WHERE id_partita = 1;
    		END IF;
    END IF;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Semifinali`
--

CREATE TABLE `Semifinali` (
  `id_torneo` int(11) NOT NULL,
  `id_partita` int(11) NOT NULL,
  `nome_squadra_1` varchar(32) DEFAULT NULL,
  `nome_squadra_2` varchar(32) DEFAULT NULL,
  `gol_squadra_1` int(11) DEFAULT NULL,
  `gol_squadra_2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Trigger `Semifinali`
--
DELIMITER $$
CREATE TRIGGER `semifinali_finale` AFTER UPDATE ON `Semifinali` FOR EACH ROW BEGIN	
	IF (NEW.gol_squadra_1 != -1 AND NEW.gol_squadra_2 != -1)
    THEN
	IF(NEW.id_partita = 0)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN INSERT INTO Finali VALUES (NEW.id_torneo,0, 							NEW.nome_squadra_1, null, -1, -1);
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN INSERT INTO Finali VALUES (NEW.id_torneo,0, 							NEW.nome_squadra_2, null, -1, -1);
    		END IF;
    END IF;
    
	IF(NEW.id_partita = 1)
    	THEN
    		IF(NEW.gol_squadra_1 > NEW.gol_squadra_2)
    			THEN UPDATE Finali SET Finali.nome_squadra_2=NEW.nome_squadra_1 WHERE id_partita=0;
    		END IF;
    		IF(NEW.gol_squadra_1 < NEW.gol_squadra_2)
    			THEN UPDATE Finali SET Finali.nome_squadra_2=NEW.nome_squadra_2 WHERE id_partita=0;
    		END IF;
    END IF;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Squadre`
--

CREATE TABLE `Squadre` (
  `id_torneo` int(11) NOT NULL,
  `nome_squadra` varchar(11) DEFAULT NULL,
  `id_utente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `Squadre`
--

INSERT INTO `Squadre` (`id_torneo`, `nome_squadra`, `id_utente`) VALUES
(1, '1', 40),
(1, '2', 41),
(1, '3', 42),
(1, '4', 43),
(1, '5', 44),
(1, '6', 45),
(1, '7', 46),
(1, '8', 47),
(1, '9', 48),
(1, '10', 49),
(1, '11', 50),
(1, '12', 51),
(1, '13', 52),
(1, '14', 53),
(1, '15', 54),
(1, '16', 55),
(3, 'a', 7),
(3, 'b', 8),
(3, 'c', 9),
(3, 'd', 10),
(3, 'e', 11),
(3, 'f', 12),
(3, 'g', 13),
(3, 'h', 14),
(3, 'i', 15),
(3, 'l', 16),
(3, 'm', 17),
(3, 'n', 18),
(3, 'o', 19),
(3, 'p', 20),
(3, 'q', 21),
(3, 'r', 22);

--
-- Trigger `Squadre`
--
DELIMITER $$
CREATE TRIGGER `decremento_squadre` AFTER INSERT ON `Squadre` FOR EACH ROW BEGIN
	UPDATE Torneo SET Torneo.completo=Torneo.completo-1 WHERE Torneo.id_torneo = NEW.id_torneo;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Torneo`
--

CREATE TABLE `Torneo` (
  `id_torneo` int(11) NOT NULL,
  `id_utente` int(11) DEFAULT NULL,
  `nome` varchar(32) DEFAULT NULL,
  `luogo` varchar(32) DEFAULT NULL,
  `formato` int(11) NOT NULL,
  `data_inizio` date DEFAULT NULL,
  `data_fine` date DEFAULT NULL,
  `completo` int(11) DEFAULT NULL,
  `fase` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `Torneo`
--

INSERT INTO `Torneo` (`id_torneo`, `id_utente`, `nome`, `luogo`, `formato`, `data_inizio`, `data_fine`, `completo`, `fase`) VALUES
(1, 5, 'Piscine', 'Lerma', 16, '2018-08-23', '2018-08-25', 0, 1),
(2, 1, 'Mauro', 'casaleggio', 32, '2018-08-07', '2018-08-08', 0, 1),
(3, 5, 'Don Salvi', 'Ovada', 16, NULL, NULL, 0, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `Utenti`
--

CREATE TABLE `Utenti` (
  `id_utente` int(11) NOT NULL,
  `psw` varchar(256) DEFAULT NULL,
  `username` varchar(32) NOT NULL,
  `nome` varchar(32) DEFAULT NULL,
  `cognome` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `Utenti`
--

INSERT INTO `Utenti` (`id_utente`, `psw`, `username`, `nome`, `cognome`, `email`) VALUES
(5, '$2y$10$/FuGAhyvpaw9kA6PdIDJ9e/45OylTDCu0ZTYWeRM7fp52LvwKVLYe', 'cenno1996', 'filippo', 'cenonfolo', 'cenno@live.com'),
(6, '$2y$10$iU/1JMjpw1iBgFwp1gQSouvyT3WteO8sR1QoB8Tzp.mLkwJf57S7y', 'riky98', 'Riccardo', 'Cenonfolo', 'riky@libero.it');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `Finali`
--
ALTER TABLE `Finali`
  ADD PRIMARY KEY (`id_torneo`);

--
-- Indici per le tabelle `Gironi`
--
ALTER TABLE `Gironi`
  ADD PRIMARY KEY (`id_torneo`,`id_girone`,`nome_squadra`);

--
-- Indici per le tabelle `Ottavi`
--
ALTER TABLE `Ottavi`
  ADD PRIMARY KEY (`id_torneo`,`id_partita`);

--
-- Indici per le tabelle `Partite_girone`
--
ALTER TABLE `Partite_girone`
  ADD PRIMARY KEY (`id_torneo`,`id_girone`,`id_partita`);

--
-- Indici per le tabelle `Quarti`
--
ALTER TABLE `Quarti`
  ADD PRIMARY KEY (`id_torneo`,`id_partita`);

--
-- Indici per le tabelle `Semifinali`
--
ALTER TABLE `Semifinali`
  ADD PRIMARY KEY (`id_torneo`,`id_partita`);

--
-- Indici per le tabelle `Squadre`
--
ALTER TABLE `Squadre`
  ADD PRIMARY KEY (`id_torneo`,`id_utente`);

--
-- Indici per le tabelle `Torneo`
--
ALTER TABLE `Torneo`
  ADD PRIMARY KEY (`id_torneo`);

--
-- Indici per le tabelle `Utenti`
--
ALTER TABLE `Utenti`
  ADD PRIMARY KEY (`id_utente`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `Torneo`
--
ALTER TABLE `Torneo`
  MODIFY `id_torneo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `Utenti`
--
ALTER TABLE `Utenti`
  MODIFY `id_utente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `Partite_girone`
--
ALTER TABLE `Partite_girone`
  ADD CONSTRAINT `partite_girone_ibfk_1` FOREIGN KEY (`id_torneo`,`id_girone`) REFERENCES `Gironi` (`id_torneo`, `id_girone`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
