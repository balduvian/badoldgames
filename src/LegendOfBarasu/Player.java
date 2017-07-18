package LegendOfBarasu;

import java.awt.Color;
import java.awt.Rectangle;

public class Player extends GameObject{

	public Player(int xx, int yy) {
		super(xx, yy);
		size = 40;
		rs  = new Rectangle[4];
		rs[0] = new Rectangle(0,0,size,size);//BODY
		rs[1] = new Rectangle(4,24,32,8);//MOUTH
		rs[2] = new Rectangle(4,4,8,16);//LEYE
		rs[3] = new Rectangle(28,4,8,16);//REYE
		cs = new Color[4];
		cs[0] = Color.BLUE;
		cs[1] = Color.BLACK;
		cs[2] = Color.BLACK;
		cs[3] = Color.BLACK;
		name = "player";
	}
	
	//public void tick(){
	//	coll = getColl();
	//}
}
