<?php if($_POST['delete_user'] != ''){
	$url_delete = 'http://0.0.0.0:8000/delete_user/id='.$_POST['delete_user'].'?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
	$options_delete = array(
	  'http' => array(
	    /*'header'  => array(
	                  'USERNAME: '.$_POST['username'],
	                  'PASSWORD: '.$_POST['password'],
	                ),*/
	    'method'  => 'GET',
	  ),
	);
	$context_delete = stream_context_create($options_delete);
	$output_delete = file_get_contents($url_delete, false,$context_delete);
	/*echo $output3;*/
	$arr_delete = json_decode($output_delete,true);

}?>

<?php
$url3 = 'http://0.0.0.0:8000/get_edit_profile/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options3 = array(
  'http' => array(
    /*'header'  => array(
                  'USERNAME: '.$_POST['username'],
                  'PASSWORD: '.$_POST['password'],
                ),*/
    'method'  => 'GET',
  ),
);
$context3 = stream_context_create($options3);
$output3 = file_get_contents($url3, false,$context3);
/*echo $output3;*/
$arr3 = json_decode($output3,true);


?>



<form action="add_vendor.php">
    <input type="submit" value="Add vendor">
</form>





<?php 
  for ($x = 0; $x < count($arr3); $x++) { ?>

  		
      <form action="edit.php" method="post">
      		<label>
              User_id
            </label>
            <input type="text" readonly="true" name="user_id" value=<?php echo $arr3[$x]['pk'] ?>>
            <br>
            <label>
              Username
            </label>
            <input type="text" readonly="true" name="username" value=<?php echo $arr3[$x]['username'] ?>>
            <br>
            <label>
              Password
            </label>
            <input type="text" readonly="true" name="password" value=<?php echo $arr3[$x]['password'] ?>>
            <br>
            <label>
              Name
            </label>
            <input type="text" readonly="true" name="name" value=<?php echo $arr3[$x]['name'] ?>>
            <br>
            <label>
              Email
            </label>
            <input type="text" readonly="true" name="email" value=<?php echo $arr3[$x]['email'] ?>>
            <br>
            <label>
              Phone
            </label>
            <input type="text" readonly="true" name="phone" value=<?php echo $arr3[$x]['phone'] ?>>
            <br>
            <label>
              Address
            </label>
            <input type="text" readonly="true" name="address" value=<?php echo $arr3[$x]['address'] ?>>
            <br>
<?php
$url_city_name = 'http://0.0.0.0:8000/get_city_from_id/id='.$arr3[$x]['city_id'].'/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
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


?>			
			
			<label>
              City Id
            </label>
            <input type="text" readonly="true" name="city_id" value=<?php echo $arr3[$x]['city_id']; ?>>
            <br>

            <label>
              City
            </label>
            <input type="text" readonly="true" name="city" value=<?php echo $arr_city_name[0]['name']; ?>>
            <br>

            <input type="submit" value="edit">
            
	  </form>
	  <br><br><br>
<?php  } 
?>
