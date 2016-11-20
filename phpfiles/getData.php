<?php

if($_SERVER['REQUEST_METHOD']=='GET'){

	$id  = $_GET['id'];

	require_once('dbConnect.php');

	$sql = "SELECT * FROM tb_agencias WHERE codigo_agencia='".$id."'";

	$r = mysqli_query($con,$sql);

	$res = mysqli_fetch_array($r);

	$result = array();
	
	
	
	array_push($result,array(
			"id_agencia"=>$res['id_agencia'],
			"nombre_agencia"=>$res['nombre_agencia'],
 			"direccion_agencia"=>$res['direccion_agencia'],
			 "codigo_agencia"=>$res['codigo_agencia'],
			"google_play_agencia"=>$res['google_play_agencia']
	)
			);

	
	echo json_encode(array("result"=>$result));

	mysqli_close($con);

}