<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<jsp:include page="NavBar.jsp" />

	<div class="container-fluid">
		<h2>Add ToDo Item</h2>

		<form class="form-inline" role="form" method="Post"
			action="displayList">
			<div class="form-group" col-sm-2>
				<div class="col-xs-2">
					<input type="text" class="form-control" id="desc" name="desc"
						placeholder="Enter Description">
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-2">
					<label for="dueDate">Date due:</label>
				</div>
				<div class="col-xs-2">
					<input type="date" class="form-control" id="dueDate" name="dueDate">
				</div>
			</div>
			<div class="form-group">
			<label for="priorityRadio">Priority:</label> <label
				class="radio-inline" name="priorityRadio" id="priorityRadio">
				<input type="radio" name="priority" value = "1">Low
			</label> <label class="radio-inline"> <input type="radio"
				name="priority" value = "2">Medium
			</label> <label class="radio-inline"> <input type="radio"
				name="priority" value= "3">High
			</label>
			</div>
			<div class="form-group">
				<div class="col-md-2">
					<label for="Status">Select Status:</label>
				</div>
				<div class="col-xs-2">
					<select class="form-control" id="status" name="status">
						<option value= "1">1</option>
						<option value= "2">2</option>
						<option value= "3">3</option>
					</select>

				</div>
			</div>
			<div class="form-group" col-sm-1>
				<div class="col-xs-2">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
			</div>
		</form>
	</div>
	
	${display}
</body>
</html>