//This class represents the table of the game 

public class Board {
		int N; //The dimension of the table
		int S; // The number of the supplies on the table
		int W; // The totall number of walls 
		Tile[] tiles; //Array of tiles
		Supply[] supplies; //Array of supplies
	
//Constructors of the class
	public Board() {
		N = 0;
		S = 0;
		W = 0;
	}
	
	public Board(int N, int S, int W) {
		this.N = N;
		this.S = S;
		this.W = W;
		tiles = new Tile[N*N];
		supplies = new Supply[S];
	}
	
	public Board(Board boardOb) {
		N = boardOb.N;
		S = boardOb.S;
		W = boardOb.W;
		tiles = new Tile[N*N];
		supplies = new Supply[S];
	}
	
//Setters & Getters of the variables
	public void setN(int N) {
		this.N = N;
	}
	public int getN() {
		return N;
	}
	public void setS(int S) {
		this.S = S;
	}
	public int getS() {
		return S;
	}
	public void setW(int W) {
		this.W = W;
	}
	public int getW() {
		return W;
	}
	
// Function that converts boolean variables to int variables
	public int boolToInt (boolean b) {
		
		if(b)  return 1;
		else return 0;
	}
	
// This function constructs with random numbers the array of tiles
	public void createTile() {
	
		int numOfWalls = W - (4*N-1);  
		
		
		for(int i=0;i<N*N;i++) {
			tiles[i] = new Tile(i, i/N, i%N, false, false, false, false);  //Initialization of tiles array
		}
		
		//Construction of board's contour 
		for(int i=0;i<N;i++) {           //The entrance is closed from the start
			tiles[i].down = true;
		}
		
		for(int i=0;i<N*N;i+=N) {
			tiles[i].left = true;
		}
		
		for(int i=N*(N-1);i<N*N;i++) {
			tiles[i].up = true;
		}
		
		for(int i=N-1;i<N*N;i+=N) {
			tiles[i].right = true;
		}
		
//Placement of random walls
		
		for( int i=0 ; i<N*N ; i++) {
				
			int counter=0;
			
		if(tiles[i].getUp()) 
		    counter++;
		    
		if(tiles[i].getDown()) 
		    counter++;
		    
		if(tiles[i].getLeft()) 
		    counter++;
		    
	    if(tiles[i].getRight()) 
		    counter++;
	    
	    
	   int count = (int)(1+(Math.random()*2)); 		     //Variable that takes random prices between 1 and 2
		    
//Checks if there is space for more walls in a tile
	 if(counter < 2) {
		 switch(count) {
		    	
		    case 1:
		     
		      int counter2=0; //This counter increases when the tile with id i-N has a wall
			  if(i>=N) {      //i>=N <=> i-N>=0 in order to use i-N to the tile's array
				if(tiles[i-N].getUp()) 
		          counter2++;
						    
			  if(tiles[i-N].getDown()) 
			      counter2++;
						    
			  if(tiles[i-N].getLeft()) 
				  counter2++;
						    
			  if(tiles[i-N].getRight()) 
				  counter2++;}
				    	
			  if(tiles[i].getDown() == false && i!=0 && counter2<2) { //Checks if we can put a wall in tiles with id i and i-N
				   tiles[i].setDown(true);
				   tiles[i-N].setUp(true);
				    numOfWalls=numOfWalls-2;
				 break;
		    		     }
			
			  else break;
		    				
		    case 2:
		    		
		    	int counter3=0; //This counter increases when the tile with id i+1 has a wall
		    	
		    	if(tiles[i+1].getUp()) 
				   counter3++;
				    
				if(tiles[i+1].getDown()) 
				   counter3++;
				    
				if(tiles[i+1].getLeft()) 
				   counter3++;
				    
				if(tiles[i+1].getRight()) 
				   counter3++;
		    		
		    	if(tiles[i].getRight() == false && counter3<2) { //Checks if we can put a wall in tiles with id i and i-N
		    	  tiles[i].setRight(true);
		    	  tiles[i+1].setLeft(true);
		    	    numOfWalls=numOfWalls-2;
		    	break;
		    		     }
		    	else break;
		    
		    		
		    	}
		    }
		   
	         if (numOfWalls==0) break;
		}
	}

		
//This function constructs with random numbers the array of supplies
      public void createSupply() {
		
	      for ( int i=0; i<S; i++) {
			boolean b=true;          //we use this boolean variable in order to finish the "do...while" loop
			int k = 0;               //here we save the id of a random supply
			int d = 0;               //here we save the id of the tile at position j 
			int x;                   
			int y;
			
		do {
				
			 x = (int) (Math.random()*N);   //random value between 0...N
			 y = (int) (Math.random()*N);   //random value between 0...N
			 int counter = 0;               //this counter counts how many times we have the same id of supplies
			 k=x*N+y;
			 
//Here we check if there is already a supply in a random location 
		   for (int j = 0; j<i; j++) {
			
			  d = supplies[j].getSupplyTileId();
			
				if(k == d ) 
				   counter++;}
				if(counter>=1)
				   b = false;
				else {
				   if (k == 0 || k == (N*N/2)) 
					b = false;
				else 
					b=true;
		                    }
				        
				   
		      }  while (b == false);
			
			
		      supplies[i] = new Supply (i, x, y, x * N + y);
		}		
	}

	
//Construction of the Board
	public void createBoard() {
		
		createTile();
		createSupply();
	 }
	
	
//This function displays everything (walls, supplies and players) to the console 
	public String[][] getStringRepresentation(int theseusTile, int minotaurTile){
	
	   String[][] strArray = new String[2*N+1][N];
			
		  for (int j=0; j<N; j++) {        //Here we create the lower border of the board
					
			if(j!=(N-1))                  //Checks if we are in the last column (we put +---+) or not (we put +---)
			   strArray[0][j]= "+---";   
			else
			   strArray[0][j]= "+---+";   
		}
				
				
		  for (int j=0; j<N; j++) {          //Here we create the higher border of the board
					
			if(j!=(N-1))                        //Checks if we are in the last column (we put +---+) or not (we put +---)
			   strArray[2*N][j]= "+---";
			else
				strArray[2*N][j]= "+---+";
	   }
			
		  for (int i=1; i<2*N; i++) {       
			 for (int j=0; j<N; j++) {
				
			 if( i % 2 == 0 ) {                                   //Here we are only in even lines where we have to put the up and down walls
				  if (j!=(N-1)) {                                    //Again we check whether we are in the last column or not
					  
					if ( tiles[(i/2)*N+j].getDown() == true )
					   strArray[i][j] = "+---";
					else                                            // tiles[(i/2)*N+j].getDown() == false
					   strArray[i][j] = "+   ";
				  }
				  
				   else {
					  if ( tiles[(i/2)*N+j].getDown() == true )
						strArray[i][j] = "+---+";
					  else                                          //tiles[(i/2)*N+j].getDown() == true
						 strArray[i][j] = "+   +";      
					  }
			    }
						
				else    //if(i%2!=0)  odd lines
							
            //We have to check whether there is a left (or right) wall, a player and a supply or not
				for (int n=0; n<S; n++) {
								
					if (j!=(N-1)) {
								     
					  if( tiles[(i/2)*N+j].getLeft() == true && minotaurTile == ((i/2)*N+j) && supplies[n].getSupplyTileId() == ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) {
						 strArray[i][j] = "|s" + (supplies[n].getSupplyId()+1) + "M";
						  break; }
									
					  else if (tiles[(i/2)*N+j].getLeft() == true && minotaurTile == ((i/2)*N+j) && supplies[n].getSupplyTileId() != ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) 
						  strArray[i][j] = "| M ";
									
					  else if (tiles[(i/2)*N+j].getLeft() == true && minotaurTile != ((i/2)*N+j) && supplies[n].getSupplyTileId() == ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) {
						  strArray[i][j] = "|s" + (supplies[n].getSupplyId()+1) + " ";
							break; }
									
					  else if( tiles[(i/2)*N+j].getLeft() == false && minotaurTile == ((i/2)*N+j) && supplies[n].getSupplyTileId() == ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) {
						  strArray[i][j] = "s" + (supplies[n].getSupplyId()+1) + " M";
							break; }
									
					  else if( tiles[(i/2)*N+j].getLeft() == true && minotaurTile != ((i/2)*N+j) && supplies[n].getSupplyTileId() != ((i/2)*N+j) && theseusTile == ((i/2)*N+j) ) 
						  strArray[i][j] = "| T ";
									
					  else if( tiles[(i/2)*N+j].getLeft() == true && minotaurTile != ((i/2)*N+j) && supplies[n].getSupplyTileId() != ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) 
						  strArray[i][j] = "|   ";
									
					  else if( tiles[(i/2)*N+j].getLeft() == false && minotaurTile == ((i/2)*N+j) && supplies[n].getSupplyTileId() != ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) 
						  strArray[i][j] = "  M ";
									
					  else if( tiles[(i/2)*N+j].getLeft() == false && minotaurTile != ((i/2)*N+j) && supplies[n].getSupplyTileId() == ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) {
						  strArray[i][j] = "  s" + (supplies[n].getSupplyId()+1) ;
							break; }
									
					  else if( tiles[(i/2)*N+j].getLeft() == false && minotaurTile != ((i/2)*N+j) && supplies[n].getSupplyTileId() != ((i/2)*N+j) && theseusTile == ((i/2)*N+j) ) 
						  strArray[i][j] = "  T ";
									
					  else //if( tiles[(i/2)*N+j].getLeft() == false && minotaurTile != ((i/2)*N+j) && supplies[n].getSupplyTileId() != ((i/2)*N+j) && theseusTile != ((i/2)*N+j) )
						  strArray[i][j] = "    ";
										
									
								
										
							}
								else // if (j==(N-1)) last column 
								{
					if( tiles[(i/2)*N+j].getLeft() == true && minotaurTile == ((i/2)*N+j) && supplies[n].getSupplyTileId() == ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) {
						strArray[i][j] = "|s" + (supplies[n].getSupplyId()+1) + "M|";
						   break; }
									
					else if (tiles[(i/2)*N+j].getLeft() == true && minotaurTile == ((i/2)*N+j) && supplies[n].getSupplyTileId() != ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) 
						strArray[i][j] = "| M |";
									
					else if (tiles[(i/2)*N+j].getLeft() == true && minotaurTile != ((i/2)*N+j) && supplies[n].getSupplyTileId() == ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) {
						strArray[i][j] = "|s" + (supplies[n].getSupplyId()+1) + " |";
							break; }
									
					else if( tiles[(i/2)*N+j].getLeft() == false && minotaurTile == ((i/2)*N+j) && supplies[n].getSupplyTileId() == ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) {
						strArray[i][j] = "s" + (supplies[n].getSupplyId()+1) + " M|";
							break; }
									
					else if( tiles[(i/2)*N+j].getLeft() == true && minotaurTile != ((i/2)*N+j) && supplies[n].getSupplyTileId() != ((i/2)*N+j) && theseusTile == ((i/2)*N+j) ) 
						strArray[i][j] = "| T |";
									
					else if( tiles[(i/2)*N+j].getLeft() == true && minotaurTile != ((i/2)*N+j) && supplies[n].getSupplyTileId() != ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) 
						strArray[i][j] = "|   |";
									
					else if( tiles[(i/2)*N+j].getLeft() == false && minotaurTile == ((i/2)*N+j) && supplies[n].getSupplyTileId() != ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) 
						strArray[i][j] = "  M |";
									
					else if( tiles[(i/2)*N+j].getLeft() == false && minotaurTile != ((i/2)*N+j) && supplies[n].getSupplyTileId() == ((i/2)*N+j) && theseusTile != ((i/2)*N+j) ) {
						strArray[i][j] = "  s" + (supplies[n].getSupplyId()+1) + "|";
						  break; }
									
					else if( tiles[(i/2)*N+j].getLeft() == false && minotaurTile != ((i/2)*N+j) && supplies[n].getSupplyTileId() != ((i/2)*N+j) && theseusTile == ((i/2)*N+j) ) 
						strArray[i][j] = "  T |";
									
					else //if( tiles[(i/2)*N+j].getLeft() == false && minotaurTile != ((i/2)*N+j) && supplies[n].getSupplyTileId() != ((i/2)*N+j) && theseusTile != ((i/2)*N+j) )
						strArray[i][j] = "    |";
										
								}
							}		
						}
					}	
	
//Here we print the board 
				
   for (int k=2*N; k>=0; k--) {
	for (int t=0; t<N; t++)
		System.out.print(strArray[k][t]);
		System.out.println();
						
       }
	
   return strArray;
						
  }		
}

