<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Customer Registration</title>
<link rel="shortcut icon" href="canteenFav.svg" type="image/svg+xml" />
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">
<!-- Font Icon -->
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://kit.fontawesome.com/e8acaf88b1.js"
	crossorigin="anonymous"></script>
<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Registration</h2>

						<form action="register" class="register-form" id="register-form"
							method="post">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name" placeholder="Your Name" />

							</div>

							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email" placeholder="Your Email" />
							</div>
							<div class="form-group">
								<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="password" id="password"
									placeholder="Password" />
							</div>
							<div class="form-group">
								<label for="address"><i class="zmdi zmdi-home"></i></label>
								<textarea class="form-control" aria-label="With textarea"
									id="address" rows=1 placeholder="Address" name="address"></textarea>
							</div>
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-phone"></i></label> <input
									type="text" name="contact" id="contact"
									placeholder="Contact no" />
							</div>
							<div class="form-group form-button">
								<input type="button" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/logo.jpg" alt="Register image">
						</figure>
						<a href="login" class="signup-image-link">I am already customer</a>
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
		if(c == 'success'){			
			swal({ 
				title: "<%=request.getAttribute("message")%>",
				text: "Login to Order",
				 button: "Continue to Login",
				 icon: "success",
				 type: "success"}).then(okay => {
				   if (okay) {
				    window.location.href = "<%=request.getContextPath()%>" + "/login";
				  }
				});
		}else if(c == 'failure'){
			swal({
				  title: "<%=request.getAttribute("status")%>",
				  text: "<%=request.getAttribute("message")%>",
				  icon: "error",
				  button: "Close",
				});
		}
		
		 $('#signup').click(function(e) {
			    e.preventDefault();
			    var isFormValidated = true;
			    var first_name = $('#name').val();
			    var email = $('#email').val();
			    var password = $('#password').val();
			    var contact = $('#contact').val();
				var address = $('#address').val();
			    $(".error").remove();

			    if (first_name.length < 1) {
			    	isFormValidated = false;
			      $('#name').after('<span class="error" style="color: red; font-size:12px;">This field is required</span>');
			    }
			    if (email.length < 1) {
			    	isFormValidated = false;
			      $('#email').after('<span class="error" style="color: red; font-size:12px;">This field is required</span>');
			    }
			    if (password.length < 8) {
			    	isFormValidated = false;
			      $('#password').after('<span class="error" style="color: red; font-size:12px;">Password must be at least 8 characters long</span>');
			    }
			    if (address.length < 1) {
			    	isFormValidated = false;
				      $('#address').after('<span class="error" style="color: red; font-size:12px;">Address is required');
				    }
			    if (contact.length < 10) {
			    		isFormValidated = false;
				      $('#contact').after('<span class="error" style="color: red; font-size:12px;">Enter a valid Mobile Number of 10 digits</span>');
				  	} else if(contact.length == 10){
				      var regEx = /^[6789][0-9]{9}/;
				      var validContact = regEx.test(contact);
				      if (!validContact) {
				    	  isFormValidated = false;
				        $('#contact').after('<span class="error" style="color: red; font-size:12px;">Enter a valid Mobile Number of 10 digits</span>');
				      }
				    } else if(contact.length > 10){
				    	isFormValidated = false;
					      $('#contact').after('<span class="error" style="color: red; font-size:12px;">Enter a valid Mobile Number of 10 digits</span>');
				    }
			    if(isFormValidated){
			    	$('#register-form').submit();
			    }
			  });
		 

		
	});
	
</script>

</body>
</html>