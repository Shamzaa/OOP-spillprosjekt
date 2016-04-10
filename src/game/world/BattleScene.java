package game.world;



import game.Game;
import game.graphics.Sprite;

public class BattleScene extends Level{
	private Sprite background;
	public BattleScene(Sprite background) {
		super(0, 0);
		this.background = background;
		background.setDepth(100000);
	}
	@Override
	public void update(long dtime){
		super.update(dtime);;
	}
	@Override
	public void render(){
		Game.getCanvas().addToQueue(background);
		super.render();
		
	}

}
