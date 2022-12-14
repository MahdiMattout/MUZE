package entity;

import java.io.File;
import java.io.Serializable;

import javax.swing.DefaultListModel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "User")
public class User implements Serializable {

	/**
	 * 
	 */
	
	// PS: ADJUST: User has only to save the name of the first song. all others can be found in the Song table
	
	private static final long serialVersionUID = 1L;
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String firstName;
	@DatabaseField
	private String lastName;
	@DatabaseField
	private String username;
	@DatabaseField
	private String password;
	@DatabaseField
	private String emailAddress;
	@DatabaseField
	private boolean isNew = false;
	
	private File songFile;
	
	@DatabaseField
	private String songFileString;

	@DatabaseField
	private String songName;
	
	private final DefaultListModel<String> songsGivenNameList = new DefaultListModel<>(); // needs to be updated on uploads
	
	private final DefaultListModel<File> songsFileList = new DefaultListModel<>(); // needs to be updated on uploads
		
	public User() {
//		this.firstName = "";
//		this.lastName = "";
//		this.username = "";
//		this.password = "";
//		this.emailAddress = "";
//		this.isNew = false;
		super();
	}
	

	public User(int id, String firstName, String lastName, String username, 
			String emailAddress, String password, boolean isNew, String song, File songFile) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;

		this.emailAddress = emailAddress;

		this.isNew = isNew;
		
		this.songName = song;
		this.songFile = songFile;
		
		this.songFileString = songFile.getAbsolutePath();
		
		this.songsGivenNameList.add(0, songName); // add the name of each song

		this.songsFileList.add(0, songFile); // add the file of each song
	}

	public User(String firstName, String lastName, String username, String emailAddress,String password,  String song, File songFile) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
		
		this.songName = song;
		this.songFile = songFile;
		this.songFileString = songFile.getAbsolutePath();
		
		this.songsGivenNameList.add(0, songName); // add the name of each song
		this.songsFileList.add(0, songFile); // add the file of each song

	}

	public User(String firstName, String lastName, String username,String emailAddress, String password, boolean isNew, String song,  File songFile) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;

		this.emailAddress = emailAddress;

		this.isNew = isNew;
		
		this.songName = song;
		this.songFile = songFile;
		
		this.songFileString = songFile.getAbsolutePath();
		
		this.songsGivenNameList.add(0, songName); // add the name of each song

		this.songsFileList.add(0, songFile); // add the file of each song
	}
	

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.isNew = false;
	}
	

	public User(int id) {
		super();
		this.id = id;
	}
	
	
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return emailAddress;
	}

	public void setEmail(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DefaultListModel<String> getSongsGivenNameList() {
		return songsGivenNameList;
	}

	public DefaultListModel<File> getSongsFileList() {
		return songsFileList;
	}
	
	public File getSongFile() {
		return songFile;
	}

	public void getSongFile( File songFile) {
		this.songFile = songFile;
	}
	
	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}
	
	public String getSongFileString() {
		return songFileString;
	}

	public void setSongFileString(String songFileString) {
		this.songFileString = songFileString;
	}

	public DefaultListModel<File> getSongsList() {
		return songsFileList;
	}


}

