<?php
include_once 'DBConnection.php';
$conn = new DBConnection();
$query = "delete from iplist where (".time()."-time) > 20";
$conn->runQuery($query);
$query = "select * from iplist";
$result = $conn->runQuery($query);
$arr = $conn->getDataArray($result);
$datastr = "";
for ($i = 0 ; $i < count($arr) ; $i++)
{
        if($i==0)
            $datastr = $arr[$i][0];
        else
            $datastr = $datastr." ".$arr[$i][0];
}
echo $datastr;
?>
