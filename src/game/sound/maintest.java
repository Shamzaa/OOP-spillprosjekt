package game.sound;

public class maintest {
	public static void main(String[] args){
		AudioMixer ayy = new AudioMixer();
		
		ayy.getChannel("ayylmao3").play();
		while(true){
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ayy.getChannel("ayylmao2").stop();
		}
		
		
	}
}
