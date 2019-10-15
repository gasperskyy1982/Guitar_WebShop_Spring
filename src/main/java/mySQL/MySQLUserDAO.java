package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.DBUtils;
import dao.DaoFactory;
import dao.UserDAO;
import models.User;

public class MySQLUserDAO implements UserDAO {

	public static String userLogin;
	public static String userPassword;
	public static String userName;
	public static String userRegion;
	public static Boolean userGender;
	public static String userComment;
	
	private final static String SELECT_QUERY2 = "Select * FROM users WHERE login = ? && password = ?";

	private final static String SELECT_QUERY = "SELECT * FROM users WHERE login = ?";

	private final static String INSERT_QUERY = "INSERT INTO users (login, password, name, region, gender, comment)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";

	
	private final static String CORRECT_QUERY = "UPDATE users SET login = ?, password = ?, name = ?, region = ?, gender = ?, comment = ?"
			+ " WHERE login = ?";
	
	private DaoFactory factory = new MySQLDAOFactory();
	
	
	@Override
	public User createUser(String login, String password, String name, String region, boolean gender, String comment) {
		return new User(login, password, name, region, gender, comment);
	}

	
	@Override
	public void setUser(String login, String password, String name, String region, boolean gender,
			String comment) {

		Connection conn = factory.openConnection();
	
		Statement selectStmt = null;
		ResultSet rs = null;

		try {

			PreparedStatement prepSt = conn.prepareStatement(INSERT_QUERY);
			prepSt.setString(1, login);
			prepSt.setString(2, DBUtils.getHash(password));
			prepSt.setString(3, name);
			prepSt.setString(4, region);
			prepSt.setBoolean(5, gender);
			prepSt.setString(6, comment);
			prepSt.executeUpdate();

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		} finally {
				if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} 
				rs = null;
			}

			if (selectStmt != null) {
				try {
					selectStmt.close();
					factory.closeConnection();
				} catch (SQLException sqlEx) {
				} 

				selectStmt = null;
			}
			
		}

	}

	@Override
	public void correctUser(String login, String password, String name, String region, boolean gender,
			String comment) {
		
		Connection conn = factory.openConnection();
		
		Statement selectStmt = null;
		ResultSet rs = null;

		try {
		
			PreparedStatement prepSt = conn.prepareStatement(CORRECT_QUERY);
			prepSt.setString(1, login);
			prepSt.setString(2, DBUtils.getHash(password));
			prepSt.setString(3, name);
			prepSt.setString(4, region);
			prepSt.setBoolean(5, gender);
			prepSt.setString(6, comment);
			prepSt.setString(7, login);
			prepSt.executeUpdate();
			

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (selectStmt != null) {
				try {
					selectStmt.close();
					factory.closeConnection();
				} catch (SQLException sqlEx) {
				} // ignore

				selectStmt = null;
			}

		}
	}

	@Override
	public boolean getUniqueLogin(String login) {
		boolean isUnique = true;
		
		Connection conn = factory.openConnection();
		
		Statement selectStmt = null;
		ResultSet rs = null;

		try {

			PreparedStatement prepSt = conn.prepareStatement(SELECT_QUERY);
			prepSt.setString(1, login);
			rs = prepSt.executeQuery();

			while (rs.next()) {
				if (login.equals(rs.getString("login"))) {
					isUnique = false;
				}
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (selectStmt != null) {
				try {
					selectStmt.close();
					factory.closeConnection();
				} catch (SQLException sqlEx) {
				} // ignore

				selectStmt = null;
			}
		}
		return isUnique;
	}

	
	
	@Override
	public boolean getAuth(String login, String password) {
		boolean isAuth = false;
		
		Connection conn = factory.openConnection();
		
		Statement selectStmt = null;
		ResultSet rs = null;

		try {

			PreparedStatement prepSt = conn.prepareStatement(SELECT_QUERY2);
			prepSt.setString(1, login);
			prepSt.setString(2, DBUtils.getHash(password));
			rs = prepSt.executeQuery();
			
			while (rs.next()) {
				if (login.equals(rs.getString("login")) && DBUtils.getHash(password).equals(rs.getString("password"))) {
					userLogin = rs.getString("login");
					userPassword = rs.getString("password");
					userName = rs.getString("name");
					userRegion = rs.getString("region");
					userGender = rs.getBoolean("gender");
					userComment = rs.getString("comment");
					isAuth = true;
				}
			
			}
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		} finally {
		
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (selectStmt != null) {
				try {
					selectStmt.close();
					factory.closeConnection();
				} catch (SQLException sqlEx) {
				} // ignore

				selectStmt = null;
			}
		}
		return isAuth;
	}

	
	
}
