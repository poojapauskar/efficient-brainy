<html>
<head>
<link rel="stylesheet" type="text/css" href="manage_city.css">
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


<?php if($_POST['delete_city'] != ''){
  $url_delete = 'https://efficient-brainy.herokuapp.com/delete_city/id='.$_POST['delete_city'].'?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
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
$url3 = 'https://efficient-brainy.herokuapp.com/get_edit_city/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
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

<h4>Manage City</h4>

<form action="add_city.php">
<button onclick="show_wait_msg()" type="submit" >Add New City</button>
</form>

<table>
  <tr>
    <!-- <th>City Id</th> -->
    <th>City</th>
    <th>Pincode</th>
    <th>Action</th>
  </tr>
<?php 
for ($x = 0; $x < count($arr3); $x++) { ?>
<form action="edit_city.php" method="post">
  <tr>
    <td><?php echo $arr3[$x]['name'] ?></td>
    <td><?php echo $arr3[$x]['pin_code'] ?></td> 
    <td><button onclick="show_wait_msg()" id="edit" type="submit">edit</button></td>
  </tr>

<input type="hidden" readonly="true" name="city" value=<?php echo $arr3[$x]['name'] ?>>
<input type="hidden" readonly="true" name="pin_code" value=<?php echo $arr3[$x]['pin_code'] ?>>
<input type="hidden" readonly="true" name="city_id" value=<?php echo $arr3[$x]['id'] ?>>


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


