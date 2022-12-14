package services;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

import database.ProjectsQuery;
import entity.Project;
import uitls.Constants;

public class ProjectEchoServer extends Thread {
	
	private static ServerSocket serverSocket;
	private static Socket clientSocket;

	@Override
	public void run() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void init() throws IOException {
		establishConnection();
		listen();
	}

	private static void establishConnection() throws IOException {
		if (serverSocket == null) {
			serverSocket = new ServerSocket(Constants.PROJECT_PORT);
			System.out.println("------------------------------------ server started listening to port "
					+ Constants.PROJECT_PORT + " -------------------------------------------");
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
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			ProjectEchoServer.init();
		} catch (IOException e) {
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
				Project receivedProject = handleRequest();
				// if user does not exists respond with new user with negative id
				if (receivedProject == null)
					receivedProject = new Project(-1, "", "");
				respond(receivedProject);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}

		private Project handleRequest() throws IOException, ClassNotFoundException {
			Project project = null;
			// getting object input stream from client
			ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
			// reading object
			Object obj = ois.readObject();
			Project receivedProject = (Project) obj;
			if (receivedProject.isNew()) {
				try {
					project = createProject(receivedProject);
				} catch (InterruptedException | SQLException e) {
					e.printStackTrace();
				}
			} else {
				// search if project exits
				project = ProjectsQuery.findProjectByUploader_SongName( receivedProject.getSongName(), receivedProject.getUploaderName());
			}
			System.out.println("project created" + receivedProject.getId());
			// ois.close();
			return project;
		}

		private Project createProject(Project project) throws InterruptedException, SQLException {
			ProjectsQuery.createProject(project);
			return ProjectsQuery.findProjectBySongId_UserId(project.getSongId(), project.getUserId());
		}
		
		
		private void respond(Project project) throws IOException {
			// reply to client with user
			ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
			oos.writeObject(project);
			// close resources

			// oos.close();
			// clientSocket.close();
		}

	}
}
