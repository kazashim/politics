<?php
require_once __DIR__ . '/db_connect.php';
/*need 
user_id
cand_id
vote
username
reason
anonymous
*/
$db = new DB_CONNECT();
//echo 'hii'; 
$response=array();
$message="start";
$user_id=$_POST['user_id'];
$cand_id=$_POST['cand_id'];
$vote=$_POST['vote'];
$username=$_POST['username'];
$reason=$_POST['reason'];
$anonymous=$_POST['anonymous'];
$in_reason=mysql_query("insert into VOTE_UP_DOWN values('',$cand_id,$user_id,$vote,'$username',$anonymous,'$reason',now())") or die(mysql_error());
if($vote==0)
$in_vote_count=mysql_query("update  VOTE_COUNT set vote_down=votedown+1 where cand_id=$cand_id") or die(mysql_error);
if($vote==1)
$in_vote_count=mysql_query("update  VOTE_COUNT set vote_up=vote_up+1 where cand_id=$cand_id") or die(mysql_error());

if($in_reason && $in_vote_count )
$response['success']=1;
else
{$response['message']="NOt perfect: ".mysql_error();
$respnse['success']=0;
}
	
echo jason_encode($response);
/*echo $response['success'];
echo $respinse['message'];*/
?>

