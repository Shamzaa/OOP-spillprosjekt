package game.resource;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;



public class ResourceManager {
	static HashMap<String,byte[]> fileMap = new HashMap<String,byte[]>();
	static HashMap<String,BufferedImage> imageMap = new HashMap<String,BufferedImage>();
	
	
	public static byte[] getFileBuffer(String filename){
		if(fileMap.containsKey(filename)){
			return fileMap.get(filename);
		}
		byte[] buffer = null;
		File f = new File(filename);
		if(f.isFile()){
			buffer = new byte[(int) f.length()];
			InputStream fReader = null;
			try {
				fReader = new FileInputStream(f);
				fReader.read(buffer);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			fileMap.put(filename, buffer);
		}
		return buffer;
	}
	public static String getFileContent(String filename){
		return new String(getFileBuffer(filename));
		
	}
	public static BufferedImage getImage(String filename){
		if(imageMap.containsKey(filename)){
			return imageMap.get(filename);
		}
		BufferedImage img = null;
		try {
			img = ImageIO.read(new ByteArrayInputStream(getFileBuffer(filename)));
			imageMap.put(filename, img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}
