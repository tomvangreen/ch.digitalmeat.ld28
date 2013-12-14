package ch.digitalmeat.ld28;

import ch.digitalmeat.ld28.person.Person;
import ch.digitalmeat.ld28.person.PersonSheet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InGameScreen implements Screen{
	private MapRenderer mapRenderer;
	private OrthographicCamera camera;
	private BackgroundRenderer backgroundRenderer;
	
	public InGameScreen(){
		int w = ConcertSmugglers.instance.config.xResolution;
		int h = ConcertSmugglers.instance.config.yResolution;
		this.camera = new OrthographicCamera(w, h);
		mapRenderer = new MapRenderer();
		mapRenderer.create(camera);
		camera.update();
		Assets assets = ConcertSmugglers.instance.assets;
		this.backgroundRenderer = new BackgroundRenderer(assets.ground, assets.sky, camera);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		Person focus = mapRenderer.focusedPerson;
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
