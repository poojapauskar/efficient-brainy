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
$url_profile = 'http://0.0.0.0:8000/get_edit_profile/update/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options_profile = array(
  'http' => array(
    'header'  => array(
                  'PK: '.$_POST['user_id'],
                  'NAME: '.$_POST['name'],
                  'EMAIL: '.$_POST['email'],
                  'PHONE: '.$_POST['phone'],
                  'CITY: '.$_POST['city1'],
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

  $url_city_name = 'http://0.0.0.0:8000/get_city_from_id/id='.$_POST['city1'].'/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
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

<form action="" method="post">
            <!-- <label>
              User_id
            </label> -->
            <input type="hidden" readonly="true" name="user_id" value="<?php echo $_POST['user_id'] ?>">
            <br>
      		  <label>
              Username
            </label>
            <input type="text" readonly="true" name="username" value="<?php echo $_POST['username']?>" required>
            <br>
            <label>
              Password
            </label>
            <input type="text" readonly="true" name="password" value="<?php echo $_POST['password']?>" required>
            <br>
            <label>
              Name
            </label>
            <input type="text" name="name" value="<?php echo $_POST['name']?>">
            <br>
            <label>
              Email
            </label>
            <input type="email" name="email" value="<?php echo $_POST['email']?>">
            <br>
            <label>
              Phone
            </label>
            <input type="text" pattern="[0-9]{12}" title="Phone number starting with country code and 12 digits" name="phone" value="<?php echo $_POST['phone']?>" required>
            <br>
            <label>
              City
            </label>
            <select required="True" class="custom" name="city1" style="">

                <option value=<?php echo $value1; ?> ><?php echo $option1; ?></option>
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
            <input type="text" name="address" value="<?php echo $_POST['address']?>">
            <br>
            <br>
            <input type="submit" value="Save Changes">
            
	  </form>



<form action="manage_vendor.php" method="post">
            <input type="hidden" readonly="true" name="delete_user" value="<?php echo $_POST['user_id'] ?>">
            <input type="submit" value="Delete Vendor">
            
    </form>

<form action="manage_vendor.php" method="post">
             <input type="submit" value="Cancel">
            
    </form>