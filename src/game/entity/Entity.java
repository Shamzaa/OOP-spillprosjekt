package game.entity;

import game.math.Rectangle;
import game.math.Shape;
import game.math.Vector3f;
import game.mechanics.Inventory;
import game.resource.ResourceManager;
import game.tile.Tile;
import game.tile.Wall;
import game.world.Level;

import org.json.JSONArray;
import org.json.JSONObject;

import game.Game;
import game.graphics.*;

public abstract class Entity {
	protected Vector3f position;
	protected Vector3f walkDir = new Vector3f(0,0,0);
	protected Vector3f facing = new Vector3f(0,0,0);
	public final double walkSpeed = 256;
	protected double currentSpeed = walkSpeed;
	protected Sprite[] sprites;
	protected Inventory inventory = new Inventory();
	protected Sprite currentSprite;
	protected Shape shape;
	private boolean dead = false;
	
	/* vars needed:
	 * sprite
	 * size?
	 * trigger: what can this entity do when "activated"
	 * 
	 */
	
	// other init vars needed: filepath to sprite, file containing options to import
	public Entity(Vector3f position, Sprite[] sprites){
		this.position = position;
		this.sprites = sprites;
		currentSprite = sprites[0];
		shape = new Rectangle(position,currentSprite.getDimension().mul(new Vector3f(1,0.25f,0)));
	}
	public Entity(Vector3f pos,JSONObject data){
		this.position = pos;
		JSONObject spritesMeta = data.getJSONObject("sprites");
		JSONArray start = spritesMeta.getJSONArray("start");
		JSONArray dimArray = spritesMeta.getJSONArray("dim");
		Vector3f sIndex = new Vector3f(start.getInt(0),start.getInt(1),start.getInt(2));
		Vector3f dim = new Vector3f(dimArray.getInt(0),dimArray.getInt(1),0);
		this.sprites = new Sprite[4];
		for(int i=0; i< sprites.length;i++){
			this.sprites[i] = new Sprite(
				ResourceManager.getImage(spritesMeta.getString("image")),pos,sIndex.add(new Vector3f(0,i,0)),new Vector3f(0.5f,0.7f,0),dim
			);
		}
		currentSprite = sprites[0];
		shape = new Rectangle(position,currentSprite.getDimension().mul(new Vector3f(1,0.25f,0)));
		
		
		
		
	}
	public void setSpeed(float speed){
		this.currentSpeed = speed;
	}
	public double getSpeed(){
		return this.currentSpeed;
	}
	public void setDirection(Vector3f dir){
		this.walkDir = dir.normalize();
	}
	public void update(long dtime){
		if(dead){
			Game.getCurrentLevel().destroyEntity(this);
		}
		if(walkDir.getLength() > 0){
			face(walkDir);
			Tile[] tilesAround = new Tile[9];
			for(int i=0; i<9;i++){
				tilesAround[i] = Game.getCurrentLevel().getTileAt(new Vector3f((i%3)-1,Math.floorDiv(i,3)-1,0).scale(32).add(position),true);
				//Game.getCurrentLevel().setTileAt(new Wall(wallTile),new Vector3f((i%3)-1,Math.floorDiv(i,3)-1,0).scale(32).add(position),true);
			}
			currentSprite.setFPS((int)(12/walkSpeed * currentSpeed));
			currentSprite.update(dtime);
			Vector3f deltaPos = walkDir.normalize().scale((float) (currentSpeed*dtime/1000));
			Vector3f newPos = position.add(deltaPos);
			Tile oTile = Game.getCurrentLevel().getTileAt(position,true); 		
			Tile nTile = Game.getCurrentLevel().getTileAt(newPos,true);
			Tile bGround = Game.getCurrentLevel().getTileAt(newPos.sub(new Vector3f(0,0,1)),true);
			
			boolean col = false;//bGround == null;
			//shape.setPosition(newPos);
			if(!col){
				setShapePos(newPos);
				for(Tile i : tilesAround){
				//	System.out.println(i + " : " + (i!=null ? i.isSolid() : "<>"));	
					if(i!=null && i.isSolid()){
						if(shape.overlaps(i.getShape())){
							col = true;
							break;
						}
					}
				}
			}
			if(col){
				setPosition(position);
			}else{
				setPosition(newPos);
				if(oTile!=nTile){
					if(oTile != null){
						oTile.leave(this);
					}
					if(nTile != null){
						nTile.enter(this);
					}
				}
			}
			
		}else{
			currentSprite.setCurrentFrame(0);
		}
	}
	public void face(Vector3f dir){
		Sprite old = currentSprite;
		facing = dir;
		if(facing.getY() < 0){
			currentSprite = sprites[3];
		}else if(facing.getY() > 0){
			currentSprite = sprites[2];
		}else if(facing.getX() > 0){
			currentSprite = sprites[0];
		}else if(facing.getX() < 0){
			currentSprite = sprites[1];
		}
		currentSprite.setCurrentFrame(old.getCurrentFrame());
	}
	public void render(){
		if(currentSprite != null){
			currentSprite.setPosition(position);
			currentSprite.setDepth(calcDepth());
			shape.setDepth(calcDepth() - 1.0f);
			
			Game.getCanvas().addToQueue(currentSprite);
			Game.getCanvas().addToQueue(shape);
		}
	}
	private void setShapePos(Vector3f pos){
		Vector3f sPos = (pos).sub(sprites[0].getOffset());
		sPos = sPos.add(sprites[0].getDimension().sub(shape.getDimension()).mul(new Vector3f(0,1,0)));
		shape.setPosition(sPos);
		
	}
	protected float calcDepth(){
		return -position.getY() - (currentSprite.getDimension().getY() - currentSprite.getOffset().getY()) - position.getZ()*Tile.SIZE;
	}
	public void setPosition(Vector3f position){
		setShapePos(position);
		this.position = position;
	}
	public Vector3f getPosition(){
		return position;
	}
	public abstract void enter(Level lvl);
	public abstract void leave(Level lvl);
	public abstract void touch(Entity ent);
	
	//Called when the entity is destroyed
	public abstract void destory();
	
	public boolean isAlive(){
		return !dead;
	}
	
	public void kill(){
		dead = true;
	}
}
