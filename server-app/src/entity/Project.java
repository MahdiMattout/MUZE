package entity;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Projects")
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@DatabaseField(id = true)
	public static int id;
	@DatabaseField
	public static String title;
	@DatabaseField
	public static String content;
	@DatabaseField
	public static boolean isNew = false;
	@DatabaseField
	public static int userId;

	public Project() {
		super();
	}
	public Project(String title, String content, boolean isNew) {
		super();
		this.title = title;
		this.content = content;
		this.isNew = isNew;
	}

	public Project(String title, String content, boolean isNew, int userId) {
		super();
		this.title = title;
		this.content = content;
		this.isNew = isNew;
		this.userId = userId;
	}

	public Project(int id, String title, String content, boolean isNew, int userId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.isNew = isNew;
		this.userId = userId;
	}

	public Project(int id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
