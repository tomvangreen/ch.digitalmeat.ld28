package ch.digitalmeat.ld28.person.ai;

import ch.digitalmeat.ld28.person.Person;
import ch.digitalmeat.ld28.person.Person.LookingDirection;
import ch.digitalmeat.ld28.person.Person.PersonState;

public class ActionChecker extends Node {
	@Override
	public boolean onExecute(Person person){
		if(person.isTransporting){
			return false;
		}
		if(person.waypoint != null){
			float wx = person.waypoint.pos.x;
			float px = person.getX() + person.getWidth() / 2;
			LookingDirection dir = person.getDirection();
			if((dir == LookingDirection.Left && px < wx) || (dir == LookingDirection.Right &&  px > wx)){
				person.setState(PersonState.Idle);
				person.waypoint = null;
				person.actionRunning = true;
				person.actionTimer = 0;
				person.actionTime = person.roamDelay;
			}
			else{
				return false;
			}
		}
		if(person.actionRunning){
			if(person.actionTimer <= person.actionTime){
				return false;
			}
		}
		if (person.aiUpdateTimer > person.aiUpdateTime) {
			person.aiUpdateTimer = 0;
			return true;
		}
		return false;
	}
}
