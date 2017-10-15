<html>
  <head>
    <title>Network Security Homework 4</title>
    <style>
<?php
# check if the user has selected a new theme
if (isset($_GET['theme'])) {
  # if yes, then update the theme cookie
  setcookie('theme', $_GET['theme']);
  $_COOKIE['theme'] = $_GET['theme'];
}
# include the style file of the selected theme (or of the default theme)
if (isset($_COOKIE['theme'])) 
  include($_COOKIE['theme']);
else
  include('light.css');
?>
    </style>
  </head>
  <body style="background: gray; font-family: Arial;">
    <div id="container" style="width: 1000px; margin-left: auto; margin-right: auto; margin-top: 0px; padding: 1em;">