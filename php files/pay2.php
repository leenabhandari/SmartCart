<?php
require "connection.php";
$total=$_POST["total"];
$CustID=$_POST["CustID"];
$code=$_POST["code"];
//$total=1500;
//$CustID=3;
//mysqli_query($conn,"update Balance set Balance=Balance+100 where CustID='$CustID';");
$result0= mysqli_query($conn,"select Balance from customer where CustID='$CustID';");
$row = $result0->fetch_array(MYSQLI_BOTH);	
			
if($row['Balance']-$total>0)
{
  
    
	$result1= mysqli_query($conn,"update customer inner join couponcode on '$code'=code set Balance=Balance-'$total'+value where CustID='$CustID' ");
   
	if(mysqli_affected_rows($conn)>0)
	{
		mysqli_query($conn,"update cart set PaymentStatus='p';");
		$result2= mysqli_query($conn,"select Balance from customer where CustID='$CustID';");
		if(mysqli_num_rows($result2)>0)
		{
			
		$row = $result2->fetch_array(MYSQLI_BOTH);
		echo "balance updated %" .$row['Balance'];

             $qtyquery="UPDATE items SET ItemQty = ItemQty-1 WHERE items.ItemID = 1;";
              mysqli_query($conn,$qtyquery);
		}
	}
    
	else
	{
	    	$result2= mysqli_query($conn,"select Balance from customer where CustID='$CustID';");
	$row = $result2->fetch_array(MYSQLI_BOTH);
	echo "balance error %".$row['Balance'];
	}
}
else
{
	echo "Insufficient balance %".$row['Balance'];
}
mysqli_close($conn);
?>