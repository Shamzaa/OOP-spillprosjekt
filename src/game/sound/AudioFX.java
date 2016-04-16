package game.sound;

public class AudioFX extends AudioMixer{
	AudioFX(){
		// TODO: load all soundfx into clips hashmap
		super();
	}
	public void play(String name){
		// play soundFX "name"
		if(clips.containsKey(name)){
			clips.get(name).play();
		}else{
			throw new IllegalArgumentException("Trying to play from a channel that doesn't excist, you rat?");
		}
	}
	
}
