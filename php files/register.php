<?php
require "connection.php";
//$password='';
//$user_name="von matt";
//$bal=5000;
$user_name=$_POST["name"];
$password=$_POST["pass"];
$bal = $_POST["bal"];
$phone = $_POST["phone"]; 
$qry = "insert into customer values($phone,'$user_name','$password','$bal')";
$result = mysqli_query($conn,$qry);
if(mysqli_affected_rows($conn)>0)
{
	echo "customer added";
}
else
{
	echo "cust error";
}

?>