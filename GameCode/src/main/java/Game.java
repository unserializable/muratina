import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game
{
	private double score;
	
	private HighScoreList highScoreList;
	
	public Player player;
		
	public Integer day;
	
	public void start( )
	{
		
	}

	public Game(Player player) {
		this.player = player;
	}
}
