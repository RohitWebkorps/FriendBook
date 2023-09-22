<%@ page import="java.util.List" %>
<%@ page import="com.webkorps.friendBook.Model.User"%>
<html>
    <head>
        <%@ include file="Navbar.jsp" %>
    </head>
    <body>
    
    <h2> ${error1}</h2>
        
        <ul class="list-group">
    <%
    List<User> searchedPerson = (List<User>) request.getAttribute("searchedPerson");
      if(searchedPerson==null ||searchedPerson.isEmpty() )
      {%>
      <%}
      else{
    for (User i : searchedPerson) {
    %>
      <div class="list-group-item">
       <form action="searched" method="post">
        <input type="hidden" value="<%=i.getUserName()%>" name="searchedPersonName">
        <li class="list-group-item"><%= i.getUserName()%> <input type="submit" value="Search" class="btn btn-primary"/></li>  
       
       </form>  
       </div>
  <%
    }
  }
  %>
</ul>

  
</body>
</html>