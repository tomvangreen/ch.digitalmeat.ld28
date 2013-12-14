package ch.digitalmeat.ld28;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class IntroScreen implements Screen {
	private Stage stage;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		stage = new Stage();
		Config cfg = ConcertSmugglers.instance.config;
		Assets assets = ConcertSmugglers.instance.assets;
		Skin skin = assets.skin;
		Label label = new Label("You only get one...", skin);
		
		label.setPosition((cfg.xResolution - label.getWidth())/ 2, cfg.yResolution / 2);
		label.addAction(Actions.alpha(0));
		label.act(10f);
		label.addAction(
			Actions.sequence(
				Actions.fadeIn(2f)
				, Actions.forever(
					Actions.sequence(
						Actions.moveBy(0, 50f, 2f)
						, Actions.moveBy(0, -50f, 2f)
					)
				)
			)
		);
		stage.addActor(label);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
