package ch.digitalmeat.ld28;

import ch.digitalmeat.ld28.person.Person;
import ch.digitalmeat.ld28.person.Person.LookingDirection;
import ch.digitalmeat.ld28.person.Person.PersonState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class InGameScreen implements Screen{
	private MapRenderer mapRenderer;
	private OrthographicCamera camera;
	private BackgroundRenderer backgroundRenderer;
	private ConcertSmugglers cs;
	
	public InGameScreen(){
		this.cs = ConcertSmugglers.instance;
		int w = cs.config.xResolution;
		int h = cs.config.yResolution;
		this.camera = new OrthographicCamera(w, h);
		mapRenderer = new MapRenderer();
		mapRenderer.create(camera);
		camera.update();
		Assets assets = cs.assets;
		this.backgroundRenderer = new BackgroundRenderer(assets.ground, assets.sky, camera);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		PlayerController c = cs.controller;
		c.clear();
		c.update();
		if(c.switchPlayer.isDown){
			mapRenderer.nextPlayer();
		}
		Person focus = mapRenderer.focusedPerson;		
		if(focus != null){
			if(c.left && !c.right){
				focus.setDirection(LookingDirection.Left);
				focus.setState(PersonState.Walking);
			}
			else if(!c.left && c.right){
				focus.setDirection(LookingDirection.Right);
				focus.setState(PersonState.Walking);
			}
			else if(c.left && c.right){
				focus.setDirection(LookingDirection.None);
				focus.setState(PersonState.Idle);
			}
			else{
				focus.setState(PersonState.Idle);
			}
		}
		
		c.clear();
		if(focus != null){
			camera.position.set(focus.getX(), focus.getY(), 0);
		}
		camera.update();
		mapRenderer.update();
		
		backgroundRenderer.render();
		mapRenderer.renderBackground();
		mapRenderer.renderEntities();
		mapRenderer.renderForeground();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
//		camera.setToOrtho(false, 500, 500);
		mapRenderer.loadMap("data/test-level.tmx");
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
		// TODO Auto-generated method stub
		
	}

}
