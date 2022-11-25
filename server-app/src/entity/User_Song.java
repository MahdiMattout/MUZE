/**
 * 
 */
package entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "User_Song")
public class User_Song implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField
	private int song_id;
	@DatabaseField
	private int user_id;

	
	public User_Song(int id, int song_id, int user_id) {
		super();
		this.id = id;
		this.song_id = song_id;
		this.user_id = user_id;
		
	}

	public User_Song(int song_id, int user_id) {
		super();
		this.song_id = song_id;
		this.user_id = user_id;
		
	}
	
}
