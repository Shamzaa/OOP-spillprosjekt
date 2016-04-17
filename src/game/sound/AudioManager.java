package game.sound;

public class AudioManager {
	private static AudioMixer mixer = new AudioMixer();
	private static AudioChannel currentAmbient;
	public static AudioMixer getMixer(){
		return mixer;
	}
	public static void playBG(String channelName){
		playBG(mixer.getChannel(channelName));
	}
	public static void playBG(AudioChannel channel){
		if(currentAmbient == channel){
			return;
		}
		if(currentAmbient != null){
			currentAmbient.stop();
		}
		if(channel == null){
			stopBG();
		}else{
			play(channel,true,false);
		}
		currentAmbient = channel;
	
	}
	public static void stopBG(){
		if(currentAmbient != null){
			currentAmbient.stop();
		}
	}
	public static void play(String channelName, boolean loop,boolean replay){
		play(mixer.getChannel(channelName),loop,replay);
	}
	public static void play(AudioChannel sound, boolean loop,boolean replay){
		if(!sound.isRunning() || (sound.isRunning() && replay)){
			sound.play(loop);
		}
	}
	public static void play(String channelName, boolean loop){
		play(channelName,loop,true);
	}
	public static void play(AudioChannel sound, boolean loop){
		play(sound,loop,true);
	}
	
	public static void stop(String channelName){
		stop(mixer.getChannel(channelName));
	}
	public static void stop(AudioChannel sound){
		sound.stop();
	}
	
}
