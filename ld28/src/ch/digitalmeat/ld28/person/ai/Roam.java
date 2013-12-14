package ch.digitalmeat.ld28.person.ai;

import java.util.Random;

import ch.digitalmeat.ld28.ConcertSmugglers;
import ch.digitalmeat.ld28.person.Person;
import ch.digitalmeat.ld28.person.Person.LookingDirection;
import ch.digitalmeat.ld28.person.Person.PersonState;

public class Roam extends Node {
	private Random random;

	public Roam(){
		this.random = ConcertSmugglers.instance.random;
	}
	
	@Override
	public boolean onExecute(Person person){
		int pick = random.nextInt(4);
		LookingDirection dir = LookingDirection.None;
		if(pick == 1){
			dir = LookingDirection.Left;
		}
		else if(pick == 2){
			dir = LookingDirection.Right;
		}
		person.setDirection(dir);
		if(dir == LookingDirection.None){
			person.setState(PersonState.Idle);
		}
		else{
			person.setState(random.nextBoolean() ? PersonState.Idle : PersonState.Walking);
		}
		person.actionTimer = 0f;
		person.actionTime = random.nextFloat() * 1 + 0.3f;
		person.actionRunning = true;
		return true;
	}
}
