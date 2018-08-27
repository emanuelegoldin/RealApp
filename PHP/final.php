<?php

	require_once('final_phase_manager.php');
	
	$response = final_data($_POST['id_torneo']);

echo json_encode(array('final' => $response));
