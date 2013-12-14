package ch.digitalmeat.ld28.person;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Person extends Actor {
	private PersonSheet sheet;


	public Person(){
	}
	
	public void init(PersonSheet sheet){
		this.sheet = sheet;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		batch.draw(sheet.front[0], getX(), getY(), getWidth(), getHeight());
	}
}
