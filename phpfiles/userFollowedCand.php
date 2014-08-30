<?php
require_once __DIR__ . '/db_connect.php';
/*
Needed variables:
$user_id 
seemore
*/
$db = new DB_CONNECT();
//echo 'hii'; 
$response=array();
$message="start";
$user_id=$_POST['user_id'];
//$cand_const_id=$_POST['cand_const_id'];
$seemore=$_POST['seemore'];
//$cand_id=//$_POST['cand_id'];
$max=($seemore+1)*5;
$min=$max-5;
if($min <=0)
$min=0;
$result=mysql_query("select name,vote_up,vote_down,pic from VOTE_COUNT as V,CAND_SIGNUP as C,USER_FOLLOWED as F where F.user_id=$user_id and F.following_id=V.cand_id and C.cand_id=F.following_id order by date_time ") or die(mysql_error());
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
	$response['pic'.$x]=$row['pic'];
	$row=mysql_fetch_array($result);
//	echo "<br>";
	$x++;
	}


	
echo json_encode($response);
/*for($i=0;$i<$x;$i++)
{
echo $response['cand_name'.$i];
echo $response['vote_up'.$i];
echo $response['vote_down'.$i];
echo $response['pic'.$i];
echo "<br>";
}*/
?>

