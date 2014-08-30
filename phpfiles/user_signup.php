<?php
require_once __DIR__ . '/db_connect.php';
/*$name
$username
$password
$email
$sex
$dob
$city
$pic
$constituency
*/
$name="sssiiiddhhaanntt";//$_POST['name'];
$username="aaa";//$_POST['username'];
$password="aaaaa";//$_POST['password'];
$email="siddhant180wins@gmail.commerce";//$_POST['email'];
$sex="male";//$_POST['sex'];
//$dob=$_POST['dob'];
$city="ala";//$_POST['city'];
$pic="a";//$_POST['pic'];
$constituency_id=2;//$_POST['constituency'];
$db = new DB_CONNECT();
//echo 'hii'; 
$response=array();
$message="start";
$insert=mysql_query("insert into USER_SIGNUP values(0,'$name','$username','$password','$sex','$email',20131212,'$city','$pic',$constituency_id)") or die(mysql_error());
$query=mysql_query("select user_id from USER_SIGNUP order by user_id desc") or die(mysql_error());
$row=mysql_fetch_array($query);
$user_id=$row['user_id'];
if($insert)
{
$response['success']=1;
$response['id']=$user_id;
$response['message']="perfect";
}
else
{
$response['message']="NOt perfect: ".mysql_error();
$respnse['success']=0;

}
	
echo json_encode($response);
/*echo $response['success'];
echo $response['message'];
echo $response['id'];*/
?>

