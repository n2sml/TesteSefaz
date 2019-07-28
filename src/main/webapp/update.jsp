<%@page import="model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="TAG" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Usuário</title>
        <TAG:BootstrapCSS />
    </head>
    <body>
        <h1>Alterar Usuário</h1>
        <% ArrayList<Usuario> temp = (ArrayList<Usuario>) request.getAttribute("array");  %>

        <table style="width:100%">    
            <% for (int index = 0; index < temp.size(); index++) {%>
            <tr id=" <%=temp.get(index).getId()%>">
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
                    <button class="update-button" id="<%= temp.get(index).getId()%>">Editar</button>
                </td>
            </tr>            
            <% }%>
        </table>
        <TAG:jQuery />
        <TAG:BootstrapJS />
        <script src="https://cdn.jsdelivr.net/npm/jquery.redirect@1.1.4/jquery.redirect.min.js"></script>
    </body>
    <script>
        $(".update-button").bind('click', function (a) {
//            $.ajax({
//                method: "POST",
//                url: "./DeleteUser",
//                data: {
//                    id: a.currentTarget.id
//                },
//                beforeSend: function () {
//                    console.log('ENVIANDO...');
//                }
//            }).done(function () {
//                location.reload();
//            }).fail(function (err) {
//                console.log(err);
//            });
            let idUser = a.currentTarget.id;
            $.redirect('edit.jsp', {'id': idUser});
        })
    </script>
</html>
