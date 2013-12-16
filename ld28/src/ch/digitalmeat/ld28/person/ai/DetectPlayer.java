package ch.digitalmeat.ld28.person.ai;

import java.util.List;

import ch.digitalmeat.ld28.ConcertSmugglers;
import ch.digitalmeat.ld28.MapRenderer;
import ch.digitalmeat.ld28.person.Person;
import ch.digitalmeat.ld28.person.Person.LookingDirection;

public class DetectPlayer extends Node {
	@Override 
	public boolean onExecute(Person source){
		MapRenderer map = ConcertSmugglers.instance.mapRenderer;
		List<Person> persons = map.players();
		for(Person target : persons){
			if(isInSight(source, target) && isNearEnough(source, target)){

				
				return false;
			}
		}
		return true;
	}

	private boolean isNearEnough(Person source, Person target) {
		float vd = Math.abs(source.getY() - target.getY());
		if(vd > 8){
			return false;
		}
		float hd = Math.abs(source.getX() - target.getX());
		return hd < 20;
	}

	private boolean isInSight(Person source, Person target) {
		boolean isLeft = source.getX() > target.getX();
		LookingDirection dir = source.getDirection();
		return (isLeft && dir == LookingDirection.Left) || (!isLeft && dir == LookingDirection.Right);
	}
}
