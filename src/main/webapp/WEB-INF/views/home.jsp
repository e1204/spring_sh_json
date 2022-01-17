<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready( () => {				
				$.ajax({
	                type : "get",
	                url : "${pageContext.request.contextPath}/sample/getSample",
	                success : function(data){
						console.log(data);
						$("#hello").text(data);
	                },
	                error : (xhr, textStatus, errorThrown) => {
	                    console.log(xhr);
	                    console.log(textStatus);
	                    console.log(errorThrown)
	                }
	            });				
		});
	</script>
</head>
<body>
hello world!
	<div id="hello"></div>
</body>
</html>