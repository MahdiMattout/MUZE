/**
 * 
 */
package entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "Song")
public class Song implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String song_name;
	@DatabaseField
	private String song_filePath; // this has to be casted to File once queried
	@DatabaseField
	private int uploader_id; // id of the user who added this song
	
	private String uploader_username;
	
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
	
	public Song( String uploader_username, String name_song) {
		super();
		this.uploader_username = uploader_username;
		this.song_name = name_song;
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

	
	public String getSongFilePathUsingName(String uploader_usermame, String name_song) {
		return song_filePath;
	}


}
