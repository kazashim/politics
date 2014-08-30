<?php
require_once __DIR__ . '/db_connect.php';
/*need 
user_id
cand_id
user_const_id
*/
$db = new DB_CONNECT();
//echo 'hii'; 
$response=array();
$message="start";
$user_id=$_POST['user_id'];
$cand_id=$_POST['cand_id'];
$user_const_id=$_POST['user_const_id'];
$check_cand_const=mysql_query("select constituency_id from CAND_SIGNUP where cand_id=$cand_id") or die(mysql_error());
$cand_const_id=mysql_fetch_array($check_cand_const);
if($user_const_id==$cand_const_id['constituency_id'])
{

$addvote=mysql_query("update PSEUDO_VOTING set votes=votes+1 where cand_id=$cand_id") or die(mysql_error());
//echo $addvote;
}

if($addvote)
$response['success']=1;
else
$response['success']=0;	
echo json_encode($response);
/*echo $response['success'];
echo $respinse['message'];*/
?>

