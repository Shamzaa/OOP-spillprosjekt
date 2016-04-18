package game.sound;

import java.util.HashMap;

import javax.sound.sampled.*;

import com.sun.istack.internal.NotNull;

public class AudioMixer {
	private Mixer mixer;
	// TODO: make clips hashmap, play clip based on string name
	protected HashMap<String, AudioChannel> clips = new HashMap<String, AudioChannel>();
	public AudioMixer(){
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		mixer = AudioSystem.getMixer(mixInfos[0]);
		
		for(Mixer.Info i : mixInfos){
			System.out.println(i.getName());
		}
		clips.put("BackgroundMusic", new AudioChannel(mixer));
	}
	
	public AudioChannel addChannel(String name, String fileURL){
		if(!clips.containsKey(name)){
			clips.put(name, new AudioChannel(fileURL, mixer));
		}
		return clips.get(name);
	}
	public AudioChannel getChannel(String name){
		if(clips.containsKey(name)){
			return clips.get(name);
		}else{
			throw new IllegalArgumentException("channel not found");
		}
	}
	//is this usefull?
	public void loadChannel(String name, String fileURL){
		// load clip into channel without playing it. might be useful
		if(clips.containsKey(name)){
			clips.get(name).loadAudio(fileURL);
		}else{
			throw new IllegalArgumentException("Trying to play from a channel that doesn't exist, you rat?");
			
		}
	}
	
	public void play(String name){
		// play fileURL on channel name, relevant for background music
		if(clips.containsKey(name)){
			clips.get(name).play();
		}else{
			throw new IllegalArgumentException("Trying to play from a channel that doesn't exist, you rat?");
		}
	}


}
