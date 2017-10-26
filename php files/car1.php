<?php
$db_name="id2756184_mall";
$uname="id2756184_root";
$pwd="root123";
$sername="localhost";
$conn=mysqli_connect($sername,$uname,$pwd,$db_name);
$ItemID=$_POST["itemID"];
$CustID=$_POST["user_ID"];
//$ItemID="1";
//$CustID="3";

$urlqry="select url from items where ItemID='$ItemID';";
$urlres= mysqli_query($conn,$urlqry);
if(mysqli_num_rows($urlres)>0)
{
$row = $urlres->fetch_array(MYSQLI_BOTH);
//echo "  @url@ ".$row['url'];
$url=$row['url'];
}
else{
	echo "xerror";
}
$qry = "insert into cart values(NULL,'$CustID','$ItemID',current_timestamp,'n','$url') ;";


$result = mysqli_query($conn,$qry);
if(mysqli_affected_rows($conn)>0)
{
	echo "item added";
}
else
{
	echo "error";
}

$Pricequery="select ItemPrice from items where ItemID='$ItemID';";
$PriceResult= mysqli_query($conn,$Pricequery);
if(mysqli_num_rows($PriceResult)>0)
{
$row = $PriceResult->fetch_array(MYSQLI_BOTH);
print "  PRICE: " .$row['ItemPrice'];
}
else{
	echo "Price error";
}

$Pricequery="select ItemName from items where ItemID='$ItemID';";
$PriceResult= mysqli_query($conn,$Pricequery);
if(mysqli_num_rows($PriceResult)>0)
{
$row = $PriceResult->fetch_array(MYSQLI_BOTH);
echo "  /Name/ " .$row['ItemName'];
}
else{
	echo "Name error";
}

mysqli_close($conn);

?>