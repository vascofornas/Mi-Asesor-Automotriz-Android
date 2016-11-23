<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 $nombre_cliente = $_POST['nombre_cliente'];
 $email_cliente = $_POST['email_cliente'];
 $cel_cliente = $_POST['cel_cliente'];
 $modelo = $_POST['modelo'];
 $tipo = $_POST['tipo'];
 $kilometros = $_POST['kilometros'];
 $ano = $_POST['ano'];
 $fecha = $_POST['fecha'];
 
 $hora = $_POST['hora'];
 $comentarios = $_POST['comentarios'];
 $codigo = $_POST['codigo'];
 
 
 require_once('dbConnect.php');
 
 $sql = "INSERT INTO tb_citas_servicio (nombre_cliente,email_cliente,cel_cliente,
 		modelo, ano, kilometros, tipo,fecha,hora,comentarios,codigo) VALUES ('$nombre_cliente','$email_cliente','$cel_cliente',
 		'$modelo','$ano','$kilometros','$tipo','$fecha','$hora','$comentarios','$codigo')
 		";
 
  if(mysqli_query($con,$sql)){
 echo "Successfully Registered";
 }else{
 echo "Could not register";
 
 }
 }else{
echo 'error';


 }