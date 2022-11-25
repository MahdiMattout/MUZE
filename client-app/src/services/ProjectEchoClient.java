package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import entity.Project;
import utils.Constants;

public class ProjectEchoClient {

	// http://www.jgyan.com/networking/how%20to%20send%20object%20over%20socket%20in%20java.php
	// https://www.baeldung.com/a-guide-to-java-sockets

	private static Socket clientSocket;
	private static ObjectOutputStream os;
	private static ObjectInputStream ois;

	private static void establishConnection() throws UnknownHostException, IOException {
		// if (clientSocket == null) {
		clientSocket = new Socket(Constants.SERVER_IP, Constants.PROJECT_PORT);
		clientSocket.setSoTimeout(50000);
		// }

	}

	public static Project sendProject(Project project) throws IOException, ClassNotFoundException {
		establishConnection();
		os = new ObjectOutputStream(clientSocket.getOutputStream());
		os.writeObject(project);
		ois = new ObjectInputStream(clientSocket.getInputStream());
		Project receivedProject = (Project) ois.readObject();
		System.out.println("---------------------------- received project id " + receivedProject.getId()
				+ "--------------------------------");
		// if (receivedUser != null && receivedUser.getId() > 0)
		// stopConnection();
		return project;
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
