<html>
<head>
<link rel="stylesheet" type="text/css" href="manage_vendor.css">
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
<body onload="hide_wait_msg()">



<?php if($_POST['delete_user'] != ''){
	$url_delete = 'https://efficient-brainy.herokuapp.com/delete_user/id='.$_POST['delete_user'].'?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
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
$url3 = 'https://efficient-brainy.herokuapp.com/get_edit_profile/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
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

<nav class="navbar navbar-default navbar-fixed-top" id="nav_top">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="admin_page.php" style="text-decoration:none;">
            <img src="images/Brainy_Logo.jpg" id="logo" class="img-responsive">
          </a>
        </div>
      </div>
    </nav>


<div id="loadingPleaseWait"><div>Loading, please wait...</div></div>


<h4>Manage Vendor</h4>

<form action="add_vendor.php">
   <!--  <input type="submit" value="Add vendor"> -->
    <button onclick="show_wait_msg()" type="submit">Add New Vendor</button>
</form>



<table>
  <tr>
    <!-- <th>City Id</th> -->
    <th>Vendor Name</th>
    <th>City</th>
    <th>Email</th>
    <th>Contact</th>
    <th>Action</th>
  </tr>

<?php 
for ($x = 0; $x < count($arr3); $x++) { ?>
<?php
$url_city_name = 'https://efficient-brainy.herokuapp.com/get_city_from_id/id='.$arr3[$x]['city_id'].'/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options_city_name = array(
  'http' => array(
    'method'  => 'GET',
  ),
);
$context_city_name= stream_context_create($options_city_name);
$output_city_name = file_get_contents($url_city_name, false,$context_city_name);
/*echo $output_city_name;*/
$arr_city_name = json_decode($output_city_name,true);
?>
<form action="edit.php" method="post" id="form1">
  <tr>
  
    <td><?php echo $arr3[$x]['name'] ?></td>
    <td><?php echo $arr_city_name[0]['name']; ?></td>
    <td><?php echo $arr3[$x]['email'] ?></td>
    <td><?php echo $arr3[$x]['phone'] ?></td> 
  
    
    <!-- <td><input type="submit" value="edit"></td> -->
    <td><button onclick="show_wait_msg()" id="edit" type="submit">edit</button></td>
  </tr>


<input type="hidden" readonly="true" name="name" value=<?php echo $arr3[$x]['name'] ?>>
<input type="hidden" readonly="true" name="email" value=<?php echo $arr3[$x]['email'] ?>>
<input type="hidden" readonly="true" name="phone" value=<?php echo $arr3[$x]['phone'] ?>> 
<input type="hidden" readonly="true" name="city" value=<?php echo $arr_city_name[0]['name']; ?>>
<input type="hidden" readonly="true" name="user_id" value=<?php echo $arr3[$x]['pk'] ?>>
<input type="hidden" readonly="true" name="username" value=<?php echo $arr3[$x]['username'] ?>>
<input type="hidden" readonly="true" name="password" value=<?php echo $arr3[$x]['password'] ?>>
<input type="hidden" readonly="true" name="address" value=<?php echo $arr3[$x]['address'] ?>>
<input type="hidden" readonly="true" name="city_id" value=<?php echo $arr3[$x]['city_id']; ?>>


</form>
<br><br><br>
<?php  } 
?>
  
</table>

<nav class="navbar navbar-default navbar-fixed-bottom" id="nav_bottom">
      <div class="container-fluid">
        <div class="navbar-header">
          
        </div>

        <div class="row" style="padding:1%;">
          <hr>
          <p style="font-size:8px; text-align:center; margin-top:1%;font-family:Lato-Light">2016 Efficient Brainy. All Rights Reserved.</p>
        </div>
      </div>
    </nav>

</body>
</html>



