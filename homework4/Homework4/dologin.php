<?php 
# extract username and password from the request
$username = $_GET['username'];
$password = $_GET['password'];

# find the correct password for the requested username
include('dbconn.php');
$query = "SELECT password FROM users WHERE username = '" . $username . "';";
$result = pg_query($query);

# check if the username was found in the database
if (pg_num_rows($result) != 1) {
  # username was not found in the database, let the user know about it, and stop here
  include('header.php');
  echo('<h2>Invalid username!</h2>Please log in <a href="login.php">here</a>.');
  include('footer.php');
  exit();
}

# extract the correct password from the result
$row = pg_fetch_array($result, null, PGSQL_ASSOC);
$correct_password = $row['password'];

# check if the password is correct
if (strcmp($password, $correct_password) == 0) {
  # succesful login, redirect user to the main page
  header('Location: http://192.168.56.2/index.php');
  # save the authentication cookie, which includes the username and a token computed from the password
  setcookie('username', $username);
  setcookie('token', md5($password));
} else {
  # incorrect password, let the user know about it
  include('header.php');
  echo('<h2>Incorrect password!</h2>Please log in <a href="login.php">here</a>.');
  include('footer.php');
}
?>