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
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

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

		private File handleRequest() throws Exception {
			File f = null;
			InputStream input = clientSocket.getInputStream();
//			ObjectInputStream ois = new ObjectInputStream(input);
//			int size = ois.readInt();
//			AudioInputStream audio = new AudioInputStream((TargetDataLine) clientSocket.getInputStream());
//			audio.read(b, 0, b.length);
			FileOutputStream fs = new FileOutputStream(Constants.songsFolder);
			int fileSize = input.read();
			byte[] b = new byte[fileSize];
			input.read(b, 0, b.length);
			fs.write(b, 0, b.length);
			return f;

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
