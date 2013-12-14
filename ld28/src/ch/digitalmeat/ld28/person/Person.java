package ch.digitalmeat.ld28.person;

import ch.digitalmeat.ld28.ConcertSmugglers;
import ch.digitalmeat.ld28.TextManager;
import ch.digitalmeat.ld28.person.PersonConfig.PersonType;
import ch.digitalmeat.ld28.person.ai.GuardData;
import ch.digitalmeat.ld28.person.ai.Node;

import com.badlogic.gdx.graphics.Color;
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
	private boolean saidSomething;
	private ParticleEffect effect;
	private PersonSheet sheet;
	private PersonConfig config;
	private PersonState state;
	private LookingDirection dir;
	private float animationTimer = 0f;
	private int animationIndex;
	private static float animationStepLength = 0.2f;
	public float aiUpdateTimer;
	public float aiUpdateTime = 0.1f;
	public boolean running;
	private Node node;
	public float actionTimer;
	public float actionTime;
	public boolean actionRunning = false;
	public GuardData guardData = new GuardData();
	public Person() {
		state = PersonState.Idle;
		dir = LookingDirection.Left;
		animationIndex = 0;
	}

	public void init(PersonSheet sheet, PersonConfig config, Node node) {
		this.sheet = sheet;
		this.config = config;
		this.node = node;
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
		if(!saidSomething){
			if(config.type == PersonType.Player){
				say("Naaay", Color.WHITE);
			}
			saidSomething = true;
		}
		if (effect != null) {
			effect.setPosition(getX() + 8, getY() + 4); 
			effect.update(delta); 
			effect.start();
		}
		aiUpdateTimer += delta;
		actionTimer += delta;
		node.execute(this);
		animationTimer += delta;
		while (animationTimer > animationStepLength) {
			animationTimer -= animationStepLength;
			animationIndex++;
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		TextureRegion[][] parts = getRelevantParts();
		if (effect != null) {
			effect.draw(batch);
		}
		float x = getX();
		float w = getWidth();
		if (dir == LookingDirection.Left) {
			x += w;
			w *= -1;
		}
		batch.setColor(config.secondaryColor);
		batch.draw(getRelevantTextureRegion(parts, PersonSheet.SHEET_LEGS), x, getY(), w, getHeight());
		batch.setColor(config.mainColor);
		batch.draw(getRelevantTextureRegion(parts, PersonSheet.SHEET_TORSO), x, getY(), w, getHeight());
		batch.setColor(config.faceColor);
		batch.draw(getRelevantTextureRegion(parts, PersonSheet.SHEET_HEAD), x, getY(), w, getHeight());
		batch.setColor(config.hairColor);
		batch.draw(getRelevantTextureRegion(parts, PersonSheet.SHEET_HAIR), x, getY(), w, getHeight());
	}

	private TextureRegion getRelevantTextureRegion(TextureRegion[][] parts,
			int partIndex) {
		return parts[partIndex][animationIndex % parts[partIndex].length];
	}

	private TextureRegion[][] getRelevantParts() {
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
	
	public void say(String text, Color color){
		TextManager manager = ConcertSmugglers.instance.textManager;
		manager.spawnText(text, color, getX() + getWidth() / 2, getY() + getHeight());
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
