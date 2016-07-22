<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Domain Counter Demo</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<h2>Domain Counter</h2>
The domain <b><i>${domain.name}</red></i></b> has been seen <b><i>${domain.count}</red></i></b> times.
</body>
</html>