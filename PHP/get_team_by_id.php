<?php
require_once('tournament_manager.php');

$response = array();

if(!get_team_by_id($_POST['id_torneo'], $_POST['creatore'])) {
		$response['error'] = true;
	}
    else {
		$response['error'] = false;
		$temp = get_team_by_id($_POST['id_torneo'], $_POST['creatore']);
		$response['team'] = $temp['nome_squadra'];
	}
	
echo json_encode($response);
