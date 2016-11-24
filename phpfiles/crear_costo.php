<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 $nombre_cliente = $_POST['nombre_cliente'];
 $email_cliente = $_POST['email_cliente'];
 $cel_cliente = $_POST['cel_cliente'];
 $modelo = $_POST['modelo'];

 $kilometros = $_POST['kilometros'];
 $ano = $_POST['ano'];

 $comentarios = $_POST['comentarios'];
 $codigo = $_POST['codigo'];
 $agencia_costo = $_POST['agencia_costo'];
 
 require_once('dbConnect.php');
 
 $sql = "INSERT INTO tb_costos_servicio (nombre_cliente,email_cliente,cel_cliente,
 		modelo, ano, kilometros, comentarios,codigo,agencia_costo) VALUES ('$nombre_cliente','$email_cliente','$cel_cliente',
 		'$modelo','$ano','$kilometros','$comentarios','$codigo','$agencia_costo')
 		";
 
  if(mysqli_query($con,$sql)){
 echo "Successfully Registered";
 }else{
 echo "Could not register";
 
 }
 }else{
echo 'error';


 }