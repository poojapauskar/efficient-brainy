<html>

<head>
<link rel="stylesheet" type="text/css" href="add_vendor.css">
<link rel="stylesheet" type="text/css" href="navigation_footer.css">
<script type="text/javascript">

function hide_wait_msg ()
{
    document.getElementById('loadingPleaseWait').style.display = 'none';
}

function show_wait_msg ()
{
     document.getElementById('loadingPleaseWait').style.display = 'block';
}

</script>
<style>

</style>
</head>
<body onload="hide_wait_msg()">
<nav class="navbar navbar-default navbar-fixed-top" id="nav_top">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="admin_page.php" style="text-decoration:none;">
            <img src="images/Brainy_Logo.jpg" id="logo" class="img-responsive">
          </a>
        </div>
      </div>
    </nav>

<?php

$url_check = 'https://efficient-brainy.herokuapp.com/update_logged_in/check/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options_check = array(
  'http' => array(
    /*'header'  => array(
                  'LOGGED-IN: 1',
                ),*/
    'method'  => 'GET',
  ),
);
$context_check = stream_context_create($options_check);
$output_check = file_get_contents($url_check, false,$context_check);
/*echo $output_check;*/
$arr_check = json_decode($output_check,true);
/*echo $arr_check;*/

if($arr_check['status'] == 400){
      echo "<script>location='index.php'</script>";
}else{
    /*echo "<script>location='index.php'</script>";*/  
}


?>

<?php
$url_city = 'https://efficient-brainy.herokuapp.com/get_edit_city/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options_city = array(
  'http' => array(
    /*'header'  => array(
                  'USERNAME: '.$_POST['username'],
                  'PASSWORD: '.$_POST['password'],
                ),*/
    'method'  => 'GET',
  ),
);
$context_city = stream_context_create($options_city);
$output_city = file_get_contents($url_city, false,$context_city);
/*echo $output3;*/
$city = json_decode($output_city,true);


?>


<?php
$url_name = 'https://efficient-brainy.herokuapp.com/check_username/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options_name = array(
  'http' => array(
    'header'  => array(
                  'USERNAME: '.$_POST['username'],
                ),
    'method'  => 'GET',
  ),
);
$context_name = stream_context_create($options_name);
$output_name = file_get_contents($url_name, false,$context_name);

$name = json_decode($output_name,true);
if($name['status']==400){
 /*echo  "Username already exists";*/
}else{
  /*echo "Valid username";*/
}



?>

<?php 

if($_POST['username'] != '' && $name['status']==200){


       /* echo "hi";*/

        $url8 = 'https://efficient-brainy.herokuapp.com/register/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
        $data8 = array('token_generated' => '','is_admin' => '','logged_in' => '','username' => $_POST['username'],'password' => $_POST['password'],'city_id' => $_POST['city'],'phone' => $_POST['phone'], 'name' => $_POST['name'],'email' => $_POST['email'], 'address' =>$_POST['address']);
        // use key 'http' even if you send the request to https://...
        $options8 = array(
          'http' => array(
            'header'  => "Content-type: application/x-www-form-urlencoded\r\n",
            'method'  => 'POST',
            'content' => http_build_query($data8),
          ),
        );
        $context8  = stream_context_create($options8);
        $result8 = file_get_contents($url8, false, $context8);
        /*echo $result8;*/
        $arr9 = json_decode($result8,true);
        if($arr9 != ''){
          /*echo "Vendor registered";*/
        }


}?>

<script>
/*function check(){
    document.getElementById("myform").submit();
}*/
</script>

<div id="loadingPleaseWait"><div>Loading, please wait...</div></div>

<h4>New Vendor Form</h4>

<?php 
if($name['status']==400){
 $message1="Username already exists";
}
if($arr9 != ''){
 $message2="Vendor registered";
}
?>
<h6 style="color:#F03F32;margin-left:41.8%;font-size:14px;"><?php echo $message1;?></h6>
<h6 style="color:#49AC4D;margin-left:43%;font-size:14px;"><?php echo $message2;?></h6>  


<form action="" method="post" id="myform">
            
          
            <input type="text" name="name" placeholder="Name" value="<?php echo $_POST['name'] ?>">
            <br><br>
            <select required="True" class="custom" name="city" style="">

<?php if($_POST['city'] != ''){

$url_city_name = 'https://efficient-brainy.herokuapp.com/get_city_from_id/id='.$_POST['city'].'/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options_city_name = array(
'http' => array(
/*'header'  => array(
        'USERNAME: '.$_POST['username'],
        'PASSWORD: '.$_POST['password'],
      ),*/
'method'  => 'GET',
),
);
$context_city_name= stream_context_create($options_city_name);
$output_city_name = file_get_contents($url_city_name, false,$context_city_name);
/*echo $output_city_name;*/
$arr_city_name = json_decode($output_city_name,true);
/*echo $arr_city_name[0]['name'];*/
/*$value1=$_POST['city1'];
$option1=$arr_city_name[0]['name'];*/


$value3=$_POST['city'];
$option3=$arr_city_name[0]['name'];
}else{
$value3='';
$option3="Select City";  
}?>
            <!-- <option value=<?php echo $city[$x]['id'] ?> ><?php echo $city[$x]['name'] ?></option> -->
                  <option value="<?php echo $value3; ?>"><?php echo $option3; ?></option>
                    <?php 
                      for ($x = 0; $x <= count($city); $x++) { ?>
                          <option value=<?php echo $city[$x]['id'] ?> ><?php echo $city[$x]['name'] ?></option>
                    <?php  } 
                    ?>
            </select>
            <br><br>
            <input type="email" name="email" placeholder="Email" value="<?php echo $_POST['email'] ?>">
            <br><br>
            <input type="text" placeholder="Mobile" pattern="[0-9]{12}" value="<?php echo $_POST['phone'] ?>" title="Phone number starting with country code and 12 digits" name="phone" required>
            <br><br>
            <input type="text" name="address" placeholder="Address" value="<?php echo $_POST['address'] ?>">
            <!-- <textarea rows="4" placeholder="Address" name="address" form="myform"></textarea> -->
            <br><br>
            
            <!-- <input type="text" placeholder="Address" name="address">
            <br><br> -->
            <input type="text" name="username" placeholder="Username" value="<?php echo $_POST['username'] ?>" required>
            <br><br>
            <input type="password" name="password" placeholder="Password" pattern="[A-Za-z0-9]{8,16}" title="Password must contain 8-16 digits" value="<?php echo $_POST['password'] ?>" required>
            <br><br>
            <!-- <input type="submit" value="Register"> -->

            <button onclick="show_wait_msg()" id="btn1" type="submit">Generate</button>
            
            
    </form>

<form action="manage_vendor.php" method="post">
             <button onclick="show_wait_msg()" id="btn2" type="submit">Cancel</button>
            
    </form>


<nav class="navbar navbar-default navbar-fixed-bottom" id="nav_bottom">
      <div class="container-fluid">
        <div class="navbar-header">
          
        </div>

        <div class="row" style="padding:9%;">
          <hr>
          <p style="font-size:8px; text-align:center; margin-top:0%;font-family:Lato-Light">2016 Efficient Brainy. All Rights Reserved.</p>
        </div>
      </div>
    </nav>
</body>
</html>
