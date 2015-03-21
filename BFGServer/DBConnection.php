<?php
/**
 * PHP Template.
 */
    class DBConnection
    {
            
         function getConn() 
         {
             $usrName = "root";
             $password = "";
             $server = "localhost";
             $database_name = "bfg";
             $conn = mysql_connect($server, $usrName, $password) or die(mysql_error($conn));
             mysql_select_db($database_name, $conn);
             return $conn;
         }
         
         function runQuery($query) 
         {
             $conn = $this->getConn();
             $result = mysql_query($query,$conn) or die("error");
             $this->close($conn);
             return $result;
         }
         
         function readData($query) 
         {
             $conn = $this->getConn();
             $result = mysql_query($query,$conn) or die("error");
             
             for ($dataRow = 0 ; $dataRow < mysql_num_rows($result) ; $dataRow++) 
             {
                 $currentRow = mysql_tablename($result, $dataRow);
                 $fields = mysql_list_fields($db, $currentRow);
                 for ($dataField = 0 ; $dataField < mysql_num_fields($fields); $dataField++) 
                 {
                     $data[$dataRow][$dataField] = mysql_field_name($fields, $dataField);
                 }   
             }
             $this->close($conn);
             return $data;
                        
         }
         
         function close($conn) 
         {
             mysql_close($conn);
         }
         function getDataArray($result)
         {
             $data = new ArrayObject();
             $i = 0;
             while($datum = mysql_fetch_row($result))
             {
                 $data[$i++] = $datum;
             }
             return $data;
         }
    }

?>
