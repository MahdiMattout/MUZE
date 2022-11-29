package entity;

import java.io.Serializable;

public class Project implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public int id;
	public String uploader_name;
	public String song_name;
	public boolean isNew = false;
	public int userId;
	public int songId;

	public Project() {
		super();
	}
	public Project(String uploader_name, String song_name, boolean isNew) {
		super();
		this.uploader_name = uploader_name;
		this.song_name = song_name;
		this.isNew = isNew;
	}

	public Project(String uploader_name, String song_name, boolean isNew, int userId, int songId) {
		super();
		this.uploader_name = uploader_name;
		this.song_name = song_name;
		this.isNew = isNew;
		this.userId = userId;
		this.songId = songId;
	}

	public Project(int id, String uploader_name, String song_name, boolean isNew, int userId, int songId) {
		super();
		this.id = id;
		this.uploader_name = uploader_name;
		this.song_name = song_name;
		this.isNew = isNew;
		this.userId = userId;
		this.songId = songId;
	}

	public Project(int id, String uploader_name, String song_name) {
		super();
		this.id = id;
		this.uploader_name = uploader_name;
		this.song_name = song_name;
	}

	public boolean isNew() {
		return this.isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public int getId() {
		return this.id;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	public int getSongId() {
		return this.songId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUploaderName() {
		return this.uploader_name;
	}

	public void setUploaderName(String uploader_name) {
		this.uploader_name = uploader_name;
	}

	public String getSongName() {
		return this.song_name;
	}

	public void setSongName(String song_name) {
		this.song_name = song_name;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
