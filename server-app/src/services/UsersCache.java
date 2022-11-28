package services;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import database.UsersQuery;
import entity.User;

public class UsersCache {

	// https://docs.oracle.com/javase/tutorial/essential/concurrency/collections.html
	// https://dzone.com/articles/java-concurrency-synchronization
	private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
	private static ConcurrentHashMap<Integer, User> usersMap;
	private static ConcurrentHashMap<Integer, Integer> usersFilesMap;

	private static void init() {
		usersMap = UsersQuery.findAllUsers();

	}

	public static User findUserById(Integer id) {

		try {
			readWriteLock.writeLock().tryLock(3, TimeUnit.SECONDS);
			if (usersMap == null) {
				init();
			}
			return UsersQuery.findUserById( id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			readWriteLock.writeLock().unlock();
		}

		return null;
	}

	public static User findUserByUsername(String username) {

		try {
			readWriteLock.writeLock().tryLock(3, TimeUnit.SECONDS);
			if (usersMap == null) {
				init();
			}
			return UsersQuery.findUserByUsername(username);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			readWriteLock.writeLock().unlock();
		}

		return null;
	}
	
	public static User findUserByUsernameAndPassword(String username, String password) {

		try {
			readWriteLock.writeLock().tryLock(3, TimeUnit.SECONDS);
			if (usersMap == null) {
				init();
			}
			return UsersQuery.findUserByUsernameAndPassword(username, password);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			readWriteLock.writeLock().unlock();
		}

		return null;
	}

	public static User addUser(User user) throws Exception {
		User newUser = null;
		try {
			readWriteLock.writeLock().tryLock(3, TimeUnit.SECONDS);
			if (usersMap == null) {
				init();
			}
			UsersQuery.createUser(user);
			newUser = UsersQuery.findUserByUsername(user.getUsername());
			usersMap.put(newUser.getId(), newUser);
		} finally {
			readWriteLock.writeLock().unlock();
		}
		return newUser;

	}

}
