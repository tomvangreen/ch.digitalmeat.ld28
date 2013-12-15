package ch.digitalmeat.ld28;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerController {
	
	public boolean left;
	public boolean right;
	public boolean switchPlayer;
	public boolean use;
	
	public KeyTrap switchPlayerTrap = new KeyTrap(Input.Keys.TAB);
	public KeyTrap useTrap = new KeyTrap(Input.Keys.SPACE);
	
	public Trap leftButton = new NullTrap();
	public Trap rightButton = new NullTrap();
	public Trap switchButton = new NullTrap();
	public Trap useButton = new NullTrap();
	
	private List<Trap> traps;
	public PlayerController(){
		traps = new ArrayList<Trap>();
	}
	public void addTrapsToList(){
		traps.clear();
		traps.add(switchPlayerTrap);
		traps.add(leftButton);
		traps.add(rightButton);
		traps.add(useButton);
		traps.add(switchButton);
	}
	
	public void clear(){
		left = false;
		right = false;
	}
	
	public void update(){
		left = Gdx.input.isKeyPressed(Input.Keys.LEFT) || leftButton.isPressed;
		right = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || rightButton.isPressed;
		for(Trap trap : traps){
			trap.update();
		}
		switchPlayer = switchPlayerTrap.isDown || switchButton.isDown;
		use = useTrap.isDown || useButton.isDown;
	}
}
