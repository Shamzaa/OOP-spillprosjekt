package game.sound;

import java.util.HashMap;

import javax.sound.sampled.*;

public class AudioMixer {
	private Mixer mixer;
	// TODO: make clips hashmap, play clip based on string name
	private HashMap<String, AudioChannel> clips = new HashMap<String, AudioChannel>();
	AudioMixer(){
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		mixer = AudioSystem.getMixer(mixInfos[0]);
		
		clips.put("BackgroundMusic", new AudioChannel(mixer));
	}
	
	public void addChannel(String name, String fileURL){
		clips.put(name, new AudioChannel(fileURL, mixer));
	}
	
	public AudioChannel getChannel(String name){
		if(clips.containsKey(name)){
			return clips.get(name);
		}else{
			throw new IllegalArgumentException("channel not found");
		}
	}
	
	public void loadChannel(String name, String fileURL){
		// load clip into channel without playing it. might be useful
		if(clips.containsKey(name)){
			clips.get(name).loadAudio(fileURL);
		}else{
			throw new IllegalArgumentException("Trying to play from a channel that doesn't excist, you rat?");
			
		}
	}
	
	public void play(String name, String fileURL){
		// play fileURL on channel name, relevant for background music
		if(clips.containsKey(name)){
			clips.get(name).play(fileURL);
		}else{
			throw new IllegalArgumentException("Trying to play from a channel that doesn't excist, you rat?");
		}
	}
	
	public void play(String name){
		// relevant for soundFX mixer
		if(clips.containsKey(name)){
			clips.get(name).play();
		}else{
			throw new IllegalArgumentException("Trying to play from a channel that doesn't excist, you rat?");
		}
	}

}
