/*
 Ονοματεπώνυμο:Γιώργος Βοζίκης
 ΑΕΜ: 9838
 Τηλέφωνο:6951305685
 EMAIL: vozikisg@ece.auth.gr
 
  Ονοματεπώνυμο:Ευγενία Δούμου
 ΑΕΜ: 10178
 Τηλέφωνο:6982285582
 EMAIL: evgedoum@ece.auth.gr
 
 Ονοματεπώνυμο:Ελένη Σιμιτσάκη
 ΑΕΜ: 10211
 Τηλέφωνο:6989456675
 EMAIL: simielen@ece.auth.gr
 
 
 */
//This class represents the whole game
public class Game {
	private int round; //This variables shows the round of the game
	
//Constructors of the class
    public Game() {
		round = 0;
	}
	public Game(int round) {
		this.round = round;
	}
	
//Setter & Getter for the variable
	public void setRound(int r) {
		round = r;
	}
	public int getRound() {
		return round;
	}
	
	
/* This function control the whole game. 
* Creates a table with dimensions 15*15,
* Input 4 supplies at the table
* Defines 2 players (Player1: Theseus Player2:Minotaur)
* Players play alternately (they roll the dice and they move on the table) 
* until someone wins or passed 2n rolls (n=100).
* For each round we print analytically the round, the table of the game and each player's move.
* At the end of the game the will be printed
*/
	
	public static void main(String[] args) {
		
		int N = 15;                   //We set the dimension equals to 15
		int S = 4;                    //We create 4 supplies
		int W = (3 * N * N + 1)/2;    //The total number of walls
		int n = 100;                  //The game ends after 2n (n=100 because for each round we have 2 moves one for Theseus and one for Minotaur)
		int i = 0;                    //We use this variable in order to count the rounds
    	boolean b = true;
    	
	    Game game = new Game();
	    Board boardGame = new Board (N,S,W);
	    boardGame.createBoard();
	    Player player1 = new Player(1, "Theseus", boardGame, 0, 0, 0);                                      //when the game starts Theseus is at position (0,0) with playerid 1
	    Player player2 = new Player(2, "Minotauros", boardGame, 0, boardGame.getN()/2, boardGame.getN()/2);  //when the game starts Minotaur is at position (N/2,N/2) with playerid 2
    
    
      
    	System.out.println("Start:");
    	boardGame.getStringRepresentation (player1.getX()*boardGame.getN() + player1.getY() ,player2.getX()*boardGame.getN() + player2.getY()); //The first illustration of the table
    
    	
        do{
        	i++;             
        	game.setRound(i); 
        	
    	System.out.println(); 
    	System.out.println();
    	System.out.println("Round: " + game.getRound());                //Here we print the current round
    	System.out.println("-----------");
    	
    	player1.move(player1.getX()*boardGame.getN() + player1.getY());  // player1.getX()*boardGame.getN() + player1.getY() that corresponds to the current player id
    	
    	
    	if(player1.getScore() == boardGame.getS()) {                    //the scores increases when the Theseus collects a supply so when score=number_of_supplies he wins
	    		System.out.println(" Theseus won the game! ");
	    		b = false;
	    	    break;	
	    	}
    	 
    	//playerNUM.getX()*boardGame.getN() + player_.getY()) returns the id of the playerNUM
    	 else if ((player1.getX()*boardGame.getN() + player1.getY()) == (player2.getX()*boardGame.getN() + player2.getY())) {
	    		System.out.println(" Minotaur won the game! ");
	    		b = false;
    	       break;	
    	    }
    	
	    
    	player2.move(player2.getX()*boardGame.getN() + player2.getY());
    		 
    		 if((player1.getX()*boardGame.getN() + player1.getY()) == (player2.getX()*boardGame.getN() + player2.getY())) {
	    		System.out.println(" Minotaur won the game ! ");
	    		b = false;
    		   break; }
    		 
	
    	 if(i==n) {                                //After 2n moves the game ends  with draw 
    		System.out.println(" Nobody wins! ");
    		b = false; }
    	
    	boardGame.getStringRepresentation (player1.getX()*boardGame.getN() + player1.getY() ,player2.getX()*boardGame.getN() + player2.getY());  //final board
      
    	
        } 
        
       while(b);
	       
        System.out.println();
     
    	
    	
    	 
    	
    	}
    	
	}
	

