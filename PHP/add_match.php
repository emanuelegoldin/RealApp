<?php
require_once('group_manager.php');

$response = array();

if(!insert_match($_POST['id_torneo'], $_POST['id_girone'], $_POST['id_partita'], $_POST['nome_squadra_1'], $_POST['nome_squadra_2'])) {
		$response['error'] = true;
        $response['message'] = "Inserimento non riuscito";
	}
    else {
		$response['error'] = false;
	}
	
echo json_encode($response);
