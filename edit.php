<html>
<head>
<link rel="stylesheet" type="text/css" href="edit.css">
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
$url_profile = 'https://efficient-brainy.herokuapp.com/get_edit_profile/update/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
if($_POST['city'] != ''){
  $city2= $_POST['city_id'];
}
else{
  $city2= $_POST['city1'];
}
$options_profile = array(
  'http' => array(
    'header'  => array(
                  'PK: '.$_POST['user_id'],
                  'NAME: '.$_POST['name'],
                  'EMAIL: '.$_POST['email'],
                  'PHONE: '.$_POST['phone'],
                  'CITY: '.$city2,
                  'ADDRESS: '.$_POST['address'],
                ),
    'method'  => 'GET',
  ),
);
$context_profile = stream_context_create($options_profile);
$output_profile = file_get_contents($url_profile, false,$context_profile);
/*echo $output3;*/
$profile = json_decode($output_profile,true);


?>

<?php if($_POST['city1'] != ''){
  /*echo $_POST['city1'];*/

  $url_city_name = 'https://efficient-brainy.herokuapp.com/get_city_from_id/id='.$_POST['city1'].'/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
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
  $value1=$_POST['city1'];
  $option1=$arr_city_name[0]['name'];

}else{
  $value1=$_POST['city_id'];
  $option1=$_POST['city'];
}?>


<div id="loadingPleaseWait"><div>Loading, please wait...</div></div>

<h4>Edit Vendor Details</h4>

<?php 
if($profile['status']==200){
 $message2="Changes Saved";
}
?>
<h6 style="color:#49AC4D;margin-left:44%;font-size:14px;"><?php echo $message2;?></h6>  

<form action="" method="post" id="myform">
            <input type="hidden" readonly="true" name="user_id" value="<?php echo $_POST['user_id'] ?>">
            <br>
            <label>Name:</label>
            <input type="text" name="name" value="<?php echo $_POST['name']?>">
            <br><br>
            <label>City:</label>
            <select required="True" class="custom" name="city1" style="">

                <option value=<?php echo $value1; ?> ><?php echo $option1; ?></option>
                    <?php 
                      for ($x = 0; $x <= count($city); $x++) { ?>
                          <option value=<?php echo $city[$x]['id'] ?> ><?php echo $city[$x]['name'] ?></option>
                    <?php  } 
                    ?>
            </select>
            <br><br>
            <label>Email:</label>
            <input type="email" name="email" value="<?php echo $_POST['email']?>">
            <br><br>
            <label>Mobile:</label>
            <input type="text" pattern="[0-9]{12}" title="Phone number starting with country code and 12 digits" name="phone" value="<?php echo $_POST['phone']?>" required>
            <br><br>
            <label>Address:</label>
            <!-- <textarea rows="4"  name="address" form="myform" value="<?php echo $_POST['address']?>"></textarea>
            <br><br>  -->
            
            <input type="text" name="address" value="<?php echo $_POST['address']?>">
            <br><br>
            <label>Username:</label>
            <input type="text" readonly="true" name="username" value="<?php echo $_POST['username']?>" required>
            <br><br>
            <label>Password:</label>
            <input type="text" readonly="true" name="password" value="<?php echo $_POST['password']?>" required>
            <br><br>
            <!-- <input type="submit" value="Register"> -->

            <button id="btn1" type="submit">Save Changes</button>
           
            

            
            
    </form>


<form action="manage_vendor.php" method="post">
     <button onclick="show_wait_msg()" id="btn2" type="submit">Cancel</button>
</form>


<form action="manage_vendor.php" method="post">
            <input type="hidden" readonly="true" name="delete_user" value="<?php echo $_POST['user_id'] ?>">
            <button onclick="show_wait_msg()" id="btn3" >Delete</button>
</form>

<nav class="navbar navbar-default navbar-fixed-bottom" id="nav_bottom">
      <div class="container-fluid">
        <div class="navbar-header">
          
        </div>

        <div class="row" style="padding:7%;">
          <hr>
          <p style="font-size:8px; text-align:center; margin-top:0%;font-family:Lato-Light">2016 Efficient Brainy. All Rights Reserved.</p>
        </div>
      </div>
    </nav>
</body>
</html>