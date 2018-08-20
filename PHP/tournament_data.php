<?php
require_once('tournament_manager.php');


$response = tournaments_data();

echo json_encode(array('tournaments' => $response));
