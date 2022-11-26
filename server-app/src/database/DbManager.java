package database;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import entity.Project;
import entity.Song;
import entity.User;
import entity.User_Song;

public class DbManager {

	private static JdbcConnectionSource connection = null;
	private static Dao<User, Integer> usesrDao = null;
	private static Dao<Song, Integer> songsDao = null;

	private static String url = "jdbc:sqlite:MUZE.db";
	
//	public static void createDataBase(Connection connection) {
//		try {
//			String sql = "CREATE DATABASE IF NOT EXISTS MUZE"; 
//			PreparedStatement s = connection.prepareStatement(sql);
//			s.execute();
//			s.close();
//		} catch (Exception e) {
//	        e.printStackTrace();
//		}
//	}

	@SuppressWarnings("resource")
	public static JdbcConnectionSource establishConnection() throws SQLException {
		if (connection == null) {
				setConnection(new JdbcConnectionSource(url));
				usesrDao = DaoManager.createDao(connection, User.class);
				songsDao = DaoManager.createDao(connection, Song.class);
				
				TableUtils.createTableIfNotExists(connection, User.class);
				TableUtils.createTableIfNotExists(connection, Song.class);
				return connection;
		}
		return null;
	}

	public JdbcConnectionSource getConnection() {
		return connection;
	}
	public static void setConnection(JdbcConnectionSource jdbcConnectionSource) {
		DbManager.connection = jdbcConnectionSource;
	}

	
	public static Dao<User, Integer> getUserDao() {
		return usesrDao;
	}
	public static void setUserDao(Dao<User, Integer> userDao) {
		DbManager.usesrDao = userDao;
	}

	public static Dao<Song, Integer> getSongDao() {
		return songsDao;
	}
	public static void setSongDao(Dao<Song, Integer> songDao) {
		DbManager.songsDao = songDao;
	}

}