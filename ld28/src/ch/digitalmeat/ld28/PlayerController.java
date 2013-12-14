package ch.digitalmeat.ld28;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerController {
	
	public boolean left;
	public boolean right;
	
	public KeyTrap switchPlayer = new KeyTrap(Input.Keys.TAB);
	
	private List<KeyTrap> traps;
	public PlayerController(){
		traps = new ArrayList<KeyTrap>();
		traps.add(switchPlayer);
	}
	
	public void clear(){
		left = false;
		right = false;
	}
	
	public void update(){
		left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		for(KeyTrap trap : traps){
			trap.update();
		}
	}
}
