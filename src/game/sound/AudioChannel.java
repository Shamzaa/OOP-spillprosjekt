package game.sound;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


import javax.sound.sampled.*;


import game.resource.ResourceManager;

public class AudioChannel {
	
	private Clip clip;
	private Mixer mixer;
	private String fileURL;
	
	public AudioChannel(Mixer mixer){
		this.mixer = mixer;
		try{
			clip = (Clip) this.mixer.getLine(new DataLine.Info(Clip.class, null));
		}catch(LineUnavailableException lue){
			lue.printStackTrace();
		}
	}
	
	public AudioChannel(String soundURL, Mixer mixer){
		this(mixer);
		loadAudio(soundURL);
		
	}
	
	public void play(boolean loop){
		stop();
		clip.start();
		if(loop){
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	public boolean isRunning(){
		return clip.isRunning();
	}
	public void play(){
		play(false);
	}
	
	public void stop(){
		clip.stop();
		clip.setFramePosition(0);
	}
	
	public void pause(){
		clip.stop();
	}
	
	public void restart(){
		stop();
		play();
		//clip.start();
	}
	
	public void loadAudio(String fileURL){
		this.fileURL = fileURL;
		clip.close();
		try{
			InputStream byteStream = new ByteArrayInputStream(ResourceManager.getFileBuffer(fileURL));
			clip.open(AudioSystem.getAudioInputStream(byteStream));
		}catch(LineUnavailableException lue){
			lue.printStackTrace();
		}catch(UnsupportedAudioFileException uafe){
			uafe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
