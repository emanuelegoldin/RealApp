<?php
require_once('group_manager.php');

$response = array();

if(!insert_team($_POST['id_torneo'], $_POST['id_girone'], $_POST['name'])) {
		$response['error'] = true;
        $response['message'] = "Inserimento non riuscito";
	}
    else {
		$response['error'] = false;
	}
	
echo json_encode($response);
