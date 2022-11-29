package utils;

import java.io.IOException;
import java.net.InetAddress;

public class Constants {
	public final static String SERVER_IP() throws IOException {
		InetAddress IP = InetAddress.getLocalHost();
		return IP.getHostAddress();
	}
	public final static int USER_PORT = 6666;
	public final static int PROJECT_PORT = 6667;
	public final static int SONG_PORT = 6668;
	public final static int FILE_TRANSFER_PORT = 6777;
	public final static String TRANSFERRED_FILES_PATH = "/Users/mahdimattout/Desktop/MUZE/MUZE/server-app/src/songs/";
}
