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
	
	
/* This function controls the whole game. 
* Creates a table with dimensions 15*15,
* Input 4 supplies at the table
* Defines 2 players (Player1: Theseus Player2:Minotaur)
* Players play alternately (they roll the dice and they move on the table) 
* until someone wins or passed 2n rolls (n=100).
* For each round we print analytically the round, the table of the game and each player's move.
* At the end of the game they will be printed
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
	    MinMaxPlayer player1 = new MinMaxPlayer(1, "Theseus", boardGame, 0, 0, 0);                   //when the game starts Theseus is at position (0,0) with playerid 1
	    Player player2 = new Player(2, "Minotaur", boardGame, boardGame.getN()/2, boardGame.getN()/2, 0);  //when the game starts Minotaur is at position (N/2,N/2) with playerid 2
    
    
      
    	System.out.println("Start:");
    	boardGame.getStringRepresentation (player1.getX()*boardGame.getN() + player1.getY() , player2.getX()*boardGame.getN() + player2.getY()); //The first illustration of the table
    
    	
        do{
        	i++;             
        	game.setRound(i); 
        	
    	System.out.println(); 
    	System.out.println();
    	System.out.println("Round: " + game.getRound());                //Here we print the current round
    	System.out.println("-----------");
    	
    	
    	//we move Minotaur with a random way
    	player2.move(player2.getX()*boardGame.getN() + player2.getY(),  player2.randomDice());// player2.getX()*boardGame.getN() + player2.getY() that corresponds to the current player id
    		 
    	//we check if Minotaur and Theseus are in the same tile (Minotaur is the winner)
    		 if((player1.getX()*boardGame.getN() + player1.getY()) == (player2.getX()*boardGame.getN() + player2.getY())) {
    			System.out.println();
     		 	System.out.println("-----------------------");	
     		 	System.out.println(" Minotaur won the game! ");
 	    		System.out.println();

 	    		b = false;
    		    break; }
    		 
    	//Theseus is moving with the best movement for his current position
    	player1.getNextMove(player1.getX()*boardGame.getN() + player1.getY(), player2.getX()*boardGame.getN() + player2.getY());// player1.getX()*boardGame.getN() + player1.getY() that corresponds to the current player id
   
    	
    	//we check whether Theseus got all the supplies (Theseus is the winner)
    	if(player1.getScore() == boardGame.getS()) {                    //the scores increases when the Theseus collects a supply so when score=number_of_supplies he wins
    		    System.out.println();
    		    System.out.println("-----------------------");
    		    System.out.println("Theseus won the game! "); 
    			System.out.println();
	
	    		b = false;
	    	    break;	
	    	}
    	
    	//we check if Minotaur and Theseus are in the same tile (Minotaur is the winner)
      	 else if ((player1.getX()*boardGame.getN() + player1.getY()) == (player2.getX()*boardGame.getN() + player2.getY())) {
    		 	System.out.println();
    		 	System.out.println("-----------------------");	
    		 	System.out.println(" Minotaur won the game! ");
	    		System.out.println();

	    		b = false;
                break;	
    	    }
    	
	  //After 2n moves the game ends  with draw 
    	 if(i==n) {                                
    	    System.out.println();
  		 	System.out.println("-----------------------");	
    		System.out.println(" Nobody wins! ");
    		System.out.println();	
    		
    		b = false; }
    	
            boardGame.getStringRepresentation (player1.getX()*boardGame.getN() + player1.getY() ,player2.getX()*boardGame.getN() + player2.getY());  // board in each round

    	
        } while(b);
       
        if(i!=100)
    	   boardGame.getStringRepresentation (player1.getX()*boardGame.getN() + player1.getY() ,player2.getX()*boardGame.getN() + player2.getY());  //final board when the game ends before 100 rounds
        
        System.out.println();
        System.out.println();
        System.out.println("---------------------");	
        System.out.println("        FINAL");	
        System.out.println("---------------------");	
        boardGame.getStringRepresentation (player1.getX()*boardGame.getN() + player1.getY() ,player2.getX()*boardGame.getN() + player2.getY());  //FINAL BOARD OF THE WHOLE GAME
        System.out.println();
     
        
        player1.statistics(i);
    	
    	 
    	
    	}
    	
	}
	
