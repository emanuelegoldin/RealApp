<?php

require_once('config.php');

// Verifica se l'username e password sono corretti e in caso effettua il login
function login($username, $password)
{
    // Check username existance
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($login_stmt = $db->prepare("SELECT * FROM Utenti WHERE username = ?"))
    {	
        $login_stmt->bind_param("s", $username);
        $login_stmt->execute();

        $login_result = $login_stmt->get_result();

        // Check if we have 1 and only 1 row
        if ($login_result->num_rows === 0 || $login_result->num_rows > 1)
        {	
            $db->close();

            return false;
        }

        // Check user password
        $user_row = $login_result->fetch_assoc();

        $user_password_hash = $user_row['psw'];

        if (!password_verify($password, $user_password_hash))
        {	
            $db->close();

            return false;
        }

        $db->close();

        return $user_row;
    }

    $db->close();
    return false;
}


// Inserisce un utente nel database
function register($name, $surname, $username, $password, $email)
{	
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);
	
    if($reg_stmt = $db->prepare("INSERT INTO Utenti (nome, cognome, username, psw, email) VALUES (?, ?, ?, ?, ?)"))
    {	
        $reg_stmt->bind_param("sssss", $name, $surname, $username, password_hash($password, PASSWORD_DEFAULT), $email);
        $reg_stmt->execute();

        if($reg_stmt->affected_rows != 1)
        {
            $db->close();
            return false;
        }

        $db->close();
        return true;

    }

    $db->close();
    return false;
}

// Verifica se l'username rispetta le condizioni, deve essere alfanumerico e con meno di 32 caratteri
function validate_username($username)
{
    return ctype_alnum($username) && strlen($username) <= 32;
}

// Verifica se c'è già un utente con questo nome utente
function username_exists($username)
{
    $db = new mysqli($GLOBALS['mysql_server'], $GLOBALS['mysql_user'], $GLOBALS['mysql_pass'], $GLOBALS['mysql_db']);

    if($user_stmt = $db->prepare("SELECT id_utente FROM Utenti WHERE username = ?"))
    {
        $user_stmt->bind_param("s", $username);
        $user_stmt->execute();

        $user_result = $user_stmt->get_result();

        if($user_result->num_rows === 0 || $user_result->num_rows > 1)
        {
            $db->close();

            return false;
        }

        $db->close();

        return true;
    }

    $db->close();
    return false;
}
