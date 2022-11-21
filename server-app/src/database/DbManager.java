package database;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import entity.Project;
import entity.Song;
import entity.User;

public class DbManager {

	private static JdbcConnectionSource connection = null;
	private static Dao<User, Integer> userDao = null;
	private static Dao<Song, Integer> songDao = null;
	private static Dao<Project, Integer> projectDao = null;
	private static String url = "jdbc:sqlite:MUZE.db";
	
//	public static void createDataBase(Connection connection) {
//		try {
//			String sql = "CREATE DATABASE IF NOT EXISTS " + getDbName(); 
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
				userDao = DaoManager.createDao(connection, User.class);
				songDao = DaoManager.createDao(connection, Song.class);
				projectDao = DaoManager.createDao(connection, Project.class);
				TableUtils.createTableIfNotExists(connection, User.class);
				TableUtils.createTableIfNotExists(connection, Song.class);
				TableUtils.createTable(projectDao);
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
		return userDao;
	}

	public static void setUserDao(Dao<User, Integer> userDao) {
		DbManager.userDao = userDao;
	}

	public static Dao<Song, Integer> getSongDao() {
		return songDao;
	}

	public static void setSongDao(Dao<Song, Integer> songDao) {
		DbManager.songDao = songDao;
	}

	public static Dao<Project, Integer> getProjectDao() {
		return projectDao;
	}

	public static void setProjectDao(Dao<Project, Integer> projectDao) {
		DbManager.projectDao = projectDao;
	}

}
