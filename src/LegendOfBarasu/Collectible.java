package LegendOfBarasu;

public class Collectible extends GameObject{
	
	public static int colid;
	
	public Collectible(int xx, int yy) {
		super(xx, yy);

	}
	
	public void collect(){
		Game.inv.add(colid);
		destroy();
	}
	
	public void tick(){
		coll = getColl();
		if(colliding(Game.object.get(0).getColl())){
			collect();
		}
	}
}
