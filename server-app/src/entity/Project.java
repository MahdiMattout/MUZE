//package entity;
//
//import java.io.Serializable;
//
//import com.j256.ormlite.field.DatabaseField;
//import com.j256.ormlite.table.DatabaseTable;
//
//@DatabaseTable(tableName = "Projects")
//public class Project implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	@DatabaseField(id = true)
//	public int id;
//	@DatabaseField
//	public String title;
//	@DatabaseField
//	public String content;
//	@DatabaseField
//	public boolean isNew = false;
//	@DatabaseField
//	public int userId;
//
//	public Project() {
//		super();
//	}
//	public Project(String title, String content, boolean isNew) {
//		super();
//		this.title = title;
//		this.content = content;
//		this.isNew = isNew;
//	}
//
//	public Project(String title, String content, boolean isNew, int userId) {
//		super();
//		this.title = title;
//		this.content = content;
//		this.isNew = isNew;
//		this.userId = userId;
//	}
//
//	public Project(int id, String title, String content, boolean isNew, int userId) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.content = content;
//		this.isNew = isNew;
//		this.userId = userId;
//	}
//
//	public Project(int id, String title, String content) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.content = content;
//	}
//
//	public boolean isNew() {
//		return this.isNew;
//	}
//
//	public void setNew(boolean isNew) {
//		this.isNew = isNew;
//	}
//
//	public int getId() {
//		return this.id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getTitle() {
//		return this.title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getContent() {
//		return this.content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public int getUserId() {
//		return this.userId;
//	}
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
//
//}
