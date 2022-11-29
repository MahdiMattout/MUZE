package services;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
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
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

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
		FileInputStream fs = new FileInputStream(file);
		fs.read(b);
		OutputStream out = clientSocket.getOutputStream();
		byte[] path = file.getName().getBytes();
		out.write(path);
//		BufferedInputStream buffered = new BufferedInputStream(clientSocket.getInputStream());
//		AudioInputStream ais = AudioSystem.getAudioInputStream(buffered);
//		DataOutputStream data = new DataOutputStream(clientSocket.getOutputStream());
//		data.writeUTF(file.getAbsolutePath());
//		data.writeInt((int) file.length());
//		ais.read(b, 0, b.length);
//		FileOutputStream fs= new FileOutputStream(Constants.TRANSFERRED_FILES_PATH+file.getName());
		out.write(b);
		out.flush();
		fs.close();
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

