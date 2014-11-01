import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @(#) HighScoreList.java
 */

public class HighScoreList
{
	private Player player;
	
	private Game game;
	
	public String latestScoresList;
	

	public HighScoreList() {
		getScoreList();
	}

	public String getScoreList() {
		try {
			File file = new File("M:\\highscorelist.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			System.out.println("Contents of file:");
			System.out.println(stringBuffer.toString());
			latestScoresList = stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public String saveToScoreList(String playerScore){
		try {
			File file = new File("M:\\highscorelist.txt");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(playerScore);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void viewScoresList() {
		
		
	}
	
	
}
