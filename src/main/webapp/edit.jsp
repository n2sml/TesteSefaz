<%@page import="model.Usuario"%>
<%@page import="model.UsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="TAG" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    System.out.println("ID AQUI " + id);
    Usuario usuarioTemp = UsuarioDAO.getUserById(id);
%>

<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editando Usuário</title>
        <TAG:BootstrapCSS />
    </head>
    <body>
        <h1>Editando Usuário</h1>
        <div id="form-div">
            <form action="./UpdateUser" method="post" id="form">
                <label>Nome</label>
                <input type="text" name="nome" value="<%= usuarioTemp.getNome()%>">
                <br>        
                <label>Email</label>
                <input type="text" name="email" value="<%= usuarioTemp.getEmail()%>">
                <br>
                <label>Senha</label>
                <input type="text" name="senha" value="<%= usuarioTemp.getSenha()%>">
                <br>

                <div id="contact-init">
                    <label>DDD</label>
                    <input type="text" name="ddd" value="<%= usuarioTemp.getTelefones().get(0).getDdd()%>">
                    <br>
                    <label>Numero</label>
                    <input type="text" name="numero" value="<%= usuarioTemp.getTelefones().get(0).getNumero()%>">
                    <br>
                    <label>Tipo</label>
                    <input type="text" name="tipo" value="<%= usuarioTemp.getTelefones().get(0).getTipo()%>">
                    <br>
                    <input type="text" name="id" value="<%= id %>" hidden>
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
