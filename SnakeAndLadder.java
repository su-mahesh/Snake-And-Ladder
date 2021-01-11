class Player
{
	//variable
	private int playerPosition = 0;
	
	//functions
	void setPosition(int position)
	{
	playerPosition = position;
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
	
	static void initialiseGame(Player p)
	{
	p.setPosition(start_position);
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
	//Main funtion
	public static void main(String[] args)
	{
	
	Player player[] = new Player[1];	
	player[0] = new Player();
	play_Option nextMove = play_Option.NoPlay;	
	
	//computation
	initialiseGame(player[0]);
	
	diceOutcome = rollDice();
	
	nextMove = checkPlayOption();
	
	player[0].setPosition(getMoves( nextMove,	diceOutcome ));

	System.out.println("Dice rolled, Outcome: " + diceOutcome);
	System.out.println("play option: " + nextMove);	
 	System.out.println("Player Position: "+ player[0].getPosition() );

	
	}

}