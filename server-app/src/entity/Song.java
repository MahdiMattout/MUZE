/**
 * 
 */
package entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "Songs")
public class Song implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String song_name;
	@DatabaseField
	private String song_filePath; // this has to be casted to File once queried
	
	public Song(int id, String name, String path) {
		super();
		this.id = id;
		this.song_name = name;
		this.song_filePath = path;
	}

	public Song(String name, String path) {
		super();
		this.song_name = name;
		this.song_filePath = path;
	}

}
