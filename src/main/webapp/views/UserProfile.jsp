<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 <%@ page import="com.webkorps.friendBook.Model.Post"%>
<%@ page import="com.webkorps.friendBook.Service.UserService"%>
<%@ page import="java.util.List" %>
<%@ page import="com.webkorps.friendBook.Model.Comments"%>
<html>

<head>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
  
<script>

 var jq = $.noConflict(); 
    
         function fileSelected() {
            const fileInput = document.getElementById('file-input');
            const uploadButton = document.getElementById('upload-button');

            if (fileInput.files.length > 0) {
                uploadButton.style.display = 'block';
            } else {
                uploadButton.style.display = 'none';
            }
        }

        

        
</script>
<style>
    #upload-button {
            display: none;
        }
.container {
    display: flex;
    justify-content: center; 
    align-items: center; 
   margin-top: 50px;
  
}

th {
            margin: 10px;
            padding: 10px; /* Adding padding for spacing inside the headers */
        }

        /* Apply margin to table rows */
        tr {
            margin: 5px;
        }

        /* Apply some additional styling for demonstration purposes */
        table {
            
            width: 100%;

        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
            margin:auto;
        }
        input{
          margin:10px;
          
        }
        #field{
          
          width:100%;
          
        }
        #upload-button {
            display: none;
        }
.content {
    text-align: center; /* Center text horizontally */
    padding:60px;
}
#container{
  align-content: center;
}
#container{
  width:100%;
  
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
    width: 100%; /* Each box takes up 50% of the container width to make them side by side */
     /* Float the boxes left so they sit next to each other */
    box-sizing: border-box; /* Include padding and border in the box's total width */
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

  
</style>
<%@ include file="Navbar.jsp" %>
</head>
<body>
   <h1>Your Profile</h1>
  
<table>
<tr>
 <td> <h1>${user.getNoOfPost()}</h1> </td><td> <h1>${followers}</h1></td><td><h1>${following}</h1></td>
</tr>
<tr>
<td><h4>Post</h4></td><td><h4>Followers</h4></td><td><h4>Following</h4></td>
 </tr>
</table>


<div class="outerbox">
  <div class="innerbox">
    <div class="card" style="width: 18rem;">
      <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrU0Gde07VczRe-cziTPsSJbb_eqVlK1BgRQ&usqp=CAU" class="card-img-top" alt="can't display">
      <div class="card-body">
        
          <h3>${user.userName}</h3>
       
      <img  src="${user.getProfilePic()}" >
    
    <form action="/users/upload" method="post"  enctype="multipart/form-data">
          <label>Upload Image</label><br><input type="file" name="profileImage">
          <input type="hidden" value="${user.getUserName()}" name="userName">
          <input type="submit" class="btn btn-primary" value="Update">

        </form> </div>
    </div>





    
    <div class="innerbox">
      <form action="/users/updateDetails" method="Post">
  <table id="container">
      <tr>  <th colspan="2"><h3>My Favourite</h3></th></tr>
      <tr>
        <td><label>Favourite Songs </label></td><td><input id="field" type="text" name="favSong" value="${user.getFavSong()}"></td>
        <tr>
          <tr>
         <td><label>Favourite Book</label></td><td><input id="field" type="text" name="favBook" value="${user.getFavBook()}"></td>
         </tr>
    <tr>
    <td><label>Favourite place</label></td><td><input id="field" type="text" name="favPlace" value="${user.getFavPlace()}"></td>
   </tr>
   <tr>
     <input type="hidden" name="userName" value="${user.userName}">
    </tr>
    
<tr>
  <td><input class="btn btn-primary" type="submit" value="Update"></td>
  </tr>
  </form>
    </table>
    </div>
</div>

 </center>

  

 <center>
  <% List<Post> posts = (List<Post>) request.getAttribute("post");
    for (Post post : posts) {
 %>
 <div id="outer">
   <div class="request-item">
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQrU0Gde07VczRe-cziTPsSJbb_eqVlK1BgRQ&usqp=CAU"> 
 
    <p id="LikesShow<%=post.getPostId()%>"   data-post-id="<%=post.getPostId()%>"><%= post.getLikes()%> Likes</p>
     <!-- Like button -->
     <form id="likeform<%=post.getPostId()%>">
       <input type="hidden" name="PostId" value="<%= post.getPostId() %>">
       <button type="button" class="btn btn-primary like-button" data-post-id="<%=post.getPostId()%>">Like</button>
     </form>
    
     <!-- Comment button and form -->
     <button class="btn btn-info comment-button" type="button">Comment</button>
     <form id="commentform<%=post.getPostId()%>" class="comment-form" style="display: none;"  data-post-id="<%=post.getPostId()%>">
      
        <input type="text" id="commentField<%=post.getPostId()%>" placeholder="Enter your comment" name="comment">
       <p>(<%=post.getPostId()%>)</p>
        <input type="hidden" name="PersonName" value="<%=post.getUserName()%>">
        <input type="hidden" name="postId" value="<%=post.getPostId()%>">
        <input type="hidden" name="commentedBy" value="${user.getUserName()}">
      
        <input type="submit" id="sendbutton<%=post.getPostId()%>"  value="Send" class="btn btn-dark" >
  
<div >See Comments</div>  
<div >
  
  Commented By : ${user.getUserName()}<p id="showcommenthere<%=post.getPostId()%>"></p>
  
  <%
  List<Comments> comments = (List<Comments>) request.getAttribute("comments");
  for(Comments currentComment : comments) {
  if(currentComment.getPostId()==post.getPostId())
  {
%>
       Commented By : <%=currentComment.getCommentedBy()%>
<br>
         <%=currentComment.getComments()%><br>

  
  <%
  }
}
%>
     </div>
   </div>
     </form>
 <script>
 

 </script>
     
    
 <% } %>
 
  </center>
</div>
</div>
<br><br><br><br>
<script>
  var requestButton=document.getElementById("submiButton");

  $(document).ready(function () {
    // Iterate through all elements with class 'like-button' to check like status for each one
    $('.like-button').each(function () {
        var myButton = $(this);
        var postId = myButton.data('post-id'); // Get the post ID from the data attribute

        $.ajax({
            type: 'GET',
            url: '/likes/checkLikeStatus',
            data: {
                postId: postId,
            },
            success: function (response) {
                if (response == "Liked") {
                    myButton.text('Liked');
                } else {
                    myButton.text('Like');
                }
            },
            error: function (error) {
                console.error('Error checking like status: ' + error);
            },
        });
    });





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












  });
  
$(document).ready(function() {
  $(".like-button").click(function() {
    var $button = $(this);
    var postId = $button.data("post-id");
    
    if ($button.text() === "Like") {
      // Handle liking logic

      console.log("rohit");
         
          var $likesCountElement = $("#LikesShow" + postId);
console.log($likesCountElement);

var currentLikes = parseInt($likesCountElement.text());
var newLikes = currentLikes + 1;
$likesCountElement.text(newLikes + " Likes");

      $.ajax({
        type: "POST",
        url: "/likes/addLikes",
        data: { PostId: postId },
        success: function(response) {
          console.log("Post liked successfully");
          $button.text("Liked");
         
        },
        error: function(xhr, status, error) {
          console.error("Error liking the post: " + error);
        }
      });
    } else {
     
         var $likesCountElement = $("#LikesShow" + postId);
console.log($likesCountElement);

var currentLikes = parseInt($likesCountElement.text());
var newLikes = currentLikes - 1;
$likesCountElement.text(newLikes + " Likes");

      // Handle unliking logic
      $.ajax({
        type: "POST",
        url: "/likes/removeLikes",
        data: { PostId: postId },
        success: function(response) {
          console.log("Post unliked successfully");
          $button.text("Like");
          // Update the like count in the UI if needed
        },
        error: function(xhr, status, error) {
          console.error("Error unliking the post: " + error);
        }
      });
    }
  });
});
$(document).ready(function() {
  $(".comment-button").click(function() {
    var $form = $(this).siblings(".comment-form");
    $form.slideToggle("slow");
  });
});



$(document).ready(function() {
    $(".comment-form").submit(function(e) {
        e.preventDefault();
        var $form = $(this);
        var formData = $form.serialize();
        var postId = $form.data("post-id"); // Get the postId from the data attribute
console.log(postId);
        $.ajax({
            type: "POST",
            url: "/comments/addComment",
            data: formData,
            success: function(response) {
                console.log("Comment submitted successfully");
                console.log(postId);
                var currentComment=document.getElementById("commentField"+postId).value;
        console.log("commenting");
        document.getElementById("showcommenthere"+postId).innerHTML+="<br>"+currentComment;
    },
            error: function(xhr, status, error) {
                console.error("Error submitting comment: " + error);
            }
        });
    });
});








</script>
</body>
</html>