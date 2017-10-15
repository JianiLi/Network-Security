<?php 
# first, check if the user has an authentication cookie
if (!isset($_COOKIE['username'])) {
  # if not, then redirect to the login page
  header('Location: http://192.168.56.2/login.php');
  exit();
}

# second, check if the cookie is valid
$username = $_COOKIE['username'];
$token = $_COOKIE['token'];

include('dbconn.php');
$query = "SELECT password FROM users WHERE username = '" . $username . "';";
$result = pg_query($query);
$row = pg_fetch_array($result, null, PGSQL_ASSOC);
$password = $row['password'];
# authentication token is supposed to be the MD5 hash of the password
$hash = md5($password);

# check if the token is equal the MD5 hash
if (strcmp($token, $hash) != 0) {
  # if not, then let the user know about it, and stop here
  echo('<html><body>Invalid authentication token!<br/>Please log in <a href="login.php">here</a>.</body></html>');
  exit();
}

# user seems to be logged in, nothing left to do
?>