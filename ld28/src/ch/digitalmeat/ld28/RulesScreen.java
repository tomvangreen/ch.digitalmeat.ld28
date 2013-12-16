package ch.digitalmeat.ld28;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class RulesScreen implements Screen{
	private Stage stage;
	private ParticleEffect effect;
	private SpriteBatch batch;
	private Image sightBlocker;
	
	public RulesScreen(){
		this.batch = new SpriteBatch();
		Config cfg = ConcertSmugglers.instance.config;
		stage = new Stage(cfg.xTarget, cfg.yTarget);
	}
	
	@Override
	public void render(float delta) {
		PlayerController c = ConcertSmugglers.instance.controller;
		c.update();
		if(c.any){
			ConcertSmugglers.instance.game();
			return;
		}

		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		effect.update(delta);
		batch.begin();
		effect.draw(batch);
		batch.end();
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		Config cfg = ConcertSmugglers.instance.config;
		Assets assets = ConcertSmugglers.instance.assets;
		this.effect = assets.introEffect();
		this.effect.setPosition(cfg.xResolution / 2, 0);
		String[] howto = assets.howto.split("\n");
		
		float textX = cfg.xTarget / 2;
		float textY = cfg.yTarget / 4;
		
		for(int index = 0; index < howto.length; index++){
			String text =howto[index];
			if(text != null && text != ""){
				createIntroFadeText(assets.skin, 0.5f + index * 1f, text , textX, textY);
			}
		}
		sightBlocker = new Image(assets.blank);
		sightBlocker.setColor(Color.BLACK);
		sightBlocker.setSize(cfg.xTarget, cfg.yTarget);
		sightBlocker.addAction(
			Actions.sequence(
				Actions.fadeOut(1f)
				, Actions.run(new Runnable() {
					@Override
					public void run() {
						ConcertSmugglers.instance.running = true;						
					}
				})
			)
		);
		stage.addActor(sightBlocker);
	}
	
	private void createIntroFadeText(Skin skin, float delay, String text,
			float textX, float textY) {
		Label introFadeText = new Label(text, skin);
		textX -= introFadeText.getWidth() / 2;
		textY -= introFadeText.getHeight() / 2;
		introFadeText.addAction(Actions.alpha(0));
		introFadeText.act(10);
		introFadeText.addAction(
			Actions.sequence(
				Actions.delay(delay)
				, Actions.parallel(
					Actions.moveBy(0, 200, 15)
					, Actions.sequence(
						Actions.fadeIn(3.5f)
						, Actions.delay(3f)
						, Actions.fadeOut(2.5f)
					)
				)
			)
		);
		introFadeText.setPosition(textX, textY);
		stage.addActor(introFadeText);
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
