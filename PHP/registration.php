<?php
require_once('user_manager.php');

$response = array();

if(isset($_POST['username']) && isset($_POST['password']) && isset($_POST['password_confirmation']) && isset($_POST['email']))
{
    if(empty($_POST['username'])) {
		$response['error'] = true;
        $response['message'] = "Devi inserire un username!";
	}
    elseif(empty($_POST['password'])) {
		$response['error'] = true;
        $response['message'] = "Devi inserire una password!";
	}
    elseif(empty($_POST['password_confirmation'])) {
		$response['error'] = true;
        $response['message'] = "Devi inserire la conferma della password!";
	}
    elseif($_POST['password_confirmation'] != $_POST['password']) {
		$response['error'] = true;
        $response['message'] = "La conferma della password non corrisponde";
	}
    elseif(!filter_var($_POST['email'], FILTER_VALIDATE_EMAIL)) {
		$response['error'] = true;
        $response['message'] = "Indirizzo email inserito non valido";
	}
    elseif(!validate_username($_POST['username'])) {
		$response['error'] = true;
        $response['message'] = "L'username deve contenere solo lettere e numeri";
	}
    elseif(username_exists($_POST['username'])) {
		$response['error'] = true;
        $response['message'] = "L'username è già stato usato";
	}
    elseif(!register($_POST['name'], $_POST['surname'], $_POST['username'], $_POST['password'], $_POST['email'])) {
        $response['error'] = true;
        $response['message'] = "Errore interno. Contatta il supporto tecnico.";
	}
    else {
		$response['error'] = false;
	}
}

echo json_encode($response);
