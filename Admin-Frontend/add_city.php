<html>
<body>
<head>
<link rel="stylesheet" type="text/css" href="new_city_form.css">
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

if($_POST['city'] != '' && $_POST['pin_code'] != ''){


       /* echo "hi";*/

        $url8 = 'http://0.0.0.0:8000/city/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
        $data8 = array('name' => $_POST['city'],'pin_code' => $_POST['pin_code']);
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
          echo "City added";
        }


}?>



<h4>Add New City</h4>

<form action="" method="post" id="myform">
            
            <select required="True" class="custom" name="city" style="">

                <option value=<?php echo $value1; ?> ><?php echo $option1; ?></option>
                    <?php 
                      for ($x = 0; $x <= count($city); $x++) { ?>
                          <option value=<?php echo $city[$x]['id'] ?> ><?php echo $city[$x]['name'] ?></option>
                    <?php  } 
                    ?>
            </select>
            <br><br>
            
            <input type="text" name="pin_code" placeholder="Pin Code" pattern="[0-9]{6}" title="Pincode should contain 6 digits. Only numbers allowed." required>
            <br><br>
            
            <button id="btn1" type="submit">Generate</button>
           
            
    </form>


<form action="manage_city.php" method="post">
              <button id="btn2" type="submit">Cancel</button>
            
    </form>

</body>
</html>