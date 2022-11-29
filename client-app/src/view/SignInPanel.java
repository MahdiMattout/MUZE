package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import controller.MainFrameController;
import entity.User;
import services.Singleton;
import services.UserEchoClient;

public class SignInPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */

	private FieldPanel usernamePanel = new FieldPanel("/resources/account.png", "username");

	private PasswordPanel passwordPanel = new PasswordPanel("/resources/lock.png", "password");

	private JLabel saveLabel = new JLabel("Sign In");

	private MainFrameController frameController;

	public SignInPanel(MainFrameController frameController) {
		this.frameController = frameController;

		setBackground(Color.WHITE);

		saveLabel.setForeground(Color.WHITE);
		saveLabel.setOpaque(true);
		saveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		saveLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		saveLabel.setBackground(new Color(15, 157, 88));

		textField = new JTextField();
		textField.setBorder(null);
		textField.setColumns(10);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(usernamePanel, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(textField,
								GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE))
						.addComponent(passwordPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
						.addComponent(saveLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(usernamePanel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(passwordPanel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(saveLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
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
				User user = new User(username, password);
				try {
					user = UserEchoClient.authenticateUser(user);
					if (user.getId() > 0) {
						Singleton.setCurrentUser(user);
						frameController.navigateToProject(new ClientPanel(user));
					}
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

}
