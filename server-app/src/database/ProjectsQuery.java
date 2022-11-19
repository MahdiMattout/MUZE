package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Project;
import services.UsersCache;

public class ProjectsQuery extends DbManager {

	public static Project findProjectByTitleAndUser(String title, int userId) {
		try {
			PreparedStatement stmt = establishConnection()
					.prepareStatement("SELECT * FROM project p WHERE p.title = ? AND p.user_id = ?");
			stmt.setString(1, title);
			stmt.setInt(2, userId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int projectId = rs.getInt(1);
				String content = rs.getString(3);
				return new Project(projectId, title, content);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<Project> findAllProjects() {
		List<Project> projects = new ArrayList<Project>();
		try {
			PreparedStatement stmt = establishConnection().prepareStatement("SELECT * FROM project");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int projectId = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				int userId = rs.getInt(4);
				Project p = new Project(projectId, title, content, false, userId);
				projects.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projects;
	}

	public static Object[][] findProjectsForDataTable() {
		Object[][] arr = null;
		try {
			PreparedStatement stmt = establishConnection().prepareStatement("SELECT * FROM project");
			ResultSet rs = stmt.executeQuery();
			rs.last(); //move cursor to the last record
			int rowsCount = rs.getRow();
			rs.beforeFirst();
			int i = 0;
			arr = new Object[rowsCount][3];
			while (rs.next()) {
				String title = rs.getString(2);
				String content = rs.getString(3);
				int userId = rs.getInt(4);
				arr[i][0] = title;
				arr[i][1] = content;
				arr[i][2] = UsersCache.findUserById(userId).getUsername();
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	public static void createProject(Project project) throws SQLException {
		String query = "INSERT INTO project (" + " title ," + " content," + " user_id ) VALUES ( ?, ?, ? )";
		System.out.println(query);
		PreparedStatement st = establishConnection().prepareStatement(query);
		st.setString(1, project.getTitle());
		st.setString(2, project.getContent());
		st.setInt(3, project.getUserId());

		// execute the preparedstatement insert
		st.executeUpdate();
		st.close();
		return;
	}

}
