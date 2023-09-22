<%@ page import="com.webkorps.friendBook.Model.RequestEntities" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Request List</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <%@ include file="Navbar.jsp" %>
</head>
<body>
    <h1>Request Senders:</h1>
  
    <%
        List<RequestEntities> totalRequest = (List<RequestEntities>) request.getAttribute("totalRequest");
        for (RequestEntities i : totalRequest) {
    %>
    <div class="request-item">
        <p><%=i.getSender()%></p> 
        <button class="btn btn-warning add-button" data-sender="<%=i.getSender()%>">Add</button>
        <button class="btn btn-dark remove-button" data-sender="<%=i.getSender()%>">Remove</button>
        <button class="btn btn-primary send-button" data-sender="<%=i.getSender()%>">Send Request</button>
        <div class="added-result"></div>
    </div>
    <%
        }
    %>

    <div id="added-result"></div>

    <script>$(document).ready(function () {

        // Using class selectors for click events
        $(".add-button").click(function () {
            var sender = $(this).data("sender");
            console.log(sender);
            var button = $(this);
            var requestItem = button.closest(".request-item");
            var addedResult = requestItem.find(".added-result"); // Find the result div in the same request-item
       
            $.ajax({
                type: "POST",
                url: "/request/accepted", // Replace with your controller URL
                data: { sender: sender }, // Send the sender data to the controller
                success: function (response) {
                    if (response === "added") {
                        // Handle success if needed
                    }
                    addedResult.append("<p>" + response + " request of " + sender + "</p>");
                }
            });
        });
    
        $(".remove-button").click(function () {
            var sender = $(this).data("sender");
            console.log(sender);
            var button = $(this);
            var requestItem = button.closest(".request-item");
            var addedResult = requestItem.find(".added-result"); 
       
            $.ajax({
                type: "POST",
                url: "/request/remove", 
                data: { sender: sender },
                success: function (response) {
                    if (response === "removed") {
                        requestItem.remove();
                    }
                    addedResult.append("<p>" + response + " request of " + sender + "</p>");
                }
            });
        });
    
        $(".send-button").click(function () {
            var sender = $(this).data("sender");
            console.log(sender);
            var button = $(this);
            var requestItem = button.closest(".request-item");
            var addedResult = requestItem.find(".added-result"); 
       
            $.ajax({
                type: "POST",
                url: "/request/sendRequestFromNotifications", 
                data: { sender: sender },
                success: function (response) {
                    if (response === "Send") {
                        console.log("rohit");
                   
                    }
                    addedResult.append("<p>" + response + " request of " + sender + "</p>");
                }
            });
        });
    
    });
    
     
    </script>
</body>
</html>
