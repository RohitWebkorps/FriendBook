<%@ page import="com.webkorps.friendBook.Model.Post"%>
<%@ page import="com.webkorps.friendBook.Service.UserService"%>
<%@ page import="java.util.List" %>
<%@ page import="com.webkorps.friendBook.Model.Comments"%>
<html>

<head>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>  
  <% Boolean requested = (Boolean) request.getAttribute("requested"); %>
  <%
  UserService userService=new UserService();
  
  String buttonText = requested ? "Cancel Request" : "Send Request";
  %>
<script>
    var jq = $.noConflict(); 
    
    $(document).ready(function() {
      $("#submiButton").click(function() {

      
            var formData = jq("#myForm").serialize(); 
            var  buttonValue=document.getElementById("submitButton").value;

             if(buttonValue==="Send Request")
             {
            jQuery.ajax({
                type: "POST",
                url: "/request/sendRequest",
                data: formData,
                success: function(response) {
        
                },
                error: function(error) {
                  
                }
            });
          }else{
            jQuery.ajax({
                type: "POST",
                url: "/request/deleteRequest",
                data: formData,
                success: function(response) {
            
                },
                error: function(error) {
                  
                }
            });
          }
        });
      });
   

   


</script>
<style>
#container{
  width:60%;
  border: solid 2px;
}
th,td{
  border: solid 2px;
  padding:10px;
  font-size: 30px;
}
.outerbox {
    width: 100%;
    overflow: hidden; 
  align-items: center;
  }
  .innerbox{
    width: 100%; 
    float: left; 
    box-sizing: border-box; 
    padding: 20px;
    display: flex;
  }
  .request-item{
    width:40%;
  }
  #outer{
    width:100%;
    align-items: center;
  }
  #commentField{
    display:none;
  }
  #sendButton{
    display:none;
  }

  #panel, #flip {  
    padding: 5px;  
    text-align: center;  
    background-color: #00FFFF;  
    border: solid 1px #c3c3c3;  
}  
#panel {  
    padding: 50px;  
    display:none;  
} 
 
</style>


<%@ include file="Navbar.jsp" %>
</head>
<body>

<center>
  <div class="outerbox">
    <div class="innerbox">
      <div class="card" style="width: 18rem;">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrU0Gde07VczRe-cziTPsSJbb_eqVlK1BgRQ&usqp=CAU" class="card-img-top" alt="can't display">
        <div class="card-body">
          <form  id="myForm" method="post">
            <h3>${searchedPerson.getUserName()}</h3>
            <input type="hidden" name="reciever" value="${searchedPerson.getUserName()}">
            <input type="hidden" name="sender" value="${user.getUserName()}">
            <input type="hidden" name="searchedPersonName" value="${searchedPerson.getUserName()}">
            <input type="hidden" id="submitButton" class="btn btn-warning" value="<%=buttonText%>">
            <button type="submit" id="submiButton" class="btn btn-primary">send request</button>
          </form> </div>
      </div>
      
      <div class="innerbox">
    <table id="container">
        <tr>  <th colspan="2"><h3>My Favourite</h3></th></tr>
        <tr>
          <td><label>Favourite Songs </label></td><td><p>${searchedPerson.getFavSong()}</p></td>
          <tr>
            <tr>
           <td><label>Favourite Book</label></td><td><p>${searchedPerson.getFavBook()}</p></td>
           </tr>
      <tr>
      <td><label>Favourite place</label></td><td><p>${searchedPerson.getFavPlace()}</p></td>
      
      </tr>
      </table>
      </div>
</div>
 </center>
 <table id="container">
  <tr>
   <td> <h1>${searchedPerson.getNoOfPost()}</h1> </td><td> <h1>${followers}</h1></td><td><h1>${following}</h1></td>
  </tr>
  <tr>
  <td><h4>Post</h4></td><td><h4>Followers</h4></td><td><h4>Following</h4></td>
   </tr>
  </table>
  

<center>

  <%
  List<Post> post = (List<Post>) request.getAttribute("post");
  for (Post i : post) {
%>
<div id="outer">
<div class="request-item">
  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrU0Gde07VczRe-cziTPsSJbb_eqVlK1BgRQ&usqp=CAU"> 
 
 <p id="LikesShow"></p>
  <div class="input-group mb-3">
    <form id="likeform">
      <input type="hidden" id="postIdInput" name="PostId" value="<%= i.getPostId() %>">
      
      <button type="submit" id="likeButton" class="btn btn-primary" >Like</button>
  </form>
 
    <button class="btn btn-info" type="button" onclick="Displayfield()">Comment</button>
    <form id="commentform" method="post">
      <input type="text" id="commentField" placeholder="Enter your comment" name="comment">
      <input type="hidden" name="PersonName" value="<%=i.getUserName()%>">
      <input type="hidden" name="postId" value="<%=i.getPostId()%>">
      <input type="hidden" name="commentedBy" value="${user.getUserName()}">
    
      <input type="submit" id="sendbutton" style="display: none;" value="Send" class="btn btn-dark" onclick="appendComment()">
  </form>
 <script>
var like_status= <%=i.getLikes()%>;
var id=<%=i.getPostId() %>;
 </script>
</div>
<div id="flip">See Comments</div>  
<div id="panel">
  Commented By : ${user.getUserName()}<p id="showcommenthere"></p>
  
  <%
  List<Comments> comments = (List<Comments>) request.getAttribute("comments");
  for (Comments currentComment : comments) {
  
%>
Commented By : <%=currentComment.getCommentedBy()%>
<br>
<%=currentComment.getComments()%><br>

  
  <%

}
%>
</div>
  <%
  }
%>
  </center>
</div>
</div>
<script>
 var likes= <%= i.getLikes()%> ;
  //Requestform text change according to requested/send request/added
var requestButton=document.getElementById("submiButton");

  $.ajax({
        type: "GET",
        url: "/request/checkFriendshipStatus",
        data: {
            searchedpersonName:"${searchedPerson.getUserName()}", 
        },
        success: function (response) {
            if (response === "added") {
                console.log("1");
              requestButton.textContent = "added";
            }
             else if(response === "requested"){
              console.log("2");
              requestButton.textContent = "requested";
            
            }else if(response === "notFriend"){
              console.log("3");
              requestButton.textContent = "send request";
            }
        },
        error: function (error) {
            console.error("Error checking FriendshipStatus status: " + error);
        },
    });





    var myVariable=like_status;
var myButton=document.getElementById("likeButton");

$.ajax({
        type: "GET",
        url: "/Users/checkLikeStatus",
        data: {
            postId: id, 
        },
        success: function (response) {
            if (response === "Liked") {
                
                myButton.textContent = "Liked";
            } else {
               
            }
        },
        error: function (error) {
            console.error("Error checking like status: " + error);
        },
    });


$(document).ready(function() {
$("#likeform").submit(function(e) {
    e.preventDefault(); 

        
         if(myButton.textContent=="Like")
         {
          myVariable++;
          document.getElementById("LikesShow").innerHTML = myVariable+" Likes";
          myButton.textContent="Liked";
          
          var formData = $("#likeform").serialize();
          console.log("rohit");
          $.ajax({
            type: "POST",
            url: "/Users/addLikes", 
            data:formData, 
            success: function (response) {
             
                console.log("Post liked successfully");
            },
            error: function (xhr, status, error) {
                
                console.error("Error liking the post: " + error);
            }
        });
        
        }
        
        else{
          
          myVariable--;
          document.getElementById("LikesShow").innerHTML = myVariable+" Likes";
          myButton.textContent="Like";
          var formData = $("#likeform").serialize();
          $.ajax({
            type: "POST",
            url: "/Users/removeLikes",
            data:formData, 
            success: function (response) {
                
                console.log("Post liked successfully");
            },
            error: function (xhr, status, error) {
              
                console.error("Error liking the post: " + error);
            }
        });
         }
      });
      })

      $(document).ready(function() {
        $("#commentform").submit(function(e) {
            e.preventDefault(); 

            var formData = $(this).serialize();

     
            $.ajax({
                type: "POST",
                url: "/Users/addComment",
                data: formData,
                success: function(response) {
                    console.log("Form submitted successfully");
                },
                error: function(xhr, status, error) {
                    
                    console.error("Error submitting form: " + error);
                }
            });
        });
       
    });
  
    function Displayfield() {
        document.getElementById("commentField").style.display = 'block';
      }
  function toggleSubmitButton() {
      var commentField = document.getElementById("commentField");
      var submitButton = document.getElementById("sendbutton");

      if (commentField.value.trim() !== "") {
        submitButton.style.display = 'block';
      } else {
        submitButton.style.display = 'none';
      }
    }
    document.addEventListener("DOMContentLoaded", function () {
      document.getElementById("commentField").addEventListener("input", toggleSubmitButton);
    
      toggleSubmitButton();
    });

    function DisplayCommentField() {
      document.getElementById("sendbutton").innerHTML="sended";
    }

      function appendComment()
      {
        var currentComment=document.getElementById("commentField").value;
        console.log("commenting");
        document.getElementById("showcommenthere").innerHTML+="<br>"+currentComment;
      }

      $(document).ready(function(){  
    $("#flip").click(function(){  
        $("#panel").slideToggle("slow");  
    });  

});  
</script>

</body>
</html>