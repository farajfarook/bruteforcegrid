<?php
include_once 'DBConnection.php';
$conn = new DBConnection();

$ip = $_SERVER['REMOTE_ADDR'];

$query = "delete from iplist where ip = '".$ip."'";
$conn->runQuery($query);
    
$query = "INSERT INTO  `iplist` VALUES ('".$ip."','".time()."');";
$conn->runQuery($query);
echo "done with ip ".$ip;
?>
