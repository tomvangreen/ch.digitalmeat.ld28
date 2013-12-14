package ch.digitalmeat.ld28;

import java.util.ArrayList;
import java.util.List;

import ch.digitalmeat.ld28.PersonConfig.PersonType;
import ch.digitalmeat.ld28.person.Person;
import ch.digitalmeat.ld28.person.PersonSheet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MapRenderer {
	private OrthographicCamera camera;
	private TiledMap map;
	private TiledMapRenderer mapRenderer;

	private int[] backgroundLayers = null;
	private int[] foregroundLayers = null;
	private Stage stage;
	private List<Integer> layerList;
	private int[] entityLayers;
	private int mapWidth;
	private int mapHeight;
	private int tilePixelWidth;
	private int tilePixelHeight;
	private int mapPixelWidth;
	private int mapPixelHeight;

	public Person focusedPerson = null;
	private List<Person> playerPersons;
	private List<Person> guardPersons;
	private List<Person> guestPersons;
	private PersonManager personManager;
	private MapLayer collisionLayer; 
	
	public MapRenderer()
	{
		this.layerList = new ArrayList<Integer>();
		playerPersons = new ArrayList<Person>();
		guardPersons = new ArrayList<Person>();
		guestPersons = new ArrayList<Person>();
		personManager = new PersonManager();
	}
	
	public void create(OrthographicCamera camera){
		this.camera = camera;
		int w = ConcertSmugglers.instance.config.xResolution;
		int h = ConcertSmugglers.instance.config.yResolution;
		this.stage = new Stage(w, h);
		
	}
	
	public void loadMap(String file)
	{
		this.mapRenderer = null;
		focusedPerson = null;
		playerPersons.clear();
		guardPersons.clear();
		guestPersons.clear();
		map = ConcertSmugglers.instance.assets.loadTilemap(file);
		MapProperties prop = map.getProperties();

		mapWidth = prop.get("width", Integer.class);
		mapHeight = prop.get("height", Integer.class);
		tilePixelWidth = prop.get("tilewidth", Integer.class);
		tilePixelHeight = prop.get("tileheight", Integer.class);

		mapPixelWidth = mapWidth * tilePixelWidth;
		mapPixelHeight = mapHeight * tilePixelHeight;
		
		MapLayers layers = map.getLayers();
		backgroundLayers = getLayers(layers, "bg");
		foregroundLayers = getLayers(layers, "fg");
		entityLayers = getLayers(layers, "entity");
		int[] collision = getLayers(layers, "collision");
		
		if(collision != null && collision.length > 0){
			this.collisionLayer = layers.get(collision[0]);
		}
		//TODO: Load properties
		int w = ConcertSmugglers.instance.config.xResolution;
		int h = ConcertSmugglers.instance.config.yResolution;

		int toCreate = 10;
		stage.clear();
		for(int layerIndex : entityLayers){
			MapLayer layer = layers.get(layerIndex);
			MapObjects objects = layer.getObjects();
			for(MapObject obj : objects){
				String type = obj.getProperties().get("type", String.class);
				PersonConfig config = null;
				List<Person> list = null;
				if("player_ticket".equals(type)){
					config = PersonConfig.PLAYER_WITH_TICKET;
					list = playerPersons;
				}
				else if("player_without_ticket".equals(type)){
					config = PersonConfig.PLAYER_WITHOUT_TICKET;
					list = playerPersons;
				}
				else if("guard".equals(type)){
					config = PersonConfig.NORMAL_GUARD;
					list = guardPersons;
				}
				else if("guest".equals(type)){
					config = PersonConfig.Guest();
					list = guestPersons;
				}
				if(config != null){
					list.add(spawnPerson(config, obj));
				}
			}
		}
		if(playerPersons.size() > 0){
			focusedPerson = playerPersons.get(0);
		}
		camera.position.x = +mapPixelWidth / 2;
		camera.position.y = +mapPixelHeight / 2;
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		mapRenderer.setView(camera.combined, 0, 0, w, h);
	}
	
	private Person spawnPerson(PersonConfig config, MapObject obj) {
		if(!(obj instanceof EllipseMapObject)){
			return null;
		}
		EllipseMapObject eObj = (EllipseMapObject) obj;
		System.out.println("Spawning " + config.type);
		PersonSheet[] sheets = ConcertSmugglers.instance.assets.sheets;
		Person person = new Person();
		person.init(sheets[0], config);
		person.setSize(16f, 16f);
		float y = eObj.getEllipse().y;
		y -= y % 32;
		person.setPosition(eObj.getEllipse().x, y);
		stage.addActor(person);
		if(config.type == PersonType.Player){
			person.addAction(Actions.moveBy(50, 500, 10));
		}
		return person;
	}

	private int[] getLayers(MapLayers layers, String name){
		layerList.clear();
		int count = layers.getCount();
		for(int index = 0; index < count; index ++){
			MapLayer layer = layers.get(index);
			if(name.equals(layer.getProperties().get("type"))){
				layerList.add(index);
			}
		}
		if(layerList.size() == 0){
			return null;
		}
		int[] returnValue = new int[layerList.size()];
		for(int index = 0; index < layerList.size(); index++){
			returnValue[index] = layerList.get(index);
		}
		return returnValue;
	}
	
	public void render(){
		if(mapRenderer != null){
			mapRenderer.setView(camera);
			mapRenderer.render();
		}
	}

	public void render(int[] layers){
		if(mapRenderer != null && layers != null){
			mapRenderer.setView(camera);
			mapRenderer.render(layers);
		}
	}
	
	public void renderBackground(){
		render(backgroundLayers);
	}
	
	public void renderForeground(){
		render(foregroundLayers);
	}
	
	public void update(){
		personManager.handleMovement(Gdx.graphics.getDeltaTime(), stage.getActors(), collisionLayer);
		stage.act();
	}
	
	public void renderEntities(){
		stage.setCamera(camera);
		stage.draw();

	}
}
