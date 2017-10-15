<?php
# remove the authentication cookie by setting the expiration date to be in the past
setcookie('username', '', time() - 60 * 60 * 24 * 365);
setcookie('token', '', time() - 60 * 60 * 24 * 365);
unset($_COOKIE['username']);
unset($_COOKIE['token']);

# display the goodbye webpage
include('header.php');
?>
<h1>Goodbye!</h1>
Click <a href="login.php">here</a> to log in again.
<?php
include('footer.php');
?>
