package database;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import entity.Song;
import entity.User;
import services.SongsCache;
import services.UsersCache;

public class SongsQuery  extends DbManager {
	
		private static QueryBuilder<Song, Integer> queryBuilder = null;
		public static Where<Song, Integer> where = null;
		private PreparedQuery<Song> query;

		
		//int id -  String song_name - String song_filePath; // this has to be casted to File once queried
		
		// works because id is unique
		public static Song findSongByID(int id) {
			try {
				establishConnection();
				queryBuilder = DbManager.getSongDao().queryBuilder();
				where = queryBuilder.where();
				List<Song> songs = where.eq("id", id).query();
				if (songs.size() == 1) {
					return songs.get(0);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		public static Song findSongByIDAndName(int id, String song_name) {
			try {
				establishConnection();
				queryBuilder = DbManager.getSongDao().queryBuilder();
				where = queryBuilder.where();
				List<Song> songs = where.and(where.eq("id", id), where.eq("song_name", song_name)).query();
				if (songs.size() == 1) {
					return songs.get(0);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		public static Song findSongByUploaderIdAndName(int uploader_id, String song_name) {
			try {
				establishConnection();
				queryBuilder = DbManager.getSongDao().queryBuilder();
				where = queryBuilder.where();
				List<Song> songs = where.and(where.eq("uploader_id", uploader_id), where.eq("song_name", song_name)).query();
				if (songs.size() == 1) {
					return songs.get(0);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public static Song findSongByPath( String song_filePath) {
			try {
				establishConnection();
				queryBuilder = DbManager.getSongDao().queryBuilder();
				where = queryBuilder.where();
				List<Song> songs = where.eq("song_filePath", song_filePath).query();
				if (songs.size() == 1) {
					return songs.get(0);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		
		public static ConcurrentHashMap<Integer, Song> findAllSongs() {
			ConcurrentHashMap<Integer, Song> songsMap = new ConcurrentHashMap<Integer, Song>();
			try {
				establishConnection();
				queryBuilder = DbManager.getSongDao().queryBuilder();
				where = queryBuilder.where();
				
				List<Song> songs = DbManager.getSongDao().queryForAll();
				for (int i = 0; i < songs.size(); i++){
					Song song = songs.get(i);
					songsMap.put(song.getId(), song);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return songsMap;
		}

		public static void createSong(Song song) throws Exception {
			if (SongsCache.findSongByPath(song.getSongFilePath()) == null) {
				String query = MessageFormat.format("INSERT INTO Song ("
						+ "" 
						+ " song_name ," 
						+ " song_filePath ) VALUES ( {0}, {1})", song.getSongName(), song.getSongFilePath());

				System.out.println(query);
				establishConnection();
				queryBuilder = DbManager.getSongDao().queryBuilder();
				where = queryBuilder.where();
				// execute the prepared statement insert
				DbManager.getSongDao().create(song);
				return;
			}
			throw new IllegalArgumentException("Song path IS UNIQUE. CREATION FAILED !");
		}


	}
