
<html>
<body>
<script>
function change(){
    document.getElementById("myform").submit();
}
</script>

<?php
$url3 = 'http://0.0.0.0:8000/get_edit_city/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options3 = array(
  'http' => array(
    /*'header'  => array(
                  'USERNAME: '.$_POST['username'],
                  'PASSWORD: '.$_POST['password'],
                ),*/
    'method'  => 'GET',
  ),
);
$context3 = stream_context_create($options3);
$output3 = file_get_contents($url3, false,$context3);
/*echo $output3;*/
$arr3 = json_decode($output3,true);


?>

<?php
$url4 = 'http://0.0.0.0:8000/get_vendor_from_city_id/id='.$_POST['city_selected'].'?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options4 = array(
  'http' => array(
    /*'header'  => array(
                  'USERNAME: '.$_POST['username'],
                  'PASSWORD: '.$_POST['password'],
                ),*/
    'method'  => 'GET',
  ),
);
$context4 = stream_context_create($options4);
$output4 = file_get_contents($url4, false,$context4);
/*echo $output3;*/
$arr4 = json_decode($output4,true);
/*echo $arr4['user_details'][0]['username'];
echo $arr4['city_details'][0]['name'];*/


?>

<?php
$url_get_otp = 'http://0.0.0.0:8000/get_otp_from_user_id/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options_get_otp = array(
  'http' => array(
    'header'  => array(
                  'ID: '.$_POST['vendor_id'],
                ),
    'method'  => 'GET',
  ),
);
$context_get_otp = stream_context_create($options_get_otp);
$output_get_otp = file_get_contents($url_get_otp, false,$context_get_otp);
/*echo $output3;*/
$arr_get_otp = json_decode($output_get_otp,true);



?>

<?php

/*echo $_POST['vendor_id'];
echo $_POST['generated_otp'];*/


if($_POST['generated_otp'] != ''){
$url_send_msg_mail = 'http://0.0.0.0:8000/send_otp_msg_mail/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options_send_msg_mail = array(
  'http' => array(
    'header'  => array(
                  'ID: '.$_POST['vendor_id'],
                  'OTP: '.$_POST['generated_otp'],
                ),
    'method'  => 'GET',
  ),
);
$context_send_msg_mail = stream_context_create($options_send_msg_mail);
$output_send_msg_mail = file_get_contents($url_send_msg_mail, false,$context_send_msg_mail);
/*echo $output_send_msg_mail;*/
$arr_send_msg_mail = json_decode($output_send_msg_mail,true);

if($arr_send_msg_mail[0]['status']==200){
  echo "OTP is send to vendors phone and email";
}

}

?>


<?php if(count($arr4['city_details']) == 0){
  $option="Select City";
  $value="";
}else{
  $option=$arr4['city_details'][0]['name'];
  $value=$arr4['city_details'][0]['id'];
}?>

<?php if(count($arr_get_otp) == 0){
  $option1="Select Vendor";
  $value1="";
}else{
  $option1=$arr_get_otp['vendor_name'];
  $value1=$arr_get_otp['vendor_id'];
}?>

<form action="" method="post" id="myform">
      		  <select class="custom" name="city_selected" style="" onchange="change()">
              <option value=<?php echo $value; ?>><?php echo $option; ?></option>
            
            <?php for ($x = 0; $x < count($arr3); $x++) { ?>
              <option style=" background-color:lightgray;" value=<?php echo $arr3[$x]['id'] ?>><?php echo $arr3[$x]['name'] ?></option>
            <?php }?> 
            
            </select>
            <br>
            <br>
            <select class="custom" name="vendor_id" style="">
             <option value=<?php echo $value1; ?>><?php echo $option1; ?></option>
            <?php for ($x = 0; $x < count($arr4['user_details']); $x++) { ?>
              <option style=" background-color:lightgray;" value=<?php echo $arr4['user_details'][$x]['pk'] ?>><?php echo $arr4['user_details'][$x]['username'] ?></option>
            <?php }?>
            </select>
            <br>
            <br>
            <input type="submit" value="Generate OTP">
            
	  </form>





    <form action="" method="post" id="">
             <input type="hidden" name="vendor_id" value=<?php echo $arr_get_otp['vendor_id']; ?>>
            <input type="text" name="generated_otp" value=<?php echo $arr_get_otp['otp'][0]['otp']; ?>>
            <input type="submit" value="Send OTP">
            
    </form>
</body>
</html>