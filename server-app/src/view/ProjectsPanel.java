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

import database.ProjectsQuery;
import database.SongsQuery;
import entity.Song;

public class ProjectsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String displayLabel = "songs";
	
	private JTable table;
	private JLabel projectLabel = new JLabel("");
	private JLabel refreshLabel = new JLabel("");
	
	 private JRadioButton rdbtnUser = new JRadioButton("User");
	 private JRadioButton rdbtnSong = new JRadioButton("Song");
	 private JRadioButton rdbtnSearch = new JRadioButton("Search");
	 
     private JLabel UploadNewSong = new JLabel("Upload");
	
	/**
	 * Create the panel.
	 */
	public ProjectsPanel() {
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

		JButton btnSearch = new JButton("Search");
		
		final JPopupMenu playlistMenu = new JPopupMenu();
		JMenuItem createPlaylistOption = new JMenuItem("Play");
		JMenuItem deletePlaylistOption = new JMenuItem("Download");
		

		rdbtnUser.setForeground(Color.GREEN);
		rdbtnUser.setOpaque(true);
		rdbtnUser.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnUser.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		
		rdbtnSong.setForeground(Color.GREEN);
		rdbtnSong.setOpaque(true);
		rdbtnSong.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSong.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		rdbtnSearch.setForeground(Color.GREEN);
		rdbtnSearch.setOpaque(true);
		rdbtnSearch.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSearch.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		///-------

		projectLabel.setIcon(new ImageIcon(ProjectsPanel.class.getResource("/resources/music_Icon.png")));

		JLabel lblProjects = new JLabel("MUZE");
		lblProjects.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjects.setForeground(Color.BLACK);
		lblProjects.setFont(new Font("Andalus", Font.BOLD, 40));
		lblProjects.setBackground(Color.WHITE);
		// ----
		GroupLayout groupLayout0 = new GroupLayout(this);
		groupLayout0.setHorizontalGroup(groupLayout0.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout0
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout0.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnUser, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
						.addComponent(rdbtnSong, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
						.addComponent(rdbtnSearch, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
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

		refreshLabel.setIcon(new ImageIcon(ProjectsPanel.class.getResource("/resources/refresh (1).png")));
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_2.createSequentialGroup().addContainerGap(715, Short.MAX_VALUE).addComponent(refreshLabel,
						GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(refreshLabel,
				GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE));
		panel_2.setLayout(gl_panel_2);

		table = new JTable(ProjectsQuery.findProjectsForDataTable(), new String[] { "User", "Song" });
		table.setRowHeight(50);
		table.getTableHeader().setBackground(Color.white);
		table.getTableHeader().setFont(new Font("Andalus", Font.BOLD, 22));
		scrollPane.setBackground(Color.white);
		scrollPane.setViewportView(table);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
		setLayout(groupLayout);

	};
	


	private void addMouseListener() {
		refreshLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.setModel(new DefaultTableModel(ProjectsQuery.findProjectsForDataTable(),
						new String[] { "User", "Song" }));
				((AbstractTableModel) table.getModel()).fireTableDataChanged();
				System.out.println("refreshed");

			}

		});
		
		rdbtnSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayLabel = "Songs";
			}
		});
		
		
		rdbtnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayLabel = "Users";
			}
		});
				
		//When SEARCH is clicked
		rdbtnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//...
				
			}
		});
			
		
	};
	
	
//	MouseListener playlistListener = new MouseAdapter() {
//		// show the Stream / Download options for any shown  song 
//		public void mouseClicked(MouseEvent e) {
//			if (SwingUtilities.isRightMouseButton(e)) {//if right clicked
//				//get the pointer and select the list item 
//    			int row = .......locationToIndex(e.getPoint());
//    			.........setSelectedIndex(row);
//    			
//    			//Show the playlist menu
//    			playlistMenu.show(e.getComponent(), e.getX(), e.getY());
//		    }
//
//		}
//	};
//	
//	
	
	
	public void init() {
		addMouseListener();
		
		
	}
}

