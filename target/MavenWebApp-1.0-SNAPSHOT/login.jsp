<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="./LoginUser" method="POST">
            Nome:<br>
            <input type="text" name="nome" value="">
            <br>
            Senha:<br>
            <input type="text" name="senha" value="">
            <br><br>
            <input type="submit" value="Submit">
        </form> 

    </body>
</html>
