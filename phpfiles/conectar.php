<?php 
 /**
  * Connecting / disconnecting Database
  *
  * @author AndroidMorefast
  */
  class DbConnect {
     
    private $conn;
 
    // constructor
     function __construct() {
        
        // conexion a la database
        $this->connect();
    }

     // destructor
     function __destruct() {
         // cerrar la conexion
         $this->close();
     }
 
    /**
      * Estableciendo database conexion
      * @return database handler
      */
     function connect() {        
         include_once dirname(__FILE__) . './conexion.php';
         
         // conectando con mysql database
         $this->conn = mysql_connect(DB_HOST, DB_USERNAME, 
         DB_PASSWORD) or die(mysql_error());
 
         // seleccionar database
         mysql_select_db(DB_NAME) or die(mysql_error());
         
         // returing conexion resource
         return $this->conn;
     }
 
     /**
      * cerrar database conexion
      */
     function close() {
         // cerrar db conexion
         mysql_close($this->conn);
     }
 
 }
 
 ?>