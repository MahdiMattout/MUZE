/**
 * 
 */
package services;

import java.io.File;

import javax.swing.JFileChooser;

/**
 * @author mahdimattout
 *
 */
public class ClientFileChooser {
	public JFileChooser chooser = new JFileChooser();

	public final File[] fileToSend = new File[1];
	
	public void ClientUploader() {
		this.chooser = new JFileChooser();
	}
	
	public void ChooseFile() {
		chooser.setDialogTitle("Choose a file to upload");
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			fileToSend[0] = chooser.getSelectedFile();
		}
		
	}

	public JFileChooser getChooser() {
		return chooser;
	}

	public void setChooser(JFileChooser chooser) {
		this.chooser = chooser;
	}

	public File[] getFileToSend() {
		return fileToSend;
	}

}
