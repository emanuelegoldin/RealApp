<?php

	require_once('group_manager.php');
	
	$response = group_data($_POST['id_torneo'], $_POST['id_girone']);

echo json_encode(array('group' => $response));
