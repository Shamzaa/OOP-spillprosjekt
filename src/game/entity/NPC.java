package game.entity;
import game.graphics.Sprite;
import game.math.Vector3f;

public class NPC extends Entity{
	
	//vars: dialogue: .txt file?
	//
	NPC(float x, float y, float z){
		position = new Vector3f(x, y, z);
		sprite = new Sprite();
	}
	
	// TODO: patrol method?, trigger dialogue object(observatør-observert-teknikken?)
}
