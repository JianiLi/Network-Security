<?php
# check if the user is logged in
include('checklogin.php');
# if the user is logged in, then display the forum webpage
include('header.php');
?>
 <h1>Gallery</h1>
 <div style="width: 100%; text-align: right;"><a href="index.php">Back to the Main Page</a></div>
<?php
# tests whether string ends with end
function endsWith($string, $end) {
  return (substr($string, -strlen($end)) === $end);
}
# check if the user has clicked on one of the remove image buttons
if (isset($_GET['removeImage'])) {
  # remove image using the rm system command
  system("rm /var/www/images/" . $_GET['removeImage']);
  echo("<p>Image removed!</p>");
}
# check if the user has clicked on the upload button of the form below
if (isset($_POST['uploadImage'])) {
  # check if the uploaded filename has extension jpg or jpeg
  if (endsWith($_FILES["fileToUpload"]["name"], ".jpg") or endsWith($_FILES["fileToUpload"]["name"], ".jpeg")) {
    # generate random filename for storage
    $target_file = "/var/www/images/upload" . rand() . ".jpg";
    # store file
    if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) 
      echo("<p>" . $_FILES["fileToUpload"]["name"] . " has been uploaded.</p>");
    else
      echo("<p>Upload error!</p>");
  }
  else echo("<p> Only JPEG files are allowed!</p>");
}
# display the form for posting new messages
?>
<form action="gallery.php" method="POST" enctype="multipart/form-data">
    Select image to upload:
    <input type="file" name="fileToUpload" accept="image/jpeg" />
    <input type="submit" value="Upload" name="uploadImage" />
</form>
<?php
# display gallery
$images = scandir('/var/www/images/');
# iterate over all files
foreach($images as $file) 
  # check if the file has extension jpg
  if (endsWith($file, ".jpg")) 
    # display image and link for removal
    echo("<img src='images/" . $file . "' style='margin-top: 1em;' /><br /><a href='gallery.php?removeImage=" . $file . "'>Delete</a><br/>");
include('footer.php');
?>
