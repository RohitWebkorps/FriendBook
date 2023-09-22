<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Profile Picture</title>
</head>
<body>
    <h1>Upload Profile Picture</h1>
    
    <!-- Display an error message if there's an issue with the upload -->
    <c:if test="${param.error != null}">
        <p style="color: red;">Error uploading the profile picture. Please try again.</p>
    </c:if>
    
    <!-- Display a success message if the upload is successful -->
    <c:if test="${param.updated != null}">
        <p style="color: green;">Profile picture updated successfully!</p>
    </c:if>
    
    <!-- Form for uploading the profile picture -->
    <form action="/register/upload" method="post" enctype="multipart/form-data">
        <label for="profileImage">Choose a profile picture:</label>
        <input type="file" name="profileImage" id="profileImage" accept="image/*" required>
        <br>
        <input type="submit" value="Upload">
    </form>
</body>
</html>
