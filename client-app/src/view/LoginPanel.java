package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import controller.MainFrameController;

public class LoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel signinLabel = new JLabel("Sign In");
	private JLabel signupLabel = new JLabel("Sign Up");
	private JPanel panel = new JPanel();
	private JPanel signInPanel;
	private JPanel signUpPanel;
	private boolean isSignInSelected = true;
	private MainFrameController frameController;

	public LoginPanel(MainFrameController frameController) {

		this.frameController = frameController;
		signInPanel = new SignInPanel(frameController);
		signUpPanel = new SignupPanel(frameController);

		setBackground(Color.WHITE);
		signinLabel.setOpaque(true);
		signinLabel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(15, 157, 88)));

		signinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signinLabel.setForeground(new Color(85, 85, 85));
		signinLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		signinLabel.setBackground(Color.WHITE);
		signupLabel.setOpaque(true);

		signupLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signupLabel.setForeground(new Color(85, 85, 85));
		signupLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		signupLabel.setBackground(new Color(233, 235, 238));

		panel.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(signinLabel, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(signupLabel, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(signinLabel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addComponent(signupLabel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE).addContainerGap()));
		setLayout(groupLayout);

		init();

	}

	private void init() {
		addSignInLabelClickListener();
		addSignUpLabelClickListener();
		panel.removeAll();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(signInPanel);
		signInPanel.setPreferredSize(panel.getPreferredSize());
		panel.repaint();
		panel.validate();
	}

	private void addSignInLabelClickListener() {
		signinLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!isSignInSelected) {
					signinLabel.setBackground(Color.WHITE);
					signinLabel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(15, 157, 88)));
					signupLabel.setBackground(new Color(233, 235, 238));
					signupLabel.setBorder(null);
					panel.removeAll();
					panel.add(new SignInPanel(frameController));
					panel.repaint();
					panel.validate();
					isSignInSelected = true;
				}
			}
		});
	}

	private void addSignUpLabelClickListener() {
		signupLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isSignInSelected) {
					signinLabel.setBackground(new Color(233, 235, 238));
					signinLabel.setBorder(null);
					signupLabel.setBackground(Color.WHITE);
					signupLabel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(15, 157, 88)));
					panel.removeAll();
					panel.add(new SignupPanel(frameController));
					panel.repaint();
					panel.validate();
					isSignInSelected = false;
				}
			}
		});
	}

}
