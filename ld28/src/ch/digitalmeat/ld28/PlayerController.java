package ch.digitalmeat.ld28;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerController {
	
	public boolean left;
	public boolean right;
	
	public void clear(){
		left = false;
		right = false;
	}
	
	public void update(){
		left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
	}
}
