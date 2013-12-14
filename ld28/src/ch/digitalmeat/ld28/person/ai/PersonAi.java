package ch.digitalmeat.ld28.person.ai;

public class PersonAi {
	public static Node guestAi;
	public static Node guardAi;
	
	public static void buildAi(){
		//guestAi = new AiTimerNode();
		guestAi = new ActionChecker();
		guestAi.add(new Roam());
		
		guardAi = new ActionChecker();
		guardAi.add(new Roam());
	}
}
