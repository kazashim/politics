<?php
require_once __DIR__ . '/db_connect.php';
/*need 
user_id
msg_id
*/
$db = new DB_CONNECT();
//echo 'hii'; 
$response=array();
$message="start";
$user_id=$_POST['user_id'];
$msg_id=$_POST['msg_id'];
$add=mysql_query("insert into CAND_MSG_LIKE values($msg_id,$user_id)") or die(mysql_error());

if($add)
$response['success']=1;
else
{$response['message']="NOt perfect: ".mysql_error();
$respnse['success']=0;
}
	
echo jason_encode($response);
/*echo $response['success'];
echo $respinse['message'];*/
?>

