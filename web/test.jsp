<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:p="http://primefaces.org/ui">
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <title>Insert title here</title>
   <script src="https://apis.google.com/js/platform.js" async defer></script>
   <meta name="google-signin-client_id" content="672705305193-vt46md0rceq4s61om3f6n21voofvae5p.apps.googleusercontent.com">
</head>
<body>
   <div class="g-signin2" data-onsuccess="onSignIn" id="myP"></div>
      <img id="myImg"><br>
      <p id="name"></p>
      <p id="ident"></p>
      <div id="status">
   </div>
   <script type="text/javascript">
        function onSignIn(googleUser) {
            var profile = googleUser.getBasicProfile();
            var ident = profile.getId();
            var imagurl=profile.getImageUrl();
            var name=profile.getName();
            var email=profile.getEmail();
            document.getElementById("myImg").src = imagurl;
            document.getElementById("name").innerHTML = name;
            document.getElementById("myP").style.visibility = "hidden";
            document.getElementById("status").innerHTML = 'Welcome '+name+'!<br>'+
                    '<form action="faces/googleRejestracja.xhtml" method="post">'+
                    '<input type="hidden" value ="' + name + '" name="name">'+
                    '<input type="hidden" value ="' + ident + '" name="ident">'+
                    '<input type="hidden" value ="' + email + '" name="email">'+
                    '<button type="submit">Idz dalej</button></form>';
        }
    </script>
      <button onclick="myFunction()">Sign Out</button>
   <script>
      function myFunction() {
      gapi.auth2.getAuthInstance().disconnect();
      location.reload();
   }
   </script>
</body>
</html>