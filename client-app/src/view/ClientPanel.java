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

public class ClientPanel extends JPanel {
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
	 */
	public ClientPanel(User user) {
		
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
		// And CLicking on each has to show the menu options
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
		
		
		GroupLayout gl_panel2 = new GroupLayout(panel);
		gl_panel2.setHorizontalGroup(gl_panel2.createParallelGroup(Alignment.LEADING).addGap(0, 751, Short.MAX_VALUE)
				.addGroup(gl_panel2.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(165, Short.MAX_VALUE)));
		gl_panel2.setVerticalGroup(gl_panel2.createParallelGroup(Alignment.LEADING).addGap(0, 77, Short.MAX_VALUE)
				.addGroup(gl_panel2.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 75, Short.MAX_VALUE).addContainerGap()));
		panel.setLayout(gl_panel2);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addGap(20)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
								.addComponent(scrollPane))
						.addGap(63)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE).addContainerGap()));

		refreshLabel.setIcon(new ImageIcon(ClientPanel.class.getResource("/resources/refresh (1).png")));
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_2.createSequentialGroup().addContainerGap(715, Short.MAX_VALUE).addComponent(refreshLabel,
						GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(refreshLabel,
				GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE));
		panel_2.setLayout(gl_panel_2);

		
		
		// HERE !! NEED TO LET IT BE LIKE THE SERVER: DISPLAY ALL SONGS
		table = new JTable(ClientPanel.findProjectsForDataTable(), new String[] { "User", "Song" });
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
			
			
			saveSongLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
				
					String song_name = songNamePanel.getTxtField().getText();
					

					Song uploadedsong = new Song(user.getId(), song_name, songFile.getAbsolutePath());

					
					Project project = new Project(user.getUsername(), song_name, true, user.getId(), uploadedsong.getId());

					try {
						SongEchoClient.createSong(uploadedsong);
						ProjectEchoClient.sendProject(project);
						
						
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
				}

			});
			
			
			uploadLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JFileChooser file_upload = new JFileChooser();
					int save = file_upload.showOpenDialog(null);
					
					String songName = songNamePanel.getTxtField().getText();

					
					if (save == JFileChooser.APPROVE_OPTION) {
						//Creating a File object
						songFile = new File(file_upload.getSelectedFile().getAbsolutePath());
						try {

						      System.out.println("Successful upload of: " + songFile);
						      System.out.println("Given song name: " + songName);
						      
						      //System.out.println("playing song without repeat.");
						
						      
						      
						      // NEED TO MOVE THIS TO WHEN A SONG IS PRESSED TO PLAY
						  	    //initialize TinySound
								TinySound.init();
		
								//load with Files, URLs or InputStreams
								Music song = TinySound.loadMusic(songFile);
								//start playing the music on loop
								//song.play(false);

							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    
					}
				}
			});
				
		 playPlaylistOption.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//initialize TinySound
					TinySound.init();
					Music song = TinySound.loadMusic(songFile);
					song.play(false);
				}
			});
		 
		 playWithLoopPlaylistOption.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//initialize TinySound
					TinySound.init();
					Music song = TinySound.loadMusic(songFile);
					song.play(true);
				}
			});
			
			pausePlaylistOption.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//initialize TinySound
					TinySound.init();
					Music song = TinySound.loadMusic(songFile);
					song.pause();
				}
			});
			
			resumePlaylistOption.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//initialize TinySound
					TinySound.init();
					Music song = TinySound.loadMusic(songFile);
					song.resume();
				}
			});
			
			replayPlaylistOption.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//initialize TinySound
					TinySound.init();
					Music song = TinySound.loadMusic(songFile);
					song.rewind();
				}
			});
			
			downlaodPlaylistOption.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//initialize TinySound
					
					
					
					// also here: still need to donwload songFile
				}
			});

			
			
			// ----- end of mouse events
		}
}
