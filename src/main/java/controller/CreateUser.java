package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import model.Telefone;
import model.Usuario;
import model.UsuarioDAO;

public class CreateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servidor recebeu Get() no servlet de Criação de Usuário");

        RequestDispatcher rd = request.getRequestDispatcher("create.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servidor recebeu Post() no servlet de Criação de Usuário");

        Usuario tempUsuario = new Usuario();

        tempUsuario.setNome((String) request.getParameter("nome"));
        tempUsuario.setSenha((String) request.getParameter("senha"));
        tempUsuario.setEmail((String) request.getParameter("email"));

        int telefonesSize = request.getParameterValues("numero").length;

        ArrayList<Telefone> telefoneArrayTemp = new ArrayList();

        for (int index = 0; index < telefonesSize; index++) {
            Telefone tempTelefone = new Telefone();

            tempTelefone.setDdd(Integer.parseInt((String) request.getParameter("ddd")));
            tempTelefone.setNumero((String) request.getParameter("numero"));
            tempTelefone.setTipo((String) request.getParameter("tipo"));
            
            telefoneArrayTemp.add(tempTelefone);
        }
        
        tempUsuario.setTelefones(telefoneArrayTemp);
        
        UsuarioDAO.setNewUser(tempUsuario);
        
        RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
