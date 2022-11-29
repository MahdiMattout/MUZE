package services;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Base64;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.TargetDataLine;

import entity.Song;
import uitls.Constants;

public class FileServer extends Thread {
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
			serverSocket = new ServerSocket(Constants.FILE_TRANSFER_PORT);
			System.out.println("------------------------------------ server started listening to port "
					+ Constants.FILE_TRANSFER_PORT + " -------------------------------------------");
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
			FileServer.init();
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
				handleRequest();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void handleRequest() throws Exception {
			File f;
			InputStream in = clientSocket.getInputStream();
//			FileInputStream fs = new FileInputStream(filePath);
			byte [] name = new byte[64000];
			byte[] file = new byte[17000000];
			in.read(name);
			String fileName = new String(name);
			System.out.println(fileName);
			in.read(file);
			FileOutputStream fs = new FileOutputStream(Constants.TRANSFERRED_FILES_PATH + fileName);
			fs.write(file);
//			OutputStream os = clientSocket.getOutputStream();
//			os.write(b, 0, b.length);
			System.out.println("Habibi Khaled");
			fs.flush();
			fs.close();
		}

//		private void respond(Song song) throws IOException {
//			// reply to client with user
//			ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
//			oos.writeObject(song);
//			// close resources
//
//			// oos.close();
//			// clientSocket.close();
//		}

	}
}
