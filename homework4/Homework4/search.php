<?php
# check if the user is logged in
include('checklogin.php');
# if the user is logged in, then display the search webpage
include('header.php');

# extract the search keyword from the request
$keyword = $_GET['keyword'];
# remove all occurrences of "<script" from the keyword to prevent XSS 
$keyword = str_ireplace("<script", "", $keyword);

# display the form for searching messages (note that the keyword input is set to the previously entered value)
?>
 <h1>Search</h1>
 <div style="width: 100%; text-align: right;"><a href="forum.php">Back to the Forum</a></div>

 <form action="search.php" method="GET">
  Keyword: <input type="text" name="keyword" value="<?php echo($keyword); ?>" />
  <input type="submit" value="Search" />
 </form>
<?php
include('dbconn.php');
# search the database for message whose content contains the keyword
$query = "SELECT posts.content AS content, posts.datetime AS datetime, users.username AS username FROM posts, users WHERE users.id = posts.user_id AND posts.content LIKE '%" . $keyword . "%' ORDER BY datetime DESC;";
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
