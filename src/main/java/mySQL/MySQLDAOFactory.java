package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.DaoFactory;
import dao.ProductDAO;
import dao.UserDAO;

public class MySQLDAOFactory extends DaoFactory{

	Connection conn = null;
	
	@Override
	public ProductDAO getProductDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/iteashop?" + "user=root&password=");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("Failed");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	@Override
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public UserDAO getUserDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
