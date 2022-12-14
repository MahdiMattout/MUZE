package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import entity.Song;
import utils.Constants;

public class SongEchoClient {

	private static Socket clientSocket;
	private static ObjectInputStream os;
	private static ObjectInputStream ois;

	private static void establishConnection() throws UnknownHostException, IOException {
//		if (clientSocket == null) {
		clientSocket = new Socket(Constants.SERVER_IP(), Constants.SONG_PORT);
		clientSocket.setSoTimeout(50000);
//		}

	}

//	public static User authenticateUser(User user) throws IOException, ClassNotFoundException {
//		establishConnection();
//		os = new ObjectOutputStream(clientSocket.getOutputStream());
//		os.writeObject(user);
//		ois = new ObjectInputStream(clientSocket.getInputStream());
//		User receivedUser = (User) ois.readObject();
//		System.out.println("---------------------------- received user id " + receivedUser.getId()
//				+ "--------------------------------");
//		//		if (receivedUser != null && receivedUser.getId() > 0)
//		//			stopConnection();
//		return receivedUser;
//	}

	public static Song createSong(Song song) throws IOException, ClassNotFoundException {
		establishConnection();
		ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());
		os.writeObject(song);
		ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
		Song receivedSong = (Song) ois.readObject();
		System.out.println("--------------------------- created song id " + receivedSong.getId()
				+ " -------------------------------------");
		//		if (receivedUser != null && receivedUser.getId() > 0)
		//			stopConnection();
		return receivedSong;
	}

	public static void stopConnection() {
		try {
			os.close();
			ois.close();
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		InetAddress ip;
		String hostname;
		try {
			ip = InetAddress.getLocalHost();
			hostname = ip.getHostName();
			System.out.println(ip.getHostAddress());
			System.out.println("Your current IP address : " + ip);
			System.out.println("Your current Hostname : " + hostname);

		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
	}
}

