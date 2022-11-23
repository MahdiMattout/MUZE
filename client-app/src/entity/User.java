package entity;

import java.io.Serializable;

import javax.swing.DefaultListModel;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String emailAddress;
	DefaultListModel<String> songsList = new DefaultListModel<>(); // needs to be updated on uploads
	//private String song;
	private boolean isNew = false;

	public User(int id, String firstName, String lastName, String username, 
			String emailAddress, String password, boolean isNew, String song) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
		// list of downloaded songs: updates on uploads
		//this.song = song;
		this.songsList.add(0, song);
		this.isNew = isNew;
	}

	public User(String firstName, String lastName, String username, String emailAddress,String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
	}

	public User(String firstName, String lastName, String username,String emailAddress, String password, boolean isNew, String song) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
		// list of downloaded songs: updates on uploads
		//this.song = song;
		this.songsList.add(0, song);
		this.isNew = isNew;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
	
	public DefaultListModel<String> getSongsList() {
		return songsList;
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

}
