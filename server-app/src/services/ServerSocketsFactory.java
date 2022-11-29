package services;

public class ServerSocketsFactory {

	public static void createSockets() {
		ProjectEchoServer projectEchoServer = new ProjectEchoServer();
		projectEchoServer.start();
		UserEchoServer userEchoServer = new UserEchoServer();
		userEchoServer.start();
		SongEchoServer songEchoServer = new SongEchoServer();
		songEchoServer.start();
		FileServer fserver = new FileServer();
		fserver.start();
	}

}
