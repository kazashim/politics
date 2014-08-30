<?php
require_once __DIR__ . '/db_connect.php';
/*$name
$password
$party
$email
$sex
$dob
$city
$constituency
*/
$name="a";//$_POST['name'];
$password="a";//$_POST['password'];
$party="a";//$_POST['party'];
$email="g";//$_POST['email'];
$sex="n";//$_POST['sex'];
$dob=20100202;//$_POST['dob'];
$city="a";//$_POST['city'];
$constituency_id=2;//$_POST['constituency'];

$db = new DB_CONNECT();

//echo 'hii'; 
$response=array();
$message="start";
try{
$cand_signup=mysql_query("insert into CAND_SIGNUP values(0,'$name',SHA($password),'$party','$email','$sex','$dob','$city',$constituency_id)") ;//or die(mysql_error());
$query=mysql_query("select cand_id from CAND_SIGNUP order by cand_id desc");// or die(mysql_error());
$row=mysql_fetch_array($query) or die(mysql_error());
$cand_id=$row['cand_id'];

//if success then insert into other tables;
if($cand_signup && $cand_id)
{
$in_constituency=mysql_query("update CONSTITUENCY set total_candidates = (total_candidates + 1) where constituency_id = $constituency_id ");// or die(mysql_error());
$in_popularity=mysql_query("insert into POPULARITY values ($cand_id,0)");//  or die(mysql_error());

$in_votecount=mysql_query("insert into VOTE_COUNT values ($cand_id,$constituency_id,0,0)");// or die(mysql_error());

}


if($cand_signup && $cand_id && $in_constituency && $in_popularity && $in_votecount)
{

$response['success']=1;
$response['id']=$cand_id;
$response['message']="Profile Successfully Created";
}
else
{
$response['message']="There is some problem creating your profile. Please re-check your data.";
$respnse['success']=0;
}
}
catch(Exception e)
{
$response['success']=0;
}
echo json_encode($response);
?>

