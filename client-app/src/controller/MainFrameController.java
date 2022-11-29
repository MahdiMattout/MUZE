package controller;

import java.awt.Frame;

import javax.swing.JPanel;

import view.MainFrame;

public class MainFrameController {
	private JPanel contentPanel;
	private MainFrame frame;

	public MainFrameController(MainFrame frame) {
		this.frame = frame;
	}

	public void navigateToProject(JPanel projectPanel) {
		contentPanel.removeAll();
		// contentPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.X_AXIS));
		contentPanel.add(projectPanel);
		projectPanel.setPreferredSize(contentPanel.getPreferredSize());
		contentPanel.repaint();
		contentPanel.validate();
		frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);

	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

}
