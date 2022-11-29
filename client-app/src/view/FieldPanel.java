package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class FieldPanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtField = new JTextField();

	/**
	 * Create the panel.
	 */

	private void init(String iconPath, String placeHolder) {

		setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		setBackground(Color.WHITE);

		JLabel iconLabel = new JLabel("");
		iconLabel.setBorder(null);
		iconLabel.setIcon(new ImageIcon(FieldPanel.class.getResource(iconPath)));

		txtField.setOpaque(false);
		txtField.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtField.setBorder(null);
		txtField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(iconLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addGap(1)
						.addComponent(txtField, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtField, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(iconLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)));
		setLayout(groupLayout);

	}

	private void onChangeListener(String placeHolder) {
		txtField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtField.getForeground().equals(Color.GRAY)) {
					txtField.setText("");
					txtField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				checkField(placeHolder);
			}

		});

	}

	private void checkField(String placeHolder) {
		if (txtField.getText().isEmpty()) {
			txtField.setForeground(Color.GRAY);
			txtField.setText(placeHolder);
		}
	}

	public FieldPanel(String iconPath, String placeHolder) {
		init(iconPath, placeHolder);
		onChangeListener(placeHolder);
		checkField(placeHolder);
	}

	public JTextField getTxtField() {
		return txtField;
	}

	public void setTxtField(JTextField txtField) {
		this.txtField = txtField;
	}

}
