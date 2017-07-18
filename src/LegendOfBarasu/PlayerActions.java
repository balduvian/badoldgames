package LegendOfBarasu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class PlayerActions implements KeyListener{

	private final Set<Character> pressed = new HashSet<Character>();
	public boolean up=false,left=false,down=false,right=false; 
	
	public void keyPressed(KeyEvent e) {
		pressed.add(e.getKeyChar());
		up=pressed.contains('w');
		left=pressed.contains('a');
		down=pressed.contains('s');
		right=pressed.contains('d');
	}
	public void keyReleased(KeyEvent e) {
		pressed.remove(e.getKeyChar());
		up=pressed.contains('w');
		left=pressed.contains('a');
		down=pressed.contains('s');
		right=pressed.contains('d');
	}
	public void keyTyped(KeyEvent e) {
	}

}
