package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import entity.Project;
import entity.User;
import services.ProjectEchoClient;
import services.Singleton;
import services.UserEchoClient;

import javax.swing.JEditorPane;

public class ProjectPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */

	private FieldPanel titlePanel;
	private JPanel projectInfoPanel = new JPanel();
	private JLabel projectLabel = new JLabel("");
	private JEditorPane editorPane = new JEditorPane();
	private JLabel saveLabel = new JLabel("");

	public ProjectPanel() {
		setBackground(Color.WHITE);

		projectInfoPanel.setBackground(Color.WHITE);

		JPanel textPanel = new JPanel();
		textPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		textPanel.setBackground(Color.WHITE);

		ImageIcon imageIcon = new ImageIcon(ProjectPanel.class.getResource("/resources/doc.png"));

		projectLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(70, 75, Image.SCALE_DEFAULT)));

		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING,
								groupLayout.createSequentialGroup().addGap(27).addComponent(textPanel,
										GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE))
						.addComponent(headerPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addGap(27).addComponent(projectInfoPanel,
								GroupLayout.PREFERRED_SIZE, 710, Short.MAX_VALUE)))
				.addGap(26)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addGap(34)
						.addComponent(projectInfoPanel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addGap(39).addComponent(textPanel, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addContainerGap()));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GroupLayout gl_headerPanel = new GroupLayout(headerPanel);
		gl_headerPanel.setHorizontalGroup(gl_headerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_headerPanel.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(179, Short.MAX_VALUE)));
		gl_headerPanel.setVerticalGroup(
				gl_headerPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_headerPanel.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 75, Short.MAX_VALUE).addContainerGap()));

		JLabel lblNewLabel = new JLabel("New Project");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Andalus", Font.BOLD, 40));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.WHITE);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(26)
						.addComponent(projectLabel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(168, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(projectLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
				.addContainerGap()));
		panel_1.setLayout(gl_panel_1);
		headerPanel.setLayout(gl_headerPanel);

		editorPane.setFont(new Font("Andalus", Font.PLAIN, 21));
		GroupLayout gl_textPanel = new GroupLayout(textPanel);
		gl_textPanel.setHorizontalGroup(
				gl_textPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_textPanel.createSequentialGroup()
						.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE).addContainerGap()));
		gl_textPanel.setVerticalGroup(
				gl_textPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_textPanel.createSequentialGroup()
						.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE).addContainerGap()));
		textPanel.setLayout(gl_textPanel);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setLabelFor(lblTitle);
		lblTitle.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Andalus", Font.PLAIN, 22));
		lblTitle.setBackground(Color.WHITE);
		titlePanel = new FieldPanel("/resources/title.png", "title");

		titlePanel.setBackground(Color.WHITE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GroupLayout gl_projectInfoPanel = new GroupLayout(projectInfoPanel);
		gl_projectInfoPanel.setHorizontalGroup(gl_projectInfoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_projectInfoPanel.createSequentialGroup()
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)));
		gl_projectInfoPanel.setVerticalGroup(gl_projectInfoPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_projectInfoPanel.createSequentialGroup()
						.addGroup(gl_projectInfoPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
								.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
						.addContainerGap()));

		saveLabel.setIcon(new ImageIcon(ProjectPanel.class.getResource("/resources/save.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(42)
						.addComponent(saveLabel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(24, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(saveLabel,
				GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE));
		panel.setLayout(gl_panel);
		projectInfoPanel.setLayout(gl_projectInfoPanel);
		setLayout(groupLayout);
		init();
	}

	public void init() {
		addMouseListener();
	}

	private void addMouseListener() {
		saveLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String title = titlePanel.getTxtField().getText();
				String content = editorPane.getText();
				Project project = new Project(title, content, true, Singleton.getCurrentUser().getId());
				try {
					Project p = ProjectEchoClient.sendProject(project);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
	}
}
