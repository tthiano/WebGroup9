package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.contentModel;

public class contentDAO {
	private String WhereSql = " ";
	private String jdbcURL = "jdbc:mysql://localhost:3306/myds";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Quyquynh1";
	private static final String getContentF="SELECT id,Title,brief,CreateDate FROM content ";
	private static final String getContentL=" ORDER BY CreateDate DESC LIMIT ? OFFSET  ? ";
	private int NumPage = 0;
	private int NumId = 0;
	public contentDAO() {}
	
	public String getWhereSql() {
		return WhereSql;
	}

	public void setWhereSql(String whereSql) {
		WhereSql = whereSql;
	}

	public List < contentModel > selectAllContents(int numId,int NumP) {
        int row_num=1;row_num=NumP * 10;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < contentModel > users = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(getContentF+WhereSql+getContentL);) {
            preparedStatement.setInt(1, numId);
            preparedStatement.setInt(2, row_num);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	String id = rs.getString("id");
                String Title = rs.getString("Title");
                String brief = rs.getString("brief");
                String CreateDate = rs.getString("CreateDate");
                users.add(new contentModel(id, Title, brief, CreateDate));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

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

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
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

	private static final String seclect_NumId = "SELECT COUNT(id) AS NumId FROM content";

	public int getNumPage() {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(seclect_NumId + WhereSql);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				NumId = rs.getInt("NumId");
				NumPage = (NumId / 10);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return NumPage;
	}
	public int getNumId() {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(seclect_NumId + WhereSql);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				NumId = rs.getInt("NumId");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return NumId;
	}
}
