package view;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;


import controller.MainFrameController;
import entity.Song;
import entity.User;
import services.ClientFileChooser;
import services.Singleton;
import services.UserEchoClient;
import utils.Constants;

public class SignupPanel extends JPanel {

	public ClientFileChooser chooser = new ClientFileChooser();
	private FieldPanel usernamePanel = new FieldPanel("/resources/account.png", "username");

	private PasswordPanel passwordPanel = new PasswordPanel("/resources/lock.png", "password");

	private FieldPanel firstNamePanel = new FieldPanel("/resources/name.png", "first name");
	
	private FieldPanel lastNamePanel = new FieldPanel("/resources/name.png", "last name");
	
	private FieldPanel emailPanel = new FieldPanel("/resources/account.png", "email");
	
	private JLabel uploadLabel = new JLabel("Upload file");
	
	private JLabel saveLabel = new JLabel("Sign Up");

	private MainFrameController frameController;

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
						.addComponent(emailPanel, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(passwordPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(uploadLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
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
						.addComponent(emailPanel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(passwordPanel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(uploadLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
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
				String email = lastNamePanel.getTxtField().getText();
				try {
					FileInputStream fis = new FileInputStream(chooser.getFileToSend()[0].getAbsolutePath());
					Song song = new Song(chooser.getFileToSend()[0].getName(),chooser.getFileToSend()[0].getAbsolutePath());
					DataOutputStream audio = new DataOutputStream(new Socket(Constants.SERVER_IP, Constants.USER_PORT).getOutputStream());
					String fileName = chooser.getFileToSend()[0].getName();
					byte[] fileBytes = fileName.getBytes();
					byte[] fileContentBytes = new byte[(int) chooser.getFileToSend()[0].length()];
					fis.read(fileContentBytes);
					audio.writeInt(fileBytes.length);
					audio.write(fileBytes);
					
					audio.writeInt(fileContentBytes.length);
					audio.write(fileContentBytes);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				User user = new User(firstName, lastName, username, email, password, true);
				try {
					user = UserEchoClient.createUser(user);

					if (user.getId() > 0) {
						Singleton.setCurrentUser(user);
						frameController.navigateToProject(new ProjectPanel());
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
					chooser.ChooseFile();
				}
			});
	}
	
	
	
}
