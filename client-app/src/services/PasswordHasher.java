/**
 * 
 */
package services;
import java.security.NoSuchAlgorithmException;  
import java.security.MessageDigest;  
  
public class PasswordHasher   
{  
	public MessageDigest messageDigest;
	
	public PasswordHasher() {
		try {
			this.messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("Error occured while storing password. Please try again.");
		}
	}
	
	public String hashPassword(String password) {
		String encryptedPassword = password;
		this.messageDigest.update(password.getBytes());
		/* Convert the hash value into bytes */   
		byte[] bytes = this.messageDigest.digest();  
		  
		/* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
		StringBuilder stringBuilder = new StringBuilder();  
		for(int i=0; i< bytes.length ;i++)  
		{  
		    stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
		}  
		/* Complete hashed password in hexadecimal format */  
		encryptedPassword = stringBuilder.toString();
		return encryptedPassword;
	}

}
