package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.TargetDataLine;

import utils.Constants;

public class FileClient {

	private static Socket clientSocket;
	private static ObjectInputStream os;
	private static ObjectInputStream ois;

	private static void establishConnection() throws UnknownHostException, IOException {
//		if (clientSocket == null) {
		clientSocket = new Socket(Constants.SERVER_IP(), Constants.FILE_TRANSFER_PORT);
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

//	public static Song createSong(Song song) throws IOException, ClassNotFoundException {
//		establishConnection();
//		ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());
//		os.writeObject(song);
//		ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
//		Song receivedSong = (Song) ois.readObject();
//		System.out.println("--------------------------- created song id " + receivedSong.getId()
//				+ " -------------------------------------");
//		//		if (receivedUser != null && receivedUser.getId() > 0)
//		//			stopConnection();
//		return receivedSong;
//	}
	
	public static void init() throws IOException {
		establishConnection();
	}
	
	public static void uploadSong(File file) throws IOException {
		FileClient.init();
		byte[] b = new byte[(int) file.length()];
//		InputStream is = clientSocket.getInputStream();
//		ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());
//		os.writeInt((int) file.length());
		FileInputStream fis= new FileInputStream(file.getAbsoluteFile());
		fis.read(b, 0, b.length);
		OutputStream os = clientSocket.getOutputStream();
		os.write((int) file.length());
		os.write(b, 0, b.length);
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

