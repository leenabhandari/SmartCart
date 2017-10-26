<?php
require "connection.php";
$ItemID=$_POST["itemID"];
//$CustID=$_POST["user_ID"];
//$ItemID=1;
//$CustID="3";


//$qry = "insert into cart values('$CustID','$ItemID',current_timestamp,500) ;";
$qry1="select ItemName,ItemPrice,url from items where ItemID ='$ItemID';";
$res=array();
$stmt = $conn->prepare($qry1);
$stmt->execute();
$stmt->bind_result($iname, $iprice,$iurl);
while($stmt->fetch()){
 
 //pushing fetched data in an array 
 $temp = [
 'item_name'=>$iname,
 'item_price'=>$iprice,
 'image'=>$iurl
 
 ];
 
 //pushing the array inside the hero array 
 array_push($res,$temp);
}
 
//displaying the data in json format 
echo json_encode(array('ans'=>$res));
 
 mysqli_close($conn);
 ?>