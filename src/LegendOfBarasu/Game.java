package LegendOfBarasu;

import java.util.LinkedList;

public class Game {
	//static int psx;
	//static int psy;
	public static LinkedList<GameObject> object = new LinkedList<GameObject>();
	public static boolean[][] walls;
	public static int level =0;
	public static LinkedList<Integer> inv = new LinkedList<Integer>();
	
	public static void makeLevel(){
		level++;
		object.clear();
		int s=(int)(3+(Math.floor(level/2)*2)),r=(int)(7+(Math.floor(level/2)*2));
		genroom(s,r);
		object.addFirst(new Player(((s*r)/2)*40,((s*r)/2)*40));
	}
	
	public static void main(String[] args) {
		makeLevel();
		new LWindow();
		while(true){
			LWindow.tick();
			for(int x=0;x<object.size();x++){
				object.get(x).tick();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void genroom(int size,int rs){
		walls = new boolean[size*rs][size*rs];
		int[][] conns = new int[size][size];
		for(int iy=0;iy<size;iy++){
			for(int ix=0;ix<size;ix++){
				for(int y=0;y<rs;y++){
					for(int x=0;x<rs;x++){
						if(y==0 || y==rs-1 || x==0 || x==rs-1){
							if(y+rs*iy ==0 || x+rs*ix ==0 || y+rs*iy ==size*rs-1 || x+rs*ix ==size*rs-1){
								walls[(int)(y+rs*iy)][x+rs*ix] = true;
							}
							//else if((x<=(int)(rs/2+1) && x>=(int)(rs/2-1)) || (y<=(int)(rs/2+1) && y>=(int)(rs/2-1))){
							//	walls[(int)(y+rs*iy)][x+rs*ix] = false;
							//}
							else{
								walls[(int)(y+rs*iy)][x+rs*ix] = true;
							}
						}
						else if(y==1 || y==rs-2 || x==1 || x==rs-2){
							
						}
						else if(Math.random()>0.8){
							walls[(int)(y+rs*iy)][x+rs*ix] = true;
						}
					}
				}
			}
		}
		int spc = 0;
		int sx=(int)(size/2),sy=(int)(size/2);
		while(spc<size*size-size*2){
				//System.out.println(spc);
				if(conns[sy][sx] == 0){
					if(spc == size*size-size*2-1){
						conns[sy][sx] = 2;
					}
					else if(spc==0){
						conns[sy][sx] = 3;
					}
					else{
						conns[sy][sx] = 1;
					}
					spc++;
				}
				LinkedList<Integer> dirs = new LinkedList<Integer>();
				if(sy != 0 && conns[sy-1][sx]==0){
					dirs.add(0);
				}
				if(sx != 0 && conns[sy][sx-1]==0){
					dirs.add(1);
				}
				if( sy != size-1 && conns[sy+1][sx]==0){
					dirs.add(2);
				}
				if(sx != size-1 && conns[sy][sx+1]==0){
					dirs.add(3);
				}
				int go;
				try{
					go=dirs.get((int)(Math.random()*dirs.size()));
				}catch(Exception e){
					go=0;
				}
				//System.out.println(dirs.get((int)(Math.random()*dirs.size())));
				if(dirs.size()>0){
					if(go ==0){
						System.out.println("up");
						walls[(int)(sy*rs)][(int)((sx*rs)+(int)(rs/2))] = false;
						walls[(int)(sy*rs-1)][(int)((sx*rs)+(int)(rs/2))] = false;
						sy--;
					}
					else if(go==1){
						System.out.println("left");
						walls[(int)((sy*rs)+(int)(rs/2))][(int)((sx*rs))] = false;
						walls[(int)((sy*rs)+(int)(rs/2))][(int)((sx*rs)-1)] = false;
						sx--;
					}
					else if(go==2){
						System.out.println("down");
						walls[(int)((sy*rs)+(rs))][(int)((sx*rs)+(rs/2))] = false;
						walls[(int)((sy*rs)+(rs)-1)][(int)((sx*rs)+(rs/2))] = false;
						sy++;
					}
					else if(go==3){
						System.out.println("right");
						walls[(int)((sy*rs)+(rs/2))][(int)((sx*rs)+(rs))] = false;
						walls[(int)((sy*rs)+(rs/2))][(int)((sx*rs)+(rs)-1)] = false;
						sx++;
					}
				}
				else{
					System.out.println("stuck");
					conns[sy][sx] = 2;
					while(true){
						int ty = (int)(Math.random()*size);
						int tx = (int)(Math.random()*size);
						if(conns[ty][tx]==1){
							sy=ty;sx=tx;
							break;
						}
					}
				}
			
		}
		//CONTINUE
		walls[((size*rs)/2)][((size*rs)/2)] = false;
		object.addLast(new LevelDoor((size*rs)/2*40,((size*rs)/2-1)*40));
		walls[(size*rs)/2][((size*rs)/2-1)] = false;
		for(int y =0;y<conns.length;y++){
			for(int x =0;x<conns[y].length;x++){
				if(conns[y][x]==0){
					for(int yy=0;yy<rs;yy++){
						for(int xx=0;xx<rs;xx++){
							try{
							walls[y*rs+yy][x*rs+xx] = true;
							}catch(Exception e){}
						}
					}
				}
				if(conns[y][x]==3){
					for(int yy=0;yy<rs;yy++){
						for(int xx=0;xx<rs;xx++){
							if(yy!=0 && yy!=rs-1 && xx!=0 && xx!=rs-1){
								walls[y*rs+yy][x*rs+xx] = false;
							}
						}
					}
				}
				if(conns[y][x]==2){
					object.addLast(new DoorKey((x*rs*40)+(rs/2)*40,(y*rs*40)+(rs/2)*40));
					walls[(y*rs)+(rs/2)][(x*rs)+(rs/2)] = false;
				}
			}
		}
		System.out.println("DONE! ");
	}

}
