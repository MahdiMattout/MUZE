/**
 * 
 */
package entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author mahdimattout
 *
 */
@DatabaseTable(tableName = "UserSongIds")
public class UserSongId implements Serializable {
	private static final long serialVersionUID = 1L;
	@DatabaseField(generatedId = true)
	public int id;
	@DatabaseField
	public int userId;
	@DatabaseField
	public int songId;

	public UserSongId() {
		super();
		this.userId = -1;
		this.songId = -1;
	}
	public UserSongId(int userId, int songId) {
		super();
		this.userId = userId;
		this.songId = songId;
	}

}
