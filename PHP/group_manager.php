<?php

require_once('config.php');
	
// Inserisce una squadra in un girone
function insert_team($id_tournament, $id_group, $name)
{	
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);
	
    if($tournament_stmt = $db->prepare("INSERT INTO Gironi (id_torneo, id_girone, nome_squadra) VALUES (?, ?, ?)"))
    {	
        $tournament_stmt->bind_param("iis", $id_tournament, $id_group, $name);
        $tournament_stmt->execute();

        if($tournament_stmt->affected_rows != 1)
        {
            $db->close();
            return false;
        }

        $db->close();
        return true;

    }

    $db->close();
    return false;
}

// Inserisce una partita in un girone
function insert_match($id_tournament, $id_group, $id_match, $team_1, $team_2)
{	
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);
	
    if($tournament_stmt = $db->prepare("INSERT INTO Partite_girone (id_torneo, id_girone, id_partita, nome_squadra_1, nome_squadra_2, gol_squadra_1, gol_squadra_2) VALUES (?, ?, ?, ?, ?, -1, -1)"))
    {	
        $tournament_stmt->bind_param("iiiss", $id_tournament, $id_group, $id_match, $team_1, $team_2);
        $tournament_stmt->execute();

        if($tournament_stmt->affected_rows != 1)
        {
            $db->close();
            return false;
        }

        $db->close();
        return true;

    }

    $db->close();
    return false;
}

function group_data($id_torneo, $id_girone)
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($octave_stmt = $db->prepare("SELECT * FROM Partite_girone WHERE id_torneo = ? AND id_girone = ?"))
    {	
		$array = array();
		$octave_stmt->bind_param("ii", $id_torneo, $id_girone);
        $octave_stmt->execute();

        $octave_result = $octave_stmt->get_result();
        
        while($octave_row = $octave_result->fetch_assoc()) {
			$array[] = $octave_row;
		}
        
        $db->close();

        return $array;
    }

    $db->close();
    return false;
}

function set_result($id_torneo, $id_girone, $id_partita, $gol_squadra_1, $gol_squadra_2)
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($reg_stmt = $db->prepare("UPDATE Partite_girone SET gol_squadra_1 = ?, gol_squadra_2 = ? WHERE id_torneo = ? AND id_girone=? AND id_partita=?"))
    {
        $reg_stmt->bind_param("iiiii",  $gol_squadra_1, $gol_squadra_2, $id_torneo, $id_girone, $id_partita);
        $reg_stmt->execute();

        if($reg_stmt->affected_rows != 1)
        {
            $db->close();
            return false;
        }

        $db->close();
        return true;
    }

    $db->close();
    return false;
}

function get_rank ($id_torneo, $id_girone)
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($octave_stmt = $db->prepare("SELECT * FROM Gironi WHERE id_torneo = ? AND id_girone = ? ORDER BY punteggio_squadra DESC"))
    {	
		$array = array();
		$octave_stmt->bind_param("ii", $id_torneo, $id_girone);
        $octave_stmt->execute();

        $octave_result = $octave_stmt->get_result();
        
        while($octave_row = $octave_result->fetch_assoc()) {
			$array[] = $octave_row;
		}
        
        $db->close();

        return $array;
    }

    $db->close();
    return false;
}







