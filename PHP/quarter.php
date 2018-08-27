<?php

	require_once('final_phase_manager.php');
	
	$response = quarter_data($_POST['id_torneo']);

echo json_encode(array('quarter' => $response));
