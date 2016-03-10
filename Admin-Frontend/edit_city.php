
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

<form action="" method="post">
            <label>
              City ID
            </label>
            <input type="text" readonly="true" name="city_id" value="<?php echo $_POST['city_id'] ?>">
            <br>
      		  <label>
              City
            </label>
            <input type="text" name="city" value="<?php echo $_POST['city']?>">
            <br>
            <label>
              Pincode
            </label>
            <input type="text" name="pin_code" value="<?php echo $_POST['pin_code']?>">
            <br>
            
            <input type="submit" value="Save Changes">
            
	  </form>



<form action="manage_city.php" method="post">
            <input type="hidden" readonly="true" name="delete_city" value="<?php echo $_POST['city_id'] ?>">
            <input type="submit" value="Delete City">
            
    </form>

<form action="manage_city.php" method="post">
             <input type="submit" value="Cancel">
            
    </form>