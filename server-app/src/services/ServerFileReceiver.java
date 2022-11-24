/**
 * 
 */
package services;

/**
 * @author mahdimattout
 *
 */
public class ServerFileReceiver {

	private int id;
	private String name;
	private byte[] data;
	private String fileExtension;

	public ServerFileReceiver(int id, String name, byte[] data, String fileExtension) {
		this.id = id;
		this.name = name;
		this.data = data;
		this.fileExtension = fileExtension;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
