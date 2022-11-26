package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import entity.Song;
import uitls.Constants;

public class SongEchoServer extends Thread {
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
			serverSocket = new ServerSocket(Constants.SONG_PORT);
			System.out.println("------------------------------------ server started listening to port "
					+ Constants.SONG_PORT + " -------------------------------------------");
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

	@SuppressWarnings("unused")
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
			SongEchoServer.init();
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
				Song receivedSong = handleRequest();
				// if user does not exists respond with new user with negative id
				if (receivedSong == null)
					receivedSong = new Song(-1);
				respond(receivedSong);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private Song handleRequest() throws Exception {
			Song song = null;
			// getting object input stream from client
			ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
			// reading object
			Object obj = ois.readObject();
			// cast to user
			Song receivedSong= (Song) obj;
			song = SongsCache.findSongByPath(receivedSong.getSongFilePath());
			if (song == null) {
				try {
					song = createSong(receivedSong);
				} catch (InterruptedException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("server" + receivedSong);
			// ois.close();
			return song;

		}

		private Song createSong(Song s) throws Exception {
			return SongsCache.addSong(s);
		}

		private void respond(Song song) throws IOException {
			// reply to client with user
			ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
			oos.writeObject(song);
			// close resources

			// oos.close();
			// clientSocket.close();
		}

	}
}
