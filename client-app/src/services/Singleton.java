package services;

import entity.User;

public class Singleton {

	private static User currentUser;

	public static void setCurrentUser(User user) {
		if (currentUser == null)
			currentUser = user;
	}

	public static User getCurrentUser() {
		return currentUser;
	}

}
