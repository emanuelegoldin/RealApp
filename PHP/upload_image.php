<?php 
	
	require_once('config.php');
	
	$response = array();
	
	$db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);
	
	$image = $_POST['image'];
	$name = $_POST['name'];
	$upload_path = "uploads/images/$name.jpg";
	
	if($image_stmt = $db->prepare("INSERT INTO Immagini (`nome`) VALUES (?)"))
    {
        $image_stmt->bind_param("s", $name);
		$image_stmt->execute();
		
		file_put_contents($upload_path, base64_decode($image));
		$response['message'] = "Immagine caricata correttamente";
	}
	else 
	{
		$response['message'] = "Immagine non caricata correttamente";
	}
	
	echo json_encode($response);
	
