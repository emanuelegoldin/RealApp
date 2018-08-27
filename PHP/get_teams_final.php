<?php
require_once('final_phase_manager.php');


$response = get_group_teams($_POST['id_torneo']);

echo json_encode(array('teams' => $response));
