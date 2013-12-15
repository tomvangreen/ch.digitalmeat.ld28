package ch.digitalmeat.ld28;

import ch.digitalmeat.ld28.person.Person;
import ch.digitalmeat.ld28.person.Person.LookingDirection;
import ch.digitalmeat.ld28.person.Person.PersonState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.ObjectSet.SetIterator;

public class InGameScreen implements Screen{
	private MapRenderer mapRenderer;
	private OrthographicCamera camera;
	private BackgroundRenderer backgroundRenderer;
	private ConcertSmugglers cs;
	
	private Stage androidControls;
	private ImageButton leftButton;
	private ImageButton rightButton;
	private ImageButton switchButton;
	private ImageButton actionButton;
	
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
		androidControls = new Stage(cs.config.xTarget, cs.config.yTarget, true);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		PlayerController c = cs.controller;
		c.clear();
		c.update();
		if(c.switchPlayer){
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
		androidControls.act();
		androidControls.draw();
	}

	@Override
	public void resize(int width, int height) {
		mapRenderer.resize(width, height);
		androidControls.setViewport(ConcertSmugglers.instance.config.xTarget, ConcertSmugglers.instance.config.yTarget);
	}

	@Override
	public void show() {
//		camera.setToOrtho(false, 500, 500);
		mapRenderer.loadMap("data/test-level.tmx");
		Assets assets = ConcertSmugglers.instance.assets;
		Config config = ConcertSmugglers.instance.config;
		androidControls = new Stage(cs.config.xTarget, cs.config.yTarget, true);
		Table table = new Table(assets.skin);
		table.setSize(config.xTarget, config.yTarget);
		table.setFillParent(true);
		Texture tex = assets.androidButtons;
		PlayerController controller = ConcertSmugglers.instance.controller;
		leftButton = new ImageButton(
				new Image(new TextureRegion(tex, 0, 0, 32, 32)).getDrawable()
				, new Image(new TextureRegion(tex, 0, 32, 32, 32)).getDrawable()
		);
		rightButton = new ImageButton(
				new Image(new TextureRegion(tex, 32, 0, 32, 32)).getDrawable()
				, new Image(new TextureRegion(tex, 32, 32, 32, 32)).getDrawable()
		);
		switchButton = new ImageButton(
				new Image(new TextureRegion(tex, 64, 0, 32, 32)).getDrawable()
				, new Image(new TextureRegion(tex, 64, 32, 32, 32)).getDrawable()
		);
		actionButton = new ImageButton(
				new Image(new TextureRegion(tex, 96, 0, 32, 32)).getDrawable()
				, new Image(new TextureRegion(tex, 96, 32, 32, 32)).getDrawable()
		);
		controller.leftButton = new ButtonTrap(leftButton);
		controller.rightButton = new ButtonTrap(rightButton);
		controller.switchButton = new ButtonTrap(switchButton);
		controller.useButton = new ButtonTrap(actionButton);
		controller.addTrapsToList();
		table.row().align(Align.bottom);
		table.add(leftButton).expand();
		table.add(switchButton).expand();
		table.add(actionButton).expand();
		table.add(rightButton).expand();
		androidControls.addActor(table);
		Gdx.input.setInputProcessor(androidControls);
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
