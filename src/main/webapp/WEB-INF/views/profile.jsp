<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
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
	<%@ page import="entity.Customer"%>
	<%@ page import="entity.OrderDetails"%>
	<%@ page import="entity.OrderFoodItem"%>
	<%@ page import="java.time.LocalDateTime"%>
	<%@ page import="java.time.format.DateTimeFormatter"%>
		<%@ page import="java.util.List"%>
		<%
		Customer profile = (Customer) request.getAttribute("profile");
		List<OrderDetails> previousOrderList = profile.getPreviousOrders();
		%>
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<div class="osahan-account-page-left shadow-sm bg-white h-100">
						<div class="border-bottom p-4">
							<div class="osahan-user text-center">
								<div class="osahan-user-media">
									<h4 class="font-weight-bold mt-0 mb-4">Profile Info</h4>
									<img class="mb-3 rounded-pill shadow-sm mt-1"
										src="https://bootdey.com/img/Content/avatar/avatar1.png"
										alt="gurdeep singh osahan">
									<div class="osahan-user-media-body">
										<h6 class="mb-2"><%=profile.getCustomerName()%></h6>
										<p class="mb-1">+91<%=profile.getMobileNumber()%></p>
										<p><%=profile.getCustomerEmail()%></p>
										<p><%=profile.getAddress()%></p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-9">
					<div class="osahan-account-page-right shadow-sm bg-white p-4 h-100">
						<div class="tab-content" id="myTabContent">
							<div class="tab-pane  fade  active show" id="orders"
								role="tabpanel" aria-labelledby="orders-tab">
								<h4 class="font-weight-bold mt-0 mb-4">Past Orders</h4>
								<% for(OrderDetails order : previousOrderList) { 
									DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
							        String formatDateTime = order.getOrderTime().format(formatter);
								%>
								<div class="bg-white card mb-4 order-list shadow-sm">
									<div class="gold-members p-4">
										<a href="#"> </a>
										<div class="media">
											<div class="media-body">
												<a href="#"> <span class="float-right text-info">Delivered
														on <%=formatDateTime%><i
														class="icofont-check-circled text-success"></i>
												</span>
												</a>
												<h6 class="mb-2">
													<a href="#" class="text-black">ORDER #<%=order.getOrderId()%></a>
												</h6>
												<p class="text-dark">
												<% List<OrderFoodItem> foodItemsList = order.getFoodItemsList();
													for(OrderFoodItem food : foodItemsList){
												%>
												<span class="badge" style="background: grey"><%=food.getFoodName()%> x <%=food.getFoodQuantity()%>	</span>											
												<% } %>
												</p>
												<hr>
												<div class="float-right">
													<a class="btn btn-sm btn-primary" href="home"><i
														class="icofont-refresh"></i> REORDER</a>
												</div>
												<p class="mb-0 text-black text-primary pt-2">
													<span class="text-black font-weight-bold"> Total
														Paid:</span> &#8377;<%=order.getFinalAmount()%>
												</p>
											</div>
										</div>

									</div>
								</div>
								<% } %>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>


</html>