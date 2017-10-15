<?php
# check if the user is logged in
include('checklogin.php');
# if the user is logged in, then display the welcome webpage
include('header.php');
# welcome webpage include a form for selecting the color theme
?>
<h1>Welcome!</h1>
<h2><a href="forum.php">Forum</a></h2>
<h2><a href="gallery.php">Gallery</a></h2>
<form action="index.php" method="GET">
  Theme: <select name="theme">
    <option value="light.css">light</option>
    <option value="dark.css">dark</option>
  </select>
  <input type="submit" value="Select" />
</form>
<?php
include('footer.php');
?>
