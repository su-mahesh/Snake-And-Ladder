class Player
{
	//variable
	private int playerPosition = 0;
	private int diceRollCount = 0;
	
	//functions
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
	static int diceOutcome = 0;
	
	Player p;
	
	//constanst
	static final int start_position = 0;
	static final int winning_position = 100;
		
	static enum play_Option{
	NoPlay,
	Ladder,
   Snake
	}
	
	static play_Option checkPlayOption() {

	return play_Option.values()[(int) (Math.random() * 10) %3];


	}

	static int rollDice()
	{
	return (int) (Math.random() * 10) %6 + 1;
	
	}
	
	static void initialiseGame(	Player p	)
	{
	p.setPosition(	start_position	);
	}
	static int getMoves(play_Option nextMove, int diceOutcome){
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
	
	static int toss()
	{
		return (int) (Math.random() * 10) %2;
	}
	
	//Main funtion
	public static void main(String[] args)
	{
	
	Player player[] = new Player[2];	
	player[0] = new Player();
	player[1] = new Player();
	
	play_Option nextMove = play_Option.NoPlay;	
	int playerPreviousPosition = 0;
	int currentPlayer = 0;
	int idlePlayer = 1;
	
	//computation
	initialiseGame(player[0]);
	initialiseGame(player[1]);
	
	if(	toss()	==	0 )
	{
	currentPlayer = 1;
	idlePlayer = 0;
	}	
	
while(	player[0].getPosition() != winning_position && player[1].getPosition() != winning_position	)
{	
	diceOutcome = rollDice();
	player[	currentPlayer	].countDiceRoll();
	nextMove = checkPlayOption();
	
	playerPreviousPosition = player[currentPlayer].getPosition();
	
	player[	currentPlayer	].setPosition(getMoves( nextMove,	diceOutcome ));
	System.out.println("Player "+(currentPlayer	+	1)+" playing");
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

	System.out.println("Player "+(currentPlayer	+	1)+" won");
	
	

	
	}

}
