<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Canteen Management System Login</title>
<link rel="shortcut icon" href="canteenFav.svg" type="image/svg+xml" />
<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">
	<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<div class="main">

		<!-- Sign  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
						<figure>
							<img src="images/logo.jpg" alt="logo image">
						</figure>
						<a href="register" class="signup-image-link">Not a customer yet? Register here..</a>
					</div>

					<div class="signin-form">
						<h2 class="form-title">Log in</h2>
						<form action="login" class="register-form" id="login-form"
							method="post">
							<div class="form-group">
								<label for="username"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="email" name="email" id="email"
									placeholder="Your Email"  />
							</div>
							<div class="form-group">
								<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="password" id="password"
									placeholder="Password"  />
							</div>
							<div class="form-group form-button">
								<input type="button" name="signin" id="signin"
									class="form-submit" value="Log in" />
							</div>
						</form>
						
					</div>
				</div>
			</div>
		</section>

	</div>

	<!-- JS -->
	<script src="js/main.js"></script>
	<script type="text/javascript">
	var c = 'novalue';
	<%if (request.getAttribute("status") != null) {
	out.print("c = '" + request.getAttribute("status") + "';");
	}%>
	$(document).ready(function() {
		if(c == 'failure'){
			swal({
				  title: "<%=request.getAttribute("status")%>",
				  text: "<%=request.getAttribute("message")%>",
					icon : "error",
					button : "Close",
				});
			}
		 $('#signin').click(function(e) {
			    e.preventDefault();
			    var isFormValidated = true;
			    var email = $('#email').val();
			    var password = $('#password').val();
			   
			    $(".error").remove();

			    
			    if (email.length < 1) {
			    	isFormValidated = false;
			      $('#email').after('<span class="error" style="color: red; font-size:12px;">This field is required</span>');
			    }
			    if (password.length < 1) {
			    	isFormValidated = false;
			      $('#password').after('<span class="error" style="color: red; font-size:12px;">This field is required</span>');
			    }
			    if(isFormValidated){
			    	$('#login-form').submit();
			    }
			    
			  });
	});
	</script>
</body>
</html>