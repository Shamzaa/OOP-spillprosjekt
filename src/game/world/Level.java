package game.world;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import org.json.JSONObject;

import game.Game;
import game.entity.Entity;
import game.graphics.Camera;
import game.math.Vector3f;
import game.tile.Tile;

public class Level implements KeyListener, MouseListener{
	public static final int entityMapRes = 32;
	public static final int MAX_Z = 3;
	private Tile[] tiles;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity>[] entityMap;
	private Camera camera = new Camera(new Vector3f(0,0,0));
	private int width, height;
	private ArrayList<KeyListener> keyListeners = new ArrayList<KeyListener>(5);
	
	@SuppressWarnings("unchecked")
	public Level(int width,int height){
		tiles = new Tile[width*height*MAX_Z];
		entityMap = (ArrayList<Entity>[]) new ArrayList[(int)Math.ceil((width * height)/entityMapRes)];
		for(int i=0; i< entityMap.length;i++){
			entityMap[i] = new ArrayList<Entity>(10);
		}
		this.width = width;
		this.height = height;
	}
	public Level(JSONObject levelMeta) {
	}
	public Camera getCamera(){
		return camera;
	}
	public void render(){
		Tile tmp;
		int start = 0;
		int end = tiles.length;
		int layerSize = width*height;
		for(int i=start; i<end;i++){
			tmp = tiles[i];
			if(tmp != null){
				int x = (i%width);
				int y = Math.floorDiv(i, width)%getHeight();
				int z = Math.floorDiv(i, layerSize);
				//System.out.println(i + " : " + y + " : " + Math.floorDiv(i,width)*);
				tmp.getSprite().setPosition(new Vector3f(x*Tile.SIZE,y*Tile.SIZE,z*Tile.SIZE));
				tmp.getSprite().setDepth(-(y+Tile.SIZE+z*Tile.SIZE));
				tmp.getShape().setPosition(tmp.getSprite().getPosition());
				tmp.getShape().setDepth(tmp.getSprite().getDepth()-1);
				tmp.render();
			}
		}
		for(Entity i : entities){
			i.render();
		}
	}
	public void update(long dtime){
		for(Entity i : entities){
			if(i.getPosition().getX() > 0 && i.getPosition().getX() < width*Tile.SIZE){
				int index = getEntityMapIndex(i.getPosition());
				if(index > 0 && index < entityMap.length){
					entityMap[getEntityMapIndex(i.getPosition())].add(i);
				}
			}
		}
		for(Entity i: entities){
			i.update(dtime);
		}
		for(Tile t : tiles){
			if(t != null){
				t.getShape().setPosition(t.getSprite().getPosition());
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
		return height;
	}
	public Tile getTileAt(Vector3f position){
		if(position.getX() < width && position.getX() >= 0 && 
		   position.getY() < height && position.getY() >= 0)
		{
			return getTileAt(getIndex(position));
		}
		return null;
	}
	public void setTileAt(Tile t, Vector3f position){
		tiles[getIndex(position)] = t;
	}
	public void setTileAt(Tile t, int index){
		tiles[index] = t;
	}
	public int getIndex(Vector3f position){
		return (int) (position.getX()+ position.getY()*width + position.getZ()*width*getHeight());
	}
	public Vector3f getPosition(int index){
		return new Vector3f(index%width,Math.floorDiv(index, width),Math.floorDiv(index, width*height) );
	}
	public Tile getTileAt(int index){
		if(index >= 0 && index < tiles.length){
			return tiles[index];
		}
		return null;
	}
	
	public void destroyEntity(Entity ent){
		removeEntity(ent);
		ent.destory();
	}
	public void addEntity(Entity ent){
		entities.add(ent);
	}
	public void removeEntity(Entity ent){
		entities.remove(ent);	
	}
	public void removeEntity(int index){
		entities.remove(index);
	}
	public void enter(){
		Game.getCanvas().setCamera(camera);
	}
	public void leave(){
		
	}
	public void addListener(KeyListener listener){
		keyListeners.add(listener);
	}
	public void removeListener(KeyListener listener){
		keyListeners.remove(listener);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	@Override
	public void keyPressed(KeyEvent key) {
		for(KeyListener i : keyListeners){
			i.keyPressed(key);
		}
	}
	@Override
	public void keyReleased(KeyEvent key) {
		for(KeyListener i : keyListeners){
			i.keyReleased(key);
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
