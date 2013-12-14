package ch.digitalmeat.ld28;

import ch.digitalmeat.ld28.person.PersonSheet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
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
	public ParticleEffect playerEffect;
	
	public Assets()
	{
		this.manager = new AssetManager();
	}
	
	public void create(){
		manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));		
		manager.load("data/Persons.png", Texture.class);
		manager.load("data/PeopleParts.png", Texture.class);
		manager.load("data/ground.png", Texture.class);
		manager.load("data/sky.png", Texture.class);
		manager.finishLoading();
		
		ground = manager.get("data/ground.png");
		sky = manager.get("data/sky.png");
		
		sheets = new PersonSheet[PERSON_SPRITES];
		
		playerEffect = new ParticleEffect();
		playerEffect.load(Gdx.files.internal("data/player.p"), Gdx.files.internal("data"));		
		Texture personsTexture = manager.get("data/PeopleParts.png");

		int persons = 8;
		for(int index = 0; index < persons; index++){
			PersonSheet sheet = new PersonSheet();
			sheet.front[PersonSheet.SHEET_LEGS] = new TextureRegion[1];			
			sheet.front[PersonSheet.SHEET_LEGS][0] = pr(personsTexture, px(0), py(index)); 
			sheet.side_idle[PersonSheet.SHEET_LEGS] = new TextureRegion[2];			
			sheet.side_idle[PersonSheet.SHEET_LEGS][0] = pr(personsTexture, px(1), py(index)); 
			sheet.side_idle[PersonSheet.SHEET_LEGS][1] = pr(personsTexture, px(2), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_LEGS] = new TextureRegion[4];			
			sheet.side_walk[PersonSheet.SHEET_LEGS][0] = pr(personsTexture, px(3), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_LEGS][1] = pr(personsTexture, px(1), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_LEGS][2] = pr(personsTexture, px(4), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_LEGS][3] = pr(personsTexture, px(2), py(index)); 
			
			sheet.front[PersonSheet.SHEET_TORSO] = new TextureRegion[1];
			sheet.front[PersonSheet.SHEET_TORSO][0] = pr(personsTexture, px(5), py(index)); 
			sheet.side_idle[PersonSheet.SHEET_TORSO] = new TextureRegion[2];			
			sheet.side_idle[PersonSheet.SHEET_TORSO][0] = pr(personsTexture, px(6), py(index)); 
			sheet.side_idle[PersonSheet.SHEET_TORSO][1] = pr(personsTexture, px(7), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_TORSO] = new TextureRegion[4];			
			sheet.side_walk[PersonSheet.SHEET_TORSO][0] = pr(personsTexture, px(8), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_TORSO][1] = pr(personsTexture, px(6), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_TORSO][2] = pr(personsTexture, px(9), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_TORSO][3] = pr(personsTexture, px(7), py(index)); 
			
			sheet.front[PersonSheet.SHEET_HEAD] = new TextureRegion[1];
			sheet.front[PersonSheet.SHEET_HEAD][0] = pr(personsTexture, px(10), py(index)); 
			sheet.side_idle[PersonSheet.SHEET_HEAD] = new TextureRegion[2];			
			sheet.side_idle[PersonSheet.SHEET_HEAD][0] = pr(personsTexture, px(11), py(index)); 
			sheet.side_idle[PersonSheet.SHEET_HEAD][1] = pr(personsTexture, px(12), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_HEAD] = new TextureRegion[4];			
			sheet.side_walk[PersonSheet.SHEET_HEAD][0] = pr(personsTexture, px(13), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_HEAD][1] = pr(personsTexture, px(11), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_HEAD][2] = pr(personsTexture, px(14), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_HEAD][3] = pr(personsTexture, px(12), py(index)); 
			
			sheet.front[PersonSheet.SHEET_HAIR] = new TextureRegion[1];
			sheet.front[PersonSheet.SHEET_HAIR][0]  = pr(personsTexture, px(15), py(index)); 
			sheet.side_idle[PersonSheet.SHEET_HAIR] = new TextureRegion[2];			
			sheet.side_idle[PersonSheet.SHEET_HAIR][0] = pr(personsTexture, px(16), py(index)); 
			sheet.side_idle[PersonSheet.SHEET_HAIR][1] = pr(personsTexture, px(17), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_HAIR] = new TextureRegion[4];			
			sheet.side_walk[PersonSheet.SHEET_HAIR][0] = pr(personsTexture, px(18), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_HAIR][1] = pr(personsTexture, px(16), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_HAIR][2] = pr(personsTexture, px(19), py(index)); 
			sheet.side_walk[PersonSheet.SHEET_HAIR][3] = pr(personsTexture, px(17), py(index)); 
			
			
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
