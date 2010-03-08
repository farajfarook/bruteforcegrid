<?php
include_once 'DBConnection.php';

$conn = new DBConnection();

if(isset ($_GET["name"]))
{
    $discr = $_GET["name"];
    $res = $conn->runQuery("select chksum from dlist where title='".$discr."'");
    $data = $conn->getDataArray($res);
    echo $data[0][0];
}
?>
