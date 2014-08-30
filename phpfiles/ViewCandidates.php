<?php
require_once __DIR__ . '/db_connect.php';
/*
Needed variables:
user_const_id 
seemore
*/
$db = new DB_CONNECT();
//echo 'hii'; 
$response=array();
$message="start";
$user_const_id=$_POST['user_const_id'];
$seemore=$_POST['seemore'];
$max=($seemore+1)*5;
$min=$max-5;
if($min <=0)
$min=0;
$result=mysql_query("select vote_up,vote_down,name from VOTE_COUNT as V,CAND_SIGNUP as C where V.constituency_id=$user_const_id and V.cand_id=C.cand_id order by vote_up desc ") or die(mysql_error());
$length=mysql_num_rows($result) or die(mysql_error());
//echo "$length";
$x=0;
$y=1;
$row=mysql_fetch_array($result);
//echo $row['name'];
while( $y<=($seemore+1)*5-5 && $y<$length )
{
//echo $y;
$row=mysql_fetch_array($result);
$y++;
}

	while( $row && $x<5 && $x < $length)
	{

	
//	echo $x;

	$response['cand_name'.$x]=$row['name'];
	$response['vote_up'.$x]=$row['vote_up'];
	$response['vote_down'.$x]=$row['vote_down'];
	$row=mysql_fetch_array($result);
//	echo "<br>";
	$x++;
	}


	
echo jason_encode($response);
/*for($i=0;$i<$x;$i++)
{
echo $response['cand_name'.$i];
echo $response['vote_up'.$i];
echo $response['vote_down'.$i];
echo "<br>";
}*/
?>

