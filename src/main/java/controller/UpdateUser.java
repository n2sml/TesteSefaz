package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Telefone;
import model.Usuario;
import model.UsuarioDAO;

public class UpdateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Usuario> temp = UsuarioDAO.getAllUsers();
        request.setAttribute("array", temp);
        RequestDispatcher rd = request.getRequestDispatcher("/update.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("chegou o post...");
        
        Usuario tempUsuario = new Usuario();

        tempUsuario.setNome((String) request.getParameter("nome"));
        tempUsuario.setSenha((String) request.getParameter("senha"));
        tempUsuario.setEmail((String) request.getParameter("email"));
        tempUsuario.setId(Integer.parseInt(request.getParameter("id")));

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
        
        UsuarioDAO.editUser(tempUsuario);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
