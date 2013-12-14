package ch.digitalmeat.ld28;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeyTrap {
	private int key;
	
	public boolean isDown;
	public boolean isPressed;
	public boolean isUp;
	
	public KeyTrap(int key){
		this.key = key;
	}
	
	public void flush(){
		
	}
	
	public void update(){
		boolean wasPressed = isPressed;
		isPressed = Gdx.input.isKeyPressed(key);
		isDown = isPressed && !wasPressed;
		isUp = !isPressed && wasPressed;
		if(isDown)System.out.println("Trap down");
		if(isUp)System.out.println("Trap up");
	}
}
