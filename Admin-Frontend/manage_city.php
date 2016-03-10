<html>
<head>
<style>
table, th, td {
    border: 1px solid black ;
    border-collapse: collapse;
    font-size:17px;

}
th, td {
    padding: 7px;
}
th{
  font-family: OpenSans-Semibold;
  text-align:left;
}
td{
  font-family: OpenSans-Regular;
  text-align:left;
}
</style>
<body>

<?php if($_POST['delete_city'] != ''){
  $url_delete = 'http://0.0.0.0:8000/delete_city/id='.$_POST['delete_city'].'?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
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
$url3 = 'http://0.0.0.0:8000/get_edit_city/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
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



<form action="add_city.php">
    <input type="submit" value="Add city">
</form>



<table style="">
  <tr>
    <!-- <th>City Id</th> -->
    <th>City</th>
    <th>Pincode</th>
    <th></th>
  </tr>
<?php 
for ($x = 0; $x < count($arr3); $x++) { ?>
<form action="edit_city.php" method="post">
  <tr>
    <td><?php echo $arr3[$x]['name'] ?></td>
    <td><?php echo $arr3[$x]['pin_code'] ?></td> 
    <td><input type="submit" value="edit"></td>
  </tr>

<input type="hidden" readonly="true" name="city" value=<?php echo $arr3[$x]['name'] ?>>
<input type="hidden" readonly="true" name="pin_code" value=<?php echo $arr3[$x]['pin_code'] ?>>
<input type="hidden" readonly="true" name="city_id" value=<?php echo $arr3[$x]['id'] ?>>


</form>
<br><br><br>
<?php  } 
?>
</table>

</body>
</html>


