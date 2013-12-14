package ch.digitalmeat.ld28;

import ch.digitalmeat.ld28.person.Person;
import ch.digitalmeat.ld28.person.Person.LookingDirection;
import ch.digitalmeat.ld28.person.Person.PersonState;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;


public class PersonManager {
	public void handleMovement(float delta, Array<Actor> actors, MapLayer layer)
	{
		for(Actor actor : actors){
			if(actor instanceof Person){
				handlePerson(delta, layer, (Person)actor);
			}
		}
	}

	private void handlePerson(float delta, MapLayer layer, Person p) {
		if(p.getState() == PersonState.Walking){
			System.out.println("Update walk");
			float x = p.getX();
			float moveBy = delta * (p.running ? 128f : 64f);
			if(p.getDirection() == LookingDirection.Left){
				moveBy *= -1;
			}
			p.setX(x + moveBy);
		}
	}
}
