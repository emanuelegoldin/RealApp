<?php
require_once('tournament_manager.php');

$response = array();


if(isset($_POST['name']))
{
    if(empty($_POST['name'])) {
		$response['error'] = true;
        $response['message'] = "Devi inserire un nome";
	}
    
    elseif(!add_team($_POST['id_torneo'],$_POST['id_utente'],$_POST['name'])) {
		$response['error'] = true;
        $response['message'] = "Inserimento non riuscito";
	}
    else {
		$response['error'] = false;
	}
}

echo json_encode($response);
