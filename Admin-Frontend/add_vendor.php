

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


<form action="" method="post" id="myform">
      		  <label>
              Username
            </label>
            <input type="text" name="username" value="<?php echo $_POST['username'] ?>" required>
            <br>
            <label>
              Password
            </label>
            <input type="password" name="password" pattern="[A-Za-z0-9]{8,16}" title="Password must contain 8-16 digits" value="<?php echo $_POST['password'] ?>" required>
            <br>
            <label>
              Name
            </label>
            <input type="text" name="name" value="<?php echo $_POST['name'] ?>">
            <br>
            <label>
              Email
            </label>
            <input type="email" name="email" value="<?php echo $_POST['email'] ?>">
            <br>
            <label>
              Phone
            </label>
            <input type="text" pattern="[0-9]{12}" value="<?php echo $_POST['phone'] ?>" title="Phone number starting with country code and 12 digits" name="phone" required>
            <br>
            <label>
              City
            </label>
            <select required="True" class="custom" name="city" style="">
                  <option value="">Select City</option>
                    <?php 
                      for ($x = 0; $x <= count($city); $x++) { ?>
                          <option value=<?php echo $city[$x]['id'] ?> ><?php echo $city[$x]['name'] ?></option>
                    <?php  } 
                    ?>
            </select>
            <br>
            <label>
              Address
            </label>
            <input type="text" name="address" value="<?php echo $_POST['address'] ?>">
            <br>

            <input type="submit" value="Register">
            
	  </form>


<form action="manage_vendor.php" method="post">
             <input type="submit" value="Cancel">
            
    </form>