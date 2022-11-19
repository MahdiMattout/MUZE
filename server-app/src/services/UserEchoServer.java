package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import entity.User;
import uitls.Constants;

public class UserEchoServer extends Thread {
	// https://www.baeldung.com/a-guide-to-java-sockets
	// https://stackoverflow.com/questions/27736175/how-to-send-receive-objects-using-sockets-in-java
	private static ServerSocket serverSocket;
	private static Socket clientSocket;

	@Override
	public void run() {
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void init() throws IOException {
		establishConnection();
		listen();
	}

	private static void establishConnection() throws IOException {
		if (serverSocket == null) {
			serverSocket = new ServerSocket(Constants.USERS_PORT);
			System.out.println("------------------------------------ server started listening to port "
					+ Constants.USERS_PORT + " -------------------------------------------");
			System.out.println("------------------------------------ server ip: "
					+ InetAddress.getLocalHost().getHostAddress() + " -------------------------------------------");
		}

	}

	private static void listen() throws IOException {
		establishConnection();
		while (true) {
			// waiting for request
			System.out.println(
					"------------------------------------ server waiting request  -------------------------------------------");
			clientSocket = serverSocket.accept();
			System.out.println(
					"------------------------------------ server received request  -------------------------------------------");
			// create new thread
			Thread t = new ClientHandler(clientSocket);
			// running the new thread
			t.run();
		}
	}

	private static void closeConnection() {
		try {
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			UserEchoServer.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static class ClientHandler extends Thread {

		final Socket s;

		public ClientHandler(Socket s) {
			this.s = s;
		}

		@Override
		public void run() {
			try {
				User receivedUser = handleRequest();
				// if user does not exists respond with new user with negative id
				if (receivedUser == null)
					receivedUser = new User(-1);
				respond(receivedUser);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private User handleRequest() throws IOException, ClassNotFoundException {
			User user = null;
			// getting object input stream from client
			ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
			// reading object
			Object obj = ois.readObject();
			// cast to user
			User receivedUser = (User) obj;
			if (receivedUser.isNew()) {
				try {
					user = createUser(receivedUser);
				} catch (InterruptedException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// search if user exits
				user = UsersCache.findUserByUsernameAndPassword(receivedUser.getUsername(), receivedUser.getPassword());
			}
			System.out.println("server" + receivedUser);
			// ois.close();
			return user;

		}

		private User createUser(User u) throws InterruptedException, SQLException {
			return UsersCache.addUser(u);
		}

		private void respond(User user) throws IOException {
			// reply to client with user
			ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
			oos.writeObject(user);
			// close resources

			// oos.close();
			// clientSocket.close();
		}

	}
}
