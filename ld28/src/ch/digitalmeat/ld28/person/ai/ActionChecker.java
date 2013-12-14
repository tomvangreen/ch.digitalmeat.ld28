package ch.digitalmeat.ld28.person.ai;

import ch.digitalmeat.ld28.person.Person;

public class ActionChecker extends Node {
	@Override
	public boolean onExecute(Person person){
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
