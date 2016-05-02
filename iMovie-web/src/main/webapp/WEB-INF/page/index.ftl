<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<#list pageinfo.list as test>  
id : ${test.id}
name : ${test.name}  <br/>  
</#list> 
${pageinfo.total}
</body>
</html>