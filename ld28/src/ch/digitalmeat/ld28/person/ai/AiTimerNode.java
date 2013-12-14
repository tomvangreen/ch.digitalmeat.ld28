package ch.digitalmeat.ld28.person.ai;

import ch.digitalmeat.ld28.person.Person;

public class AiTimerNode extends Node {
	@Override
	public boolean onExecute(Person person) {
		if (person.aiUpdateTimer > person.aiUpdateTime) {
			System.out.println("Update AI");
			person.aiUpdateTimer -= person.aiUpdateTime;
			return true;
		}
		return false;
	}
}
