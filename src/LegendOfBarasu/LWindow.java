package LegendOfBarasu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LWindow extends JFrame{
	
	public static Canvas canvas;
	public static PlayerActions p = new PlayerActions();
	public static LinkedList<Rectangle> wls = new LinkedList<Rectangle>();
	static Rectangle up;
	static Rectangle lp;
	static Rectangle rp;
	static Rectangle dp;
	
	public static boolean inRect(int y,int x,int t,int b,int l,int r){
		boolean bb = false;
		if(y>=t && y<=b && x>=l && x<=r){
			bb = true;
		}
		return bb;
	}
	public static void getWalls(){
		wls.clear();
		for(int yy=0;yy<3;yy++){
			for(int xx=0;xx<3;xx++){
				try{
					int x = (int)(Game.object.get(0).x/40+xx);
					int y = (int)(Game.object.get(0).y/40+yy);
					if(Game.walls[y][x]){
					wls.add(new Rectangle((int)(canvas.getWidth()/2+x*40-Game.object.get(0).x-20),(int)(canvas.getHeight()/2+y*40-Game.object.get(0).y-20),40,40));
					}
				}catch(Exception e){}
			}
		}
	}
	
	public static void tick(){
		canvas.repaint();
		GameObject obj = Game.object.get(0);
		up = new Rectangle(canvas.getWidth()/2-obj.size/2,canvas.getHeight()/2-obj.size/2, 40, 4);
		lp = new Rectangle(canvas.getWidth()/2-obj.size/2,canvas.getHeight()/2-obj.size/2, 4, 40);
		dp = new Rectangle(canvas.getWidth()/2-obj.size/2,canvas.getHeight()/2-obj.size/2+36, 40, 4);
		rp = new Rectangle(canvas.getWidth()/2-obj.size/2+36,canvas.getHeight()/2-obj.size/2, 4, 40);
		getWalls();
		if(p.up){
			obj.y -= 0.3;
		}
		if(p.left){
			obj.x -= 0.3;
	    }
		if(p.down){
			obj.y += 0.3;
	    }
		if(p.right){
			obj.x += 0.3;
	    }
		for(int x=0;x<wls.size();x++){
			if(up.intersects(wls.get(x))){
				Game.object.get(0).y += 0.3;
			}
			if(rp.intersects(wls.get(x))){
				Game.object.get(0).x -= 0.3;
			}
			if(dp.intersects(wls.get(x))){
				Game.object.get(0).y -= 0.3;
			}
			if(lp.intersects(wls.get(x))){
				Game.object.get(0).x += 0.3;
			}
		}
	}
	
	public LWindow(){
		setTitle("LEGEND OF BARASU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		canvas = new Canvas();
		Container cp = getContentPane();
	    cp.add(canvas);
	    addKeyListener(p);
	  //setResizable(false);
	    getWalls();
		setVisible(true);
	}
	
	public class Canvas extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			g.setColor(new Color(64,64,64));
			g.fillRect((int)(0), (int)(0), this.getWidth(), this.getHeight());
			g.setColor(new Color(32,32,32));
			g.fillRect((int)(this.getWidth()/2-Game.object.get(0).x),(int)(this.getHeight()/2-Game.object.get(0).y),Game.walls[0].length*40-40,Game.walls.length*40-40);
			//each position is put in 20 of where the boundary is so that there is minimal block gapping at edges
			
			g.setColor(new Color(64,64,64));
			for(int y=0;y<Game.walls.length;y++){
				for(int x=0;x<Game.walls[y].length;x++){
					if(Game.walls[y][x]){
						g.fillRect((int)(this.getWidth()/2+x*40-Game.object.get(0).x-20),(int)(this.getHeight()/2+y*40-Game.object.get(0).y-20), 40, 40);
					}
				}
			}
			//g.fillRect((int)(0), (int)(this.getWidth()/2-Game.object.get(0).y-550), canvas.getWidth(), canvas.getHeight());
			
			for(int i=0;i<Game.object.size();i++){
				for(int x=0;x<Game.object.get(i).rs.length;x++){
					GameObject obj = Game.object.get(i);
					g.setColor(obj.cs[x]);
					g.fillRect((int)(this.getWidth()/2+obj.rs[x].getX()-Game.object.get(0).x-(int)(obj.size/2)+obj.x),
						(int)(this.getHeight()/2+obj.rs[x].getY()-Game.object.get(0).y-(int)(obj.size/2)+obj.y),
						(int)(obj.rs[x].getWidth()),
						(int)(obj.rs[x].getHeight()));
				}
			}
			g.setColor(Color.WHITE);
			g.drawString("X: "+Math.round(Game.object.get(0).x)+" Y: "+Math.round(Game.object.get(0).y)+" | "+Game.object.size(), 0, 10);
			//g.drawString(Game.inv.get(0)+" . . .", 0, 20);
			//g.setColor(Color.RED);
			//for(int i=0;i<wls.size();i++){
			//	g.drawRect((int)(wls.get(i).getX()), (int)(wls.get(i).getY()), 40, 40);
			//}
			//try{
			//g.drawRect((int)(up.getX()), (int)(up.getY()), (int)(up.getWidth()), (int)(up.getHeight()));
			//g.drawRect((int)(lp.getX()), (int)(lp.getY()), (int)(lp.getWidth()), (int)(lp.getHeight()));
			//g.drawRect((int)(rp.getX()), (int)(rp.getY()), (int)(rp.getWidth()), (int)(rp.getHeight()));
			//g.drawRect((int)(dp.getX()), (int)(dp.getY()), (int)(dp.getWidth()), (int)(dp.getHeight()));
			//}catch(Exception e){}
		}
	}
}
