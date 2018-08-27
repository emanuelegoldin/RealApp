<?php
require_once('tournament_manager.php');

$response = array();


if(isset($_POST['name']) && isset($_POST['place']))
{
    if(empty($_POST['name'])) {
		$response['error'] = true;
        $response['message'] = "Devi inserire un nome!";
	}
    elseif(empty($_POST['place'])) {
		$response['error'] = true;
        $response['message'] = "Devi inserire un luogo!";
	}
    elseif(!create_tournament($_POST['creatore'], $_POST['name'], $_POST['place'], $_POST['formato'], $_POST['completo'], $_POST['fase'])) {
		$response['error'] = true;
        $response['message'] = "Login non riuscito";
	}
    else {
		$response['error'] = false;
	}
}

echo json_encode($response);
