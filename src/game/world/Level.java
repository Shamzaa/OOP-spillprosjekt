package game.world;

import java.util.ArrayList;

import org.json.JSONObject;

import game.entity.Entity;
import game.graphics.Camera;
import game.math.Vector3f;
import game.tile.Tile;

public class Level{
	public static final int entityMapRes = 32;
	public static final int MAX_Z = 3;
	private Tile[] tiles;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity>[] entityMap;
	private Camera camera;
	private int width;
	
	@SuppressWarnings("unchecked")
	public Level(int width,int height){
		tiles = new Tile[width*height*MAX_Z];
		entityMap = (ArrayList<Entity>[]) new ArrayList[(int)Math.ceil((width * height)/entityMapRes)];
		for(int i=0; i< entityMap.length;i++){
			entityMap[i] = new ArrayList<Entity>(10);
		}
		this.width = width;
	}
	public Level(JSONObject levelMeta) {
	}
	public void render(){
		Tile tmp;
		int start = 0;
		int end = tiles.length;
		for(int i=start; i<end;i++){
			tmp = tiles[i];
			if(tmp != null){
				int x = (i%width)*Tile.SIZE;
				int y = ((int)((i/width))*Tile.SIZE)%getHeight();
				int z = (int)(i/(width*getHeight()));
				tmp.getSprite().setPosition(new Vector3f(x,y,z));
				tmp.render();
			}
		}
		for(Entity i : entities){
			i.render();
		}
	}
	public void update(long dtime){
		for(Entity i : entities){
			entityMap[getEntityMapIndex(i.getPosition())].add(i);
		}
		for(Entity i: entities){
			i.update(dtime);
		}
		for(Tile t : tiles){
			if(t != null){
				t.update(dtime);
			}
		}
		for(ArrayList<Entity> m: entityMap){
			m.clear();
		}
	}
	public int getWidth(){
		return width;
	}
	public Entity[] getEntitiesCloseTo(Vector3f position){
		return (Entity[]) entityMap[getEntityMapIndex(position)].toArray();
	}
	private int getEntityMapIndex(Vector3f position){
		return (int)((position.getX()/Tile.SIZE)/entityMapRes) + (int)((position.getY()/Tile.SIZE)/entityMapRes) * width;
	}
	public int getHeight(){
		return tiles.length/width;
	}
	public Tile getTileAt(Vector3f position){
		return getTileAt(getIndex(position));
	}
	public void setTileAt(Tile t, Vector3f position){
		tiles[getIndex(position)] = t;
	}
	public void setTileAt(Tile t, int index){
		tiles[index] = t;
	}
	public int getIndex(Vector3f position){
		return (int) ((position.getX()+ position.getY()*width)*position.getZ());
	}
	public Tile getTileAt(int index){
		return tiles[index];
	}
	public void enter(){
		
	}
	public void leave(){
		
	}
}
