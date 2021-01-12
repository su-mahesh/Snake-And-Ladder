class Player
{
	//variable
	private int playerPosition = 0;
	private int diceRollCount = 0;
	private String playerName = null; 
	
	//functions
	String getPlayerName()
	{
	return playerName;
	}

	Player(String playerName)
	{
	this.playerName = playerName;
	}

	void setPosition(int position)
	{	
	
	if(	playerPosition	+ position <= SnakeAndLadder.winning_position	)
			playerPosition += position;
			
	if (	playerPosition < 0	)
	playerPosition = 0;
	}
	int getDiceRollCount()
	{
	return diceRollCount;
	}
	void countDiceRoll()
	{
	diceRollCount++;
	}
	
	int getPosition()
	{
	return	playerPosition;
	}		
}

class SnakeAndLadder
{
	//variable
	int diceOutcome = 0;
	static int winning_player = 0;
	
	//constanst
	final int start_position = 0;
	static final int winning_position = 100;
		
	enum play_Option{
	NoPlay,
	Ladder,
   Snake
	}
	
	play_Option checkPlayOption() {
	return play_Option.values()[(int) (Math.random() * 10) %3];
	}

	int rollDice()
	{
	return (int) (Math.random() * 10) %6 + 1;
	}
	
	int getMoves(play_Option nextMove, int diceOutcome){
	switch(nextMove)
	{
		case Ladder:	
		return diceOutcome;
					
		case Snake:		
		return -diceOutcome;
					
		default: 
		return 0;	
	}
	}
	
	int toss()
	{
		return (int) (Math.random() * 10) %2;
	}
	
	void playGame(Player player[])
	{
	player[0].setPosition(start_position);
	player[1].setPosition(start_position);
	
	play_Option nextMove = play_Option.NoPlay;	
	int playerPreviousPosition = 0;
	int currentPlayer = 0;
	int idlePlayer = 1;
	
	if( toss()	==	0 )
	{
	currentPlayer = 1;
	idlePlayer = 0;
	}	
	
	System.out.println(player[currentPlayer].getPlayerName()+ " won the toss \n");
	
	while(	player[0].getPosition() != winning_position && player[1].getPosition() != winning_position	)
	{
	diceOutcome = rollDice();
	player[	currentPlayer	].countDiceRoll();
	nextMove = checkPlayOption();
	
	playerPreviousPosition = player[currentPlayer].getPosition();
	
	player[	currentPlayer	].setPosition(getMoves( nextMove, diceOutcome ));
	System.out.println("Player '"+player[currentPlayer].getPlayerName()+"' is playing");
	System.out.println("Dice roll No: " +	player[currentPlayer].getDiceRollCount()	+ " Outcome: " + diceOutcome);
	System.out.println("play option: " + nextMove);
	System.out.println("Player Position: "+ playerPreviousPosition+" >> "+player[currentPlayer].getPosition() + "\n");

	if(nextMove	!=	play_Option.Ladder)
	{
	int temp = idlePlayer;
	idlePlayer = currentPlayer;
	currentPlayer = temp;
	}
	}
	winning_player = currentPlayer; 
	}
	
	//Main funtion
	public static void main(String[] args)
	{
	Player player[] = new Player[2];	
	player[0] = new Player("Jack");
	player[1] = new Player("Jill");
	
	SnakeAndLadder	SnakeAndLadderGame = new SnakeAndLadder();

	//computation	
	SnakeAndLadderGame.playGame(player);
		
	System.out.println("Player "+	player[winning_player].getPlayerName()	+" won");
	
	}

}
