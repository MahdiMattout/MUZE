package view;

import java.awt.Color;
	import java.awt.Font;
	import java.awt.MenuItem;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.io.File;
	import java.io.IOException;
	import java.net.UnknownHostException;
	import java.sql.SQLException;
	import java.util.List;

	import javax.security.auth.Refreshable;
	import javax.swing.GroupLayout;
	import javax.swing.GroupLayout.Alignment;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JFileChooser;
	import javax.swing.JLabel;
	import javax.swing.JMenuItem;
	import javax.swing.JPanel;
	import javax.swing.JPopupMenu;
	import javax.swing.JRadioButton;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.LayoutStyle.ComponentPlacement;
	import javax.swing.SwingConstants;
	import javax.swing.SwingUtilities;
	import javax.swing.UIManager;
	import javax.swing.border.MatteBorder;
	import javax.swing.plaf.FontUIResource;
	import javax.swing.table.AbstractTableModel;
	import javax.swing.table.DefaultTableModel;

	import entity.Project;
	import entity.Song;
	import entity.User;
	import kuusisto.tinysound.Music;
	import kuusisto.tinysound.TinySound;
	import services.ProjectEchoClient;
	import services.Singleton;
	import services.SongEchoClient;

public class ClientProjectPanel extends JPanel {

		/**
		 * 
		 */
		 private User user;
		 private File songFile;

		 private static final long serialVersionUID = 1L;
		
		 private JTable table;
		 private JLabel projectLabel = new JLabel("");
		 private JLabel refreshLabel = new JLabel("");
		
		 private FieldPanel  songNamePanel = new FieldPanel("/resources/name.png", "song name");
		 private JLabel      saveSongLabel = new JLabel(""); // icon to add and save a song


		 private JLabel      uploadLabel = new JLabel("Upload Song"); //  button to choose the file
		 
		 JButton btnSearch = new JButton("Search"); // search for song
		 
		 // Options per song
		 final JPopupMenu listMenu = new JPopupMenu();
		 JMenuItem playPlaylistOption = new JMenuItem("Play");
		 JMenuItem pausePlaylistOption = new JMenuItem("Pause");
		 JMenuItem resumePlaylistOption = new JMenuItem("Resume");
		 JMenuItem replayPlaylistOption = new JMenuItem("Replay");
		 JMenuItem playWithLoopPlaylistOption = new JMenuItem("Play with looping");
		 JMenuItem downlaodPlaylistOption = new JMenuItem("Download");
			
		/**
		 * Create the panel.
		 * @throws IOException 
		 * @throws ClassNotFoundException 
		 * @throws UnknownHostException 
		 * @throws SQLException 
		 */
		public ClientProjectPanel(User user) throws UnknownHostException, ClassNotFoundException, IOException {
			
			Singleton.setCurrentUser(user); // current user after signing in/up
	        this.user = user;
			
			init();
			FontUIResource font = new FontUIResource("Andalus", Font.PLAIN, 24);
			UIManager.put("Table.font", font);
			UIManager.put("Table.foreground", Color.black);
			setBackground(Color.WHITE);

			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);

			JPanel panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			
			
			///-----

		

			projectLabel.setIcon(new ImageIcon(ClientPanel.class.getResource("/resources/account.png")));
			

			JLabel lblProjects = new JLabel("Welcome to  Your MUZE!");
			lblProjects.setHorizontalAlignment(SwingConstants.CENTER);
			lblProjects.setForeground(Color.BLACK);
			lblProjects.setFont(new Font("Andalus", Font.BOLD, 40));
			lblProjects.setBackground(Color.WHITE);

			
			// First part of the page: get the name of any song and upload it
			songNamePanel = new FieldPanel("/resources/title.png", "Song Name");
			songNamePanel.setBackground(Color.WHITE);
			saveSongLabel.setIcon(new ImageIcon(ProjectPanel.class.getResource("/resources/save.png")));
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup().addGap(42)
							.addComponent(saveSongLabel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(24, Short.MAX_VALUE)));
			gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(saveSongLabel,
					GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE));
			panel.setLayout(gl_panel);		
			
			
			// Second part of the page: a table showing all users and songs + Refresh
			// And Clicking on each has to show the menu options
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup().addGap(26)
							.addComponent(projectLabel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblProjects, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(148, Short.MAX_VALUE)));
			gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
					.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addComponent(lblProjects, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
							.addComponent(projectLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addContainerGap()));
			panel_1.setLayout(gl_panel_1);
			
			GroupLayout groupLayout = new GroupLayout(this);
			

			JScrollPane scrollPane = new JScrollPane();
		
			// HERE !! NEED TO LET IT BE LIKE THE SERVER: DISPLAY ALL SONGS
			try {
				//table = new JTable(ProjectEchoClient.findProjectsForDataTable(), new String[] { "User", "Song" });
				table = new JTable();
			} catch (Exception e) {
			//catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			table.setRowHeight(50);
			table.getTableHeader().setBackground(Color.white);
			table.getTableHeader().setFont(new Font("Andalus", Font.BOLD, 22));
		    scrollPane.setBackground(Color.white);
			scrollPane.setViewportView(table);
			table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
			setLayout(groupLayout);

		};
		
		public void init() {
			addMouseListener();
		}

		
		  // ----------- start of mouse events
		private void addMouseListener() {
				
				// ----- end of mouse events
		}
	}
