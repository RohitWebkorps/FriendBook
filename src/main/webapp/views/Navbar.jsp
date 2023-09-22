<%@ page import="com.webkorps.friendBook.ServiceImpl.UserServiceImpl"%>
<%@ page import="javax.servlet.http.HttpSession"%>;
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<%
HttpSession session1=request.getSession();
if (session1==null)
{
response.sendRedirect("/users");
} 
%>

        <%
        Integer count=(Integer)request.getAttribute("notification");
        %>


        <style>
            #upload-button {
                    display: none;
                }
          </style>
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
        
 function checkNotifications() {
    // Send an AJAX request to a server endpoint
    jq.ajax({
                url: '/request/notification',
        method: 'Post',
        dataType: 'json',
        success: function (data) {
          
            updateHomePage(data);
        },
        error: function (error) {
            console.error('Error checking for notifications: ' + error);
        }
    });

}
function updateHomePage(data) {
    const parsedData = JSON.parse(data);
    const notificationText = parsedData.notification; 
    document.getElementById("if").innerHTML = data;


}

setInterval(checkNotifications, 500); 

        
                
        </script>

       
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">${user.getUserName()}</a>
      
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
           
          <li class="nav-item">
            <a class="nav-link" href="/commons/homes">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/users/profile">Profile</a>
          </li>

          <li><!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
            Post
            </button>
            <li>
              <a type="button" class="nav-link"  href="/users/logout" >Logout</a> 
            </li>
            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Create Post</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <form enctype="multipart/form-data" action="/Users/createPost" method="Post">
                  <div class="modal-body" >
                   
                      <input type="file" name="Postimg" required>
                      
                    
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Post</button>
                  </div>
                </form>
                </div>
              </div>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">${count}</a>
          </li>
            <li class="nav-item">
                <a class="nav-link" href="/users/seeNotification">you have <span id="if"></span> Notifications</a>
              </li>
              
         </ul>
         <form class="d-flex" action="/users/searchList"  method="Post">
            <input class="form-control me-2" type="text" placeholder="Search" aria-label="Search" name="searchedPersonName">
          
            <input class="btn btn-outline-success" type="submit" value="search">
          </form>
          
      </div>
    </div>
  </nav>
  </head>
  <body></body>
  </html>