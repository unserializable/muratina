import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @(#) HighScoreList.java
 */

public class HighScoreList
{
	private Player player;
	
	private Game game;
	
	public String latestScoresList;
	
	public String getScoreList() {
		try {
			File file = new File("./../highscorelist.txt");
			if(file.exists()== false) {
				file.createNewFile();
			}
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			System.out.println(stringBuffer.toString());
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public String saveToScoreList(String playerScore){
		try {
			File file = new File("./../highscorelist.txt");
			if(file.exists()== false) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file,true);
			fileWriter.append("\n"+playerScore);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void viewScoresList() {
		
		
	}
	
	
}
