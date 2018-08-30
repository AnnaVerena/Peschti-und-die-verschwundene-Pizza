package game;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import event.MapEvent;
import render.Image;

public class Map 
{
	int width;
	int height;
	int[][][] map;
	boolean[][] blocked;
	public ArrayList<MapEvent> mapEvents = new ArrayList<MapEvent>();
	public Image tileset;
	
	public Map(int width, int height)
	{
		this.width = width;
		this.height = height;
		map = new int[3][width][height];
		blocked = new boolean[width][height];
	}
	
	public Map( File f )
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader( f ));
			String line = br.readLine();
			int width = Integer.parseInt( line.split(",")[0] );
			int height = Integer.parseInt( line.split(",")[1] );
			
			this.width = width;
			this.height = height;
			map = new int[3][width][height];
			blocked = new boolean[width][height];
			
			for( int l = 0; l < 3; l++ )
				for( int y = 0; y < height; y++ )
				{
					line = br.readLine();
					for( int x = 0; x < width; x++ )
						map[l][x][y] = Integer.parseInt(line.split(",")[x]);
				}
			
			for( int y = 0; y < height; y++ )
			{
				line = br.readLine();
				for( int x = 0; x < width; x++ )
					blocked[x][y] = Boolean.parseBoolean(line.split(",")[x]);
			}			
			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getTile( int layer, int x, int y )
	{
		return map[layer][x][y];
	}
	
	public void setTile( int layer, int x, int y, int tile )
	{
		map[layer][x][y] = tile;
	}
	
	public boolean isTileBlocked( int x, int y )
	{
		if( x < 0 || x >= width || y<0 || y >= height) return true;
		return blocked[x][y];
	}
	
	/**
	 * 
	 *
	 * @param x x-Position 
	 * @param y y-Position
	 * @param map Karte
	 * @param chars Liste der Map-Events
	 * @return Wir erfahren, ob eine Position auf der Karte durch einen Gegenstand oder eine Figur geblockt ist
	 */

	public boolean isBlocked(int x, int y ) {
		if(isTileBlocked(x, y)) return true;
		for (MapEvent me : mapEvents) {
			if(x == me.getX() && y == me.getY() && !me.belowPlayer) return true;
		}
		return false;
	}
	
	public void setTileBlocked( int x, int y, boolean b )
	{
		blocked[x][y] = b;
	}
	
	public void toFile( File f )
	{
		try {
			FileWriter fw = new FileWriter( f );
			fw.append(width + "," + height + "\n" );
			
			for( int l = 0; l < 3; l++)
				for( int y = 0; y < height; y++ )
				{
					for(int x = 0; x < width; x++)
						fw.append( getTile(l,x,y) + "," );
					fw.append("\n");
				}
			
			for( int y = 0; y < height; y++ )
			{
				for(int x = 0; x < width; x++)
					fw.append( isBlocked(x,y) + "," );
				fw.append("\n");
			}
			
			fw.flush();
			fw.close();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
