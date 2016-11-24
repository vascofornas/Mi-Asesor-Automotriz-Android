<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 $nombre_cliente = $_POST['nombre_cliente'];
 $email_cliente = $_POST['email_cliente'];
 $cel_cliente = $_POST['cel_cliente'];
 $comentarios = $_POST['comentarios'];
 $tramite = $_POST['tramite'];
 
 $codigo = $_POST['codigo'];
 $agencia_tramite = $_POST['agencia_tramite'];

 echo $nombre_cliente."-".$email_cliente."-".$cel_cliente."-".$comentarios."-".$codigo."-".$tramite."-".$agencia_tramite;
 require_once('dbConnect.php');
 
 $sql = "INSERT INTO tb_tramites_online (nombre_cliente,email_cliente,cel_cliente,
 tramite,
 		comentarios,
 		codigo,
 		agencia_tramite) VALUES ('$nombre_cliente','$email_cliente','$cel_cliente','$tramite','$comentarios','$codigo','$agencia_tramite')
 		";
 
  if(mysqli_query($con,$sql)){
 echo "Could register";
 }else{
 echo "Could not register";

 
 
 }
 }else{
echo 'error';


 }