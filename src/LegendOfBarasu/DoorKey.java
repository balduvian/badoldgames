package LegendOfBarasu;

import java.awt.Color;
import java.awt.Rectangle;

public class DoorKey extends Collectible{
	
	public DoorKey(int xx, int yy) {
		super(xx, yy);
		size = 40;
		rs = new Rectangle[5];
		rs[0] = new Rectangle(4,4,16,32);//ring
		rs[1] = new Rectangle(8,8,8,24);//hole
		rs[2] = new Rectangle(20,16,16,8);//pole
		rs[3] = new Rectangle(24,24,4,8);//finger1
		rs[4] = new Rectangle(32,24,4,8);//finger2
		cs = new Color[5];
		cs[0] = Color.YELLOW;
		cs[1] = Color.BLACK;
		cs[2] = Color.YELLOW;
		cs[3] = Color.YELLOW;
		cs[4] = Color.YELLOW;
		colid = 0;
		name = "key";
	}

}
