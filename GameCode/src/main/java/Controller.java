

public class Controller {
	static Game game;
	static HighScoreList highScoreList;
	public static void main( String[] args )
	{
			//Game Starts here
			//initiate new Game
			//view Start Menu
			game = new Game();
	   		viewStartMenu();
	}

	private static void viewStartMenu( ) {
		System.out.println(
				""
				+ "Welcome, to Muratina Retaurant Game"
				+ "\nSelect: "
				+ "\n1.Start New Game"
				+ "\n2.View High Score List"
				+ "\n42.Exit Game");
				loadMenu();
	}

	private static void loadMenu( ) {
		do {	
					game.collectInput();
					switch (game.userInput) {
					  case "1":
						  	game.start();
					  case "2": 
						  	highScoreList = new HighScoreList();
//						  	highScoreList.saveToScoreList(player.name + " : " + restaurant.name + " : " + restaurant.availableBudget);
					        highScoreList.getScoreList();
					        System.out.println("++++++++++++++++++++++++");
					        viewStartMenu();
					  case "42":
						     System.out.println("Bye Bye");
						     game.gameOver();
					         System.exit(0);// they are executed if variable ==  any of the above c's
					  default:
				}
		}
		while (true);
	}
	
	
}
