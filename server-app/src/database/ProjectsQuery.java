//package database;
//
//
//// TO DO: STILL HERE ( if needed) AND THE PROJECT PANEL WITH ALL THE BUTTONS using written functions
//
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.MessageFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.j256.ormlite.jdbc.JdbcConnectionSource;
//import com.j256.ormlite.stmt.QueryBuilder;
//import com.j256.ormlite.stmt.Where;
//
//import entity.Project;
//import entity.User;
//import entity.User_Song;
//import services.UsersCache;
//
//public class ProjectsQuery extends DbManager {
//	
//	private static QueryBuilder<Song, Integer> queryBuilder_song = null;
//	public static Where<Song, Integer> where_song =null;
//	
//	private static QueryBuilder<User, Integer> queryBuilder_user = null;
//	public static Where<User, Integer> where_user =null;
//
//	Cursor all_user_songs = MUZE.rawQuery("SELECT * FROM Song, User " +
//            "WHERE Song.uploader_id = User.id" +
//            "GROUP BY Song.song_name", null);
//	
//	Cursor specific_song = MUZE.rawQuery("SELECT * FROM Song, User " +
//            "WHERE Song.uploader_id = User.id" +
//            "GROUP BY Song.song_name", null);
//	
//	
//	
//	// usernames are unique
//	public static User_Song find_User_Song(String username, int song_id) {
//		try {
//			establishConnection();
//			queryBuilder_song = DbManager.getSongDao().queryBuilder();
//			where_song = queryBuilder_song.where();
//			
//			queryBuilder_user = DbManager.getUserDao().queryBuilder();
//			where_user = queryBuilder_user.where();
//			
//			List<Songs> songs_of_user = where.and(where.eq("username", username), where.eq("id", song_id)).query();
//			 
//
//			
////			ResultSet rs = stmt.executeQuery(MessageFormat.format("SELECT * FROM project p WHERE p.title = {0} AND p.user_id = {1}", title, userId));
//			if (songs_of_user.size() == 1) {
//				return songs_of_user.get(0);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	// usernames given with song name are alsp unique
//	public static User_Song find_User_Song(String username, String song_name) {
//		try {
//			establishConnection();
//			queryBuilder_song = DbManager.getSongDao().queryBuilder();
//			where_song = queryBuilder_song.where();
//			
//			queryBuilder_user = DbManager.getUserDao().queryBuilder();
//			where_user = queryBuilder_user.where();
//			
//			List<Songs> songs_of_user = where.and(where.eq("username", username), where.eq("song_id", song_id)).query();
////			ResultSet rs = stmt.executeQuery(MessageFormat.format("SELECT * FROM project p WHERE p.title = {0} AND p.user_id = {1}", title, userId));
//			if (songs_of_user.size() == 1) {
//				return songs_of_user.get(0);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public static List<User_Song> findAllSongs_User() throws SQLException {
//		return DbManager.getUserSongDao().queryForAll();
//	}
//
//	public static Object[][] findProjectsForDataTable() {
//		Object[][] arr = null;
//		try {
//			establishConnection();
//			queryBuilder = DbManager.getProjectDao().queryBuilder();
//			where = queryBuilder.where();
//			List<Project> projects = findAllProjects();
//			int rowsCount = projects.size();
//			int i = 0;
//			arr = new Object[rowsCount][3];
//			while (i < rowsCount) {
//				arr[i][0] = projects.get(i).getTitle();
//				arr[i][1] = projects.get(i).getContent();
//				arr[i][2] = UsersCache.findUserById(projects.get(i).getUserId()).getUsername();
//				i++;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return arr;
//	}
//
//	public static void createUser_Song(User_Song user_Song_Project) throws SQLException {
//		String query = MessageFormat.format("INSERT INTO User_Song (" + " title ," + " content," + " user_id ) VALUES ( {0}, {1}, {2} )", project.getTitle(), project.getContent(), project.getUserId());
//		System.out.println(query);
//		establishConnection();
//		queryBuilder = DbManager.getProjectDao().queryBuilder();
//		where = queryBuilder.where();
//		// execute the preparedstatement insert
//		DbManager.getProjectDao().create(user_Song_Project);
//		return;
//	}
//		
//	
//
//}
