<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="TAG" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Criar Usuário</title>
        <TAG:BootstrapCSS />
    </head>
    <body>
        <h1>Criar Usuário</h1>
        <div id="form-div">
            <form action="./CreateUser" method="post" id="form">
                <label>Nome</label>
                <input type="text" name="nome" value="">
                <br>        
                <label>Email</label>
                <input type="text" name="email" value="">
                <br>
                <label>Senha</label>
                <input type="text" name="senha" value="">
                <br>

                <div id="contact-init">
                    <label>DDD</label>
                    <input type="text" name="ddd" value="">
                    <br>
                    <label>Numero</label>
                    <input type="text" name="numero" value="">
                    <br>
                    <label>Tipo</label>
                    <input type="text" name="tipo" value="">
                    <br>
                </div>    
                <div id="more-contacts-area"></div>

                <input type="submit" value="Submit">
            </form>    

        </div>
        <button id="more-button">+</button>

        <button id="less-button" style="display:none">-</button>
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
            console.log(fieldsCount)

            checkLessButton();
            
        })

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
            $('#more-contacts-area:last-child').remove()
            console.log(moreContactsArea)
        })
        
    </script>
</html>
