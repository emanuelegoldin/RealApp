<?php

	require_once('group_manager.php');
	
	$response = get_rank($_POST['id_torneo'], $_POST['id_girone']);

echo json_encode(array('rank' => $response));

