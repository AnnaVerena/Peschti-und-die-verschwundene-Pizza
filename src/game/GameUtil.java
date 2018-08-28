package game;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Affine;
import render.Renderer;

public class GameUtil {
	
	public final static int UP = 1;
	public final static int DOWN = 0;
	public final static int LEFT = 2;
	public final static int RIGHT = 3;
	
	public static final Affine IDENTITY = new Affine();
	
	public static void renderTextbox( int x, int y, int width, int height) {
		
		Renderer.renderSubImage(Game.textboxTileset, 0, 0, 16, 16, x*16, y*16, 16, 16  );
		for( int ty = y+1; ty < y+height-1; ty++)
		{
			Renderer.renderSubImage(Game.textboxTileset, 0, 16, 16, 16, x*16, ty*16, 16, 16  );
		}
		Renderer.renderSubImage(Game.textboxTileset, 0, 32, 16, 16, x*16, (y+height-1)*16, 16, 16  );
		
		for( int tx = x+1; tx < x+width - 1 ; tx++ ) {
			Renderer.renderSubImage(Game.textboxTileset, 16, 0, 16, 16, tx*16, y*16, 16, 16  );
			for( int ty = y+1; ty < y+height-1; ty++)
			{
				Renderer.renderSubImage(Game.textboxTileset, 16, 16, 16, 16, tx*16, ty*16, 16, 16  );
			}
			Renderer.renderSubImage(Game.textboxTileset, 16, 32, 16, 16, tx*16, (y+height-1)*16, 16, 16  );
		}
		
		Renderer.renderSubImage(Game.textboxTileset, 32, 0, 16, 16, (x+width-1)*16, y*16, 16, 16  );
		for( int ty = y+1; ty < y+height-1; ty++)
		{
			Renderer.renderSubImage(Game.textboxTileset, 32, 16, 16, 16, (x+width-1)*16, ty*16, 16, 16  );
		}
		Renderer.renderSubImage(Game.textboxTileset, 32, 32, 16, 16, (x+width-1)*16, (y+height-1)*16, 16, 16  );
		
		
	}

	public static Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;
        
        WritableImage output = new WritableImage(
          W * S,
          H * S
        );
        
        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();
        
        for (int y = 0; y < H; y++) {
          for (int x = 0; x < W; x++) {
            final int argb = reader.getArgb(x, y);
            for (int dy = 0; dy < S; dy++) {
              for (int dx = 0; dx < S; dx++) {
                writer.setArgb(x * S + dx, y * S + dy, argb);
              }
            }
          }
        }
        
        return output;
      }
	
	public static Image resample(Image input, WritableImage output, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;
        
        
        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();
        
        for (int y = 0; y < H; y++) {
          for (int x = 0; x < W; x++) {
            final int argb = reader.getArgb(x, y);
            for (int dy = 0; dy < S; dy++) {
              for (int dx = 0; dx < S; dx++) {
                writer.setArgb(x * S + dx, y * S + dy, argb);
              }
            }
          }
        }
        
        return output;
      }
}
