import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BackgroundMusic implements Runnable, LineListener{
	
	private Thread t;
	private String threadName;
	
	private ArrayList<String> musicFiles;
	private int currentSongIndex;
	
	private int vol;
	
	public BackgroundMusic(int vol, String... files) {
		this.vol = vol;
		musicFiles = new ArrayList<String>();
		for(String file : files) {
			musicFiles.add("./audio/" + file + ".wav");
		}
		
	}
	
	Clip clip;
	FloatControl gainControl;
	
	private void playSound(String fileName, int volume) {
		try {
			File soundFile = new File(fileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			try {
				clip = (Clip) AudioSystem.getLine(info);
				clip.open(ais);
				gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(volume);
				clip.start();
				while(clip.getMicrosecondLength() != clip.getMicrosecondPosition()) {
					if(fileName.equals("./audio/effects/doorOpen.wav")) {
						if(clip.getMicrosecondPosition() > 1000000 && clip.getMicrosecondPosition() < 1100000) {
							Game.key = true;
						}
					}

				}
				if(fileName.equals("./audio/effects/emperor_trumpet.wav")) {
					Game.focus = "duy";
				}
				
				if(fileName.equals("./audio/effects/doorOpen.wav")) {
					Game.focus = "duy";
				}
				
				if(fileName.equals("./audio/effects/penguin/dying.wav")) {
					Game.penguinDeathSound = false;
				}
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void playSound(String fileName) {
		try {
			File soundFile = new File(fileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			try {
				clip = (Clip) AudioSystem.getLine(info);
				clip.open(ais);
				gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-10);
				clip.start();
				while(clip.getMicrosecondLength() != clip.getMicrosecondPosition()) {
					
				}
				if(fileName.equals("./audio/effects/emperor_trumpet.wav")) {
					Game.focus = "duy";
				}
				System.out.println(fileName);
				if(fileName.equals("./audio/effects/penguin/dying.wav")) {
					Game.penguinDeathSound = false;
				}
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	



	
	public void stop() {
		clip.stop();
	}
	
	public void run() {
		playSound(musicFiles.get(currentSongIndex), vol);
	}
	
   public void start () {
      if (t == null) {
         t = new Thread (this, "t");
         t.start ();
      }
   }

	@Override
	public void update(LineEvent event) {
		if(event.getType() == LineEvent.Type.START) {
			
		}
		if(event.getType() == LineEvent.Type.STOP) {
			clip.stop();
			clip.flush();
			clip.setFramePosition(0);
			System.out.println("stop");
		}
	}

}
