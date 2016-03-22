

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="bootstrap1.css">
  <script src="jquery1.js"></script>
  <script src="jquery2.js"></script>

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

  <link rel="stylesheet" type="text/css" href="admin_page.css">
</head>
<body onload="hide_wait_msg()">

<?php

$url_check = 'https://efficient-brainy.herokuapp.com/update_logged_in/check/?access_token=QIw10aWGHb2kchy1huq5o3CyJ88kR9';
$options_check = array(
  'http' => array(
    /*'header'  => array(
                  'LOGGED-IN: 1',
                ),*/
    'method'  => 'GET',
  ),
);
$context_check = stream_context_create($options_check);
$output_check = file_get_contents($url_check, false,$context_check);
/*echo $output_check;*/
$arr_check = json_decode($output_check,true);
/*echo $arr_check;*/

if($arr_check['status'] == 400){
      echo "<script>location='index.php'</script>";
}else{
	  /*echo "<script>location='index.php'</script>"; */ 
}


?>

	<div class="container-fluid"><!-- MAIN CONTAINER Begins here -->
		
		<nav class="navbar navbar-default navbar-fixed-top" id="nav_top">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="admin_page.php" style="text-decoration:none;">
						<img src="images/Brainy_Logo.jpg" id="logo" class="img-responsive">
					</a>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#" id="hello_user">Hello User,</a></li>
					<li>
					<a href="logout.php" id="log_out">Log Out</a>
					</li>
				</ul>
			</div>
		</nav>

		<div class="row" style="padding:5%;">
			<h4>Admin Console</h4>
		</div>

<div id="loadingPleaseWait"><div>Loading, please wait...</div></div>


		<div class="row" style="padding:10%;">
			<div class="col-sm-2"></div>
			<div class="col-sm-3">
				<form action="generate_otp.php">
				    <button onclick="show_wait_msg()" type="submit" class="btn btn-md round">Generate OTP</button>
				</form>
				
			</div>
			<div class="col-sm-3">
				<form action="manage_vendor.php">
				    <button onclick="show_wait_msg()" type="submit" class="btn btn-md round">Manage Vendor</button>
				</form>
			</div>
			<div class="col-sm-3">
				<form action="manage_city.php">
				    <button onclick="show_wait_msg()" type="submit" class="btn btn-md round">Manage City</button>
				</form>	
			</div>
			<div class="col-sm-1"></div>
		</div>




	

		<nav class="navbar navbar-default navbar-fixed-bottom" id="nav_bottom">
			<div class="container-fluid">
				<div class="navbar-header">
					
				</div>

				<div class="row" style="padding:1%;">
					<hr>
					<p style="font-size:8px; text-align:center; margin-top:-1%;font-family:Lato-Light">2016 Efficient Brainy. All Rights Reserved.</p>
				</div>
			</div>
		</nav>

	</div><!-- MAIN CONTAINER Ends here-->
</body>
</html>