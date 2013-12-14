package ch.digitalmeat.ld28;

import ch.digitalmeat.ld28.person.PersonSheet;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Assets {
	public static final int PERSON_SPRITES = 8;
	public static final int PERSON_HEIGHT = 16;
	public static final int PERSON_WIDTH = 16;
	private final AssetManager manager;
	public PersonSheet[] sheets;
	public Texture ground;
	public Texture sky;
	
	public Assets()
	{
		this.manager = new AssetManager();
	}
	
	public void create(){
		manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));		
		manager.load("data/Persons.png", Texture.class);
		manager.load("data/ground.png", Texture.class);
		manager.load("data/sky.png", Texture.class);
		manager.finishLoading();
		
		ground = manager.get("data/ground.png");
		sky = manager.get("data/sky.png");
		sheets = new PersonSheet[PERSON_SPRITES];
		
		
		Texture personsTexture = manager.get("data/Persons.png");
		for(int index = 0; index < 8; index++){
			PersonSheet sheet = new PersonSheet();
			sheet.front = new TextureRegion[]{
					pr(personsTexture, px(0), py(index))	
				};
			sheet.side_idle = new TextureRegion[]{
				pr(personsTexture, px(1), py(index))	
				, pr(personsTexture, px(2), py(index))	
			};
			sheet.side_walk = new TextureRegion[]{
				pr(personsTexture, px(1), py(index))	
				, pr(personsTexture, px(3), py(index))	
				, pr(personsTexture, px(2), py(index))	
				, pr(personsTexture, px(4), py(index))	
			};
			sheets[index] = sheet;
		}
	}
	
	private TextureRegion pr(Texture texture, int x, int y){
		return new TextureRegion(texture, x, y, PERSON_WIDTH, PERSON_HEIGHT);
	}
	
	private int px(int position){
		return position * PERSON_WIDTH;
	}
	private int py(int position){
		return position * PERSON_HEIGHT;
	}
	
	public TiledMap loadTilemap(String file){
		manager.load(file, TiledMap.class);
		manager.finishLoading();
		return manager.get(file);
	}
}
