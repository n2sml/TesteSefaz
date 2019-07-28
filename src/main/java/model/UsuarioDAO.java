package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
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
    private static final String QUERY_SET_NEW_USUARIO = "INSERT INTO usuario (nome, email, senha)VALUES('?1','?2','?3');";
    private static final String QUERY_GET_ID_BY_USER = "SELECT id FROM usuario WHERE nome = '?1' AND email = '?2' AND senha = '?3'";
    private static final String QUERY_SET_NEW_TELEFONE = "INSERT INTO telefone (ddd, numero, tipo, usuarioId) VALUES (?1,'?2','?3', ?4);";

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

    private static ArrayList<Telefone> getTelefonesById(int id) {
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
            ResultSet rs = stmt.executeQuery(QUERY_GET_TELEFONES_BY_ID + id);

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

    public static void setNewUser(Usuario usuario) {
        try {
            Class.forName(HSQL_DRIVER_CLASS);
        } catch (Exception e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
            return;
        }
        try {
            // Create database connection
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);

            CallableStatement callstmt = null;

            String tempQuery = QUERY_SET_NEW_USUARIO.replace("?1", usuario.getNome());
            tempQuery = tempQuery.replace("?2", usuario.getEmail());
            tempQuery = tempQuery.replace("?3", usuario.getSenha());

            callstmt = conn.prepareCall(tempQuery);

            callstmt.execute();
            System.out.println("AQUIIII");
            //Close statement and connection 

            int id = getIdFromUsuario(usuario);

            setTelefones(usuario.getTelefones(), id);

            callstmt.close();
            conn.close();
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
    }

    public static int getIdFromUsuario(Usuario usuario) {
        //QUERY_GET_ID_BY_USER
        int id = -1;

        try {
            Class.forName(HSQL_DRIVER_CLASS);
        } catch (Exception e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
            return id;
        }
        try {
            // Create database connection
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);

            // Create and execute statement
            Statement stmt = conn.createStatement();

            String tempQuery = QUERY_GET_ID_BY_USER.replace("?1", usuario.getNome());
            tempQuery = tempQuery.replace("?2", usuario.getEmail());
            tempQuery = tempQuery.replace("?3", usuario.getSenha());

            ResultSet rs = stmt.executeQuery(tempQuery);

            while (rs.next()) {
                id = Integer.parseInt(rs.getString("id"));
            }

            rs.close();
            conn.close();
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
        return id;
    }

    public static void setTelefones(ArrayList<Telefone> telefoneArray, int id) {
        //QUERY_SET_NEW_TELEFONE
        try {
            Class.forName(HSQL_DRIVER_CLASS);
        } catch (Exception e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);

            CallableStatement callstmt = null;

            for (int index = 0; index < telefoneArray.size(); index++) {
                String tempQuery = QUERY_SET_NEW_TELEFONE.replace("?1", Integer.toString(telefoneArray.get(index).getDdd()));
                tempQuery = tempQuery.replace("?2", telefoneArray.get(index).getNumero());
                tempQuery = tempQuery.replace("?3", telefoneArray.get(index).getTipo());
                tempQuery = tempQuery.replace("?4", Integer.toString(id));

                callstmt = conn.prepareCall(tempQuery);
                callstmt.execute();
            }

            callstmt.close();
            conn.close();
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
    }
}
