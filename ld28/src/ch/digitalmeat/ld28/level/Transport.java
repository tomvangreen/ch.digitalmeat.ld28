package ch.digitalmeat.ld28.level;

import ch.digitalmeat.ld28.MapRenderer;
import ch.digitalmeat.ld28.person.Person;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Transport extends GameAction{

	public String key;
	public String targetKey;
	public Transport target;
	private MapRenderer map;
	public Vector2 pos;
	public Transport(String key, String targetKey, MapRenderer map, float x, float y){
		this.key = key;
		this.targetKey = targetKey;
		this.map = map;
		pos = new Vector2(x, y);
	}
	
	
	public boolean transport(final Person person) {
		if(target == null){
			target = map.transports.get(targetKey);
		}
		System.out.println("Transporting " + person.name);
		if(target != null){
			person.isTransporting = true;
			person.addAction(
				Actions.sequence(
					Actions.moveTo(target.pos.x, target.pos.y, 1f)
					, Actions.run(new Runnable() {
						@Override
						public void run() {
							person.isTransporting = false;
						}
					})
				)
			);
		}
		
		return true;
	}


	@Override
	public void execute(Person person) {
		transport(person);
	}

}
