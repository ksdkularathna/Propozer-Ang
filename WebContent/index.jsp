<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Starting Page</title>
<script>
function validate(){
	var x = document.forms["myForm"]["email"].value;
    if (x == null || x == "") {
        alert("Email must be filled out");
        return false;
    }
	
	
	
}

</script>
</head>
<body>
    <form name="myForm" action="MailServlet" method="post" onsubmit="return validate();">     
  <h1>Its registration page</h1>
  <h1>E-Mail ID/User ID:<input type="email" name="email" id="email"> <br>  <br/>
   <h1> Name:<input type="text" name="name" value=""></br></h1>
   <input type="submit" value="Register">
    </form>
</body>
</html>