package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import entity.User;

public class UserSocket {

	private ServerSocket serverSocket;
	private Socket clientSocket;

	public void start(int port) {
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				clientSocket = serverSocket.accept();
				Thread t = new ClientHandler(clientSocket);
				t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UserSocket server = new UserSocket();
		server.start(5000);
	}

	class ClientHandler extends Thread {

		final Socket s;

		public ClientHandler(Socket s) {
			this.s = s;
		}

		@Override
		public void run() {
			try {
				ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
				Object obj = ois.readObject();
				User u = (User) obj;
				System.out.println("server" + u);
				ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
				oos.writeObject(u);
				// close resources
				ois.close();
				oos.close();
				clientSocket.close();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
