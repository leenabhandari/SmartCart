<?php
require "connection.php";
$ind=$_POST["index"];
//$ind=2;
$qry = "delete from cart where ItemID='$ind';";
if(mysqli_query($conn, $qry))
{
	echo "deleted";
}
else
{
	echo "delete failed";
}
mysqli_close($conn);
?>