
<?php
//通用查询
error_reporting(E_ERROR);
$query=$_GET["query"]; 
$querytype=$_GET["querytype"]; 
$query = urlencode($query); 
$url='http://219.223.251.214:8080/webserver/getJson?query='.$query;
//$url='http://localhost:8080/getJson?query='.$query;
$url=$url."&querytype=".$querytype;
//echo($url);
$fh= file_get_contents($url);
$fh=str_replace("\r\n","",$fh); 
echo ($fh);
?> 