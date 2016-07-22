<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>
<html>
<head>
	<title>JQuery Datatable + Spring MVC Example</title>
</head>
<body>
<div>
	<img class="dataTableExample" src="resources/images/JQueryDatatableandSpringMVC.png">
</div>
<table id="example" class="display" cellspacing="0" width="100%" style="overflow-x:auto">
				<thead>
					<tr>
						<th>Name</th>
						<th>Desgination</th>
						<th>Salary</th>
						<th>Country</th>
					</tr>
				</thead>
			</table>
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	var data =eval('${employeeList}');
	var table = $('#example').DataTable( {
		  "aaData": data,
		  "aoColumns": [
		    { "mData": "name"},
		    { "mData": "desgination"},
		    { "mData": "salary"},
		    { "mData": "country"}
		  ],
		  "paging":false
		});
});
</script>