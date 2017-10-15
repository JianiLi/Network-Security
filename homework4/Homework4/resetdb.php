<?php 
include('dbconn.php');

$query =  <<<'EOD'
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS posts;
CREATE TABLE users (id SERIAL, username VARCHAR(255), password VARCHAR(255)); 
INSERT INTO users (username, password) VALUES ('admin', '321ewq'), ('vincent', 'RoyaleWithCheese'), ('jules', 'ezekiel');
CREATE TABLE posts (user_id INTEGER, content VARCHAR(1023), datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
INSERT INTO posts (user_id, content) VALUES ((SELECT id FROM users WHERE username = 'admin'), 'Congratulations, you have solved Problem 1!'),
  ((SELECT id FROM users WHERE username = 'vincent'), 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'),
  ((SELECT id FROM users WHERE username = 'jules'), 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');
EOD;

pg_query($query)
  or die("Database error: " . pg_last_error());

echo('Database has been reset!');
?>