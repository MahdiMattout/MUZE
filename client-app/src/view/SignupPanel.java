package view;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;


import controller.MainFrameController;
import entity.Project;
import entity.Song;
import entity.User;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import services.ProjectEchoClient;
import services.Singleton;
import services.SongEchoClient;
import services.UserEchoClient;

public class SignupPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private FieldPanel usernamePanel = new FieldPanel("/resources/account.png", "username");

	private PasswordPanel passwordPanel = new PasswordPanel("/resources/lock.png", "password");

	private FieldPanel firstNamePanel = new FieldPanel("/resources/name.png", "first name");
	
	private FieldPanel lastNamePanel = new FieldPanel("/resources/name.png", "last name");
	
	private FieldPanel SongNamePanel = new FieldPanel("/resources/name.png", "song name");
	
	private FieldPanel emailAddressPanel = new FieldPanel("/resources/account.png", "email (e.g. yyy@gmail.com)");
	
	private JLabel uploadLabel = new JLabel("Upload file");
	
	private JLabel saveLabel = new JLabel("Sign Up");

	private MainFrameController frameController;
	
	private File songFile;

	public SignupPanel(MainFrameController frameController) {
		this.frameController = frameController;

		setBackground(Color.WHITE);

		saveLabel.setForeground(Color.WHITE);
		saveLabel.setOpaque(true);
		saveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		saveLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		saveLabel.setBackground(new Color(15, 157, 88));
		
		uploadLabel.setForeground(Color.GREEN);
		uploadLabel.setOpaque(true);
		uploadLabel.setHorizontalAlignment(SwingConstants.CENTER);
		uploadLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		//uploadLabel.setBackground(new Color(15, 157, 88));


		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(firstNamePanel, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(lastNamePanel, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(usernamePanel, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(emailAddressPanel, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(passwordPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(uploadLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(SongNamePanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(saveLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(firstNamePanel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lastNamePanel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(usernamePanel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(emailAddressPanel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordPanel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(uploadLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(46, Short.MAX_VALUE)
						.addComponent(SongNamePanel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(46, Short.MAX_VALUE)
						.addComponent(saveLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(46, Short.MAX_VALUE)));
		setLayout(groupLayout);
		init();
	}

	private void init() {
		addMouseListener();
	}

	private void addMouseListener() {
		saveLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = usernamePanel.getTxtField().getText();
				String password = passwordPanel.getTxtField().getText();
				String firstName = firstNamePanel.getTxtField().getText();
				String lastName = lastNamePanel.getTxtField().getText();

				String emailAddress = emailAddressPanel.getTxtField().getText();
				String song = SongNamePanel.getTxtField().getText();
				// need to input the song and save it here: and change type from string here and in User.java
								
				User user = new User(firstName, lastName, username, emailAddress, password, true, song, songFile);
				try {
					user = UserEchoClient.createUser(user);
					if (user.getId() > 0) {
						Singleton.setCurrentUser(user);
						Song uploadedSong = SongEchoClient.createSong(new Song(user.getId(), song, songFile.getAbsolutePath()));
						Project newPr = ProjectEchoClient.sendProject(new Project(user.getUsername(), song, true, user.getId(), uploadedSong.getId()));
						frameController.navigateToProject(new ClientPanel(user));
						
					}

				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		
		uploadLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JFileChooser file_upload = new JFileChooser();
//					int res = file_upload.showOpenDialog(null);
					int save = file_upload.showOpenDialog(null);
					
					String songName = SongNamePanel.getTxtField().getText();

					
					if (save == JFileChooser.APPROVE_OPTION) {
						//Creating a File object
						songFile = new File(file_upload.getSelectedFile().getAbsolutePath());
						try {

						      System.out.println("Successful upload of: " + songFile);
						      System.out.println("Given song name: " + songName);
						      						
						  	    //initialize TinySound
//								TinySound.init();
//								Music song = TinySound.loadMusic(songFile);
//								
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					    
					}
				}
			});
	}
	
	
	
}
