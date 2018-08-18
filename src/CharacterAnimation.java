import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CharacterAnimation {
	
	static final int UP = 1;
	static final int DOWN = 0;
	static final int LEFT = 2;
	static final int RIGHT = 3;
	
	public static final GameEvent createWalkingAnimation( int x, int y, int dir, Image charset ) {
		return new GameEvent() {
			int animationTimer = 15;			
			
			public void update() {
				if( animationTimer > 0 ) animationTimer--;
			}
			
			public void draw(GraphicsContext gc) {
				if(dir == RIGHT)
		        {
		        	if( (animationTimer / 4) == 0 || (animationTimer / 4) == 3 ) gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32-animationTimer*2, (y-1)*32, 32, 64);
		        	else if( (animationTimer / 4) == 1 || (animationTimer / 4) == 2 ) gc.drawImage(charset, ((x+y)%2 +1)*32, dir*64, 32, 64, x*32-animationTimer*2, (y-1)*32, 32, 64);
		        }
		        else if(dir == LEFT)
		        {
		        	if( (animationTimer / 4) == 0 || (animationTimer / 4) == 3 ) gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32+animationTimer*2, (y-1)*32, 32, 64);
		        	else if( (animationTimer / 4) == 1 || (animationTimer / 4) == 2 ) gc.drawImage(charset, ((x+y)%2 +1)*32, dir*64, 32, 64, x*32+animationTimer*2, (y-1)*32, 32, 64);
		        }
		        else if(dir == DOWN)
		        {
		        	if( (animationTimer / 4) == 0 || (animationTimer / 4) == 3 ) gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32, (y-1)*32-animationTimer*2, 32, 64);
		        	else if( (animationTimer / 4) == 1 || (animationTimer / 4) == 2 ) gc.drawImage(charset, ((x+y)%2+1)*32, dir*64, 32, 64, x*32, (y-1)*32-animationTimer*2, 32, 64);
		        }
		        else if(dir == UP)
		        {
		        	if( (animationTimer / 4) == 0 || (animationTimer / 4) == 3 ) gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32, (y-1)*32+animationTimer*2, 32, 64);
		        	else if( (animationTimer / 4) == 1 || (animationTimer / 4) == 2 ) gc.drawImage(charset, ((x+y)%2+1)*32, dir*64, 32, 64, x*32, (y-1)*32+animationTimer*2, 32, 64);
		        }				
			}
			
			public boolean isFinished() {
				return animationTimer == 0;
			}
		};
	}
	
	public static final GameEvent createStandingAnimation( int x, int y, int dir, Image charset ) {
		return new GameEvent() {
			
			public void draw(GraphicsContext gc) {
				gc.drawImage(charset, 0*32, dir*64, 32, 64, x*32, (y-1)*32, 32, 64);
			}
		};
	}
}
