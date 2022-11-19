package entity;

import java.io.Serializable;
import services.PasswordHasher;

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
	private String email;
	private String address;
	private boolean isNew = false;
	public final PasswordHasher passwordHasher = new PasswordHasher();

	public User(int id, String firstName, String lastName, String username, 
			String email, String address, String password, 
			boolean isNew) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = this.passwordHasher.hashPassword(password);
		this.email = email;
		this.address = address;
		// list of downloaded songs
		this.isNew = isNew;
	}

	public User(String firstName, String lastName, String username, String email, String address, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = this.passwordHasher.hashPassword(password);
		this.email = email;
		this.address = address;
	}

	public User(String firstName, String lastName, String username,String email, String address, String password, boolean isNew) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = this.passwordHasher.hashPassword(password);
		this.email = email;
		this.address = address;
		this.isNew = isNew;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = this.passwordHasher.hashPassword(password);
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
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
