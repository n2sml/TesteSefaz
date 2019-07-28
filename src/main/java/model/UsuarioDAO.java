package model;

import java.sql.Connection;
import java.sql.DriverManager;
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
    private static final String QUERY_GET_USERS = "SELECT nome, email, senha, id FROM usuario;";
    private static final String QUERY_GET_TELEFONES_BY_ID = "SELECT ddd, numero, tipo FROM telefone WHERE usuarioid=";
    private static final String QUERY_SET_NEW_USUARIO = "INSERT INTO usuario (nome, email, senha)VALUES('?1','?2','?3');";
    private static final String QUERY_GET_ID_BY_USER = "SELECT id FROM usuario WHERE nome = '?1' AND email = '?2' AND senha = '?3'";
    private static final String QUERY_SET_NEW_TELEFONE = "INSERT INTO telefone (ddd, numero, tipo, usuarioId) VALUES (?1,'?2','?3', ?4);";
    private static final String QUERY_DELETE_USER_BY_ID = "DELETE FROM usuario WHERE id=?1;";
    private static final String QUERY_DELETE_TELEFONES_BY_ID = "DELETE FROM telefone WHERE usuarioId=?1;";
    private static final String QUERY_GET_USER_BY_ID = "SELECT nome, email, senha FROM usuario WHERE id=?1;";
    private static final String QUERY_EDIT_USUARIO = "UPDATE usuario SET nome='?1', email='?2', senha='?3' WHERE id=?4;";
    private static final String QUERY_EDIT_TELEFONE = "UPDATE telefone SET ddd=?1, numero='?2', tipo='?3' WHERE usuarioId=?4;";
    private static final String QUERY_LOGIN = "SELECT id FROM usuario WHERE nome='?1' AND senha='?2';";

    public static ArrayList<Usuario> getAllUsers() {
        ArrayList<Usuario> temp = new ArrayList();
        try {
            Class.forName(HSQL_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            return null;
        }
        try {
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);
            Statement stmt;
            stmt = conn.createStatement();
            try (ResultSet rs = stmt.executeQuery(QUERY_GET_USERS)) {
                while (rs.next()) {
                    Usuario tempUser = new Usuario();
                    tempUser.setNome(rs.getString("nome"));
                    tempUser.setEmail(rs.getString("email"));
                    tempUser.setSenha(rs.getString("senha"));
                    tempUser.setId(Integer.parseInt(rs.getString("id")));
                    tempUser.setTelefones(getTelefonesById(Integer.parseInt(rs.getString("id"))));
                    temp.add(tempUser);
                }
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
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
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            return null;
        }
        try {
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(QUERY_GET_TELEFONES_BY_ID + id)) {
                while (rs.next()) {
                    Telefone tempNumber = new Telefone();
                    tempNumber.setDdd(Integer.parseInt(rs.getString("ddd")));
                    tempNumber.setNumero(rs.getString("numero"));
                    tempNumber.setTipo(rs.getString("tipo"));
                    temp.add(tempNumber);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
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
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            return;
        }
        try {
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);
            CallableStatement callstmt;
            String tempQuery = QUERY_SET_NEW_USUARIO.replace("?1", usuario.getNome());
            tempQuery = tempQuery.replace("?2", usuario.getEmail());
            tempQuery = tempQuery.replace("?3", usuario.getSenha());
            callstmt = conn.prepareCall(tempQuery);
            callstmt.execute();
            int id = getIdFromUsuario(usuario);
            setTelefones(usuario.getTelefones(), id);
            callstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static int getIdFromUsuario(Usuario usuario) {
        int id = -1;
        try {
            Class.forName(HSQL_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            return id;
        }
        try {
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);
            Statement stmt = conn.createStatement();
            String tempQuery = QUERY_GET_ID_BY_USER.replace("?1", usuario.getNome());
            tempQuery = tempQuery.replace("?2", usuario.getEmail());
            tempQuery = tempQuery.replace("?3", usuario.getSenha());
            try (ResultSet rs = stmt.executeQuery(tempQuery)) {
                while (rs.next()) {
                    id = Integer.parseInt(rs.getString("id"));
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return id;
    }

    @SuppressWarnings("null")
    public static void setTelefones(ArrayList<Telefone> telefoneArray, int id) {
        try {
            Class.forName(HSQL_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
        }
        try {
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);
            CallableStatement callstmt;
            callstmt = null;
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @SuppressWarnings({"null", "UnusedAssignment"})
    public static void deleteUserById(int id) {
        try {
            Class.forName(HSQL_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
        }
        try {
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);
            CallableStatement callstmt;
            callstmt = null;
            String tempQuery = QUERY_DELETE_USER_BY_ID.replace("?1", Integer.toString(id));
            callstmt = conn.prepareCall(tempQuery);
            callstmt.execute();
            tempQuery = QUERY_DELETE_TELEFONES_BY_ID.replace("?1", Integer.toString(id));
            callstmt = conn.prepareCall(tempQuery);
            callstmt.execute();
            callstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public static Usuario getUserById(int id) {
        Usuario tempUser = new Usuario();
        System.out.println("Chegou no método");
        try {
            Class.forName(HSQL_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            return null;
        }
        try {
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);
            Statement stmt = conn.createStatement();
            String tempQuery = QUERY_GET_USER_BY_ID.replace("?1", Integer.toString(id));
            System.out.println("    QUERY: " + tempQuery);
            ResultSet rs = stmt.executeQuery(tempQuery);
            while (rs.next()) {
                tempUser.setNome(rs.getString("nome"));
                tempUser.setEmail(rs.getString("email"));
                tempUser.setSenha(rs.getString("senha"));
                tempUser.setTelefones(getTelefonesById(id));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("CHEGOU AO RETURN");
        return tempUser;
    }

    public static void editUser(Usuario usuario) {
        try {
            Class.forName(HSQL_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            return;
        }
        try {
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);
            String tempQuery = QUERY_EDIT_USUARIO.replace("?1", usuario.getNome());
            tempQuery = tempQuery.replace("?2", usuario.getEmail());
            tempQuery = tempQuery.replace("?3", usuario.getSenha());
            tempQuery = tempQuery.replace("?4", Integer.toString(usuario.getId()));
            try (CallableStatement callstmt = conn.prepareCall(tempQuery)) {
                callstmt.execute();
                int id = getIdFromUsuario(usuario);
                editTelefones(usuario.getTelefones(), id);
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @SuppressWarnings("null")
    public static void editTelefones(ArrayList<Telefone> telefoneArray, int id) {
        try {
            Class.forName(HSQL_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
        }
        try {
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);
            CallableStatement callstmt = null;
            for (int index = 0; index < telefoneArray.size(); index++) {
                String tempQuery = QUERY_EDIT_TELEFONE.replace("?1", Integer.toString(telefoneArray.get(index).getDdd()));
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static boolean login(String login, String senha) {
        boolean isEmpty = false;
        try {
            Class.forName(HSQL_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            return false;
        }
        try {
            conn = DriverManager.getConnection(DATABASE, LOGIN_USER, LOGIN_PASSWORD);
            try (Statement stmt = conn.createStatement()) {
                String tempQuery = QUERY_LOGIN.replace("?1", login);
                tempQuery = tempQuery.replace("?2", senha);
                System.out.println("    QUERY: " + tempQuery);
                ResultSet rs = stmt.executeQuery(tempQuery);
                while (rs.next()) {
                    isEmpty = true;
                }
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return isEmpty;
    }
}
