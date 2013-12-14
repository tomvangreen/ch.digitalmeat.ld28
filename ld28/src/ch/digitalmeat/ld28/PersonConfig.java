package ch.digitalmeat.ld28;

import com.badlogic.gdx.graphics.Color;

public class PersonConfig {
	public enum PersonType{
		Player
		, Guard
		, Guest
	}
	
	
	public static float rtf(int value){
		return 1f / 255 * value;
	}
	
	
	public static Color col(int r, int g, int b){
		return col(r, g, b, 255);
	}
	public static Color col(int r, int g, int b, int a){
		return new Color(rtf(r), rtf(g), rtf(b), rtf(a));
	}
	
	public final static Color[] MAIN_COLORS = new Color[]{
		col(255, 0, 255)
		, col(0, 255, 255)
		, col(255, 255, 0)
	};
	
	
	
	public static Color[] SECONDARY_COLORS = new Color[]{
		col(255, 0, 0)
		, col(0, 0, 255)
		, col(128, 0, 255)
		, col(0, 255, 0)
	};
	
	public static Color[] HAIR_COLORS = new Color[]{
		col(0, 0, 0)
	};
	public static Color[] FACE_COLORS = new Color[]{
		col(255, 255, 255)
	};
	
	public PersonType type;
	public boolean hasTicket;
	
	public Color mainColor;
	public Color secondaryColor;
	public Color hairColor;
	public Color faceColor;
	private static int spawnIndex = 0;
	public PersonConfig(PersonType type, boolean hasTicket, Color main, Color secondary, Color hair, Color face){
		this.type = type;
		this.hasTicket = hasTicket;
		this.mainColor = main;
		this.secondaryColor = secondary;
		this.faceColor = face;
		this.hairColor = hair;
	}
	
	public final static PersonConfig PLAYER_WITH_TICKET = new PersonConfig(
			PersonType.Player
			, true
			, Color.YELLOW
			, Color.BLUE
			, Color.RED
			, Color.WHITE
		);
		
	public final static PersonConfig PLAYER_WITHOUT_TICKET = new PersonConfig(
		PersonType.Player
		, true
		, Color.GRAY
		, Color.BLUE
		, Color.RED
		, Color.WHITE
	);
		
	public final static PersonConfig NORMAL_GUARD = new PersonConfig(
		PersonType.Guard
		, true
		, Color.BLUE
		, Color.BLUE
		, Color.BLACK
		, Color.WHITE
	);
	
	public static Color col(Color[] colors) {
		return colors[spawnIndex++ % colors.length];
	}
	
	public static PersonConfig Guest(){
		return new PersonConfig(
			PersonType.Guest
			, true
			, col(MAIN_COLORS)
			, col(SECONDARY_COLORS)
			, col(HAIR_COLORS)
			, col(FACE_COLORS)
		);
	}
}
