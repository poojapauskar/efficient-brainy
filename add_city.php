<html>
<body>
<head>
<link rel="stylesheet" type="text/css" href="add_city.css">
<link rel="stylesheet" type="text/css" href="navigation_footer.css">
<style>

</style>
</head>

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

if($_POST['city'] != '' && $_POST['pin_code'] != ''){


       /* echo "hi";*/

        $url8 = 'https://efficient-brainy.herokuapp.com/city/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
        $data8 = array('name' => $_POST['city'],'pin_code' => $_POST['pin_code']);
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
          /*echo "City added";*/
        }


}?>

<?php 
if($arr9 != ''){
  $message2="City Added";       /*echo "City added";*/
        }
?>

<h4>Add New City</h4>

<h6 style="color:#49AC4D;margin-left:43%;font-size:14px;"><?php echo $message2;?></h6>  

<form action="" method="post" id="myform">
            
            <input type="text" name="city" placeholder="City" value="<?php echo $_POST['city'] ?>" required>
            <br><br>
            
            <input type="text" name="pin_code" value="<?php echo $_POST['pin_code'] ?>" placeholder="Pin Code" pattern="[0-9]{6}" title="Pincode should contain 6 digits. Only numbers allowed." required>
            <br><br>
            
            <button id="btn1" type="submit">Generate</button>
           
            
    </form>


<form action="manage_city.php" method="post">
              <button id="btn2" type="submit">Cancel</button>
            
    </form>

<nav class="navbar navbar-default navbar-fixed-bottom" id="nav_bottom">
      <div class="container-fluid">
        <div class="navbar-header">
          
        </div>

        <div class="row" style="padding:23%;">
          <hr>
          <p style="font-size:8px; text-align:center; margin-top:0%;font-family:Lato-Light">2016 Efficient Brainy. All Rights Reserved.</p>
        </div>
      </div>
    </nav>
</body>
</html>