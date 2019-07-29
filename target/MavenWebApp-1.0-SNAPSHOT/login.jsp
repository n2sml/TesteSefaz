<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="TAG" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <TAG:BootstrapCSS />
        <TAG:CSS />
    </head>
    <body>        
        <div class="container">
            <div class="row">
                <div class="col-3"></div>
                <div class="col">
                    <h1>Login</h1>
                    <form action="./LoginUser" method="POST">
                        Nome:<br>
                        <input class="form-control form-control-sm" type="text" name="nome" value="" required>
                        <br>
                        Senha:<br>
                        <input class="form-control form-control-sm" type="password" name="senha" value="" required>
                        <br><br>
                        <input class="btn btn-primary btn-sm" type="submit" value="Submit">
                    </form>
                </div>
                <div class="col-3"></div>
            </div>
        </div>
    </body>
</html>
