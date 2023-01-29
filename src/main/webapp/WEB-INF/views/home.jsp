<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="shortcut icon" href="canteenFav.svg" type="image/svg+xml" />
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
	integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/e8acaf88b1.js"
	crossorigin="anonymous"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="css/master.css">
</head>
<body>
	<section id="navbar">
		<nav class="navbar navbar-expand-lg navbar-dark"
			style="background: #ebd014">
			<a class="navbar-brand" href="#"><img src="images/logo.jpg"
				alt=""></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav mx-auto">
					<li class="nav-item active"><a class="nav-link" href="home"><i
							class="fa-solid fa-house"></i> Home</a></li>
					<li class="nav-item"><a class="nav-link" href="profile"><i
							class="fa-solid fa-user-tie"></i> <%
 String name = (String) session.getAttribute("customer_name");
 out.print(" " + name);
 %> </a></li>
					<li class="nav-item"><a class="nav-link" href="logout"><i
							class="fa-solid fa-right-from-bracket"></i> Logout</a></li>
				</ul>
			</div>
		</nav>
	</section>
	<section id="content">
		<div class="container">
			<%@ page import="java.util.List"%>
			<%@ page import="entity.MenuFoodItems"%>
			<%
			List<MenuFoodItems> menuList = (List<MenuFoodItems>) request.getAttribute("menu");
			int count = 0;
			for (int i = 0; i < menuList.size(); i++) {
				if (count % 3 == 0) {
					if (count != 0) {
				out.print("</div>");
					}
					out.print("<div class='row'>");
				}
			%>

			<div class="col-sm-6 col-md-6 col-lg-4">
				<div
					class="food-card bg-white rounded-lg overflow-hidden mb-4 shadow">
					
					<div class="food-card_content">
						<div class="food-card_title-section overflow-hidden">
							<h4 class="food-card_title">
								<a class="text-dark"><%=menuList.get(i).getDisplayName()%></a>
							</h4>
						</div>
						<div class="food-card_bottom-section">
							<div class="d-flex justify-content-between">
								
								

							</div>
							<hr>
							<div class="d-flex justify-content-between">
								<div class="food-card_price">
									 <span><span>&#8377;
											<%=menuList.get(i).getMrp()%></span></span>
								</div>
								<div class="food-card_order-count">
									<div class="input-group mb-3">
										<input type="hidden" id="menuId<%=i%>"
											value="<%=menuList.get(i).getMenuId()%>" name="menuId<%=i%>">
										<input id="menuQuantity<%=i%>" type="number"
											class="form-control input-manulator" placeholder=0
											name="menuQuantity<%=i%>" min=0 max=10>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>



			<%
			if (count == menuList.size() - 1) {
				out.print("</div>");
			}
			count++;

			}
			%>

		</div>
	</section>
	<div class="order-image-div">
		<img id="order-image" class="order-image" src="images/orderNow.jpg"
			alt="">
	</div>
</body>
<script type="text/javascript">
	var menuItems = 0;
	var c = 'novalue';
	<%out.print("menuItems = " + menuList.size() + ";");
if (request.getAttribute("status") != null) {
	out.print("c = '" + request.getAttribute("status") + "';");
}%>
$(document).ready(function() {
		if(c == 'Success'){			
			swal({ 
				title: "<%=request.getAttribute("status")%>",
				text: "<%=request.getAttribute("message")%>",
				 button: "Close",
				 icon: "success",
				});
		}else if(c == 'failure'){
			swal({
				  title: "<%=request.getAttribute("status")%>",
				  text: "<%=request.getAttribute("message")%>",
				icon : "error",
				button : "Close",
			});
		}
		
		$('#order-image').on("click",function(e) {
			const menuArray = [];
			console.log("Image clicked");
			var arrayIndex = 0;
			for(let i = 0; i < menuItems; i++){				
				if($('#menuQuantity' + i).val() > 0){
					const orderItems = {
							foodId : $('#menuId' + i).val(),
							foodQuantity : $('#menuQuantity' + i).val()
					};
					menuArray[arrayIndex] = orderItems;
					arrayIndex++;
				}				
			}
			if(menuArray.length > 0){
				const orderDetails = {
						totalItems : menuArray.length,
						foodItemsList : menuArray
				};
				console.log(orderDetails);
				const actionUrl = "http://localhost:8080/CanteenManagementSystem/order";
				$.ajax({
					url: actionUrl,
					method: "POST",
					data: JSON.stringify(orderDetails),
					dataType: 'json',
					contentType: "application/json",
					 success: function(result,status,jqXHR ){
						  console.log(result);
						  swal({ 
								title: status,
								text: result.message,
								 button: "Okay",
								 icon: "success",
								}).then(okay => {
								   if (okay) {
								    window.location.href = "<%=request.getContextPath()%>" + "/home";
								  }
								});
					}
				});
			}else {
				swal({ 
					title: "Error",
					text: "Add Some Items to Cart",
					 button: "Okay",
					 icon: "error",
					});
			}
		});
		
	});
</script>

</html>