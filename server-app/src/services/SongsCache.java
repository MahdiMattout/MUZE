package services;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import database.SongsQuery;
import database.UsersQuery;
import entity.Song;
import entity.User;

public class SongsCache {

	private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
	private static ConcurrentHashMap<Integer, Song> songsMap;
	private static ConcurrentHashMap<Integer, Integer> songsFilesMap;

	private static void init() {
		songsMap = SongsQuery.findAllSongs();
	}

	public static Song findSongById(Integer id) {
		try {
			// allow multiple reading -- only one thread can write
			readWriteLock.writeLock().tryLock(3, TimeUnit.SECONDS);
			if (songsMap == null) {
				init();
			}
			return songsMap.get(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// unlock write lock
			readWriteLock.writeLock().unlock();
		}
		return null;

	}

	public static Song findSongByIdAndName(Integer id, String song_name) {

		try {
			readWriteLock.writeLock().tryLock(3, TimeUnit.SECONDS);
			if (songsMap == null) {
				init();
			}

			return SongsQuery.findSongByIDAndName(id, song_name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			readWriteLock.writeLock().unlock();
		}

		return null;
	}
	
	public static Song findSongByPath( String song_name_path) {

		try {
			readWriteLock.writeLock().tryLock(3, TimeUnit.SECONDS);
			if (songsMap == null) {
				init();
			}

			return SongsQuery.findSongByPath(song_name_path);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			readWriteLock.writeLock().unlock();
		}

		return null;
	}

	public static Song addSong(Song song) throws Exception {
		Song newSong = null;
		try {
			readWriteLock.writeLock().tryLock(3, TimeUnit.SECONDS);
			if (songsMap == null) {
				init();
			}
			SongsQuery.createSong(song);
			newSong = SongsQuery.findSongByIDAndName(song.getId(), song.getSongName());
			songsMap.put(newSong.getId(), newSong);
		} finally {
			readWriteLock.writeLock().unlock();
		}
		return newSong;

	}

}
