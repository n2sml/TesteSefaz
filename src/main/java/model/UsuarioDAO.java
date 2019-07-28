package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO {
    private static final String HSQL_DRIVER_CLASS = "org.hsqldb.jdbc.JDBCDriver";
    private static final String LOGIN_USER = "SA";
    private static final String LOGIN_PASSWORD = "";

    private static final String DATABASE = "jdbc:hsqldb:hsql://localhost/sampledb;ifexists=true";

    private static Connection conn = null;
    
    //Queries:
    private static final String QUERY_READ_USERS = "SELECT nome, email, senha, id FROM usuario;";
    private static final String QUERY_GET_TELEFONES_BY_ID = "SELECT ddd, numero, tipo FROM telefone WHERE usuarioid=";
    
    public static ArrayList<Usuario> getReadUsers() {
        ArrayList<Usuario> temp = new ArrayList();

        try {
            Class.forName(HSQL_DRIVER_CLASS);
        } catch (Exception e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
            return null;
        }
        try {
            // Create database connection
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);

            // Create and execute statement
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY_READ_USERS);

            // Criar o objeto aqui e inserir no ArrayList:
            while (rs.next()) {
                Usuario tempUser = new Usuario();
                tempUser.setNome(rs.getString("nome"));
                tempUser.setEmail(rs.getString("email"));
                tempUser.setSenha(rs.getString("senha"));
                tempUser.setId(Integer.parseInt(rs.getString("id")));

                tempUser.setTelefones(getTelefonesById(Integer.parseInt(rs.getString("id"))));
                
                temp.add(tempUser);
            }

            // Clean up
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                // Close connection
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return temp;
    }
    
    private static ArrayList<Telefone> getTelefonesById(int id){
        ArrayList<Telefone> temp = new ArrayList();
        
         try {
            Class.forName(HSQL_DRIVER_CLASS);
        } catch (Exception e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
            return null;
        }
        try {
            // Create database connection
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);

            // Create and execute statement
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY_GET_TELEFONES_BY_ID +id);

            // Criar o objeto aqui e inserir no ArrayList:
            while (rs.next()) {
                Telefone tempNumber = new Telefone();
                tempNumber.setDdd(Integer.parseInt(rs.getString("ddd")));
                tempNumber.setNumero(rs.getString("numero"));
                tempNumber.setTipo(rs.getString("tipo"));   
                temp.add(tempNumber);
            }

            // Clean up
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                // Close connection
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        
        return temp;
    }
}
