package ch.digitalmeat.ld28;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class TextManager extends Actor{
	private BitmapFont font;
	private Skin skin;
	public TextManager(){
		this.font = ConcertSmugglers.instance.assets.font_visitor_10;
		this.skin = ConcertSmugglers.instance.assets.skin;
	}	
	
	public void spawnText(String text, Color color, float x, float y){
		Label label = new Label(text, skin);
		label.setPosition(x - label.getWidth() / 2, y - label.getHeight() / 2);
		label.addAction(
			Actions.sequence(
				Actions.moveBy(0f, 20f, 2f)
				, Actions.parallel(Actions.moveBy(0f, 10f, 1f), Actions.alpha(0))
				, Actions.removeActor()
			)
		);
		getStage().addActor(label);
	}
}
