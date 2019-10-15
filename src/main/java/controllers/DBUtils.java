package controllers;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class DBUtils {

	public static StringBuilder table = new StringBuilder();

	private static final String SALT = "alex";

	public static String getHash(String password) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(StandardCharsets.UTF_8.encode(password + SALT));
			return String.format("%032x", new BigInteger(md5.digest()));
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return null;
	}

	public static boolean isLoginCorrect(String login) {
		if (Pattern.matches(".+@.+", login)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isPasswordCorrect(String password_1) {
		if (Pattern.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}", password_1)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isRe_PasswordCorrect(String password_1, String password_2) {
		if (password_1.equals(password_2)) {
			return true;
		} else {
			return false;
		}
	}

}