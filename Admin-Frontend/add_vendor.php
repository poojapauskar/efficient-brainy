<html>
<body>
<head>
<link rel="stylesheet" type="text/css" href="add_vendor.css">
<style>

</style>
</head>

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
$url_name = 'http://0.0.0.0:8000/check_username/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
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
 echo  "Username already exists";
}else{
  /*echo "Valid username";*/
}



?>

<?php 

if($_POST['username'] != '' && $name['status']==200){


       /* echo "hi";*/

        $url8 = 'http://0.0.0.0:8000/register/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
        $data8 = array('token_generated' => '','is_admin' => '','username' => $_POST['username'],'password' => $_POST['password'],'city_id' => $_POST['city'],'phone' => $_POST['phone'], 'name' => $_POST['name'],'email' => $_POST['email'], 'address' =>$_POST['address']);
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
          echo "Vendor registered";
        }


}?>

<script>
/*function check(){
    document.getElementById("myform").submit();
}*/
</script>

<h4>New Vendor Form</h4>

<form action="" method="post" id="myform">
            
          
            <input type="text" name="name" placeholder="Name" value="<?php echo $_POST['name'] ?>">
            <br><br>
            <select required="True" class="custom" name="city" style="">
                  <option value="">Select City</option>
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

            <button id="btn1" type="submit">Generate</button>
            
            
    </form>

<form action="manage_vendor.php" method="post">
             <button id="btn2" type="submit">Cancel</button>
            
    </form>

</body>
</html>
