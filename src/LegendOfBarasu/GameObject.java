package LegendOfBarasu;

import java.awt.Color;
import java.awt.Rectangle;

public class GameObject {
	public double x;
	public double y;
	public int id;
	public static int size;
	public Rectangle[] rs;
	public Color[] cs;
	public static Rectangle coll;
	public static String name;
	
	public static boolean colliding(Rectangle other){
		if(coll.intersects(other)){
			return true;
		}
		else{
			return false;
		}
	}
	public Rectangle getColl(){
		return new Rectangle((int)(LWindow.canvas.getWidth()/2-Game.object.get(0).x-(int)(size/2)+x),
				(int)(LWindow.canvas.getHeight()/2-Game.object.get(0).y-(int)(size/2)+y),
				size,
				size);
	}
	
	public void tick(){
		coll = getColl();
	}
	
	public void destroy(){
		try{
		Game.object.remove(this);
		}catch(Exception e){
			
		}
	}
	
	public GameObject(int xx,int yy){
		this.x =xx;
		this.y =yy;
		id = Game.object.size();
		coll = new Rectangle(0,0,size,size);
	}
}
