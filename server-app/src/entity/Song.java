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
@DatabaseTable(tableName = "Songs")
public class Song implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String fileName;
	@DatabaseField
	private String filePath;
	
	public Song() {
		this.fileName = "";
		this.filePath = "";
	}

	public Song(String name, String path) {
		super();
		this.fileName = name;
		this.filePath = path;
	}

}
