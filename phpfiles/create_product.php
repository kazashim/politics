<?php
require_once __DIR__ . '/db_connect.php';
/*$name
$username
$password
$party
$email
$sex
$dob
$city
$pic
*/
$name="a";//$_POST['name'];
$username="a";//$_POST['username'];
$password="a";//$_POST['password'];
$party="a";//$_POST['party'];
$email="b";//$_POST['email'];
$sex="n";//$_POST['sex'];
//$dob=$_POST['dob'];
$city="a";//$_POST['city'];
//$pic=$_POST['pic'];
$pic="a";
$constituency_id=2;$_POST['constituency'];
$db = new DB_CONNECT();
//echo 'hii'; 
$response=array();
$message="start";
$insert=mysql_query("insert into CAND_SIGNUP values(0,'$name','$username','$password','$party','$email','$sex',20131212,'$city','$pic',$constituency_id)") or die(mysql_error());
$query=mysql_query("select cand_id from CAND_SIGNUP order by cand_id");
$row=mysql_fetch_array();
$cand_id=$row['cand_id'];
if($insert)
{
$response['success']=1;
$respnse['id']=$cand_id;
}
else
{
$response['message']="NOt perfect: ".mysql_error();
$respnse['success']=0;

}
	
//echo json_encode($response);
echo $response['success'];
echo $response['message'];
echo $response['cand_id'];
?>

