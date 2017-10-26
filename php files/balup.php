<?php
require "connection.php";
//$password='';
//$user_name="3";
//$bal=500000;
$user_name=$_POST["name"];
//$password=$_POST["pass"];
$bal = $_POST["bal"];
//$phone = $_POST["phone"]; 
$qry = "update customer set Balance=Balance+'$bal' where CustID like '$user_name';";
$result = mysqli_query($conn,$qry);
if(mysqli_affected_rows($conn)>0)
{
	echo "updated";
}
else
{
	echo " error";
}

?>