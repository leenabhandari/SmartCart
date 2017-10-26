<?php
require "connection.php";
$user_name=$_POST["user_name"];
$password=$_POST["password"];
//$password='';
//$user_name=3;
$qry = "select * from customer where CustID like '$user_name' and CustPassword like '$password';";
$result = mysqli_query($conn,$qry);
if(mysqli_num_rows($result)>0)
{
	echo "login successful";
}
else
{
	echo "login failed";
}
mysqli_close($conn);
?>