<?php

if($_SERVER['REQUEST_METHOD']=='GET'){

define('HOST','localhost');
define('USER','miasesor_app');
define('PASS','Papa020432');
define('DB','miasesor_app');

$con = mysqli_connect(HOST,USER,PASS,DB);
$id= $_GET['id'];
$sql = "select * from tb_modelos WHERE agencia_modelo = '".$id."' ORDER BY nombre_modelo";

$res = mysqli_query($con,$sql);

$result = array();

while($row = mysqli_fetch_array($res)){
	array_push($result,
			array('nombre_modelo'=>$row[1],
					
						
			));
}

echo json_encode(array("result"=>$result));

mysqli_close($con);
	

}