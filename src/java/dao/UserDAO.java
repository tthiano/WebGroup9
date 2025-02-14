package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Content;
import model.User;

public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost/myds?useUnicode=true&characterEncoding=UTF-8";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Quyquynh1";

    private static final String INSERT_USERS_SQL = "INSERT INTO member" + "  (Username, Password, Email) VALUES " +
        " (?, ?, ?);";
    private static final String INSERT_CONTENT_SQL = "INSERT INTO content" + "  (Title, Brief, Content, Authorid) VALUES " +
            " (?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID_NAME = "select *  from member where id =? and Email = ?";
    private static final String SELECT_USER_BY_EMAIL = "select *  from member where Email = ?";
    private static final String SELECT_CONTENT_BY_ID = "select *  from content where id =? and Authorid = ?";


    private static final String LOGIN = "select * from member where Email = ? and Password = ?  ;";


    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String DELETE_CONTENT_SQL = "delete from content where id = ?;";

    private static final String UPDATE_CONTENT_SQL = "update content set Title = ?,Brief= ?, Content =?, Sort =? where id = ? and Authorid= ?;";
    private static final String UPDATE_USER_SQL = "update member set Firstname = ?,Lastname= ?, Phone =?, Email =? , Description = ? where id = ? ;";

    public UserDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void insertContent(Content content) throws SQLException {
        System.out.println(INSERT_CONTENT_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTENT_SQL)) {
            preparedStatement.setString(1, content.getTitle());
            preparedStatement.setString(2, content.getBrief());
            preparedStatement.setString(3, content.getContent());
            preparedStatement.setInt(4, content.getAuthorid());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public User login(String name, String password) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) { 
            	int id = rs.getInt("id");
                user = new User(id, name);
                }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    public User selectUserBYname( String email) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);) {
            preparedStatement.setString(1, email);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String pass = rs.getString("Password");
                String name = rs.getString("Username");
                user = new User( name, pass, email);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    public User selectUserBYidname(int id, String name) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_NAME);) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String fname = rs.getString("Firstname");
                String lname = rs.getString("Lastname");
                int phone = rs.getInt("Phone");
                String email = rs.getString("Email");
                String des = rs.getString("Description");
                user = new User(fname,lname,phone,email,des);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    public Content selectContentBYid(int id, int authorid) {
    	Content content = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, authorid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String Title = rs.getString("Title");
                String Brief = rs.getString("Brief");
                String Content = rs.getString("Content");
                int Sort = rs.getInt("Sort");
                content = new Content(id,Title,Brief,Content,Sort,authorid);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return content;
    }
    public boolean updateContent(Content content) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CONTENT_SQL);) {
            statement.setString(1, content.getTitle());
            statement.setString(2, content.getBrief());
            statement.setString(3, content.getContent());
            statement.setInt(4, content.getSort());
            statement.setInt(5, content.getId());
            statement.setInt(6, content.getAuthorid());

            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);) {
            statement.setString(1, user.getFname());
            statement.setString(2, user.getLname());
            statement.setInt(3, user.getPhone());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getDescription());
            statement.setInt(6, user.getId());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    public boolean deleteContent(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CONTENT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
   
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
