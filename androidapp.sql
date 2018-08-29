-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Ago 29, 2018 alle 13:33
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

--
-- Dump dei dati per la tabella `Gironi`
--

INSERT INTO `Gironi` (`id_torneo`, `id_girone`, `nome_squadra`, `punteggio_squadra`) VALUES
(1, 0, 'Bosio', 6),
(1, 0, 'Castelletto', 7),
(1, 0, 'Cremolino', 1),
(1, 0, 'Tagliolo', 5),
(1, 1, 'Capriata', 1),
(1, 1, 'Lerma', 3),
(1, 1, 'Rocca', 0),
(1, 1, 'Trisobbio', 1),
(1, 2, 'Carpeneto', 0),
(1, 2, 'Casaleggio', 0),
(1, 2, 'Molare', 0),
(1, 2, 'Rossiglione', 0),
(1, 3, 'Belforte', 0),
(1, 3, 'Campo', 0),
(1, 3, 'Mornese', 0),
(1, 3, 'Silvano', 0);

--
-- Trigger `Gironi`
--
DELIMITER $$
CREATE TRIGGER `cambio_fase` AFTER INSERT ON `Gironi` FOR EACH ROW BEGIN	
	UPDATE Torneo SET Torneo.fase = 1 WHERE NEW.id_torneo = Torneo.id_torneo;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Immagini`
--

CREATE TABLE `Immagini` (
  `id_immagine` int(11) NOT NULL,
  `nome` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `Immagini`
--

INSERT INTO `Immagini` (`id_immagine`, `nome`) VALUES
(1, 'ciao'),
(2, 'ciao8'),
(3, 'ciao8'),
(4, 'eccolo');

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
-- Dump dei dati per la tabella `Partite_girone`
--

INSERT INTO `Partite_girone` (`id_torneo`, `id_girone`, `id_partita`, `nome_squadra_1`, `nome_squadra_2`, `gol_squadra_1`, `gol_squadra_2`) VALUES
(1, 0, 0, 'Tagliolo', 'Bosio', 2, 1),
(1, 0, 1, 'Castelletto', 'Cremolino', 3, 0),
(1, 0, 2, 'Tagliolo', 'Castelletto', 1, 1),
(1, 0, 3, 'Bosio', 'Cremolino', 4, 1),
(1, 0, 4, 'Tagliolo', 'Cremolino', 3, 3),
(1, 0, 5, 'Bosio', 'Castelletto', 5, 4),
(1, 1, 0, 'Lerma', 'Rocca', 4, 2),
(1, 1, 1, 'Capriata', 'Trisobbio', 1, 1),
(1, 1, 2, 'Lerma', 'Capriata', -1, -1),
(1, 1, 3, 'Rocca', 'Trisobbio', -1, -1),
(1, 1, 4, 'Lerma', 'Trisobbio', -1, -1),
(1, 1, 5, 'Rocca', 'Capriata', -1, -1),
(1, 2, 0, 'Casaleggio', 'Molare', -1, -1),
(1, 2, 1, 'Rossiglione', 'Carpeneto', -1, -1),
(1, 2, 2, 'Casaleggio', 'Rossiglione', -1, -1),
(1, 2, 3, 'Molare', 'Carpeneto', -1, -1),
(1, 2, 4, 'Casaleggio', 'Carpeneto', -1, -1),
(1, 2, 5, 'Molare', 'Rossiglione', -1, -1),
(1, 3, 0, 'Mornese', 'Silvano', -1, -1),
(1, 3, 1, 'Campo', 'Belforte', -1, -1),
(1, 3, 2, 'Mornese', 'Campo', -1, -1),
(1, 3, 3, 'Silvano', 'Belforte', -1, -1),
(1, 3, 4, 'Mornese', 'Belforte', -1, -1),
(1, 3, 5, 'Silvano', 'Campo', -1, -1);

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
(1, 'Tagliolo', 8),
(1, 'Lerma', 9),
(1, 'Casaleggio', 10),
(1, 'Mornese', 11),
(1, 'Bosio', 12),
(1, 'Rocca', 13),
(1, 'Molare', 14),
(1, 'Silvano', 15),
(1, 'Castelletto', 16),
(1, 'Capriata', 17),
(1, 'Rossiglione', 18),
(1, 'Campo', 19),
(1, 'Cremolino', 20),
(1, 'Trisobbio', 21),
(1, 'Carpeneto', 22),
(1, 'Belforte', 23);

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
  `email` varchar(32) DEFAULT NULL,
  `url_immagine` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `Utenti`
--

INSERT INTO `Utenti` (`id_utente`, `psw`, `username`, `nome`, `cognome`, `email`, `url_immagine`) VALUES
(5, '$2y$10$/FuGAhyvpaw9kA6PdIDJ9e/45OylTDCu0ZTYWeRM7fp52LvwKVLYe', 'cenno1996', 'filippo', 'cenonfolo', 'cenno@live.com', ''),
(6, '$2y$10$iU/1JMjpw1iBgFwp1gQSouvyT3WteO8sR1QoB8Tzp.mLkwJf57S7y', 'riky98', 'Riccardo', 'Cenonfolo', 'riky@libero.it', '');

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
-- Indici per le tabelle `Immagini`
--
ALTER TABLE `Immagini`
  ADD PRIMARY KEY (`id_immagine`);

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
-- AUTO_INCREMENT per la tabella `Immagini`
--
ALTER TABLE `Immagini`
  MODIFY `id_immagine` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
