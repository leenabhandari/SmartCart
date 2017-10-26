 <?php
require "connection.php";
$user_name=$_POST["CustID"];
//$user_name=3;
$qry = "select * from customer where CustID like '$user_name';";
$result = mysqli_query($conn,$qry);

if(mysqli_num_rows($result)>0)
{
$row = $result->fetch_array(MYSQLI_BOTH);
print "  Phone: " .$row['CustID'];
print "  Name: " .$row['CustName'];
print "  Password: " .$row['CustPassword'];
print "  Balance: " .$row['Balance'];
}
else
{
	echo "failed";
}

mysqli_close($conn);
?>