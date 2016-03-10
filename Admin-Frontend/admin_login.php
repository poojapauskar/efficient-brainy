<!-- http://localhost/foodromeo/sign-up.php -->

<html>
<head>
<script type="text/javascript">
  
/*document.getElementById("myNumber").defaultValue = "16";*/
</script>
</head>
<body>


<?php
if($_POST['username'] != '' && $_POST['password'] != ''){
$url2 = 'http://0.0.0.0:8000/is_admin_login/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
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
  echo "Admin Logged In";
  header('Location: admin_page.php');
}else{
  echo "Invalid admin credentials";
}

}?>

<div class="form">
      
        <div id="login">   
          <h1>Admin login</h1>
          
          <form action="" method="post">
          
            <div class="field-wrap">
            <label>
              Username<span class="req">*</span>
            </label>
            <input type="text" name="username" required/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password" name="password" required/>
          </div>
          
          <!-- <p class="forgot"><a href="http://localhost/foodromeo/forgot-password.php">Forgot Password?</a></p>
           -->
          <button class="" style="" type="submit" id="">Log In</button>
          
          </form>

        </div>
        
      </div>
      
</div>
</body>
</html>



