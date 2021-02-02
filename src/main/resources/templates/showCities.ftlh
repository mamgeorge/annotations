<!DOCTYPE html>
<html><head><title>city list</title>
<meta charset = "UTF-8">
<meta name = "viewport" content = "width=device-width, initial-scale=1.0">
<link rel = "stylesheet" href = "style.css">
</head>

<body><center><h3>List of Cities</h3>

<table border = "0">
<tr>
	<th>id</th>
	<th>name</th>
	<th>population</th>
	<th>date</th>
	</tr>

<#assign datetimer = .now>
<#list cities as city>
<tr>
	<td>${ city.id }</td>
	<td>${ city.name }</td>
	<td>${ city.population }</td>
	<td>${ datetimer?datetime?iso_local }</td>
	</tr>
</#list>
</table>

<br/><a href = "/basics">return</a>

</center></body>
</html>
