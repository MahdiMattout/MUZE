package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Project;
import utils.Constants;

public class ProjectEchoClient {

	// http://www.jgyan.com/networking/how%20to%20send%20object%20over%20socket%20in%20java.php
	// https://www.baeldung.com/a-guide-to-java-sockets

	private static Socket clientSocket;
	private static ObjectOutputStream os;
	private static ObjectInputStream ois;
//	private static List<Project> projects;

//	public static void setProjects(List<Project> projects) {
//		ProjectEchoClient.projects = projects;
//	}
//
//	public static List<Project> getProjects() throws SQLException {
//		return ProjectsQuery.findAllProjects();
//	}
//	
	

	private static void establishConnection() throws UnknownHostException, IOException {
		// if (clientSocket == null) {
		clientSocket = new Socket(Constants.SERVER_IP(), Constants.PROJECT_PORT);
		clientSocket.setSoTimeout(50000);
		// }

	}
	
	public static Object[][] findProjectsForDataTable() throws UnknownHostException, ClassNotFoundException, IOException, SQLException {
		Object[][] arr = null;
		int rowsCount = 0;
		int i = 0;
		arr = new Object[rowsCount][4];
//		while (i < rowsCount) {
//			arr[i][0] = projects.get(i).getUploaderName();
//			arr[i][1] = projects.get(i).getSongName();
//			arr[i][2] = projects.get(i).getSongId();
//			arr[i][3] = projects.get(i).getUserId();
//			i++;
//		}
		return arr;
	}
	
//	public static void AddProjectToProjects(Project p) throws SQLException {
//		if (ProjectEchoClient.projects == null) {
//			ProjectEchoClient.projects = new ArrayList<Project>();
//		}
//		List<Project> ps = ProjectEchoClient.getProjects();
//		ps.add(p);
//		ProjectEchoClient.setProjects(ps);
//	}
	
	public static Project sendProject(Project project) throws IOException, ClassNotFoundException, SQLException {
		establishConnection();
		os = new ObjectOutputStream(clientSocket.getOutputStream());
		os.writeObject(project);
		ois = new ObjectInputStream(clientSocket.getInputStream());
		ois.readObject();
		Project receivedProject = (Project) ois.readObject();
//		AddProjectToProjects(receivedProject);
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
