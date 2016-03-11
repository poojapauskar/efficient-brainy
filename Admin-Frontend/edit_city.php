<html>
<head>
<link rel="stylesheet" type="text/css" href="edit_city.css">
<link rel="stylesheet" type="text/css" href="navigation_footer.css">
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top" id="nav_top">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="#" style="text-decoration:none;">
            <img src="images/Brainy_Logo.jpg" id="logo" class="img-responsive">
          </a>
        </div>
      </div>
    </nav>

<?php
$url_city = 'http://0.0.0.0:8000/get_edit_city/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
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
$url_profile = 'http://0.0.0.0:8000/get_edit_city/update/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options_profile = array(
  'http' => array(
    'header'  => array(
                  'ID: '.$_POST['city_id'],
                  'NAME: '.$_POST['city'],
                  'PIN: '.$_POST['pin_code'],
                ),
    'method'  => 'GET',
  ),
);
$context_profile = stream_context_create($options_profile);
$output_profile = file_get_contents($url_profile, false,$context_profile);
echo $output3;
$profile = json_decode($output_profile,true);


?>

<h4>Edit City Details</h4>

<?php 
if($profile['status']==200){
 $message2="Changes Saved";
}
?>
<h6 style="color:#49AC4D;margin-left:43%;font-size:14px;"><?php echo $message2;?></h6>  


<form action="" method="post" id="myform">
            <input type="hidden" readonly="true" name="city_id" value="<?php echo $_POST['city_id'] ?>">
            <br>
            <label>City:</label>
            <input type="text" name="city" value="<?php echo $_POST['city']?>">
            <br><br>
            <label>Pincode:</label>
            <input type="text" name="pin_code" pattern="[0-9]{6}" title="Pincode should contain 6 digits. Only numbers allowed." value="<?php echo $_POST['pin_code']?>">
            <br><br>
            

            <button id="btn1" type="submit">Save Changes</button>
            
            
</form>


            

<form action="manage_city.php" method="post">
            <input type="hidden" readonly="true" name="delete_city" value="<?php echo $_POST['city_id'] ?>">
            <button type="submit" id="btn3" >Delete</button>
            
    </form>

<form action="manage_city.php" method="post">
            <button id="btn2" type="submit" >Cancel</button>
            
</form>

<nav class="navbar navbar-default navbar-fixed-bottom" id="nav_bottom">
      <div class="container-fluid">
        <div class="navbar-header">
          
        </div>

        <div class="row" style="padding:1%;">
          <hr>
          <p style="font-size:8px; text-align:center; margin-top:0%;font-family:Lato-Light">2016 Efficient Brainy. All Rights Reserved.</p>
        </div>
      </div>
    </nav>
</body>
</html>
