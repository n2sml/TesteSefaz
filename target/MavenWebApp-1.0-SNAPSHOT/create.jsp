<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="TAG" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Criar Usuário</title>
        <TAG:BootstrapCSS />
        <TAG:CSS />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-3"></div>
                <div class="col">
                    <h1>Criar Usuário</h1>
                    <div id="form-div">
                        <form action="./CreateUser" method="post" id="form">
                            <label>Nome</label>
                            <input class="form-control form-control-sm" type="text" name="nome" value="" required>
                            <br>        
                            <label>Email</label>
                            <input class="form-control form-control-sm" type="email" name="email" value="" required>
                            <br>
                            <label>Senha</label>
                            <input class="form-control form-control-sm" type="password" name="senha" value="" required>
                            <br>

                            <div id="contact-init">
                                <label>DDD</label>
                                <input class="form-control form-control-sm" type="number" name="ddd" value="" required>
                                <br>
                                <label>Numero</label>
                                <input class="form-control form-control-sm" type="text" name="numero" value="" required>
                                <br>
                                <label>Tipo</label>
                                <input class="form-control form-control-sm" type="text" name="tipo" value="" required>
                                <br>
                            </div>    
                            <div id="more-contacts-area"></div>

                            <input class="btn btn-primary btn-sm" type="submit" value="Submit">
                        </form>    

                    </div>
                    <button id="more-button" style="display:none">+</button>

                    <button id="less-button" style="display:none">-</button>
                </div>
                <div class="col-3"></div>
            </div>
        </div>
        <TAG:BotaoVoltar />
        <TAG:jQuery />
    </body>
    <script>
        let fieldsCount = 1;
        const fieldExample = document.getElementById('contact-init').innerHTML;
        let formDiv = document.getElementById('form');
        let moreContactsArea = document.getElementById('more-contacts-area');

        $("#more-button").click(function (e) {
            e.preventDefault();
            moreContactsArea.innerHTML = moreContactsArea.innerHTML + fieldExample;

            fieldsCount++;
            console.log(fieldsCount);

            checkLessButton();

        });

        function checkLessButton() {
            if (fieldsCount > 1) {
                document.getElementById('less-button').setAttribute('style', 'display:inline');
            } else {
                document.getElementById('less-button').setAttribute('style', 'display:none');
            }
        }

        $("#less-button").click(function (e) {
            fieldsCount--;
            checkLessButton();
            $('#more-contacts-area:last-child').remove();
            console.log(moreContactsArea);
        });

    </script>
</html>
