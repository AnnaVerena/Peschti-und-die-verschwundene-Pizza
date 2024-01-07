package game;

import render.Color;
import render.Renderer;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class GameUtil {
	public static void renderTextbox( int x, int y, int width, int height) {
		renderTextbox( x, y, width, height, Color.WHITE );
	}
	
	public static void renderTextbox( int x, int y, int width, int height, Color color) {
		
		Renderer.renderSubImage(Game.textboxTileset, 0, 0, 16, 16, x*16, y*16, 16, 16, color  );
		for( int ty = y+1; ty < y+height-1; ty++)
		{
			Renderer.renderSubImage(Game.textboxTileset, 0, 16, 16, 16, x*16, ty*16, 16, 16, color  );
		}
		Renderer.renderSubImage(Game.textboxTileset, 0, 32, 16, 16, x*16, (y+height-1)*16, 16, 16, color  );
		
		for( int tx = x+1; tx < x+width - 1 ; tx++ ) {
			Renderer.renderSubImage(Game.textboxTileset, 16, 0, 16, 16, tx*16, y*16, 16, 16, color  );
			for( int ty = y+1; ty < y+height-1; ty++)
			{
				Renderer.renderSubImage(Game.textboxTileset, 16, 16, 16, 16, tx*16, ty*16, 16, 16, color  );
			}
			Renderer.renderSubImage(Game.textboxTileset, 16, 32, 16, 16, tx*16, (y+height-1)*16, 16, 16, color  );
		}
		
		Renderer.renderSubImage(Game.textboxTileset, 32, 0, 16, 16, (x+width-1)*16, y*16, 16, 16, color  );
		for( int ty = y+1; ty < y+height-1; ty++)
		{
			Renderer.renderSubImage(Game.textboxTileset, 32, 16, 16, 16, (x+width-1)*16, ty*16, 16, 16, color  );
		}
		Renderer.renderSubImage(Game.textboxTileset, 32, 32, 16, 16, (x+width-1)*16, (y+height-1)*16, 16, 16, color  );
		
		
	}

	public static BufferedImage resample(BufferedImage input, int scaleFactor) {
        final int W = input.getWidth();
        final int H = input.getHeight();

        BufferedImage output = new BufferedImage(
          W * scaleFactor,
          H * scaleFactor,
                BufferedImage.TYPE_INT_ARGB
        );

        AffineTransform at = new AffineTransform();
        at.scale(scaleFactor, scaleFactor);
        AffineTransformOp scaleOp =
                new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        output = scaleOp.filter(input, output);
        
        return output;
      }
}
