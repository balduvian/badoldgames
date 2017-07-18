package LegendOfBarasu;

import java.awt.Color;
import java.awt.Rectangle;

public class LevelDoor extends GameObject{
	
	public boolean locked;
	
	public LevelDoor(int xx, int yy) {
		super(xx, yy);
		locked = true;
		size = 40;
		rs = new Rectangle[6];
		rs[0] = new Rectangle(0,36,40,4);
		rs[1] = new Rectangle(4,8,32,28);
		rs[2] = new Rectangle(8,4,24,4);
		rs[3] = new Rectangle(12,0,16,4);
		rs[4] = new Rectangle(8,8,24,32);
		rs[5] = new Rectangle(12,4,16,4);
		cs = new Color[6];
		cs[0] = Color.YELLOW;
		cs[1] = Color.YELLOW;
		cs[2] = Color.YELLOW;
		cs[3] = Color.YELLOW;
		cs[4] = Color.GRAY;
		cs[5] = Color.GRAY;
	}
	
	public void tick(){
		coll = getColl();
		if(Game.inv.contains(0)){
			locked = false;
			cs[4] = Color.BLACK;
			cs[5] = Color.BLACK;
		}
		if(colliding(Game.object.get(0).getColl()) && locked == false){
			for(int x=0;x<Game.inv.size();x++){
				if(Game.inv.get(x)==0){
					Game.inv.remove(x);
				}
			}
			Game.makeLevel();
		}
	}
}
