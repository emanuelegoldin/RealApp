<?php
require_once('tournament_manager.php');


$response = get_teams($_POST['id_torneo']);

echo json_encode(array('teams' => $response));

