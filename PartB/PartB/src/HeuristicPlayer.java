//This class represents the players of the game that control their moves

import java.util.ArrayList;

public class HeuristicPlayer extends Player {
	
	ArrayList<Integer[]> path;  //in this array we save informations of each move : the dice, if the player got a supply, the distance from the nearest supply and the distance from Minotaur
	
//Constructors of the class	
	public HeuristicPlayer () {
		super();
		path = new ArrayList <Integer[]> ();
	} // end of default constructor
	
	
	public HeuristicPlayer (int playerId, String name, Board board, int score, int x, int y) {
		super(playerId, name, board, score, x, y);
		path = new ArrayList <Integer[]> ();
	} // end of constructor
	
	public HeuristicPlayer (HeuristicPlayer hp, Player p) {
		super(p);
		path = hp.path;
	} // end of constructor
	
	
/*TARGET FUNCTION: this function calculates the value of each move taking into account the distance from a supply(if the player can see it) and the distance from the Minotaur (if the player can see him)
 * the returned price is the value of the transaction we evaluate each time
 */
	public double f (double nearSupplies, double opponentDist) {
		double a; 
		a = (nearSupplies * 0.46) + (opponentDist * 0.54);
		return a;
	} //end of target function
	

/*EVALUATE FUNCTION: This function evaluates the player's move for the roll he has (dice), since he is in specific position (currentPos)
 * minotaurPos: this definition represents the position of Ìinotaur on the board when HeuristicPlayer has the position 'currentPos'
 * The function returns the motion evaluation according to the function target.
 */
	public double evaluate(int currentPos, int minotaurPos, int dice) {
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
		  for (int i = 0; i<getBoard().getS(); i++) {
			  
			//we have already check at function getNextMove that at currentPos Theseus can move up (so we know every time that this move is possible)
		    temporaryPos = currentPos + getBoard().getN();                           //temporaryPos=currentPos+N (N=board dimension), we "move" the player 1 tile up
		    
		    if(getBoard().supplies[i].getSupplyTileId() == temporaryPos) {           //we check if there is a supply at this tile (with tileId = temporaryPos)
		      nearSupplies += (double) 1;                                            //the distance between the player (at currentPos) and supply is 1 tile so we increase by 1 the variable
		      continue;
		      }//end of first if
		    
		      if(getBoard().tiles[temporaryPos].getUp() == false) {                  //we check if Theseus can move up 
		      temporaryPos += getBoard().getN();                                     //temporaryPos=currentPos+2*N we "move" the player 1 tile up
		    			 
		      if(getBoard().supplies[i].getSupplyTileId() == temporaryPos) {         //we check if there is a supply at this tile (with tileId = temporaryPos)
			  nearSupplies += 0.5;                                                   //the distance between the player(at currentPos) and supply is 2 tiles so we increase by 2 the variable
			  continue;
		      }//end of third if
			    			
		      if(getBoard().tiles[temporaryPos].getUp() == false) {                  //we check if Theseus can move up
		      temporaryPos += getBoard().getN();                                     //temporaryPos=currentPos+3*N  (max distance we can check)
		    		
		       if(getBoard().supplies[i].getSupplyTileId() == temporaryPos)          //we check if there is a supply at this tile (with tileId = temporaryPos)
		    	 nearSupplies += 0.3;                                                //the distance between the player(at currentPos) and supply is 3 tiles so we increase by 3 the variable
		    }//end of fourth if
		   }//end of second if
		  }//end of for
		    	 
		  		    
		  //CHECKS FOR MINOTAUR
		  //we have already check at function getNextMove that at currentPos Theseus can move up (so we know every time that this move is possible)
		  temporaryPos = currentPos + getBoard().getN();                             //temporaryPos=currentPos+N , we "move" Theseus 1 tile up
		    		
		  if(minotaurPos == temporaryPos)                                            //we check whether Theseus and Minotaur are in the same tile
		    opponentDist = (double) -1;                                              //the distance between Theseus(at currentPos) and Minotaur is 1 tiles so we increase by 1 the variable
		  
		  else if (getBoard().tiles[temporaryPos].getUp() == false) {                //we check if Theseus can move up
			temporaryPos += getBoard().getN();                                       //temporaryPos=currentPos+2*N  we "move" Theseus 1 tile up
			
			if(minotaurPos == temporaryPos)                                          //we check whether Theseus and Minotaur are in the same tile
		     opponentDist = -0.5;                                                    //the distance between Theseus(at currentPos) and Minotaur is 2 tiles so we increase by 2 the variable
			
			else if (getBoard().tiles[temporaryPos].getUp() == false) {				 //we check if Theseus can move up 
			 temporaryPos += getBoard().getN();                                      //temporaryPos=currentPos+3*N   (max distance we can check)
			 
			 if(minotaurPos == temporaryPos)                                         //we check whether Theseus and Minotaur are in the same tile
		      opponentDist = -0.3;													 //the distance between Theseus(at currentPos) and Minotaur is 3 tiles so we increase by 3 the variable
			 
			}//end of second else-if
		  }//end of first else-if
		    			 
			 
		  
		break; //end of case 1
		     
		//ÔÇÅÍ WE FOLLOW THE SAME PROSEDURE FOR THE REST OF MOVENTS
		
         case 3:
				
		   //CHECKS FOR SUPPLIES
		   for (int i = 0; i<getBoard().getS(); i++) {
			   
		    
		    temporaryPos = currentPos + 1;                                              
				    
			if(getBoard().supplies[i].getSupplyTileId() == temporaryPos) {
		     nearSupplies += (double) 1;
			 continue;
			 }//end of first if
				    
			if(getBoard().tiles[temporaryPos].getRight() == false) {                     
			 temporaryPos += 1;                                                          
				    			 
			 if(getBoard().supplies[i].getSupplyTileId() == temporaryPos) {
			   nearSupplies += 0.5;
			   continue;
			 }//end of third if
					    			
			   if(getBoard().tiles[temporaryPos].getRight() == false) {                  
				 temporaryPos += 1;                                                      
				    		
				 if(getBoard().supplies[i].getSupplyTileId() == temporaryPos)
				   nearSupplies += 0.3;
				    	
			 }//end of fourth if
			 }//end of second if
			 }//end of for
				    	 
				  		    
		   //CHECKS FOR MINOTAUR
				  
		   temporaryPos = currentPos + 1 ;                                             
				    		
		   if(minotaurPos == temporaryPos)
			opponentDist = (double) -1;
				  
		   else if (getBoard().tiles[temporaryPos].getRight() == false) {              
			temporaryPos += 1;                                                         
					
			if(minotaurPos == temporaryPos) 
			  opponentDist = -0.5;
					
			else if (getBoard().tiles[temporaryPos].getRight() == false) {			   
			  temporaryPos += 1;                                                       
					 
			  if(minotaurPos == temporaryPos) 
			   opponentDist = -0.3;
					 
			}//end of second else-if
		   }//end of first else-if
				    			 
					 
				  
		 break; //end of case 3
			     
		 case 5:
				
			  //CHECKS FOR SUPPLIES
			  for (int i = 0; i<getBoard().getS(); i++) {
				  
				
			    temporaryPos = currentPos - getBoard().getN();                           
			    
			    if(getBoard().supplies[i].getSupplyTileId() == temporaryPos) {
			      nearSupplies += (double) 1;
			      continue;
			      }//end of first if
			    
			    if(getBoard().tiles[temporaryPos].getDown() == false) {                   
			      temporaryPos -= getBoard().getN();                                      
			    			 
			      if(getBoard().supplies[i].getSupplyTileId() == temporaryPos) {
				  nearSupplies += 0.5;
				  continue;
			      }//end of third if
				    			
			      if(getBoard().tiles[temporaryPos].getDown() == false) {                  
			      temporaryPos -= getBoard().getN();                                     
			    		
			       if(getBoard().supplies[i].getSupplyTileId() == temporaryPos)
			    	 nearSupplies += 0.3;
			    	
			    }//end of fourth if
			    }//end of second if
			    }//end of for
			    	 
			  		    
			  //CHECKS FOR MINOTAUR
			  
			  temporaryPos = currentPos - getBoard().getN();                             
			    		
			  if(minotaurPos == temporaryPos)
			    opponentDist = (double) -1;
			  
			  else if (getBoard().tiles[temporaryPos].getDown() == false) {               
				temporaryPos -= getBoard().getN();                                        
				
				if(minotaurPos == temporaryPos) 
			     opponentDist = -0.5;
				
				else if (getBoard().tiles[temporaryPos].getDown() == false) {			  
				 temporaryPos -= getBoard().getN();                                      
				 
				 if(minotaurPos == temporaryPos) 
			      opponentDist = -0.3;
				 
				}//end of second else-if
			  }//end of first else-if
			    			 
				 
			  
			break; //end of case 5
			     
			     
			     
	         case 7:
					
			   //CHECKS FOR SUPPLIES
			   for (int i = 0; i<getBoard().getS(); i++) {
				temporaryPos = currentPos - 1;                                               
			    
				if(getBoard().supplies[i].getSupplyTileId() == temporaryPos) {
			     nearSupplies += (double) 1;
				 continue;
				 }//end of first if
					    
				if(getBoard().tiles[temporaryPos].getLeft() == false) {                     
				 temporaryPos -= 1;                                                          
					    			 
				 if(getBoard().supplies[i].getSupplyTileId() == temporaryPos) {
				   nearSupplies += 0.5;
				   continue;
				 }//end of third if
						    			
				   if(getBoard().tiles[temporaryPos].getLeft() == false) {                  
					 temporaryPos -= 1;                                                      
					    		
					 if(getBoard().supplies[i].getSupplyTileId() == temporaryPos)
					   nearSupplies += 0.3;
					    	
				 }//end of fourth if
				 }//end of second if
				 }//end of for
					    	 
					  		    
			   //CHECKS FOR MINOTAUR
			   temporaryPos = currentPos - 1 ;                                             
					    		
			   if(minotaurPos == temporaryPos)
				opponentDist = (double) -1;
					  
			   else if (getBoard().tiles[temporaryPos].getLeft() == false) {              
				temporaryPos -= 1;                                                        
						
				if(minotaurPos == temporaryPos) 
				  opponentDist = -0.5;
						
				else if (getBoard().tiles[temporaryPos].getLeft() == false) {			   
				  temporaryPos += 1;                                                      
						 
				  if(minotaurPos == temporaryPos) 
				   opponentDist = -0.3;
						 
				}//end of second else-if
			   }//end of first else-if
					    			 
						 
					  
			 break; //end of case 7		     
		     
		}//end of switch 
		 
	
	//Here we call the TARGET FUNCTION and it is returned the value of the move  we are considering in this case
		return f(nearSupplies, opponentDist);
		
    }//end of function evaluate
	
	
	
	
//GETNEXTMOVE FUNCTION: is responsible for the choice of the final (best) move of the player.
	
	public int getNextMove(int currentPos, int minotaurPos) {
		
		int maxMoves = 0;                                                  //this variable counts the number of possible moves in each currentPos
		
	/* In order to have an array with all possible moves of each position (with their values) we must know the total number of possible moves(=max 4)
	 * So for each direction we check whether there is a wall that prevents the movement or not.
	 * if the player is able to move at this direction, the variable maxMoves increases by 1.
	 */
		
		 if(!(getBoard().tiles[currentPos].getUp()))  {  
	    	   maxMoves++;
			}//end of if
		 if(!(getBoard().tiles[currentPos].getDown()))  {   
	    	   maxMoves++;
			}//end of if
		 if(!(getBoard().tiles[currentPos].getRight()))  {   
			 maxMoves++;
			}//end of if
		 if(!(getBoard().tiles[currentPos].getLeft()))  {   
			 maxMoves++;
			}//end of if
		 
	/*Then we bind the dynamic array possibleMoves of 2 lines and maxMoves columns
	 * (first line contains dices and second line contains the value of each dice)
	 */
		double [][] possibleMoves = new double [2][maxMoves]; 
		
		int i = 0; // index of possibleMove array 
		
	/*
	 * With this loop we initialize the array with the possible moves.
	 * The loop ends when i becomes equal to maxMoves (i increases every time we put in the array a possible move and the value of the move)
	 * In order to save the value of the move we use the function evaluate.
	 */
		
		do{
			if (getBoard().tiles[currentPos].getUp() == false) {               
				possibleMoves[0][i] = (double) 1;                               
				possibleMoves[1][i] = evaluate(currentPos, minotaurPos, 1);
				i++;
			}//end of if
			
			if (getBoard().tiles[currentPos].getRight() == false) {
				possibleMoves[0][i] = (double) 3;
				possibleMoves[1][i] = evaluate(currentPos, minotaurPos, 3);
				i++;
			}//end of if)
			
			if (getBoard().tiles[currentPos].getDown() == false) {
				possibleMoves[0][i] = (double) 5;
				possibleMoves[1][i] = evaluate(currentPos, minotaurPos, 5);
				i++;
			}//end of if
			
			if (getBoard().tiles[currentPos].getLeft() == false) {
				possibleMoves[0][i] = (double) 7;
				possibleMoves[1][i] = evaluate(currentPos, minotaurPos, 7);
				i++;
			}//end of if     
			
		} while(i<maxMoves);

		
		
		//We classify the array from higher value to lower 
		for (int last=maxMoves-1; last>0; last-- ) {
			for (int first=0; first<last; first++) {
			
				if (possibleMoves[1][first]<possibleMoves[1][first+1]) {       //we compare the values of possible moves
					for (int t=0; t<2; t++) {                                  //for t=0 we classify the the elements of the first line and respectively for t=1 the elements of the second
						double temp = possibleMoves[t][first];                
						possibleMoves[t][first] = possibleMoves[t][first+1];
						possibleMoves[t][first+1] = temp;
				      }//end of 3rd for
			    }//end of if
			}//end of 2nd for
	    }//end of 1st for
		
		
		/*Here we check whether there are moves with same values and we choose randomly one of them. 
		*Otherwise we take the move from the first position of possibleMoves array (that corresponds to the highest value).
		*/
		int finalDice = 0;                                      //here is saved the final move of Theseus
		int sameValues = 1;                                     //we use this counter to check if all or some moves have the same value (if we don't have moves with same value, sameValues=1)
		
		//We use this for to check all the elements of the second line of the array
		for (int j=1; j<maxMoves; j++) {
			if (possibleMoves[1][0]==possibleMoves[1][j])       //we compare the first element with others. 
				sameValues++;                                   //When we found the same value with the first one, the counter increases by one
		}//end of for
		
		
		if (sameValues==4) {
			
		 //we create an array where we save the corresponding dices of the values
			int [] randomMoves = {(int)possibleMoves[0][0],(int)possibleMoves[0][1],(int)possibleMoves[0][2],(int)possibleMoves[0][3]};
			int randMove = (int)((Math.random()*4));                               //randMove takes random values from 0 to 3 (that values corresponds to randomMoves index)
			finalDice = randomMoves[randMove];                                     //finalDice gets the value returned from the randMoves array with index rendMove
	        }//end of if
		
		
		if (sameValues==3) {
			int [] randomMoves = {(int)possibleMoves[0][0],(int)possibleMoves[0][1],(int)possibleMoves[0][2]};
			int randMove = (int)((Math.random()*3));
			finalDice = randomMoves[randMove];
		    }//end of 1st else if
		
		
		if (sameValues==2) {
			int [] randomMoves = {(int)possibleMoves[0][0],(int)possibleMoves[0][1]};
			int randMove = (int)((Math.random()*2));
			finalDice = randomMoves[randMove];
		    }//end of 2nd else if
		

		if (sameValues==1)
			finalDice = (int) possibleMoves[0][0]; 
		
		
		
	// MOVE THESEUS: Here we use the function move() with arguments the currentPos and finalDice 
		move(currentPos,finalDice);
		
		//we update the variable currentPos according to the previous move
		switch(finalDice) { 
	    
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
	   
		
	//We check whether Theseus got a supply 
		int sup = 0;   //if Theseus got a supply sup=1 else sup=0
		
		//with this for loop we check if there is a supply at his new position
		for(int s=0; s<getBoard().getS(); s++) {
			if(getBoard().supplies[s].getSupplyTileId() == currentPos) {
				sup = 1;
				getBoard().supplies[s].setSupplyTileId(-1);	//we disappear the supply from the board
			}//end of if
		}//end of for
		
		
		
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
	   
		if(minotaurPos == temporaryPos) {
			maxM = 1;
		  }
		
		else if(getBoard().tiles[temporaryPos].getUp() == false){
		 
		  temporaryPos += getBoard().getN();                                
		  if(minotaurPos == temporaryPos) {
			  maxM = 2;
		  }
		  else if (getBoard().tiles[temporaryPos].getUp() == false){
			  temporaryPos += getBoard().getN();                                
			  if(minotaurPos == temporaryPos)
				  maxM = 3;
		  }//end of second else-if
		}//end of first else-if
	}//end of first if
	
	if(maxM > minotaurDistance)
		minotaurDistance = maxM;
		 
	
	if (getBoard().tiles[currentPos].getDown() == false) {
		int temporaryPos = currentPos - getBoard().getN();                  
	   
		if(minotaurPos == temporaryPos) {
			maxM = 1;
		  }
		else if(getBoard().tiles[temporaryPos].getDown() == false){
		 
		  temporaryPos -= getBoard().getN();                                
		  if(minotaurPos == temporaryPos) {
			  maxM = 2;
		  }
		  else if (getBoard().tiles[temporaryPos].getDown() == false){
			  temporaryPos -= getBoard().getN();                                
			  if(minotaurPos == temporaryPos)
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
		   
			if(minotaurPos == temporaryPos) {
				maxM = 1;
			  }
			else if(getBoard().tiles[temporaryPos].getRight() == false){
			 
			  temporaryPos += 1;                                
			  if(minotaurPos == temporaryPos) {
				  maxM = 2;
			  }
			  else if (getBoard().tiles[temporaryPos].getRight() == false){
				  temporaryPos += 1;                                
				  if(minotaurPos== temporaryPos)
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
	   
		if(minotaurPos == temporaryPos) {
			maxM = 1;
		  }
		else if(getBoard().tiles[temporaryPos].getLeft() == false){
		 
		  temporaryPos -= 1;                                
		  if(minotaurPos == temporaryPos) {
			  maxM = 2;
		  }
		  else if (getBoard().tiles[temporaryPos].getLeft() == false){
			  temporaryPos -= 1;                               
			  if(minotaurPos == temporaryPos)
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
	
	
	//RENEWAL OF THE VARIABLE
		
	  //First we commit a static array of 4 elements and then we save the informations for each move
		Integer[] bestMove = new Integer[4]; 
		bestMove[0] = finalDice;
		bestMove[1] = sup;
		bestMove[2] = supplieDistance;
		bestMove[3] = minotaurDistance;
			
		
	   path.add(bestMove);
	 
	   return currentPos;  
		
}// end of function getNextMove
	

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

} //end of class
