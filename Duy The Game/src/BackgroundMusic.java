import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BackgroundMusic implements Runnable {
	
	private Thread t;
	private String threadName;
	
	private ArrayList<String> musicFiles;
	private int currentSongIndex;
	
	public BackgroundMusic(String... files) {
		musicFiles = new ArrayList<String>();
		for(String file : files) {
			musicFiles.add(file + ".wav");
		}
	}
	
	private void playSound(String fileName) {
		try {
			File soundFile = new File(fileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			try {
				Clip clip = (Clip) AudioSystem.getLine(info);
				clip.open(ais);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-10);
				clip.start();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		playSound(musicFiles.get(currentSongIndex));
	}
	
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, "t");
         t.start ();
      }
   }

}
