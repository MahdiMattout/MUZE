package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import entity.Project;
import entity.User;
import services.UsersCache;

public class ProjectsQuery extends DbManager {
	private static QueryBuilder<Project, Integer> queryBuilder = null;
	public static Where<Project, Integer> where =null;

	public static Project findProjectByTitleAndUser(String title, int userId) {
		try {
			establishConnection();
			queryBuilder = DbManager.getProjectDao().queryBuilder();
			where = queryBuilder.where();
			List<Project> projects = where.and(where.eq("title", title), where.eq("userId", userId)).query();
//			ResultSet rs = stmt.executeQuery(MessageFormat.format("SELECT * FROM project p WHERE p.title = {0} AND p.user_id = {1}", title, userId));
			if (projects.size() == 1) {
				return projects.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<Project> findAllProjects() throws SQLException {
		return DbManager.getProjectDao().queryForAll();
	}

	public static Object[][] findProjectsForDataTable() {
		Object[][] arr = null;
		try {
			establishConnection();
			queryBuilder = DbManager.getProjectDao().queryBuilder();
			where = queryBuilder.where();
			List<Project> projects = findAllProjects();
			int rowsCount = projects.size();
			int i = 0;
			arr = new Object[rowsCount][3];
			while (i < rowsCount) {
				arr[i][0] = projects.get(i).getTitle();
				arr[i][1] = projects.get(i).getContent();
				arr[i][2] = UsersCache.findUserById(projects.get(i).getUserId()).getUsername();
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	public static void createProject(Project project) throws SQLException {
		String query = MessageFormat.format("INSERT INTO project (" + " title ," + " content," + " user_id ) VALUES ( {0}, {1}, {2} )", project.getTitle(), project.getContent(), project.getUserId());
		System.out.println(query);
		establishConnection();
		queryBuilder = DbManager.getProjectDao().queryBuilder();
		where = queryBuilder.where();
		// execute the preparedstatement insert
		DbManager.getProjectDao().create(project);
		return;
	}

}
