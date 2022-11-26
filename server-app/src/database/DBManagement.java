package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class DBManagement {

    /**
     * Connect to the MUZE.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
         String url = "jdbc:sqlite:MUZE.db";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    
    
    public void createUserTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:MUZE.db";
        
        String sql = "CREATE TABLE IF NOT EXISTS User (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	firstName  text NOT NULL\n"
                + "	lastName text NOT NULL\n"
                
                + "	username text NOT NULL,\n"
                + "	password  text NOT NULL\n"
                + "	emailAddress text\n"
                + "	isNew  boolean NOT NULL\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void createSongTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:MUZE.db";
        
        String sql = "CREATE TABLE IF NOT EXISTS Song (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	song_filePath text NOT NULL\n"
                + "	song_name text NOT NULL\n"
                + "	uploader_id integer NOT NULL\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    // ----------- FOR USERS: insert update delete
    /**
     * Insert a new row into the User table
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param emailAddress
     * @param isNew
     * @param songFileString
     * @param songName
     * 
     * 
     */
    public void insertUser (int id = "", String firstName ,String lastName, String username, String password, String emailAddress, boolean isNew) {
        String sql = "INSERT INTO User(id, firstName, lastName, username, password,  emailAddress, isNew) VALUES(?,?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);

            pstmt.setString(4, username);
            pstmt.setString(5, password);

            pstmt.setString(6, emailAddress);

            pstmt.setBoolean(7, isNew);


            pstmt.executeUpdate();
            
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        
//        if(isNew == true) {
//        // insert the first song
//        // user = select the user by username
//        insertSong(user.getId(),  user.getSongName(), user.getSongFileString() );
//        }
        
    }
    
    
    public void updateUser(int id, String firstName ,String lastName, String username, String password, String emailAddress, boolean isNew) {
        String sql = "UPDATE User SET firstName = ? , "
                + "lastName = ? "
                + "username = ? "
                + "password = ? "
                + "emailAddress = ? "
                + "isNew = ? "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

           
        	   pstmt.setString(1, firstName);
               pstmt.setString(2, lastName);

               pstmt.setString(3, username);
               pstmt.setString(4, password);

               pstmt.setString(5, emailAddress);

               pstmt.setBoolean(6, isNew);

               pstmt.setInt(7, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void deleteIser(int id) {
        String sql = "DELETE FROM User WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    
    
    // ---------------- FOR SONGS : insert update delete
    /**
     * Insert a new row into the User table
     *
     * @param id ""
     * @param song_name
     * @param song_filePath
     * @param uploader_id
     * 
     */
    public void insertSong(int id = "", int uploader_id, String song_name, String song_filePath ) {
        String sql = "INSERT INTO Song(id, uploader_id, song_name, song_filePath) VALUES(?,?, ?, ?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	
            pstmt.setInt(1, id);
            pstmt.setInt(2, uploader_id);
            pstmt.setString(3, song_name);
            pstmt.setString(4, song_filePath);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateSong(int id,int uploader_id, String song_name, String song_filePath) {
        String sql = "UPDATE User SET uploader_id = ? , "
                + "song_name = ? "
                + "song_filePath = ? "
           
                + "WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

           
        	   pstmt.setInt(1, uploader_id);
               pstmt.setString(2, song_name);

               pstmt.setString(3, song_filePath);


               pstmt.setInt(4, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteSong(int id) {
        String sql = "DELETE FROM Song WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    

}