package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.Format.Field;
import java.util.List;

import javax.security.auth.Refreshable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import database.ProjectsQuery;
import database.SongsQuery;
import entity.Song;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

public class ProjectsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String displayLabel = "songs";
	
	private JTable table;
	private JLabel projectLabel = new JLabel("");
	private JLabel refreshLabel = new JLabel("");
	
	/**
	 * Create the panel.
	 */
	public ProjectsPanel() {
		//init();

		
		FontUIResource font = new FontUIResource("Andalus", Font.PLAIN, 24);
		UIManager.put("Table.font", font);
		UIManager.put("Table.foreground", Color.black);
		setBackground(Color.WHITE);

			
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);

		
		///-------

		projectLabel.setIcon(new ImageIcon(ProjectsPanel.class.getResource("/resources/account.png")));
				

		JLabel lblProjects = new JLabel("MUZE Server");
				lblProjects.setHorizontalAlignment(SwingConstants.CENTER);
				lblProjects.setForeground(Color.BLACK);
				lblProjects.setFont(new Font("Andalus", Font.BOLD, 40));
				lblProjects.setBackground(Color.WHITE);
		
		
		///-----

		
		 // Options per song
	      JMenuBar menuBar = new JMenuBar();
	     JMenu listMenu = new JMenu("Options");
		 JMenuItem playPlaylistOption = new JMenuItem("Play");
		 JMenuItem pausePlaylistOption = new JMenuItem("Pause");
		 JMenuItem resumePlaylistOption = new JMenuItem("Resume");
		 JMenuItem replayPlaylistOption = new JMenuItem("Replay");
		 JMenuItem playWithLoopPlaylistOption = new JMenuItem("Play with looping");
		 JMenuItem downlaodPlaylistOption = new JMenuItem("Download");
		 
		 listMenu.add(playPlaylistOption);
		 listMenu.add(pausePlaylistOption);
		 listMenu.add(resumePlaylistOption);
		 listMenu.add(replayPlaylistOption);
		 listMenu.add(playWithLoopPlaylistOption);
		 listMenu.add(downlaodPlaylistOption);
		 
		
		 
		 
		 menuBar.add(listMenu);
		 
		 
		// ----
		GroupLayout groupLayout0 = new GroupLayout(this);
		groupLayout0.setHorizontalGroup(groupLayout0.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout0
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout0.createParallelGroup(Alignment.LEADING)
						.addComponent(listMenu, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
				
				.addContainerGap()));
		panel.setLayout(groupLayout0);
		
			
		// ----
		

		
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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 751, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(165, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 77, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 75, Short.MAX_VALUE).addContainerGap()));
		panel.setLayout(gl_panel);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addGap(20)
				
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							
								.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
								.addComponent(scrollPane))
						.addGap(63)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE).addContainerGap()));

		refreshLabel.setIcon(new ImageIcon(ProjectsPanel.class.getResource("/resources/refresh (1).png")));
		
		
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_2.createSequentialGroup().addContainerGap(715, Short.MAX_VALUE).addComponent(refreshLabel,
						GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(refreshLabel,
				GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE));
		panel_2.setLayout(gl_panel_2);
		
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_3.createSequentialGroup().addContainerGap(715, Short.MAX_VALUE).addComponent(listMenu,
						GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addComponent(listMenu,
				GroupLayout.DEFAULT_SIZE,42, Short.MAX_VALUE));
		//panel_3.setLayout(gl_panel_3);

		table = new JTable(ProjectsQuery.findProjectsForDataTable(), new String[] { "User", "Song" });
		table.setRowHeight(50);
		table.getTableHeader().setBackground(Color.white);
		table.getTableHeader().setFont(new Font("Andalus", Font.BOLD, 22));
		scrollPane.setBackground(Color.white);
		scrollPane.setViewportView(table);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
		setLayout(groupLayout);
		
		ListSelectionModel model = table.getSelectionModel();
	
			
		model.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (! model.isSelectionEmpty()) {
					// get selected row
					int selectedRow = model.getMinSelectionIndex();

					String[] options = new String[] {"Play", "Download", "Pause","Resume", "Play with looping", "Replay"};
					
				    String song_name = table.getModel().getValueAt(selectedRow, 1).toString();
				    String uploader_username = table.getModel().getValueAt(selectedRow, 0).toString();


				    int response = JOptionPane.showOptionDialog(null, "Selected song:" + song_name, "Options",
				            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				            null, options, options[0]);
				    
				    // cast song_name to song
				      Song sng = new  Song(uploader_username, song_name);
				      File songFile = new File(sng.getSongFilePath());
				      
					   switch (response) {
					   
				         case 0: 
				         System.out.println("Playing");
				         TinySound.init();
						 Music song = TinySound.loadMusic(songFile);
						 song.play(false);
				         break;
				         
				         
				         case  1:
				         System.out.println("Downloading Using Stream");
				         URL url = new URL(sng.getSongFilePath());
				         BufferedInputStream bis = new BufferedInputStream(url.openStream());
				         FileOutputStream fis = new FileOutputStream(songFile);
				         byte[] buffer = new byte[1024];
				         int count=0;
				         while((count = bis.read(buffer,0,1024)) != -1)
				         {
				             fis.write(buffer, 0, count);
				         }
				         fis.close();
				         bis.close();
				         
				         break;
				         
				         
				         case 2:
					         System.out.println("Pause");
					         TinySound.init();
							 Music song = TinySound.loadMusic(songFile);
							 song.pause();
					         break;
					         
					         
				         case 3:
				         System.out.println("Resume");
				         TinySound.init();
							Music song = TinySound.loadMusic(songFile);
							song.resume();
				         break;
				         
				         
				         case 4:
					     System.out.println("Playing without repeat");
					     TinySound.init();
							Music song = TinySound.loadMusic(songFile);
							song.play(true);
					     break;
					     
					     
				         case 5:
					     System.out.println("Replaying");
					     TinySound.init();
						 Music song = TinySound.loadMusic(songFile);
						 song.rewind();
					     break;
				     
				      }
					
				}
			}
		
		});
		
		refreshLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.setModel(new DefaultTableModel(ProjectsQuery.findProjectsForDataTable(),
						new String[] { "User", "Song" }));
				((AbstractTableModel) table.getModel()).fireTableDataChanged();
				System.out.println("refreshed");

			}

		});

		
};
	


	
	public static void main(String[] args) {
		ProjectsPanel pp = new ProjectsPanel();
		pp.setVisible(true);
		
	}
}

