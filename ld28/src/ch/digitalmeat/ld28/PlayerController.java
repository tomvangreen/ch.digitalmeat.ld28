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
	public boolean any;
	
	public KeyTrap switchPlayerTrap = new KeyTrap(Input.Keys.TAB);
	public KeyTrap useTrap = new KeyTrap(Input.Keys.SPACE);
	
	public ButtonTrap leftButton = new ButtonTrap();
	public ButtonTrap rightButton = new ButtonTrap();
	public ButtonTrap switchButton = new ButtonTrap();
	public ButtonTrap useButton = new ButtonTrap();
	
	public Trap touch = new TouchTrap();
	
	public Trap anyKey = new KeyTrap(Input.Keys.ANY_KEY);
	
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
		traps.add(touch);
		traps.add(anyKey);
	}
	
	public void clear(){
		left = false;
		right = false;
	}
	
	public void update(){
		for(Trap trap : traps){
			trap.update();
		}
		left = Gdx.input.isKeyPressed(Input.Keys.LEFT) || leftButton.isPressed;
		right = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || rightButton.isPressed;
		switchPlayer = switchPlayerTrap.isDown || switchButton.isDown;
		use = useTrap.isDown || useButton.isDown;
		any = touch.isDown || anyKey.isDown;
	}
}
