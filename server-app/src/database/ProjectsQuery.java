package database;


import java.awt.Cursor;

// TO DO: STILL HERE ( if needed) AND THE PROJECT PANEL WITH ALL THE BUTTONS using written functions


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import entity.User;
import entity.Project;
import entity.Song;
import services.UsersCache;
import services.SongsCache;

public class ProjectsQuery extends DbManager {
	
	private static QueryBuilder<Song, Integer> queryBuilder_song = null;
	public static Where<Song, Integer> where_song =null;
	
	private static QueryBuilder<User, Integer> queryBuilder_user = null;
	public static Where<User, Integer> where_user =null;
	
	private static QueryBuilder<Project, Integer> queryBuilder_project = null;
	public static Where<Project, Integer> where_project =null;

//	Cursor all_user_songs = MUZE.rawQuery("SELECT * FROM Song, User " +
//            "WHERE Song.uploader_id = User.id" +
//            "GROUP BY Song.song_name", null);
//	
//	Cursor specific_song = MUZE.rawQuery("SELECT * FROM Song, User " +
//            "WHERE Song.uploader_id = User.id" +
//            "GROUP BY Song.song_name", null);
//	
//	
	public static Project findProjectBySongId_UserId(int song_id, int user_id) {
			try {
				establishConnection();
				queryBuilder_project = DbManager.getProjectDao().queryBuilder();
				where_project = queryBuilder_project.where();
				
				List<Project> projects = where_project.and(where_project.eq("userId",user_id), where_project.eq("songId", song_id) ).query();
				
				if (projects.size() == 1) {
					return projects.get(0);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	
	public static Project findProjectByUploader_SongName(String song_name, String user_username) {
		try {
			establishConnection();
			queryBuilder_project = DbManager.getProjectDao().queryBuilder();
			where_project = queryBuilder_project.where();
			
			List<Project> projects = where_project.and(where_project.eq("uploader_name",user_username), where_project.eq("song_name", song_name) ).query();
			
			if (projects.size() == 1) {
				return projects.get(0);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
}
	
	
	public static Song find_User_Song_By_Id(int user_id, int song_id) {
		try {
			establishConnection();
			queryBuilder_song = DbManager.getSongDao().queryBuilder();
			where_song = queryBuilder_song.where();
			
			queryBuilder_user = DbManager.getUserDao().queryBuilder();
			where_user = queryBuilder_user.where();
			
			List<Song> songs_of_user = where_song.and(where_song.eq("uploader_id", user_id), where_song.eq("id", song_id)).query();

			if (songs_of_user.size() == 1) {
				return songs_of_user.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// usernames given with song name are also unique
	public static Song find_User_Song_By_Name(String username, String song_name) {
		try {
			establishConnection();
			queryBuilder_song = DbManager.getSongDao().queryBuilder();
			where_song = queryBuilder_song.where();
			
			queryBuilder_user = DbManager.getUserDao().queryBuilder();
			where_user = queryBuilder_user.where();
			
			List<User> uploader_id = where_user.eq("username", username).query();
			
			List<Song> songs_of_user = where_song.and(where_song.eq("uploader_id", uploader_id.get(0).getId()), where_song.eq("song_name", song_name)).query();

			if (songs_of_user.size() == 1) {
				return songs_of_user.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<Song> findAllSongs() throws SQLException {
		return DbManager.getSongDao().queryForAll();
	}
	
	public static List<Project> findAllProjects() throws SQLException {
		return DbManager.getProjectDao().queryForAll();
	}
	
	

	public static Object[][] findProjectsForDataTable() {
		Object[][] arr = null;
		
		try {
			establishConnection();
			queryBuilder_project = DbManager.getProjectDao().queryBuilder();
			where_project = queryBuilder_project.where();
			
			List<Project> projects = findAllProjects();
			int rowsCount = projects.size();
			int i = 0;
			arr = new Object[rowsCount][4];
			while (i < rowsCount) {
				arr[i][0] = projects.get(i).getUploaderName();
				arr[i][1] = projects.get(i).getSongName();
				arr[i][2] = projects.get(i).getSongId();
				arr[i][3] = projects.get(i).getUserId();
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	
	

	public static void createProject(Project project) throws SQLException {
		String query = MessageFormat.format("INSERT INTO Projects (" 
				+ "" 
				+ " uploader_name,"
				+ " song_name,"
				+ " isNew,"
				+ " userId,"
				+ " songId) VALUES ( {0}, {1}, {2}, {3}, {4} )",
				project.getUploaderName(),
				project.getSongName(),
				true,
				project.getUserId(),
				project.getSongId());
		
		
		System.out.println(query);
     	establishConnection();
     	queryBuilder_project = DbManager.getProjectDao().queryBuilder();
     	where_project = queryBuilder_project.where();
     	
		// execute the preparedstatement insert
		DbManager.getProjectDao().create(project);
		return;
	}
		
	

}
