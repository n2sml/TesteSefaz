<%@page import="model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="TAG" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Apagar Usuário</title>
        <TAG:BootstrapCSS />
    </head>
    <body>
        <h1>Apagar Usuário</h1>
        <% ArrayList<Usuario> temp = (ArrayList<Usuario>) request.getAttribute("array");  %>

        <table style="width:100%">    
            <% for (int index = 0; index < temp.size(); index++) {%>
            <tr id=" <%=temp.get(index).getId()%> ">
                <td>
                    <%= temp.get(index).getNome()%>
                </td>
                <td>
                    <%= temp.get(index).getEmail()%>
                </td>
                <td>
                    <% for (int telefoneIndex = 0; telefoneIndex < temp.get(index).getTelefones().size(); telefoneIndex++) {%>
                    (<%= temp.get(index).getTelefones().get(telefoneIndex).getDdd()%>)                
                    <%= temp.get(index).getTelefones().get(telefoneIndex).getNumero()%> - 
                    "<%= temp.get(index).getTelefones().get(telefoneIndex).getTipo()%>"                    
                    <% }%>
                </td>
                <td>
                    <button class="delete-button" id="<%= temp.get(index).getId()%>">Apagar</button>
                </td>
            </tr>            
            <% }%>
        </table>
        <TAG:jQuery />
    </body>
    <script>        
        $(".delete-button").bind('click', function (a) {
            $.ajax({
                method: "POST",
                url: "./DeleteUser",
                data: {
                    id: a.currentTarget.id
                },
                beforeSend: function () {
                    console.log('ENVIANDO...');
                }
            }).done(function () {
                location.reload();
            }).fail(function (err) {
                console.log(err);
            });
        })
    </script>
</html>
