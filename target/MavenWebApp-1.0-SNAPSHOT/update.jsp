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
        <TAG:CSS />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col">

                    <h1>Alterar Usuário</h1>
                    <% ArrayList<Usuario> temp = (ArrayList<Usuario>) request.getAttribute("array");  %>

                    <table class="table table-hover">    
                        <thead>
                            <tr>
                                <th scope="col">Nome</th>
                                <th scope="col">E-mail</th>
                                <th scope="col">Contatos (ddd - número - tipo)</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
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
                                    <button class="update-button btn btn-outline-dark btn-sm" id="<%=temp.get(index).getId()%>">Editar</button>
                                </td>
                            </tr>            
                            <% }%>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>    
        <TAG:BotaoVoltar />
        <TAG:jQuery />
        <TAG:BootstrapJS />
        <TAG:jQueryRedirect />
    </body>
    <script>
        $(".update-button").bind('click', function (a) {
            let idUser = a.currentTarget.id;
            $.redirect('edit.jsp', {'id': idUser});
        });
    </script>
</html>

