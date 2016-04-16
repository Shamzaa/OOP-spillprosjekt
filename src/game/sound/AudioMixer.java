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
		
	}

}
