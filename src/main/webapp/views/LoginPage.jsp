<html>
    <head>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
        
    </head>
<body>
    <%
String error1=request.getParameter("error1");
    %>
    <center>
   <form action="/users/authenticate" method="post">     
<table>
    <tr><th><h1>Login Page</h1></th></tr>
<tr>
    <td>User Name </td><td><input type="text" name="userName" required></td>
</tr>
<tr>
    <td>Password</td><td><input type="password" name="password" required></td>
</tr>
<tr>
    <td><input class="btn btn-primary" type="submit" value="Login"></td><td><button class="btn btn-warning"  onclick="window.open('/users/register')">Register</button></td>
</tr>
<tr>
<%  if(error1!=null){ %>
   <td colspan="2"> <%=error1%></td>
 <%
} %>
</tr>
</table>
</form>
</center>
</body>
</html>