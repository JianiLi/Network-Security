    <div style="width: 80%; text-align: center; margin-top: 1em; margin-left: auto; margin-right: auto; border-top: 1px solid black; padding-top: 0.5em;">
<?php
# check if the user is logged in or not, and display some basic information
if (isset($_COOKIE['username']))
  echo('You are logged in as <span style="font-weight: bold;">' . $_COOKIE['username'] . '</span>. Click <a href="logout.php">here</a> to log out.');
else
  echo('You are not logged in.');
?>
    </div>
   </div>
  </body>
</html>