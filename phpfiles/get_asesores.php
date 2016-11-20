<?php

define('HOST','localhost');
define('USER','miasesor_app');
define('PASS','Papa020432');
define('DB','miasesor_app');

$con = mysqli_connect(HOST,USER,PASS,DB);
$id= $_GET['id'];
$sql = "select * from members WHERE agencia_usuario = '".$id."' ORDER BY nombre";

$res = mysqli_query($con,$sql);

$result = array();

while($row = mysqli_fetch_array($res)){
	array_push($result,
			array('nombre'=>$row[6],
					'apellidos'=>$row[7],
					'tel'=>$row[12],
					'email'=>$row[3],
					'id'=>$row[0]
						
			));
}

echo json_encode(array("result"=>$result));

mysqli_close($con);

?>