<%-- 
    Document   : create
    Created on : 27/07/2019, 20:23:28
    Author     : nicolas
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Criar Usuário</title>
    </head>
    <body>
        <h1>Criar Usuário</h1>
        <% 

        ArrayList<String> words = (ArrayList<String>)request.getAttribute("words");
       
        %>
        
        <%= words.get(0) %>
        
    </body>
</html>
