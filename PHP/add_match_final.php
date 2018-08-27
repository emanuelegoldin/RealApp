<?php
require_once('final_phase_manager.php');

$response = array();

if ($_POST['formato'] === "16") {

	if(!insert_match_quarter($_POST['id_torneo'], $_POST['id_partita'], $_POST['nome_squadra_1'], $_POST['nome_squadra_2'])) {
			$response['error'] = true;
			$response['message'] = "Inserimento non riuscito";
		}
		else {
			$response['error'] = false;
		}
}

else {

	if(!insert_match_octave($_POST['id_torneo'], $_POST['id_partita'], $_POST['nome_squadra_1'], $_POST['nome_squadra_2'])) {
			$response['error'] = true;
			$response['message'] = "Inserimento non riuscito";
		}
		else {
			$response['error'] = false;
		}
}
echo json_encode($response);
