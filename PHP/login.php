<?php
require_once('user_manager.php');

$response = array();


if(isset($_POST['username']) && isset($_POST['password']))
{
    if(empty($_POST['username'])) {
		$response['error'] = true;
        $response['message'] = "Devi inserire un username!";
	}
    elseif(empty($_POST['password'])) {
		$response['error'] = true;
        $response['message'] = "Devi inserire una password!";
	}
    elseif(!login($_POST['username'], $_POST['password'])) {
		$response['error'] = true;
        $response['message'] = "Login non riuscito";
	}
    else {
		$tmp = login($_POST['username'], $_POST['password']);
		$response['error'] = false;
		$response['id'] = $tmp['id_utente'];
		$response['username'] = $tmp['username'];
		$response['email'] = $tmp['email'];
	}
}

echo json_encode($response);
