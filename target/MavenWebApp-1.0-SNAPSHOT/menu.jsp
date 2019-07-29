<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="TAG" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
        <TAG:BootstrapCSS />
        <TAG:CSS />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col">
                    <h1 style="text-align: center">Bem-vindo!</h1>
                    <p style="text-align: center">
                        Escolha uma das opções à seguir.
                    </p>
                    <div class="row menu-row">
                        <div class="col-3">
                            <h2>Novo</h2>
                            <p>
                                Inserir um novo usuário.
                            </p>
                        </div>
                        <div class="col-3">
                            <h2>Listar</h2>
                            <p>
                                Listar <b>TODAS</b> as informações dos usuários.
                            </p>
                        </div>
                        <div class="col-3">
                            <h2>Editar</h2>
                            <p>
                                Lista com informações resumidas sobre os usuários e opção para editá-los.
                            </p>
                        </div>
                        <div class="col-3">
                            <h2>Excluir</h2>
                            <p>
                                Lista com informações resumidas sobre os usuários e opção para excluí-los.
                            </p>
                        </div>                        
                    </div>

                    <div class="row menu-row">
                        <div class="col-3">
                            <a class="btn btn-outline-dark btn-sm" href="./CreateUser">Create</a>
                        </div>
                        <div class="col-3">
                            <a class="btn btn-outline-dark btn-sm" href="./ReadUser">Read</a>
                        </div>
                        <div class="col-3">
                            <a class="btn btn-outline-dark btn-sm" href="./UpdateUser">Update</a>
                        </div>
                        <div class="col-3">
                            <a class="btn btn-outline-dark btn-sm" href="./DeleteUser">Delete</a>
                        </div>
                    </div>                    

                </div>
            </div>
        </div>

    </body>
</html>
