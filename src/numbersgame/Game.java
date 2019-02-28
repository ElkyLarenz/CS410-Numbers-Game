package numbersgame;

public class Game {
	Player[] players = new Player[4];
	int localPlayPos;
	int round;
	
	public Game()
	{
		round = 1;
	}
	
	//This will add score from the main player("its the one on this device")
	public void addScore(int in)
	{
		players[localPlayPos].addScore(in);
	}
	/*
	 * This will get the score from the player
	 * to display in GUI, it returns an int
	 */
	public int getScore()
	{
		return players[localPlayPos].getScore;
	}
	/*
	 * This will check if the main player, this user 
	 */
	public boolean SuperSetCheck()
	{
		return players[localPlayPos].setCheck();
	}
	/*
	 * sets the players from lobby
	 */
	public void setPlayers(Player[] in)
	{
		players = in;
	}
	
	public void changeRound()
	{
		round++;
	}
	
	public int checkRound()
	{
		return round;
	}
}
