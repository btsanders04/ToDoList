<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">ToDo!</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					
					<%
						if ((Boolean) session.getAttribute("loggedIn")) {
					%>
					<li><a href="displayList">MyList</a></li>
					
					<%
						}
					%>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<%
						if ((Boolean) session.getAttribute("loggedIn")) {
					%>
					<li><a href="index.jsp?logOut=true"><span
							class="glyphicon glyphicon-log-in"></span> Sign Out</a></li>
					<li><a href="EditProfile"><span
							class="glyphicon glyphicon-user"></span> Edit Profile</a></li>
					<%
						} else {
					%>
					<li><a href="SignUp"><span
							class="glyphicon glyphicon-user"></span> Sign Up</a></li>

					<li><a href="SignIn"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
					<%
						}
					%>

				</ul>
			</div>
		</div>
	</nav>