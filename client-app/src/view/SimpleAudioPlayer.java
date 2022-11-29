package view;

//Java program to play an Audio
//file using Clip Object
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SimpleAudioPlayer
{

	// to store current position
	Long currentFrame;
	Clip clip;

	// current status of clip
	String status;

	AudioInputStream audioInputStream;
	File filePath;

	// constructor to initialize streams and clip
	public SimpleAudioPlayer(File file)
		throws UnsupportedAudioFileException,
		IOException, LineUnavailableException
	{
		this.filePath = file;
		// create AudioInputStream object
		audioInputStream =
				AudioSystem.getAudioInputStream(this.filePath );

		// create clip reference
		clip = AudioSystem.getClip();

		// open audioInputStream to the clip
		clip.open(audioInputStream);

		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	// Work as the user enters his choice

	public void ChangeSongStatus(int c)
			throws IOException, LineUnavailableException, UnsupportedAudioFileException
	{
		/*1 for pause
		 * 2 for resume
		 * 3 for restart
		 * 4 for stop
		 */
		switch (c)
		{
			case 1:
				pause();
				break;
			case 2:
				resumeAudio();
				break;
			case 3:
				restart();
				break;
			case 4:
				stop();
				break;
			default:
				break;
		}

	}

	// Method to play the audio
	public void play()
	{
		//start the clip
		clip.start();

		status = "play";
	}

	// Method to pause the audio
	public void pause()
	{
		if (status.equals("paused"))
		{
			System.out.println("audio is already paused");
			return;
		}
		this.currentFrame =
		this.clip.getMicrosecondPosition();
		clip.stop();
		status = "paused";
	}

	// Method to resume the audio
	public void resumeAudio() throws UnsupportedAudioFileException,
								IOException, LineUnavailableException
	{
		if (status.equals("play"))
		{
			System.out.println("Audio is already "+
			"being played");
			return;
		}
		clip.close();
		resetAudioStream();
		clip.setMicrosecondPosition(currentFrame);
		this.play();
	}

	// Method to restart the audio
	public void restart() throws IOException, LineUnavailableException,
											UnsupportedAudioFileException
	{
		clip.stop();
		clip.close();
		resetAudioStream();
		currentFrame = 0L;
		clip.setMicrosecondPosition(0);
		this.play();
	}

	// Method to stop the audio
	public void stop() throws UnsupportedAudioFileException,
	IOException, LineUnavailableException
	{
		currentFrame = 0L;
		clip.stop();
		clip.close();
	}

//	// Method to jump over a specific part
//	public void jump(long c) throws UnsupportedAudioFileException, IOException,
//														LineUnavailableException
//	{
//		if (c > 0 && c < clip.getMicrosecondLength())
//		{
//			clip.stop();
//			clip.close();
//			resetAudioStream();
//			currentFrame = c;
//			clip.setMicrosecondPosition(c);
//			this.play();
//		}
//	}

	// Method to reset audio stream
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
											LineUnavailableException
	{
		audioInputStream = AudioSystem.getAudioInputStream(
		this.filePath);
		clip.open(audioInputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

}
