package ch.digitalmeat.ld28.person;

import ch.digitalmeat.ld28.person.Person.LookingDirection;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Person extends Actor {
	public enum PersonState {
		Idle, Walking
	}

	public enum LookingDirection {
		None, Left, Right
	}

	private ParticleEffect effect;
	private PersonSheet sheet;
	private PersonConfig config;
	private PersonState state;
	private LookingDirection dir;
	private float animationTimer = 0f;
	private int animationIndex;
	private static float animationStepLength = 0.2f;
	public boolean running;

	public Person() {
		state = PersonState.Idle;
		dir = LookingDirection.Left;
		animationIndex = 0;
	}

	public void init(PersonSheet sheet, PersonConfig config) {
		this.sheet = sheet;
		this.config = config;
	}

	private void clearAnimation() {
		animationIndex = 0;
		animationTimer = 0f;
	}

	public void setDirection(LookingDirection dir) {
		if (this.dir == dir) {
			return;
		}
		this.dir = dir;
		clearAnimation();
	}

	public void setState(PersonState state) {
		if (this.state == state) {
			return;
		}
		this.state = state;
		clearAnimation();
	}

	@Override
	public void act(float delta) {
		if (effect != null) {
			effect.setPosition(getX() + 8, getY() + 4); 
			effect.update(delta); 
			effect.start();
		}
		animationTimer += delta;
		while (animationTimer > animationStepLength) {
			animationTimer -= animationStepLength;
			animationIndex++;
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		TextureRegion[] regions = getRelevantRegions();
		TextureRegion region = regions[animationIndex % regions.length];
		if (effect != null) {
			effect.draw(batch);
		}
		batch.setColor(config.mainColor);
		if (dir == LookingDirection.Right || dir == LookingDirection.None) {
			batch.draw(region, getX(), getY(), getWidth(), getHeight());
		} else {
			batch.draw(region, getX() + getWidth(), getY(), -getWidth(),
					getHeight());
		}
	}

	private TextureRegion[] getRelevantRegions() {
		if (state == PersonState.Walking) {
			return sheet.side_walk;
		} else if (state == PersonState.Idle) {
			if (dir == LookingDirection.None) {
				return sheet.front;
			} else {
				return sheet.side_idle;
			}
		}
		return sheet.front;
	}

	public PersonState getState() {
		return state;
	}

	public LookingDirection getDirection() {
		return dir;
	}

	public ParticleEffect getEffect() {
		return effect;
	}

	public void setEffect(ParticleEffect effect) {
		this.effect = effect;
	}
}
