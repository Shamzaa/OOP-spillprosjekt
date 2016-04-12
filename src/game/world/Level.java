package game.world;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;

import game.Game;
import game.entity.Entity;
import game.graphics.Camera;
import game.math.Vector3f;
import game.misc.ClassUtils;
import game.resource.ResourceManager;
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
	
	public Level(int width,int height){
		init(width,height);
	}
	@SuppressWarnings("unchecked")
	private void init(int width, int height){
		tiles = new Tile[width*height*MAX_Z];
		entityMap = (ArrayList<Entity>[]) new ArrayList[(int)Math.ceil((width * height)/entityMapRes)];
		for(int i=0; i< entityMap.length;i++){
			entityMap[i] = new ArrayList<Entity>(10);
		}
		this.width = width;
		this.height = height;
	}
	public Level(JSONObject levelMeta) {
		String imageName = levelMeta.getString("image");
		BufferedImage img = ResourceManager.getImage(imageName);
		init(img.getWidth(),img.getHeight());
		JSONObject imageMeta = levelMeta.getJSONObject("imageMeta");
		HashMap<Integer,JSONObject> valueMap = new HashMap<Integer,JSONObject>();
		String elem = null;
		for(Iterator<String> s = imageMeta.keys(); s.hasNext();){
			elem = s.next();
			valueMap.put(Integer.parseInt(elem), imageMeta.getJSONObject(elem));
		}
		for(int i=0; i<width*height;i++){
			int color = img.getRGB(i%width,Math.floorDiv(i,width));
			for(int x=0; x<3; x++){
				int k = (color >> 8*x)&0xFF;
				if(valueMap.containsKey(k)){
					String className = valueMap.get(k).getString("class");
					setTileAt((Tile)ClassUtils.newInstance(className, new Object[]{valueMap.get(k)}),i + x*width*height);
				}
			}
			
		}
		
		
		
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
		ArrayList<Entity> removeList = new ArrayList<Entity>(entities.size()/4);
		for(Entity i: entities){
			if(i.isAlive()){
				i.update(dtime);
			}else{
				removeList.add(i);
			}
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
		for(Entity i : removeList){
			destroyEntity(i);
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
	public Tile getTileAt(Vector3f position,boolean worldCoord){
		/*if(position.getX() < width && position.getX() >= 0 && 
		   position.getY() < height && position.getY() >= 0)
		{*/
		return getTileAt(getIndex(position,worldCoord));
		//}
		//return null;
	}
	public Tile getTileAt(Vector3f position){
		return getTileAt(position,false);
	}
	public void setTileAt(Tile t, Vector3f position){
		setTileAt(t,getIndex(position,false));
	}
	public void setTileAt(Tile t, Vector3f position,boolean worldCoord){
		setTileAt(t,getIndex(position,worldCoord));
	}
	public void setTileAt(Tile t, int index){
		if(t != null){
			int layerSize = width*height;
			int x = (index%width);
			int y = Math.floorDiv(index, width)%getHeight();
			int z = Math.floorDiv(index, layerSize);
			t.setPosition(new Vector3f(x*Tile.SIZE,y*Tile.SIZE,z*Tile.SIZE));
		}
		tiles[index] = t;
	}
	public int getIndex(Vector3f position,boolean worldCoord){
		if(worldCoord){
			position = position.div(new Vector3f(Tile.SIZE,Tile.SIZE,1));
		}
		return (int) (Math.floor(position.getX())+ Math.floor(position.getY())*width + Math.floor(position.getZ())*width*getHeight());
	}
	public int getIndex(Vector3f position){
		return getIndex(position,false);
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
		ent.enter(this);
	}
	public void removeEntity(Entity ent){
		removeEntity(entities.indexOf(ent));
	}
	public void removeEntity(int index){
		entities.remove(index);
		entities.get(index).leave(this);
	}
	public void moveEntityTo(Entity ent, Level lvl){
		removeEntity(ent);
		lvl.addEntity(ent);	
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
