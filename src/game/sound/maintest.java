package game.sound;

public class maintest {
	public static void main(String[] args){
		AudioMixer ayy = new AudioMixer();
		ayy.addChannel("ayylmao", "/game/sound/gameover.wav");
		ayy.getChannel("ayylmao").play();
		while(true){
			
		}
		
		
	}
}
