<!-- http://localhost/foodromeo/sign-up.php -->

<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="bootstrap1.css">

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
  <script src="jquery1.js"></script>
  <script src="jquery2.js"></script>

  <link rel="stylesheet" type="text/css" href="admin_login.css">
 

<script type="text/javascript">
  
/*document.getElementById("myNumber").defaultValue = "16";*/
</script>
</head>
<body onload="hide_wait_msg()">


<?php
if($_POST['username'] != '' && $_POST['password'] != ''){
$url2 = 'https://efficient-brainy.herokuapp.com/is_admin_login/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options2 = array(
  'http' => array(
    'header'  => array(
                  'USERNAME: '.$_POST['username'],
                  'PASSWORD: '.$_POST['password'],
                ),
    'method'  => 'GET',
  ),
);
$context2 = stream_context_create($options2);
$output2 = file_get_contents($url2, false,$context2);
/*echo $output2;*/
$arr2 = json_decode($output2,true);
if($arr2['status']==200){


                   
                      $url_logged = 'https://efficient-brainy.herokuapp.com/update_logged_in/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
                      $options_logged = array(
                        'http' => array(
                          'header'  => array(
                                        'LOGGED-IN: 1',
                                      ),
                          'method'  => 'GET',
                        ),
                      );
                      $context_logged = stream_context_create($options_logged);
                      $output_logged = file_get_contents($url_logged, false,$context_logged);
                      /*echo $output2;*/
                      $arr_logged = json_decode($output_logged,true);
                     

  /*echo "Admin Logged In";*/
  /*header('Location: admin_page.php');*/
  echo "<script>location='admin_page.php'</script>";
}else{
  /*echo "Invalid admin credentials";*/
}

}?>




<div class="container-fluid"><!-- MAIN CONTAINER Begins -->
    
    <nav class="navbar navbar-default navbar-fixed-top" id="nav_top">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="#" style="text-decoration:none;">
            <img src="images/Brainy_Logo.jpg" id="logo" class="img-responsive">
          </a>
        </div>
      </div>
    </nav>

<div id="loadingPleaseWait"><div><h6>Loading, please wait...</h6></div></div>

<?php if($arr2['status']==400 || $arr['status'] == 401){
          $error="Invalid Admin Credentials";
}?>


    <div class="row">
      <div class="col-sm-6" id="image"></div>
      <div class="col-sm-5" style="background-color:; padding:12%;">
        <p id="form_title">Admin Console</p>

<h6 style="color:#F03F32;margin-left:22%"><?php echo $error;?></h6>
 
        <form role="form" action="" method="post">
          <div class="form-group">
            <input type="text" name="username" placeholder="Username" class="form-control" id="name" required/><br>
            <input type="password" name="password" placeholder="Password" class="form-control" id="pwd" required>
          </div>
          
          <button onclick="show_wait_msg()" type="submit" class="btn btn-md round">LOG IN</button>
        </form>

      </div>
    </div>

    <div class="row" style="padding:5%;"></div>

    <nav class="navbar navbar-default navbar-fixed-bottom" id="nav_bottom">
      <div class="container-fluid">
        <div class="navbar-header">
          
        </div>

        <div class="row" style="padding:1%;">
          <hr>
          <p style="font-size:8px; text-align:center; margin-top:-1%;font-family:Lato-Light">2016 Efficient Brainy. All Rights Reserved.</p>
        </div>
      </div>
    </nav>

  </div>      
</div>
</body>
</html>



