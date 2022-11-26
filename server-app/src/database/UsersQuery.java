package database;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import entity.User;
import services.UsersCache;

public class UsersQuery extends DbManager {
	private static QueryBuilder<User, Integer> queryBuilder = null;
	public static Where<User, Integer> where = null;
	private PreparedQuery<User> query;

	public static User findUserById(Integer id) {
		try {
			establishConnection();
			queryBuilder = DbManager.getUserDao().queryBuilder();
			where = queryBuilder.where();
			List<User> users = where.and(where.eq("id" ,id)).query();
			if (users.size() == 1) {
				return users.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//This works because usernames are unique!
	public static User findUserByUsername(String username) {
		try {
			establishConnection();
			queryBuilder = DbManager.getUserDao().queryBuilder();
			where = queryBuilder.where();
			List<User> users = where.eq("username" ,username).query();
			if (users.size() == 1) {
				return users.get(0);
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
			establishConnection();
			queryBuilder = DbManager.getUserDao().queryBuilder();
			where = queryBuilder.where();
			
			List<User> users = DbManager.getUserDao().queryForAll();
			for (int i = 0; i < users.size(); i++){
				User user = users.get(i);
				user.setNew(false);
				usersMap.put(user.getId(), user);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersMap;
	}

	public static void createUser(User user) throws Exception {
		if (UsersCache.findUserByUsername(user.getUsername()) == null) {
			String query = MessageFormat.format("INSERT INTO User ("

					+ "" 
					+ " firstname ," 
					+ " lastname ," 
					+ " username ,"
					+ " email ,"
					+ " password ,"
					+ " song_name ,"
					+ " song_file ) VALUES ( {0}, {1}, {2}, {3} , {4}, {5}, {6})", user.getFirstName(), user.getLastName(), 
					user.getUsername(), user.getEmail(),user.getPassword(), user.getSongName(), user.getSongFile());

			System.out.println(query);
			establishConnection();
			queryBuilder = DbManager.getUserDao().queryBuilder();
			where = queryBuilder.where();
			// execute the prepared statement insert
			DbManager.getUserDao().create(user);
			return;
		}
		throw new IllegalArgumentException("USER USERNAME IS UNIQUE. CREATION FAILED !");
	}


}
