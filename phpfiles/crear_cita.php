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
 $agencia_cita = $_POST['agencia_cita'];
 echo $agencia_cita;
 
 require_once('dbConnect.php');
 
 $sql = "INSERT INTO tb_citas_servicio (nombre_cliente,email_cliente,cel_cliente,
 		modelo, ano, kilometros, tipo,fecha,hora,comentarios,codigo,agencia_cita) VALUES ('$nombre_cliente','$email_cliente','$cel_cliente',
 		'$modelo','$ano','$kilometros','$tipo','$fecha','$hora','$comentarios','$codigo','$agencia_cita')
 		";
 
  if(mysqli_query($con,$sql)){
echo "AGENCIA CITA=".$agencia_cita;
 }else{
 echo "Could not register";
 
 }
 }else{
echo 'error';


 }