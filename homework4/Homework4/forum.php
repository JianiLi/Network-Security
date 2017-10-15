<?php
# check if the user is logged in
include('checklogin.php');
# if the user is logged in, then display the forum webpage
include('header.php');
?>
 <h1>Forum</h1>
 <div style="width: 100%; text-align: right;"><a href="search.php">Search Messages</a> · <a href="index.php">Back to the Main Page</a></div>
<?php
include('dbconn.php');
# check if the user has clicked on the post button of the form below
if (isset($_POST['postMessage'])) {
  # if yes, then extract the content from the request, extract the username from the authentication cookie, and insert the message into the database (note that datetime does not need to be specified because the database server sets it to the current time)
  $content = $_POST['content'];
  # remove all occurrences of "<script" from the content to prevent XSS
  $content = str_ireplace("<script", "", $content); 
  $username = $_COOKIE['username'];
  $query = "INSERT INTO posts (content, user_id) VALUES ('" . $content . "', (SELECT id FROM users WHERE username = '" . $username . "'));";
  $result = pg_query($query);
}
# display the form for posting new messages
?>
 <form action="forum.php" method="POST">
  Message:<br/>
  <textarea name="content" style="width: 100%; margin-bottom: 0.5em;"></textarea><br/>
  <input type="submit" value="Post" name="postMessage" />
 </form>
<?php
# retrieve messages from the database
$query = 'SELECT posts.content AS content, posts.datetime AS datetime, users.username AS username FROM posts, users WHERE users.id = posts.user_id ORDER BY datetime DESC;';
$result = pg_query($query);
$rows = pg_fetch_all($result);
# loop over messages
foreach($rows as $row) {
  # for each message, display it in a nice format
  echo('<div style="width: 100%; border: 1px solid gray; padding: 0.1em;">' . $row['content'] . '</div>');
  echo('<div style="margin-bottom: 2em; width: 100%; padding: 0.1em; background-color: lightgray; text-align: right; border: 1px solid gray;">posted by <span style="font-weight: bold;">' . $row['username'] . '</span> at <span style="font-weight: bold;">' . $row['datetime'] . '</span></div>');
}
include('footer.php');
?>
