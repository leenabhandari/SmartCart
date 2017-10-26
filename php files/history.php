 <?php
require "connection.php";
//$user_name=$_POST["CustID"];
$user_name=3;
$qry = "select ItemID from cart where CustID like '$user_name';";
$result = mysqli_query($conn,$qry);

$i = 0;
$j=0;
$ItemID[$i] = null;

if(mysqli_num_rows($result)>0)
{
 while($row =  @mysqli_fetch_assoc($result)){
    
	$ItemID[$i]=$row['ItemID'];
	//echo $ItemID[$i];
    $i++;
}
	echo "itemID successful;";
}
else
{
	echo "itemID failed";
}

while($j<$i)
{
	$qry1 = "select ItemName,ItemPrice from items where ItemID = '$ItemID[$j]';";
	$result1 = mysqli_query($conn,$qry1);
	if(mysqli_num_rows($result1)>0)
	{
	$row =  @mysqli_fetch_assoc($result1);
	echo "/";
	echo $row['ItemName']."    ";
	
	echo $row['ItemPrice'];
	}
	else
	{
		echo "itemName failed";
	}
	
	$j++;
}
echo "/+----";

//$qry = "select ItemName,ItemPrice from items where ItemID like '$ItemID';";

mysqli_close($conn);
?>