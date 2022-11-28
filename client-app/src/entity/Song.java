/**
 * 
 */
package entity;

import java.io.Serializable;

public class Song implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String song_name;
	private String song_filePath; // this has to be casted to File once queried
	private int uploader_id; // id of the user who added this song
	
	public Song() {
		super();
	}
	
	public Song(int id) {
		super();
		this.id = id;
	}
	
	public Song(int id, int uploader_id, String name, String path) {
		super();
		this.id = id;
		this.song_name = name;
		this.song_filePath = path;
		this.uploader_id = uploader_id;
	}

	public Song( int uploader_id, String name, String path) {
		super();
		this.uploader_id = uploader_id;
		this.song_name = name;
		this.song_filePath = path;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUploaderId() {
		return uploader_id;
	}
	public void setUploaderId(int uploader_id) {
		this.uploader_id = uploader_id;
	}
	
	
	public void setSongName(String song_name) {
		this.song_name = song_name;
	}
	public String getSongName() {
		return song_name;
	}
	
	public void setSongFilePath(String song_filePath) {
		this.song_filePath = song_filePath;
	}
	public String getSongFilePath() {
		return song_filePath;
	}

	

}
