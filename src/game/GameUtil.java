package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Affine;

public class GameUtil {
	
	public final static int UP = 1;
	public final static int DOWN = 0;
	public final static int LEFT = 2;
	public final static int RIGHT = 3;
	
	public static final Affine IDENTITY = new Affine();
	
	public static void drawTextbox( GraphicsContext gc, int x, int y, int width, int height) {
		gc.setTransform(IDENTITY);
		
		gc.drawImage(Game.textboxTileset, 0, 0, 32, 32, x*16*2, y*16*2, 32, 32  );
		for( int ty = y+1; ty < y+height-1; ty++)
		{
			gc.drawImage(Game.textboxTileset, 0, 32, 32, 32, x*16*2, ty*16*2, 32, 32  );
		}
		gc.drawImage(Game.textboxTileset, 0, 64, 32, 32, x*16*2, (y+height-1)*16*2, 32, 32  );
		
		for( int tx = x+1; tx < x+width - 1 ; tx++ ) {
			gc.drawImage(Game.textboxTileset, 32, 0, 32, 32, tx*16*2, y*16*2, 32, 32  );
			for( int ty = y+1; ty < y+height-1; ty++)
			{
				gc.drawImage(Game.textboxTileset, 32, 32, 32, 32, tx*16*2, ty*16*2, 32, 32  );
			}
			gc.drawImage(Game.textboxTileset, 32, 64, 32, 32, tx*16*2, (y+height-1)*16*2, 32, 32  );
		}
		
		gc.drawImage(Game.textboxTileset, 64, 0, 32, 32, (x+width-1)*16*2, y*16*2, 32, 32  );
		for( int ty = y+1; ty < y+height-1; ty++)
		{
			gc.drawImage(Game.textboxTileset, 64, 32, 32, 32, (x+width-1)*16*2, ty*16*2, 32, 32  );
		}
		gc.drawImage(Game.textboxTileset, 64, 64, 32, 32, (x+width-1)*16*2, (y+height-1)*16*2, 32, 32  );
		
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
}
