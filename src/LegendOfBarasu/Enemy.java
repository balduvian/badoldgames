package LegendOfBarasu;

import java.awt.Color;
import java.awt.Rectangle;

public class Enemy extends GameObject{
	
	public int damage;
	public double speed;
	
	public Enemy(int xx, int yy) {
		super(xx, yy);
		size = 40;
		rs = new Rectangle[6];
		rs[0] = new Rectangle(0,0,size,size);//BODY
		rs[1] = new Rectangle(4,4,8,16);//LEYE
		rs[2] = new Rectangle(28,4,8,16);//REYE
		rs[3] = new Rectangle(4,28,12,8);//MOUTHUL
		rs[4] = new Rectangle(24,28,12,8);//MOUTHUR
		rs[5] = new Rectangle(12,24,16,8);//MOUTHB
		cs = new Color[6];
		cs[0] = Color.RED;
		cs[1] = Color.BLACK;
		cs[2] = Color.BLACK;
		cs[3] = Color.BLACK;
		cs[4] = Color.BLACK;
		cs[5] = Color.BLACK;
	}

}
