package ch.digitalmeat.ld28.person;

import ch.digitalmeat.ld28.PersonConfig;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Person extends Actor {
	private PersonSheet sheet;
	private PersonConfig config;

	public Person(){
	}
	
	public void init(PersonSheet sheet, PersonConfig config){
		this.sheet = sheet;
		this.config = config;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		batch.setColor(config.mainColor);
		batch.draw(sheet.front[0], getX(), getY(), getWidth(), getHeight());
	}
}
