<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.webkorps.friendBook.Model.Post"%>
<%@ page import="com.webkorps.friendBook.Service.UserService"%>
<%@ page import="java.util.List" %>
<%@ page import="com.webkorps.friendBook.Model.Comments"%>
<html>

<head>
 
<%@ include file="Navbar.jsp" %>

</head>
<body>
    
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
        console.log("rohit");
           
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