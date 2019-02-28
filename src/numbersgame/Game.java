package numbersgame;

public class Game {
	Player main;
	
	public Game()
	{
		main = new Player();
	}
	
	//This will add score from the main player("its the one on this device")
	public void addScore(int in)
	{
		main.addScore(in);
	}
	/*
	 * This will get the score from the player
	 * to display in GUI, it returns an int
	 */
	public int getScore()
	{
		return main.getScore;
	}
	/*
	 * This will check if the main player, this user 
	 */
	public boolean SuperSetCheck()
	{
		return main.setCheck();
	}
}
