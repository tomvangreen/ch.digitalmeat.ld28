package ch.digitalmeat.ld28;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class ConcertSmugglers extends Game {
	public static ConcertSmugglers instance;
	
	public final Config config;
	public final Assets assets;

	private InGameScreen inGameScreen;
	
	public ConcertSmugglers(Config config){
		instance = this;
		
		this.config = config;
		this.assets = new Assets();
	}
	
	@Override
	public void create() {			
		assets.create();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		this.inGameScreen = new InGameScreen();
		setScreen(inGameScreen);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {		
		super.render();		
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
