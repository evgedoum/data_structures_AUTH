import java.util.ArrayList;
import java.util.Random;

public class MinMaxPlayer extends Player{
	ArrayList<Integer[]> path;
	public MinMaxPlayer() {
		super();
		path=new ArrayList<Integer[]>();
	}
	public MinMaxPlayer(int id, String n, Board b, int s, int px, int py) {
		super(id, n, b, s, px, py);
		path=new ArrayList<Integer[]>();
	}


/*TARGET FUNCTION: this function calculates the value of each move taking into account the distance from a supply(if the player can see it) and the distance from the Minotaur (if the player can see him)
* the returned price is the value of the transaction we evaluate each time
*/
   public double f (double nearSupplies, double opponentDist) {
	double a; 
	a = (nearSupplies * 0.46) + (opponentDist * 0.54);
	return a;
} //end of target function
		

/*EVALUATE FUNCTION: This function evaluates the player's move for the roll he has (dice), since he is in specific position (currentPos)
* opponentCurrentPos: this definition represents the position of Ìinotaur on the board when HeuristicPlayer has the position 'currentPos'
* The function returns the motion evaluation according to the function target.
*/
	public double evaluate(int currentPos, int dice, int opponentCurrentPos, Board board, int id ) {
/* nearSupply: we increase this variable by 0, 0.3, 0.5, 1 in order to check whether HeuristicPlayer is near or not from supplies. 
*(0=there aren't supplies around him, 0,3= there is a supply 3 tiles away from him, 0.5= there is a supply 2 tiles away from him, 1=there is a supply 1 tile away from him)
* opponentDist: we save the values 0, 0.3, 0.5, 1 in order to check whether HeuristicPlayer is near or not from Minotaur. 
*(0=Minotaur isn't arround him, -0,3= Minotaur is 3 tiles away from him, -0.5= Minotaur is 2 tiles away from him, -1=Minotaur is 1 tile away from him.
*/
	double nearSupplies = 0 , opponentDist = 0;
	//we use this variable to "move" the player at the board (max 3 tiles away for every possible move) in order to get the informations that will help him to choose the best move
	int temporaryPos;
	switch (dice) {
				
	case 1:
	//CHECKS FOR SUPPLIES
	if(id==1) {
	  for (int i = 0; i<board.getS(); i++) {
				  
	//we have already check at function getNextMove that at currentPos Theseus can move up (so we know every time that this move is possible)
	  temporaryPos = currentPos + board.getN();                           //temporaryPos=currentPos+N (N=board dimension), we "move" the player 1 tile up
			    
	  if(board.supplies[i].getSupplyTileId() == temporaryPos) {           //we check if there is a supply at this tile (with tileId = temporaryPos)
		nearSupplies += (double) 1;                                            //the distance between the player (at currentPos) and supply is 1 tile so we increase by 1 the variable
	    continue;
	}//end of first if
			    
	  if(board.tiles[temporaryPos].getUp() == false) {                  //we check if Theseus can move up 
	    temporaryPos += board.getN();                                     //temporaryPos=currentPos+2*N we "move" the player 1 tile up
			    			 
	  if(board.supplies[i].getSupplyTileId() == temporaryPos) {         //we check if there is a supply at this tile (with tileId = temporaryPos)
		nearSupplies += 0.5;                                                   //the distance between the player(at currentPos) and supply is 2 tiles so we increase by 2 the variable
		continue;
	}//end of third if
				    			
	  if(board.tiles[temporaryPos].getUp() == false) {                  //we check if Theseus can move up
		temporaryPos += board.getN();                                     //temporaryPos=currentPos+3*N  (max distance we can check)
			    		
	  if(board.supplies[i].getSupplyTileId() == temporaryPos)          //we check if there is a supply at this tile (with tileId = temporaryPos)
		nearSupplies += 0.3;                                                //the distance between the player(at currentPos) and supply is 3 tiles so we increase by 3 the variable
	 }//end of fourth if
   }//end of second if
 }//end of for
}//end of if
			  		    
//CHECKS FOR MINOTAUR
 //we have already check at function getNextMove that at currentPos Theseus can move up (so we know every time that this move is possible)
   temporaryPos = currentPos + board.getN();                             //temporaryPos=currentPos+N , we "move" Theseus 1 tile up
			    		
	if(opponentCurrentPos == temporaryPos)                                            //we check whether Theseus and Minotaur are in the same tile
	 opponentDist = (double) -1;                                              //the distance between Theseus(at currentPos) and Minotaur is 1 tiles so we increase by 1 the variable
			  
	else if (board.tiles[temporaryPos].getUp() == false) {                //we check if Theseus can move up
	 temporaryPos += board.getN();                                       //temporaryPos=currentPos+2*N  we "move" Theseus 1 tile up
				
	if(opponentCurrentPos == temporaryPos)                                          //we check whether Theseus and Minotaur are in the same tile
	 opponentDist = -0.5;                                                    //the distance between Theseus(at currentPos) and Minotaur is 2 tiles so we increase by 2 the variable
				
	else if (board.tiles[temporaryPos].getUp() == false) {				 //we check if Theseus can move up 
	 temporaryPos += board.getN();                                      //temporaryPos=currentPos+3*N   (max distance we can check)
				 
	if(opponentCurrentPos == temporaryPos)                                         //we check whether Theseus and Minotaur are in the same tile
	  opponentDist = -0.3;													 //the distance between Theseus(at currentPos) and Minotaur is 3 tiles so we increase by 3 the variable
	}//end of second else-if
}//end of first else-if
			    			 
   break; //end of case 1
			     

//ÔÇÅÍ WE FOLLOW THE SAME PROSEDURE FOR THE REST OF MOVENTS
	case 3:
 //CHECKS FOR SUPPLIES
 if(id==1) {
	for (int i = 0; i<board.getS(); i++) {
	 temporaryPos = currentPos + 1;                                              
	 if(board.supplies[i].getSupplyTileId() == temporaryPos) {
	  nearSupplies += (double) 1;
	  continue;
	 }//end of first if
					    
	 if(board.tiles[temporaryPos].getRight() == false) {                     
	  temporaryPos += 1;                                                          
					    			 
	 if(board.supplies[i].getSupplyTileId() == temporaryPos) {
	  nearSupplies += 0.5;
      continue;
     }//end of third if
     
	 if(board.tiles[temporaryPos].getRight() == false) {                  
	 temporaryPos += 1;                                                      
	 if(board.supplies[i].getSupplyTileId() == temporaryPos)
	  nearSupplies += 0.3;
   }//end of fourth if
 }//end of second if
}//end of for
}//end of if					    	 
					  		    
//CHECKS FOR MINOTAUR
  temporaryPos = currentPos + 1 ;                                             
					    		
  if(opponentCurrentPos == temporaryPos)
	opponentDist = (double) -1;
					  
  else if (board.tiles[temporaryPos].getRight() == false) {              
	 temporaryPos += 1;                                                         
						
  if(opponentCurrentPos == temporaryPos) 
	opponentDist = -0.5;
						
  else if (board.tiles[temporaryPos].getRight() == false) {			   
	temporaryPos += 1;                                                       
						 
  if(opponentCurrentPos == temporaryPos) 
	opponentDist = -0.3;
   }//end of second else-if
}//end of first else-if

 break; //end of case 3
				     


 case 5:
	//CHECKS FOR SUPPLIES
   if(id==1) {
	for (int i = 0; i<board.getS(); i++) {
	temporaryPos = currentPos - board.getN();                           
				    
	if(board.supplies[i].getSupplyTileId() == temporaryPos) {
	 nearSupplies += (double) 1;
	 continue;
	}//end of first if
				    
	if(board.tiles[temporaryPos].getDown() == false) {                   
	 temporaryPos -= board.getN();                                      
				    			 
	if(board.supplies[i].getSupplyTileId() == temporaryPos) {
	 nearSupplies += 0.5;
	 continue;
	}//end of third if
	
	if(board.tiles[temporaryPos].getDown() == false) {                  
	 temporaryPos -= board.getN();                                     
				    		
	if(board.supplies[i].getSupplyTileId() == temporaryPos)
	 nearSupplies += 0.3;
	}//end of fourth if
 }//end of second if
}//end of for
}//end of if				    	 
				  		    
//CHECKS FOR MINOTAUR
 temporaryPos = currentPos - board.getN();                             
 
  if(opponentCurrentPos == temporaryPos)
   opponentDist = (double) -1;
				  
  else if (board.tiles[temporaryPos].getDown() == false) {               
   temporaryPos -= board.getN();                                        
					
  if(opponentCurrentPos == temporaryPos) 
   opponentDist = -0.5;
					
  else if (board.tiles[temporaryPos].getDown() == false) {			  
   temporaryPos -= board.getN();                                      
					 
  if(opponentCurrentPos == temporaryPos) 
	opponentDist = -0.3;
  }//end of second else-if
}//end of first else-if
				    			 
 
   break; //end of case 5
				     
				 
  case 7:
						
//CHECKS FOR SUPPLIES
if(id==1) {
  for (int i = 0; i<board.getS(); i++) {
  temporaryPos = currentPos - 1;                                               
				    
  if(board.supplies[i].getSupplyTileId() == temporaryPos) {
	nearSupplies += (double) 1;
	continue;
  }//end of first if
						    
  if(board.tiles[temporaryPos].getLeft() == false) {                     
	temporaryPos -= 1;                                                          
						    			 
  if(board.supplies[i].getSupplyTileId() == temporaryPos) {
	nearSupplies += 0.5;
	continue;
  }//end of third if
							    			
  if(board.tiles[temporaryPos].getLeft() == false) {                  
    temporaryPos -= 1;                                                      
						    		
  if(board.supplies[i].getSupplyTileId() == temporaryPos)
     nearSupplies += 0.3;
   }//end of fourth if
  }//end of second if
 }//end of for
}//end of if						    	 
						  		    
//CHECKS FOR MINOTAUR
 temporaryPos = currentPos - 1 ;                                             
						    		
  if(opponentCurrentPos == temporaryPos)
	opponentDist = (double) -1;
						  
  else if (board.tiles[temporaryPos].getLeft() == false) {              
    temporaryPos -= 1;                                                        
							
  if(opponentCurrentPos == temporaryPos) 
	opponentDist = -0.5;
							
  else if (board.tiles[temporaryPos].getLeft() == false) {			   
    temporaryPos += 1;                                                      
							 
  if(opponentCurrentPos == temporaryPos) 
	opponentDist = -0.3;
  }//end of second else-if
 }//end of first else-if
						    			 
							 
 break; //end of case 7		     

}//end of switch 
			 
//Here we call the TARGET FUNCTION and it is returned the value of the move  we are considering in this case
 return f(nearSupplies, opponentDist);
			
}//end of function evaluate
	
	
/* FUNCTION STATISTICS:
* This function prints data about its movements player in each round of the game.
* and accepts as an argument the maximum number of rounds in each game.
* In this function we use the information stored in ÁrrayList.
*/
 public void statistics(int n) { //
  int up=0,down=0,right=0,left=0;        //we use these variables in order to count the times that player has moved in each position
  int score = 0;

  System.out.println();
  //With this loop we print in console the data we have collect for each round
  for(int i=0; i<n; i++) {
	
	  if(i!=n-1) { 
	//The following instructions implemented for all rounds except the final one
	int round = i+1;   //i+1 corresponds to the current round - cause i starts from zero
	System.out.println("---------------------------------------------------------------------------");	
	System.out.println("At Round " + round + ":");
	System.out.println("Theseus set the dice=" + path.get(i)[0] + ".");  
			   
//for each move we increase the the corresponding counter
	switch(path.get(i)[0]) {
		 case 1:
		  up++;
		  break;
		case 3:
		  right++;
		  break;
	    case 5:
		  down++;
	      break;
		case 7:
		  left++;
		  break;
 }//end of switch
			   
	
  if(path.get(i)[1]==1) {
   System.out.println("Theseus got a supplie.");
   score++;
  }//end of if
			   
  System.out.println("The total number of supplies that he has collect is: " + score  + ".");
			   
  if(path.get(i)[2]==0)
   System.out.println("There is no supply around him.");
  else 
   System.out.println("The distance between Theseus and the closet supply is " + path.get(i)[2] + " tiles." );
			  
  if(path.get(i)[3]==0)
   System.out.println("Minotaur isn't arround Theseus.");
  else
   System.out.println("The distance between Theseus and Minotaur is " + path.get(i)[3] + " tiles." );

}//end of first if
			  

   if(i==n-1) {
//The following instructions implemented only for the final round
  int round = i+1;
  System.out.println("At Round " + round + ":");
  System.out.println("Theseus set the dice=" + path.get(i)[0] + ".");
				
	switch(path.get(i)[0]) {
		 case 1:
		  up++;
		  break;
		 case 3:
		  right++;
		  break;
		 case 5:
		  down++;
		  break;
		 case 7:
		  left++;
		  break;
	 }//end of switch
				   
	
  if(path.get(i)[1]==1) {
    System.out.println("Theseus got a supplie.");
	score++;
}//end of if
				   
	System.out.println("The total number of supplies that he has collect is: " + score + ".");
	if(path.get(i)[2]==0)
	  System.out.println("There is no supply arround him.");
	else
	  System.out.println("The distance between Theseus and the closet supply is " + path.get(i)[2] + " tiles." );
	
	if(path.get(i)[3]==0)
	  System.out.println("Minotaur isn't arround Theseus.");
    else
	  System.out.println("The distance between Theseus and Minotaur is " + path.get(i)[3] + " tiles." );
				
	System.out.println();
	System.out.println();
	System.out.println("---------------------------------------------------------------------------");
	System.out.println("TOTAL STATISTICS:");
	System.out.println();
	System.out.println("Theseus move up (dice=1) " + up + " times");
	System.out.println("Theseus move right (dice=3) " + right + " times");
	System.out.println("Theseus move down (dice=5) " + down + " times");
	System.out.println("Theseus move left (dice=7) " + left + " times");
	}
			  
	System.out.println("---------------------------------------------------------------------------");
	System.out.println();
  }//end of for loop
 }//end of function statistics

 
 int[] getNextMove (int currentPos, int opponentCurrentPos) {
  Node root= new Node();
  root.setNodeBoard(getBoard());
  createMySubtree(root, currentPos, opponentCurrentPos, 1);
  int dice=chooseMinMaxMove(root);
  int move[];
  move=move(currentPos, dice);
  
//we update the variable currentPos according to the previous move
		switch(dice) { 
	    
    	case 1: 
    		currentPos=currentPos+ getBoard().getN(); //moving up 
		    break;
		  	    
		case 3:
			currentPos=currentPos+1; //moving right 
		    break;
	    	
		case 5: 
			currentPos=currentPos-getBoard().getN(); //moving down 
		    break;
		case 7:
			currentPos=currentPos-1; //moving left
		    break;
    }

//DISTANCE FROM THE NEAREST SUPPLY AT HIS NEW POSITION
		int supplieDistance = 0 , maxS = 0;
		
		//CHECK UP
		for(int s=0; s<getBoard().getS(); s++) {
			
			if (getBoard().tiles[currentPos].getUp() == false) {                    //we check if he can move up
				int temporaryPos = currentPos + getBoard().getN();                  //we "move" him
			   
				if(getBoard().supplies[s].getSupplyTileId() == temporaryPos) {      //we check whether at temporaryPos there is a supply
					maxS = 1;                                                       //if there is we save at maxS the value "1" that means that the distance between currentPos and a supply is 1 tile
					break;                                                          
				  }
				//if there isn't a supply 1 tile away, we check at 2 tiles away 
				if(getBoard().tiles[temporaryPos].getUp() == false){                //we check if he can move up
				  temporaryPos += getBoard().getN();                                //we "move" him
				  
				  if(getBoard().supplies[s].getSupplyTileId() == temporaryPos) {    //we check whether at temporaryPos there is a supply
					  maxS = 2;                                                     //if there is we save at maxS the value "2" that means that the distance between currentPos and a supply is 2 tile
					  continue;
				  }
				//if there isn't a supply 2 tiles away, we check at 3 tiles away  
				  if (getBoard().tiles[temporaryPos].getUp() == false){             //we check if he can move up
					  temporaryPos += getBoard().getN();                            //we "move" him
					  
					  if(getBoard().supplies[s].getSupplyTileId() == temporaryPos)  //we check whether at temporaryPos there is a supply
						  maxS = 3;                                                 //if there is we save at maxS the value "2" that means that the distance between currentPos and a supply is 2 tile
				  }//end of second else-if
				}//end of first else-if
			}//end of first if
		}//end of for
		
		   
		//we save the value of maxS at the variable supplieDistance(which represents the final nearest distance from a supply)
			supplieDistance = maxS;
			
	//ÔÇÅÍ WE FOLLOW THE SAME PROSEDURE FOR THE REST OF MOVEMENTS
	 
	    //CHECK DOWN
		  for(int s=0; s<getBoard().getS(); s++) {
			
			if (getBoard().tiles[currentPos].getDown() == false) {
				int temporaryPos = currentPos - getBoard().getN();                  
			   
				if(getBoard().supplies[s].getSupplyTileId() == temporaryPos) {
					maxS = 1;
					break;
				  }
				
				if(getBoard().tiles[temporaryPos].getDown() == false){
				  temporaryPos -= getBoard().getN();                                
				  
				  if(getBoard().supplies[s].getSupplyTileId() == temporaryPos) {
					  maxS = 2;
					  continue;
				  }
				  if (getBoard().tiles[temporaryPos].getDown() == false){
					  temporaryPos -= getBoard().getN();                                
					 
					  if(getBoard().supplies[s].getSupplyTileId() == temporaryPos)
						  maxS = 3;
				  }//end of second else-if
				}//end of first else-if
			}//end of first if
		}//end of for
		

		if(supplieDistance>0 ) {
			if (maxS < supplieDistance)
				supplieDistance = maxS;
		}
			
		else if(supplieDistance==0 && maxS > supplieDistance)
			supplieDistance = maxS;
		
		
		//CHECK RIGHT
		for(int s=0; s<getBoard().getS(); s++) {
			if (getBoard().tiles[currentPos].getRight() == false) {
				int temporaryPos = currentPos + 1;                  //1 tile dexia apo currentPos
			   
				if(getBoard().supplies[s].getSupplyTileId() == temporaryPos) {
					maxS = 1;
					break;
				  }
				else if(getBoard().tiles[temporaryPos].getRight() == false){
				 
				  temporaryPos += 1;                                //2 tile deixa apo currentPos
				  if(getBoard().supplies[s].getSupplyTileId() == temporaryPos) {
					  maxS = 2;
					  continue;
				  }
				  else if (getBoard().tiles[temporaryPos].getRight() == false){
					  temporaryPos += 1;                                //3 tile panw apo currentPos
					  if(getBoard().supplies[s].getSupplyTileId() == temporaryPos)
						  maxS = 3;
				  }//end of second else-if
				}//end of first else-if
			}//end of first if
		}//end of for
			
		if(supplieDistance>0 ) {
			if (maxS < supplieDistance)
				supplieDistance = maxS;
		}
			
		else if(supplieDistance==0 && maxS > supplieDistance)
			supplieDistance = maxS;
		
		//CHECK LEFT
		for(int s=0; s<getBoard().getS(); s++) {
			if (getBoard().tiles[currentPos].getLeft() == false) {
				int temporaryPos = currentPos - 1;                  //1 tile aristera apo currentPos
			   
				if(getBoard().supplies[s].getSupplyTileId() == temporaryPos) {
					maxS = 1;
					break;
				  }
				else if(getBoard().tiles[temporaryPos].getLeft() == false){
				 
				  temporaryPos -= 1;                                //2 tile aristera apo currentPos
				  if(getBoard().supplies[s].getSupplyTileId() == temporaryPos) {
					  maxS = 2;
					  continue;
				  }
				  else if (getBoard().tiles[temporaryPos].getLeft() == false){
					  temporaryPos -= 1;                                //3 tile panw apo currentPos
					  if(getBoard().supplies[s].getSupplyTileId() == temporaryPos)
						  maxS = 3;
				  }//end of second else-if
				}//end of first else-if
			}//end of first if
		}//end of for
				    
		if(supplieDistance>0 ) {
			if (maxS < supplieDistance)
				supplieDistance = maxS;
		}
			
		else if(supplieDistance==0 && maxS > supplieDistance)
			supplieDistance = maxS;
			 
	
		
 //USING SIMILAR LOGIC WE CHECK THE DISTANCE FROM MINOTAUR	
		
	int minotaurDistance = 0, maxM = 0;
	
	if (getBoard().tiles[currentPos].getUp() == false) {
		int temporaryPos = currentPos + getBoard().getN();                  
	   
		if(opponentCurrentPos == temporaryPos) {
			maxM = 1;
		  }
		
		else if(getBoard().tiles[temporaryPos].getUp() == false){
		 
		  temporaryPos += getBoard().getN();                                
		  if(opponentCurrentPos == temporaryPos) {
			  maxM = 2;
		  }
		  else if (getBoard().tiles[temporaryPos].getUp() == false){
			  temporaryPos += getBoard().getN();                                
			  if(opponentCurrentPos == temporaryPos)
				  maxM = 3;
		  }//end of second else-if
		}//end of first else-if
	}//end of first if
	
	if(maxM > minotaurDistance)
		minotaurDistance = maxM;
		 
	
	if (getBoard().tiles[currentPos].getDown() == false) {
		int temporaryPos = currentPos - getBoard().getN();                  
	   
		if(opponentCurrentPos == temporaryPos) {
			maxM = 1;
		  }
		else if(getBoard().tiles[temporaryPos].getDown() == false){
		 
		  temporaryPos -= getBoard().getN();                                
		  if(opponentCurrentPos == temporaryPos) {
			  maxM = 2;
		  }
		  else if (getBoard().tiles[temporaryPos].getDown() == false){
			  temporaryPos -= getBoard().getN();                                
			  if(opponentCurrentPos == temporaryPos)
				  maxM = 3;
		  }//end of second else-if
		}//end of first else-if
	}//end of first if

	if(minotaurDistance>0 ) {
		if (maxM < minotaurDistance)
			minotaurDistance = maxM;
	}
		
	else if(minotaurDistance==0 && maxM > minotaurDistance)
		minotaurDistance = maxM;
	
	
		if (getBoard().tiles[currentPos].getRight() == false) {
			int temporaryPos = currentPos + 1;                  
		   
			if(opponentCurrentPos == temporaryPos) {
				maxM = 1;
			  }
			else if(getBoard().tiles[temporaryPos].getRight() == false){
			 
			  temporaryPos += 1;                                
			  if(opponentCurrentPos == temporaryPos) {
				  maxM = 2;
			  }
			  else if (getBoard().tiles[temporaryPos].getRight() == false){
				  temporaryPos += 1;                                
				  if(opponentCurrentPos== temporaryPos)
					  maxM = 3;
			  }//end of second else-if
			}//end of first else-if
		}//end of first if
			
		if(minotaurDistance>0 ) {
			if (maxM < minotaurDistance)
				minotaurDistance = maxM;
		}
			
		else if(minotaurDistance==0 && maxM > minotaurDistance)
			minotaurDistance = maxM;
	
	
	if (getBoard().tiles[currentPos].getLeft() == false) {
		int temporaryPos = currentPos - 1;                  
	   
		if(opponentCurrentPos == temporaryPos) {
			maxM = 1;
		  }
		else if(getBoard().tiles[temporaryPos].getLeft() == false){
		 
		  temporaryPos -= 1;                                
		  if(opponentCurrentPos == temporaryPos) {
			  maxM = 2;
		  }
		  else if (getBoard().tiles[temporaryPos].getLeft() == false){
			  temporaryPos -= 1;                               
			  if(opponentCurrentPos == temporaryPos)
				  maxM = 3;
		  }//end of second else-if
		}//end of first else-if
	}//end of first if
	
	if(minotaurDistance>0 ) {
		if (maxM < minotaurDistance)
			minotaurDistance = maxM;
	}
		
	else if(minotaurDistance==0 && maxM > minotaurDistance)
		minotaurDistance = maxM;
  

//We check whether Theseus got a supply 
  int sup = 0;   //if Theseus got a supply sup=1 else sup=0
//with this for loop we check if there is a supply at his new position
  for(int s=0; s<getBoard().getS(); s++) {
   if(getBoard().supplies[s].getSupplyTileId() == currentPos) {
	sup = 1;
	getBoard().supplies[s].setSupplyTileId(-1000);	//we disappear the supply from the board
   }//end of if
}//end of for
		
//RENEWAL OF THE VARIABLE
  path.add(new Integer[] {dice,sup,supplieDistance,minotaurDistance});
	return move;
  
 }//end of getNextMove
 
 
 
 void createMySubtree(Node root,int currentPos, int opponentCurrentPos , int depth) {
		Board P= root.getNodeBoard();
		double parentEval=0;
		
		if(!getBoard().tiles[currentPos].getLeft()==true) {
			
			
			int []MoveLeft=new int[] {currentPos/getBoard().getN(),currentPos % getBoard().getN(),7};
			parentEval=evaluate(currentPos, 7,opponentCurrentPos,P,1);
			Node childLeft=new Node(root,new ArrayList<Node>(),depth,MoveLeft, P,parentEval);
			root.getChildren().add(childLeft);
			createOpponentSubTree(opponentCurrentPos, currentPos-1, childLeft,depth+1,parentEval);
			
		}
		if(!getBoard().tiles[currentPos].getRight()==true){
			
			int []MoveRight=new int[] {currentPos/getBoard().getN(),currentPos % getBoard().getN(),3};
			parentEval=evaluate(currentPos, 3,opponentCurrentPos,P,1);
			Node childRight=new Node(root,new ArrayList<Node>(),depth,MoveRight, P,parentEval);
			root.getChildren().add(childRight);
			createOpponentSubTree(opponentCurrentPos, currentPos+1, childRight,depth+1,parentEval);
		}
		
		if(!getBoard().tiles[currentPos].getDown()==true) {
			
			int []MoveDown=new int[] {currentPos/getBoard().getN(),currentPos % getBoard().getN(),5};
			parentEval=evaluate(currentPos, 5,opponentCurrentPos,P,1);
			Node childDown=new Node(root,new ArrayList<Node>(),depth,MoveDown, P,parentEval);
			root.getChildren().add(childDown);
			createOpponentSubTree(opponentCurrentPos, currentPos-getBoard().getN(), childDown,depth+1,parentEval);
		}
		if(!getBoard().tiles[currentPos].getUp()==true) {
			
			int []MoveUp=new int[] {currentPos/getBoard().getN(),currentPos % getBoard().getN(),1};
			parentEval=evaluate(currentPos, 1,opponentCurrentPos,P,1);
			Node childUp=new Node(root,new ArrayList<Node>(),depth,MoveUp, P,parentEval);
			root.getChildren().add(childUp);
			createOpponentSubTree(opponentCurrentPos, currentPos+getBoard().getN(), childUp,depth+1,parentEval);
		}
		
			
		
	}
 
	void createOpponentSubTree(int currentPos, int opponentCurrentPos,Node parent , int depth, double parentEval) {
		Board P= parent.getNodeBoard();
		double Eval=0;
		
		if(!getBoard().tiles[currentPos].getLeft()==true) {
			
			
			int []MoveLeft=new int[] {currentPos/getBoard().getN(),currentPos % getBoard().getN(),7};
			Eval=parentEval+evaluate(currentPos, 7,opponentCurrentPos,P,0);
			Node childLeft=new Node(parent,new ArrayList<Node>(),depth,MoveLeft, P,Eval);
			parent.getChildren().add(childLeft);
			
			
		}
		if(!getBoard().tiles[currentPos].getRight()==true){
			
			int []MoveRight=new int[] {currentPos/getBoard().getN(),currentPos % getBoard().getN(),3};
			Eval=parentEval+evaluate(currentPos, 3,opponentCurrentPos,P,0);
			Node childRight=new Node(parent,new ArrayList<Node>(),depth,MoveRight, P,Eval);
			parent.getChildren().add(childRight);
			
		}
		
		if(!getBoard().tiles[currentPos].getDown()==true) {
			
			int []MoveDown=new int[] {currentPos/getBoard().getN(),currentPos % getBoard().getN(),5};
			Eval=parentEval+evaluate(currentPos, 5,opponentCurrentPos,P,0);
			Node childDown=new Node(parent,new ArrayList<Node>(),depth,MoveDown, P,Eval);
			parent.getChildren().add(childDown);
			
		}
		if(!getBoard().tiles[currentPos].getUp()==true) {
			
			int []MoveUp=new int[] {currentPos/getBoard().getN(),currentPos % getBoard().getN(),1};
			Eval=parentEval+evaluate(currentPos, 1,opponentCurrentPos,P,0);
			Node childUp=new Node(parent,new ArrayList<Node>(),depth,MoveUp, P,Eval);
			parent.getChildren().add(childUp);
			
		}
		
			
		
	}
	
	int chooseMinMaxMove(Node root) {
      Random number= new Random();
      for(int k=0; k<root.getChildren().size(); k++) {
          double min= root.getChildren().get(k).getChildren().get(0).getNodeEvaluation();
          for(int i=1; i< root.getChildren().get(k).getChildren().size(); i++) {
              if(root.getChildren().get(k).getChildren().get(i).getNodeEvaluation()<min) {
                  min=root.getChildren().get(k).getChildren().get(i).getNodeEvaluation();
              }
              if(root.getChildren().get(k).getChildren().get(i).getNodeEvaluation()==min) {
                  if(number.nextBoolean()) {
                  min=root.getChildren().get(k).getChildren().get(i).getNodeEvaluation();

                  }
              }
          }
          root.getChildren().get(k).setNodeEvaluation(min);
      }

      double max= root.getChildren().get(0).getNodeEvaluation();
      int l=0;
      for(int p=1; p<root.getChildren().size(); p++) {
         if(root.getChildren().get(p).getNodeEvaluation()>max) {
             max= root.getChildren().get(p).getNodeEvaluation();
             l=p;
         }
         if(root.getChildren().get(p).getNodeEvaluation()==max) {
             if(number.nextBoolean()) {
             max= root.getChildren().get(p).getNodeEvaluation();
             l=p;
             }
         }
      }
      
      return  root.getChildren().get(l).getNodeMove()[2];
   }

}//end of class	
