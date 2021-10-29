package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContentBean {
	private String Id = null;
	private String Brief = null;
	private String CreatedDate = null;
	private int NumPage = 0;
	private int NumId = 0;
	private String Titles=null;
	public ContentBean() {
	}

	public String getId() {
		return Id;
	}

	public String getBrief() {
		return Brief;
	}

	public String getCreatedDate() {
		return CreatedDate;
	}

	public String getTitles() {
		return Titles;
	}
	public void setTitles(String Offset) {
		String sqlSelectId = "SELECT Title FROM content ORDER BY CreateDate DESC LIMIT 1 OFFSET " + Offset+" ";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myds", "root", "Quyquynh1");
			PreparedStatement ps = conn.prepareStatement(sqlSelectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Titles = rs.getString("Title");}
		} catch (SQLException e) {
			Titles="1";
			// handle the exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setId(String Offset) {
		String sqlSelectId = "SELECT id FROM content ORDER BY CreateDate DESC LIMIT 1 OFFSET " + Offset+" ";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myds", "root", "Quyquynh1");
			PreparedStatement ps = conn.prepareStatement(sqlSelectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Id = rs.getString("id");}
		} catch (SQLException e) {
			Id="1";
			// handle the exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setBrief(String Offset) {
		String sqlSelectId = "SELECT left(Brief,100) as Brief FROM content ORDER BY CreateDate DESC LIMIT 1 OFFSET "
				+ Offset;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myds", "root", "Quyquynh1");

			PreparedStatement ps = conn.prepareStatement(sqlSelectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Brief = rs.getString("Brief");
			}
		} catch (SQLException e) {
			// handle the exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setCreatedDate(String Offset) {
		String sqlSelectId = "SELECT CreateDate FROM content ORDER BY CreateDate DESC LIMIT 1 OFFSET " + Offset;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myds", "root", "Quyquynh1");

			PreparedStatement ps = conn.prepareStatement(sqlSelectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CreatedDate = rs.getString("CreateDate");
			}
		} catch (SQLException e) {
			// handle the exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getNumPage() {
		String sqlSelectId = "SELECT COUNT(id) AS NumId FROM content";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myds", "root", "Quyquynh1");

			PreparedStatement ps = conn.prepareStatement(sqlSelectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NumId = rs.getInt("NumId");
				NumPage = (NumId / 10);
			}
		} catch (SQLException e) {
			// handle the exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NumPage;
	}

	public int getNumId() {
		String sqlSelectId = "SELECT COUNT(id) AS NumId FROM content ";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myds", "root", "Quyquynh1");

			PreparedStatement ps = conn.prepareStatement(sqlSelectId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NumId = rs.getInt("NumId");
			}
		} catch (SQLException e) {
			// handle the exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NumId;
	}
}
