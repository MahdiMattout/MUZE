package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import entity.User;
import services.UsersCache;

public class UsersQuery extends DbManager {

	public static User findUserByUsernameAndPassword(String username, String password) {
		try {
			PreparedStatement stmt = establishConnection()
					.prepareStatement("SELECT * FROM APP_USERS U WHERE U.USERNAME = ? AND U.PASSWORD=? ");
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String firstname = rs.getString(2);
				String lastname = rs.getString(3);
				String email = rs.getString(4);
				String address = rs.getString(5);
				return new User(id, firstname, lastname, username, email, address, password, false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ConcurrentHashMap<Integer, User> findAllUsers() {
		ConcurrentHashMap<Integer, User> usersMap = new ConcurrentHashMap<Integer, User>();
		try {
			PreparedStatement stmt = establishConnection().prepareStatement("SELECT * FROM APP_USERS");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String firstname = rs.getString(2);
				String lastname = rs.getString(3);
				String username = rs.getString(4);
				String email = rs.getString(4);
				String address = rs.getString(5);
				String password = rs.getString(6);
				usersMap.put(id, new User(id, firstname, lastname, username, email, address, password, false));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersMap;
	}

	public static void createUser(User user) throws SQLException {
		if (UsersCache.findUserByUsername(user.getUsername()) == null) {
			String query = "INSERT INTO APP_USERS ("
					+ "" + " firstname ," 
					+ " lastname," 
					+ " username ,"
					+ " email ,"
					+ " address ,"
					+ " password ) VALUES ( ?, ?, ?, ? , ?, ?)";
			System.out.println(query);
			PreparedStatement st = establishConnection().prepareStatement(query);
			st.setString(1, user.getFirstName());
			st.setString(2, user.getLastName());
			st.setString(3, user.getUsername());
			st.setString(4, user.getEmail());
			st.setString(5, user.getAddress());
			st.setString(6, user.getPassword());

			// execute the prepared statement insert
			st.executeUpdate();
			st.close();
			return;
		}
		throw new IllegalArgumentException("USER USERNAME IS UNIQUE. CREATION FAILED !");
	}


}
