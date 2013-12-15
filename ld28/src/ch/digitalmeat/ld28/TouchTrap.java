package ch.digitalmeat.ld28;

import com.badlogic.gdx.Gdx;

public class TouchTrap extends Trap {

	@Override
	protected boolean isPressed() {
		return Gdx.input.isTouched();
	}

}
