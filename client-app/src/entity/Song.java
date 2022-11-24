/**
 * 
 */
package entity;

import java.io.Serializable;

public class Song implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String fileName;
	private String filePath;
	
	public Song() {
		super();
		this.fileName = "";
		this.filePath = "";
	}

	public Song(String name, String path) {
		super();
		this.fileName = name;
		this.filePath = path;
	}
	public Song(int id, String name, String path) {
		super();
		this.id = id;
		this.fileName = name;
		this.filePath = path;
	}


}
