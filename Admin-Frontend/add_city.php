

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




<form action="" method="post">
      		  <label>
              City 
            </label>
            <input type="text" name="city" required>
            <br>
            <label>
              Pincode
            </label>
            <input type="text" name="pin_code" pattern="[0-9]{6}" title="Pincode should contain 6 digits. Only numbers allowed." required>
            <br>
            <input type="submit" value="Add">
            
	  </form>


<form action="manage_city.php" method="post">
             <input type="submit" value="Cancel">
            
    </form>