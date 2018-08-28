package events;
import game.Game;
import render.Image;
import render.Renderer;

public class Player extends MapEvent {
	
	int timer;

	/**
	 * Player erbt von MapEvent
	 * @param posx x-Position
	 * @param posy y-Position
	 * @param dir Richtung
	 * @param charset Bilddatei
	 */
	
	public Player(int posx, int posy, int dir, Image charset) {
		super(posx, posy, dir, charset);
		
		timer = 0;
	}
	
	public void tryToMove( int dir ) {
		this.dir = dir;
		
		int x = posx;
		if(dir == RIGHT) x++;
		if(dir == LEFT) x--;
		int y = posy;
		if(dir == UP) y--;
		if(dir == DOWN) y++;
		
		for(MapEvent me: Game.map.mapEvents)
		{
			if( me.getX() == x && me.getY() == y && me.touchEvent != null)
			{
				Game.startEvent(me.touchEvent);
				break;
			}
		}
		
		if(Game.inputs.contains("RIGHT") && !Game.map.isBlocked(this.getX()+1, this.getY())) move(RIGHT);
    	else if(Game.inputs.contains("LEFT") && !Game.map.isBlocked(this.getX()-1, this.getY())) move(LEFT);
    	else if(Game.inputs.contains("UP") && !Game.map.isBlocked(this.getX(), this.getY()-1)) move(UP);
    	else if(Game.inputs.contains("DOWN") && !Game.map.isBlocked(this.getX(), this.getY()+1)) move(DOWN);
	}
	
	public void move( int dir ) {
		if( dir == UP ) posy--;
		if( dir == DOWN ) posy++;
		if( dir == LEFT ) posx--;
		if( dir == RIGHT ) posx++;
		
		this.dir = dir;
		timer = 15;
	}
	
	public void init()
	{
		frame = 0;
		timer = 0;
	}

	public void update() {
				
		if( timer > 0 ) timer--;
		else if(Game.inputs.contains("A"))
		{
			Game.inputs.remove("A");
			int x = posx;
			if(dir == RIGHT) x++;
			if(dir == LEFT) x--;
			int y = posy;
			if(dir == UP) y--;
			if(dir == DOWN) y++;
			
			for(MapEvent me: Game.map.mapEvents)
			{
				if( me.getX() == x && me.getY() == y && me.actionEvent != null)
				{
					Game.startEvent(me.actionEvent);
					break;
				}
			}
		}
    	else if(Game.inputs.contains("RIGHT")) tryToMove(RIGHT);
    	else if(Game.inputs.contains("LEFT")) tryToMove(LEFT);
    	else if(Game.inputs.contains("UP")) tryToMove(UP);
    	else if(Game.inputs.contains("DOWN")) tryToMove(DOWN); 
	}
	
	public void render() {
		
		if(dir == RIGHT)
        {
        	if( (timer / 4) == 0 || (timer / 4) == 3 ) Renderer.renderSubImage(charset, 0, dir*32, 16, 32, posx*16-timer, (posy-1)*16, 16, 32);
        	else if( (timer / 4) == 1 || (timer / 4) == 2 ) Renderer.renderSubImage(charset, ((posx+posy)%2 +1)*16, dir*32, 16, 32, posx*16-timer, (posy-1)*16, 16, 32);
        }
        else if(dir == LEFT)
        {
        	if( (timer / 4) == 0 || (timer / 4) == 3 ) Renderer.renderSubImage(charset, 0, dir*32, 16, 32, posx*16+timer, (posy-1)*16, 16, 32);
        	else if( (timer / 4) == 1 || (timer / 4) == 2 ) Renderer.renderSubImage(charset, ((posx+posy)%2 +1)*16, dir*32, 16, 32, posx*16+timer, (posy-1)*16, 16, 32);
        }
        else if(dir == DOWN)
        {
        	if( (timer / 4) == 0 || (timer / 4) == 3 ) Renderer.renderSubImage(charset, 0, dir*32, 16, 32, posx*16, (posy-1)*16-timer, 16, 32);
        	else if( (timer / 4) == 1 || (timer / 4) == 2 ) Renderer.renderSubImage(charset, ((posx+posy)%2+1)*16, dir*32, 16, 32, posx*16, (posy-1)*16-timer, 16, 32);
        }
        else if(dir == UP)
        {
        	if( (timer / 4) == 0 || (timer / 4) == 3 ) Renderer.renderSubImage(charset, 0, dir*32, 16, 32, posx*16, (posy-1)*16+timer, 16, 32);
        	else if( (timer / 4) == 1 || (timer / 4) == 2 ) Renderer.renderSubImage(charset, ((posx+posy)%2+1)*16, dir*32, 16, 32, posx*16, (posy-1)*16+timer, 16, 32);
        }				
	}
	
	public int getScrX() {
		if( dir == RIGHT ) return posx*16-timer;
		else if( dir == LEFT ) return posx*16+timer;
		else return posx*16;
	}
	
	public int getScrY() {
		if( dir == DOWN ) return posy*16-timer;
		else if (dir == UP ) return posy*16+timer;
		else return posy*16;
	}
	
	
}
