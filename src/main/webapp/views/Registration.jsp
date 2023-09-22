	<!DOCTYPE html>
<html>
<head>
    <title>Registration Page</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            function validatePassword() {
                var password = document.getElementById("password").value;
                var specialSymbols = /[!@#$%^&*]/;
    
                if (password.length < 8) {
                    alert("Password must be at least 8 characters long.");
                    return false;
                }
    
                if (!specialSymbols.test(password)) {
                    alert("Password must contain at least one special symbol.");
                    return false;
                }
               if(validateCaptcha())
                return true;
            return false;
            }
        </script>
    </head>
<body>
  <center>  <h1>Registration Page</h1>
  <table>
    <form action="/users"  method="POST" onsubmit="return validatePassword() " >
      <tr>
	  <td>  <label for="fullName">Full Name:</label></td>
       <td> <input type="text" id="fullName" name="fullName" required></td>
        </tr>
        <tr>
       <td> <label for="email">Email:</label></td>
        <td><input type="email" id="email" name="email" required></td>
        </tr>
        <tr>
        <td><label for="password">Password:</label></td>
        <td><input type="password" id="password" name="password" required></td>
        </tr>
        <tr>
			<td><div id="simple-captcha"></div></td>
        <td><input type="text" id="captcha-input" placeholder="Enter CAPTCHA" required></td>
        </tr>
       <tr> <td><input type="submit" value="Register"  class="btn btn-primary"></td><td><button onclick="window.open('/users')" class="btn btn-warning" > Login </button>  </td></tr>
    </form>
    </table>
</center>
	 <script>
        
        function generateCaptcha() {
            const length = 5;
            const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
            let captcha = '';

            for (let i = 0; i < length; i++) {
                const randomIndex = Math.floor(Math.random() * characters.length);
                captcha += characters.charAt(randomIndex);
            }

            return captcha;
        }

  
        function displayCaptcha() {
            const captchaDiv = document.getElementById('simple-captcha');
            const captcha = generateCaptcha();
            captchaDiv.textContent = captcha; 
            captchaDiv.setAttribute('data-captcha', captcha);
        }

 
        function validateCaptcha() {
            const enteredCaptcha = document.getElementById('captcha-input').value;
            const displayedCaptcha = document.getElementById('simple-captcha').getAttribute('data-captcha');

            if (enteredCaptcha === displayedCaptcha) {
                return true; 
            } else {
                alert('CAPTCHA is incorrect. Please try again.');
                displayCaptcha(); 
                document.getElementById('captcha-input').value = ''; 
                return false; 
            }
        }

     
        window.onload = function () {
            displayCaptcha();
        };
    </script>
</body>
</html>