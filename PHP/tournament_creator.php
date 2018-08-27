<?php

	require_once('tournament_manager.php');
	
	$response = get_creator($_POST['id_torneo']);

echo json_encode($response);
