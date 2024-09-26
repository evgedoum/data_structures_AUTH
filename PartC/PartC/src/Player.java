//This class represents the players of the game
public class Player {
	private int playerId; //The player Id
	private String name;  //The name of each player
	private Board board;  //Board of the game 
	private int score;    //Each player's score
	private int x, y;     //Each player's coordinates 
	
//The constructors of the class
    public Player() {
		playerId = 0;
		name = null;
		board = new Board();
		score = 0;
		x = 0;
		y = 0;
	}

	public Player(int playerId, String name, Board board, int x, int y, int score) {
		this.playerId = playerId;
		this.name = name;
		this.board = board;
		this.score = score;
		this.x = x;
		this.y = y;
	}

	public Player(Player  p) {
		playerId = p.playerId;
		name = p.name;
		board = p.board;
		score = p.score;
		x = p.x;
		y = p.y;
	}
	
//Setters & Getters of the variables
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Board getBoard() {
		return board;
	}
	public void setScore(int score) {
		this.score = score;
	} 

	public int getScore() {
		return score;
	} 

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

//RANDOMDICE FUNCTION: This function returns a random move
	public int randomDice() {
		int dice[] = {1,3,5,7};                     // Initialization an array with all possible moves: Up corresponds to 1, down to 5, right to 3 and left to 7
		int randDice = (int)((Math.random()*4));    //We create random numbers between 0 to 3
		
		return dice[randDice];                      //we return the value of the element with index randDice
	}
	
	
	
//This function controls each player's move
	public int[] move(int id, int dice) {
		int[] array = new int[4];                  //Here we will save the coordinates, the id of the player and the id of the collected supply
		
		for(int i=0;i<4;i++) 
			array[i] = 0;                          //Initialization of the array
		
		
	      switch(dice) {
			
		   case 1:
					
			     if(board.tiles[id].getUp() && name == "Minotaur")  {      //checks whether Minotaur can't move up (we have already check this for Theseus)
					System.out.println("Minotaur can't move up.");
					   break;
					}
				 
			     else {
					x++;                                                 //εικόνα 3 for coordinates
					id = x * board.getN() + y ;                          //εικόνα 4 for ids
					array[0] = x;
					array[1] = y;
					array[2] = id;
					
					System.out.println( name + " move up.");
					System.out.println("His new position is (" + array[0]+ "," + array[1] + (")"));
					
	              //Checks whether Theseus can collect a supply
					for(int j=0 ; j<board.getS() ; j++) {
						if(board.supplies[j].getSupplyTileId()==id && name == "Theseus") {
							score++;                                       //if he collects the supply, the score increases
							System.out.println("Theseus got a supply.");
							array[3] = board.supplies[j].getSupplyTileId();  
							
							break;
						}
					}
				  }
				     return array;
					
			case 3:
				
				if(board.tiles[id].getRight() && name == "Minotaur") {
					System.out.println("Minotaur can't move right.");
					break;
				}
				
				else  {
					y++;
					id = x * board.getN() + y;
					array[0] = x;
					array[1] = y;
					array[2] = id;
					
					System.out.println( name + " move right.");
					System.out.println("His new position is (" + array[0]+ "," + array[1] + (")"));
					
					for(int j=0 ; j<board.getS() ; j++) {
					 	if(board.supplies[j].getSupplyTileId()==id && name == "Theseus") {
					 		score++;
					 		System.out.println("Theseus got a supply.");
					 		array[3] = board.supplies[j].getSupplyTileId();
					 		
					 		break;
					 	}
					} 
				}
				return array;
				 
			
			case 5:
				if(board.tiles[id].getDown() && name == "Minotaur") {
					System.out.println("Minotaur can't move down.");
				}
				
				else {
					x--;
					id = x * board.getN() + y;
					array[0] = x;
					array[1] = y;
					array[2] = id;
					
					System.out.println( name + " move down.");
					System.out.println("His new position is (" + array[0]+ "," + array[1] + (")"));
					
					for(int j=0 ; j<board.getS() ; j++) {
						if(board.supplies[j].getSupplyTileId()==id && name == "Theseus") {
							score++;
							System.out.println("Theseus got a supply.");
							array[3] = board.supplies[j].getSupplyTileId();
							
							break;
						}
					}
			    } 
				  return array;
			
			case 7:
				if(board.tiles[id].getLeft() && name == "Minotaur") {
					System.out.println("Minotaur can't move left.");
				}
				
				else  {
					y--;
					id = x * board.getN() + y;
					array[0] = x;
					array[1] = y;
					array[2] = id;
					
					System.out.println( name + " move left.");
					System.out.println("His new position is (" + array[0]+ "," + array[1] + (")"));
					
					for(int j=0 ; j<board.getS() ; j++) {
						if(board.supplies[j].getSupplyTileId()==id && name == "Theseus") {
							score++;
							System.out.println("Theseus got a supply.");
							array[3] = board.supplies[j].getSupplyTileId();
							
							break;
						}
					}
				 }
				  return array;
				
			}
			
		
		return array;
    }
}	