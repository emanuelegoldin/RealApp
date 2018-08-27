<?php

require_once('config.php');


function octave_data($id_torneo)
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($octave_stmt = $db->prepare("SELECT * FROM Ottavi WHERE id_torneo = ?"))
    {	
		$array = array();
		$octave_stmt->bind_param("i", $id_torneo);
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

function quarter_data($id_torneo)
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($octave_stmt = $db->prepare("SELECT * FROM Quarti WHERE id_torneo = ?"))
    {	
		$array = array();
		$octave_stmt->bind_param("i", $id_torneo);
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

function semi_data($id_torneo)
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($octave_stmt = $db->prepare("SELECT * FROM Semifinali WHERE id_torneo = ?"))
    {	
		$array = array();
		$octave_stmt->bind_param("i", $id_torneo);
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

function final_data($id_torneo)
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($octave_stmt = $db->prepare("SELECT * FROM Finali WHERE id_torneo = ?"))
    {	
		$array = array();
		$octave_stmt->bind_param("i", $id_torneo);
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

function set_result_octave($id_torneo, $id_partita, $gol_squadra_1, $gol_squadra_2)
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($reg_stmt = $db->prepare("UPDATE Ottavi SET gol_squadra_1 = ? WHERE id_torneo = ? AND id_partita=?"))
    {
        $reg_stmt->bind_param("iii",  $gol_squadra_1, $id_torneo, $id_partita);
        $reg_stmt->execute();

        if($reg_stmt->affected_rows != 1)
        {
            $db->close();
            return false;
        }

    }
    
    if($reg_stmt = $db->prepare("UPDATE Ottavi SET gol_squadra_2 = ? WHERE id_torneo = ? AND id_partita=?"))
    {
        $reg_stmt->bind_param("iii", $gol_squadra_2, $id_torneo, $id_partita);
        $reg_stmt->execute();

        if($reg_stmt->affected_rows != 1)
		{
           $db->close();
           return false;
        }

    
    }

    $db->close();
    return true;
}

function set_result_quarter($id_torneo, $id_partita, $gol_squadra_1, $gol_squadra_2)
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($reg_stmt = $db->prepare("UPDATE Quarti SET gol_squadra_1 = ? WHERE id_torneo = ? AND id_partita=?"))
    {
        $reg_stmt->bind_param("iii",  $gol_squadra_1, $id_torneo, $id_partita);
        $reg_stmt->execute();

        if($reg_stmt->affected_rows != 1)
        {
            $db->close();
            return false;
        }

    }
    
    if($reg_stmt = $db->prepare("UPDATE Quarti SET gol_squadra_2 = ? WHERE id_torneo = ? AND id_partita=?"))
    {
        $reg_stmt->bind_param("iii", $gol_squadra_2, $id_torneo, $id_partita);
        $reg_stmt->execute();

        if($reg_stmt->affected_rows != 1)
		{
           $db->close();
           return false;
        }

    
    }

    $db->close();
    return true;
}

function set_result_semi($id_torneo, $id_partita, $gol_squadra_1, $gol_squadra_2)
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($reg_stmt = $db->prepare("UPDATE Semifinali SET gol_squadra_1 = ? WHERE id_torneo = ? AND id_partita=?"))
    {
        $reg_stmt->bind_param("iii",  $gol_squadra_1, $id_torneo, $id_partita);
        $reg_stmt->execute();

        if($reg_stmt->affected_rows != 1)
        {
            $db->close();
            return false;
        }

    }
    
    if($reg_stmt = $db->prepare("UPDATE Semifinali SET gol_squadra_2 = ? WHERE id_torneo = ? AND id_partita=?"))
    {
        $reg_stmt->bind_param("iii", $gol_squadra_2, $id_torneo, $id_partita);
        $reg_stmt->execute();

        if($reg_stmt->affected_rows != 1)
		{
           $db->close();
           return false;
        }

    
    }

    $db->close();
    return true;
}

function set_result_final($id_torneo, $id_partita, $gol_squadra_1, $gol_squadra_2)
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($reg_stmt = $db->prepare("UPDATE Finali SET gol_squadra_1 = ? WHERE id_torneo = ? AND id_partita=?"))
    {
        $reg_stmt->bind_param("iii",  $gol_squadra_1, $id_torneo, $id_partita);
        $reg_stmt->execute();

        if($reg_stmt->affected_rows != 1)
        {
            $db->close();
            return false;
        }

    }
    
    if($reg_stmt = $db->prepare("UPDATE Finali SET gol_squadra_2 = ? WHERE id_torneo = ? AND id_partita=?"))
    {
        $reg_stmt->bind_param("iii", $gol_squadra_2, $id_torneo, $id_partita);
        $reg_stmt->execute();

        if($reg_stmt->affected_rows != 1)
		{
           $db->close();
           return false;
        }

    
    }

    $db->close();
    return true;
}







function get_group_teams($id_torneo) 
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($tournament_stmt = $db->prepare("SELECT nome_squadra FROM Gironi WHERE id_torneo=? ORDER BY id_girone ASC, punteggio_squadra DESC "))
    {	
		$array = array();
		$tournament_stmt->bind_param("i", $id_torneo);
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

// Inserisce una partita negli ottavi
function insert_match_octave($id_torneo, $id_partita, $nome_squadra_1, $nome_squadra_2)
{	
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);
	
    if($tournament_stmt = $db->prepare("INSERT INTO Ottavi (id_torneo, id_partita, nome_squadra_1, nome_squadra_2, gol_squadra_1, gol_squadra_2) VALUES (?, ?, ?, ?, -1, -1)"))
    {	
        $tournament_stmt->bind_param("iiss", $id_torneo, $id_partita, $nome_squadra_1, $nome_squadra_2);
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

// Inserisce una partita negli ottavi
function insert_match_quarter($id_torneo, $id_partita, $nome_squadra_1, $nome_squadra_2)
{	
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);
	
    if($tournament_stmt = $db->prepare("INSERT INTO Quarti (id_torneo, id_partita, nome_squadra_1, nome_squadra_2, gol_squadra_1, gol_squadra_2) VALUES (?, ?, ?, ?, -1, -1)"))
    {	
        $tournament_stmt->bind_param("iiss", $id_torneo, $id_partita, $nome_squadra_1, $nome_squadra_2);
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












