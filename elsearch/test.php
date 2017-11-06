
<?php
//建议词
error_reporting(E_ERROR);
$query=$_GET["query"];
//$query = "安置";
$query = urlencode($query); 
$url='http://219.223.251.214:8080/webserver/suggest?query='.$query;
$fh= file_get_contents($url);
$contentStr=json_decode($fh);
$arr1=array();
$arr=$contentStr->suggest->searchsuggest[0]->options;
foreach ($arr as $value) {  
    array();
    $arr1[]=array("name"=>urlencode($value->text));
}
echo (urldecode(json_encode($arr1)));
?>  