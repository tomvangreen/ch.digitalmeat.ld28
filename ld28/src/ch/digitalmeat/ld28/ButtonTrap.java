package ch.digitalmeat.ld28;

import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class ButtonTrap extends Trap{
	private Button button;

	public ButtonTrap(Button button){
		this.button = button;
	}
	
	@Override
	protected boolean isPressed() {
		return button.isPressed();
	}

}
