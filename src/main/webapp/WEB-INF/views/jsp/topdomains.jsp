<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Domain Counter Demo</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<h2>Top Domains</h2>
<br/>
<table id="domainTable" class="table">
	<thead>
		<tr>			
			<th>id</th>
			<th>domain name</th>
			<th>count</th>
		</tr>
		<c:forEach var="domain" items="${domains}">
			<tr>
			<td>${domain.id}</td>
			<td>${domain.name}</td>
			<td>${domain.count}</td>
			</tr>
		</c:forEach>
		
	</thead>
</table>
</body>
</html>