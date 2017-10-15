<?php
# display form for entering login information
include('header.php');
?>
    <h1>Welcome to the website!</h1>
    <h2>Please log in to continue:</h2>
    <form action="dologin.php" method="GET">
      Username: <input name="username" type="text" /></br>
      Password: <input name="password" type="password" /></br>
      <input type="submit" value="Log In" />
    </form>
<?php
include('footer.php');
?>