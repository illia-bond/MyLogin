<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="./login" method="POST">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="${username}"><br>
            <label for="password">Password:</label> 
            <input type="password" id="pass" name="pass" value=""><br>
            <input type="submit" name="submit" value="Log in">
        </form>
        <p>${message}</p>
    </body>
</html>
