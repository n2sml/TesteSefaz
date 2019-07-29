<%@page import="model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="TAG" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todos os Usuários</title>
        <TAG:BootstrapCSS />
        <TAG:CSS />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col">
                    <h1>Todos os Usuários</h1>
                    <% ArrayList<Usuario> temp = (ArrayList<Usuario>) request.getAttribute("array");  %>

                    <table class="table table-hover">    
                        <thead>
                            <tr>
                                <th scope="col">Nome</th>
                                <th scope="col">E-mail</th>
                                <th scope="col">Senha</th>
                                <th scope="col">Id no Sistema</th>
                                <th scope="col">Contatos<br>(ddd - número - tipo)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int index = 0; index < temp.size(); index++) {%>
                            <tr>
                                <td>
                                    <%= temp.get(index).getNome()%>
                                </td>
                                <td>
                                    <%= temp.get(index).getEmail()%>
                                </td>
                                <td>
                                    <%= temp.get(index).getSenha()%>
                                </td>
                                <td>
                                    <%= temp.get(index).getId()%>
                                </td>
                                <td>
                                    <% for (int telefoneIndex = 0; telefoneIndex < temp.get(index).getTelefones().size(); telefoneIndex++) {%>
                                    (<%= temp.get(index).getTelefones().get(telefoneIndex).getDdd()%>)                
                                    <%= temp.get(index).getTelefones().get(telefoneIndex).getNumero()%> - 
                                    "<%= temp.get(index).getTelefones().get(telefoneIndex).getTipo()%>"                    
                                    <% } %>
                                </td>
                            </tr>    
                        </tbody>
                        <% }%>
                    </table>    
                </div>
            </div>
        </div>
        <TAG:BotaoVoltar />
    </body>
</html>
