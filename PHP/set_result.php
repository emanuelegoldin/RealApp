<?php
require_once('group_manager.php');
require_once('final_phase_manager.php');

$response = array();

if ($_POST['id_girone'] === "-1") {

	if(!set_result_octave($_POST['id_torneo'], $_POST['id_partita'], $_POST['gol_1'], $_POST['gol_2'])) {
		$response['error'] = true;
        $response['message'] = $_POST['id_girone'];
	}
    else {
		$response['error'] = false;
	}
}
	
elseif ($_POST['id_girone'] === "-2") {

	if(!set_result_quarter($_POST['id_torneo'], $_POST['id_partita'], $_POST['gol_1'], $_POST['gol_2'])) {
		$response['error'] = true;
        $response['message'] = $_POST['id_girone'];
	}
    else {
		$response['error'] = false;
	}
}

elseif ($_POST['id_girone'] === "-3") {

	if(!set_result_semi($_POST['id_torneo'], $_POST['id_partita'], $_POST['gol_1'], $_POST['gol_2'])) {
		$response['error'] = true;
        $response['message'] = $_POST['id_girone'];
	}
    else {
		$response['error'] = false;
	}
}

elseif ($_POST['id_girone'] === "-4") {

	if(!set_result_final($_POST['id_torneo'], $_POST['id_partita'], $_POST['gol_1'], $_POST['gol_2'])) {
		$response['error'] = true;
        $response['message'] = $_POST['id_girone'];
	}
    else {
		$response['error'] = false;
	}
}

else {

	if(!set_result($_POST['id_torneo'], $_POST['id_girone'], $_POST['id_partita'], $_POST['gol_1'], $_POST['gol_2'])) {
			$response['error'] = true;
			$response['message'] = "Inserimento non riuscito";
		}
		else {
			$response['error'] = false;
		}
}

echo json_encode($response);

