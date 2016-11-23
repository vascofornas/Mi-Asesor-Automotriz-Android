 <?php 
include_once './DbConnect.php';
 
function getCategories(){
    $db = new DbConnect();
    // array para json 
    $respuesta = array();
    $respuesta["frutas"] = array();
     
    // extraemos las frutas con la query
    $result = mysql_query("SELECT * FROM frutas");
     
    while($row = mysql_fetch_array($result)){
        // array temporal para crear una sola fruta
        $tmp = array();
        $tmp["id"] = $row["id"];
        $tmp["nombre"] = $row["nombre"];
         
         
        array_push($respuesta["frutas"], $tmp);
    }
     
    // manteniendo cabecera de respuesta a JSON
    header('Content-Type: application/json');
     
    // haciéndose echo al resultado en JSON
    echo json_encode($respuesta);
}
 
getCategories();
?>