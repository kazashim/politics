<?php
require_once __DIR__ . '/db_connect.php';
/*
	cand_id
	msg
*/
$db = new DB_CONNECT();
$cand_id=$_POST['cand_id'];
$msg=$_POST['msg'];
//echo 'hii'; 
$response=array();
$message="start";
$insert=mysql_query("insert into CAND_UPDATES values(0,$cand_id,'$msg',now())") or die(mysql_error());

if($insert)
{
$response['success']=1;
$response['message']="values successfully inserted";
//$response['msg_id']=$msg_id;
}
else
{
$response['message']="NOt perfect: ".mysql_error();
$respnse['success']=0;
}
	
echo json_encode($response);
/*echo $response['success'];
echo $response['message'];*/
?>

