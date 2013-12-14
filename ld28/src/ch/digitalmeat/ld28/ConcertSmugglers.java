package ch.digitalmeat.ld28;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class ConcertSmugglers extends Game {
	public static ConcertSmugglers instance;
	
	public final Config config;
	public final Assets assets;
	public final Random random;
	public final PlayerController controller;
	
	private InGameScreen inGameScreen;
	
	public ConcertSmugglers(Config config){
		instance = this;
		random = new Random();
		this.config = config;
		this.assets = new Assets();
		this.controller = new PlayerController();
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
