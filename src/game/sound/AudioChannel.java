package game.sound;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;


import game.resource.ResourceManager;

public class AudioChannel {
	
	private static Clip clip;
	private Mixer mixer;
	private URL soundURL;
	private String fileURL;
	
	public AudioChannel(Mixer mixer){
		this.mixer = mixer;
		try{
			clip = (Clip)this.mixer.getLine(new DataLine.Info(Clip.class, null));
		}catch(LineUnavailableException lue){
			lue.printStackTrace();
		}
	}
	
	public AudioChannel(String soundURL, Mixer mixer){
		this(mixer);
		loadAudio(soundURL);
		
	}
	
	public void play(String soundURL){
		loadAudio(soundURL);
		clip.start();
	}

	public void play(){
		if(soundURL == null){
			throw new IllegalArgumentException("WTF man");
		}
		// restarts the audioclip if you play the same audio again. relevant for spam of soundFX
		if(clip.isRunning()){
			clip.stop();
			clip.close();
			loadAudio(fileURL);
		}
		clip.start();
	}
	/*
	private void runAudio(){
		clip.start();
		// workaround for running audio when not implemented in game:
		
		do{
			try{
				Thread.sleep(50);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}while(clip.isActive());
		
	}
	*/
	public void loadAudio(String fileURL){
		this.fileURL = fileURL;
		clip.close();
		try{
			this.soundURL = AudioChannel.class.getResource(fileURL);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(this.soundURL);
			clip.open(audioStream);
		}catch(LineUnavailableException lue){
			lue.printStackTrace();
		}catch(UnsupportedAudioFileException uafe){
			uafe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
