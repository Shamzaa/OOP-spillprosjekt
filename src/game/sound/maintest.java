package game.sound;

public class maintest {
	public static void main(String[] args){
		AudioMixer ayy = new AudioMixer();
		ayy.addChannel("ayylmao", "/game/sound/gameover.wav");
		ayy.addChannel("ayylmao2", "/game/sound/fight2.wav");

		ayy.getChannel("ayylmao2").play();
		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ayy.getChannel("ayylmao2").stop();
		}
		
		
	}
}
