<?php

require_once('config.php');

// Inserisce un utente nel database
function create_tournament($id_user, $name, $place, $format, $complete, $phase)
{	
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);
	
    if($tournament_stmt = $db->prepare("INSERT INTO Torneo (id_utente, nome, luogo, formato, completo, fase) VALUES (?, ?, ?, ?, ?, ?)"))
    {	
        $tournament_stmt->bind_param("issiii", $id_user, $name, $place, $format, $complete, $phase);
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


function tournaments_data()
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($tournament_stmt = $db->prepare("SELECT * FROM Torneo"))
    {	
		$array = array();
		
        $tournament_stmt->execute();

        $tournament_result = $tournament_stmt->get_result();
        
        while($tournament_row = $tournament_result->fetch_assoc()) {
			$array[] = $tournament_row;
		}
        
		
        $db->close();

        return $array;
    }

    $db->close();
    return false;
}

function add_team ($id_tournament, $id_user, $name)
{	
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);
	
    if($tournament_stmt = $db->prepare("INSERT INTO Squadre (id_torneo, id_utente, nome_squadra) VALUES (?, ?, ?)"))
    {	
        $tournament_stmt->bind_param("iis", $id_tournament, $id_user, $name);
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

function get_teams($id_tournament) 
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($tournament_stmt = $db->prepare("SELECT nome_squadra FROM Squadre WHERE id_torneo=?"))
    {	
		$array = array();
		$tournament_stmt->bind_param("i", $id_tournament);
        $tournament_stmt->execute();

        $tournament_result = $tournament_stmt->get_result();
        
        while($tournament_row = $tournament_result->fetch_assoc()) {
			$array[] = $tournament_row;
		}
        
		
        $db->close();

        return $array;
    }

    $db->close();
    return false;
}
	
function get_creator($id_tournament) {

 $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($tournament_stmt = $db->prepare("SELECT id_utente FROM Torneo WHERE id_torneo=?"))
    {	
		
		$tournament_stmt->bind_param("i", $id_tournament);
        $tournament_stmt->execute();

        $tournament_result = $tournament_stmt->get_result()->fetch_assoc();
        
		
        $db->close();

        return $tournament_result;
    }

    $db->close();
    return false;

}
	
function get_team_by_id($id_torneo, $id_utente) {

 $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($tournament_stmt = $db->prepare("SELECT nome_squadra FROM Squadre WHERE id_torneo=? AND id_utente=?"))
    {	
		
		$tournament_stmt->bind_param("ii", $id_torneo, $id_utente);
        $tournament_stmt->execute();

		if($tournament_stmt->affected_rows === 0)
        {
            $db->close();
            return false;
        }


        $tournament_result = $tournament_stmt->get_result()->fetch_assoc();
        
		
        $db->close();

        return $tournament_result;
    }

    $db->close();
    return false;

}	
	
	
	
	
	
	
	
	

