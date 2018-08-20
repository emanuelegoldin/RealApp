<?php

require_once('config.php');

// Verifica se c'è già un utente con questo nome utente
function tournaments_data()
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($tournament_stmt = $db->prepare("SELECT * FROM tournaments"))
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
