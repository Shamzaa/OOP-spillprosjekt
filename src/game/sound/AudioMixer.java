package game.sound;

import java.util.ArrayList;

import javax.sound.sampled.*;

public class AudioMixer {
	private Mixer mixer;
	private ArrayList<AudioChannel> clips = new ArrayList<AudioChannel>();
	// TODO: make clips hashmap, play clip based on string name
	AudioMixer(){
		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
		mixer = AudioSystem.getMixer(mixInfos[0]);
	}
	
	public void addChannel(String fileURL){
		clips.add(new AudioChannel(fileURL, mixer));
	}

}
