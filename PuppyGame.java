package javatraining;

import java.util.Random;
import java.util.Scanner;

public class PuppyGame 
{
	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter number of rooms on each floor: ");
	        int noRooms = scanner.nextInt();
	        System.out.print("Enter number of floors: ");
	        int noFloors = scanner.nextInt();

	        // Create a random number generator
	        Random random = new Random();

	        // Initialize the positions of the players and the puppy
	        int[][] building = new int[noFloors][noRooms];
	        int plr1Row = random.nextInt(noFloors);
	        int plr1Col = random.nextInt(noRooms);
	        int plr2Row = random.nextInt(noFloors);
	        int plr2Col = random.nextInt(noRooms);
	        int puppyRow = random.nextInt(noFloors);
	        int puppyCol = random.nextInt(noRooms);

	      //if at initial both player at one position
	        if(plr1Row == plr2Row && plr1Col == plr2Col)
	        	building[plr1Row][plr1Col] = 4;
	        else
	        {	
		        // Mark player 1, player 2 in the building matrix
		        building[plr1Row][plr1Col] = 1;
		        building[plr2Row][plr2Col] = 2;
	        }
	        
	        if((puppyRow==plr1Row && puppyCol==plr1Col) || (puppyRow==plr2Row && puppyCol==plr2Col))
	        {
	        	puppyRow = random.nextInt(noFloors);
		        puppyCol = random.nextInt(noRooms);
	        }
	        else
	        	building[puppyRow][puppyCol] = 3;
	        

	        // Print the initial state of the building
	        printBuilding(building);

	        // Simulate the game
	        int currentPlayer = 1; // Player 1 starts
//	        boolean puppyFound = false;

	        while (true) {
	            int presentRow = (currentPlayer == 1) ? plr1Row : plr2Row;
	            int presentCol = (currentPlayer == 1) ? plr1Col : plr2Col;
	            
	            if(presentRow==0 && presentCol==0)
	            	System.out.println("Player " + currentPlayer +"choose 1: Down, 3: Right");
	            else if(presentRow==0 && presentCol==noRooms-1)
	            	System.out.println("Player " + currentPlayer +"choose 1: Down, 2: Left");
		        else if(presentRow==noFloors-1 && presentCol==0)
		        	System.out.println("Player " + currentPlayer +"choose 0: Up, 3: Right");
			    else if(presentRow==noFloors-1 && presentCol==noRooms-1)
			    	System.out.println("Player " + currentPlayer +"choose 0: Up, 2: Left");
				            	
	            else if(presentRow == 0)
	            	System.out.println("Player " + currentPlayer + ", choose: 1: Down, 2: Left, 3: Right");
	            else if(presentRow == noFloors-1)
	            	System.out.println("Player " + currentPlayer + ", choose: 0: Up, 2: Left, 3: Right");
	            else if(presentCol == 0)
	            	System.out.println("Player " + currentPlayer + ", choose: 0: Up, 1: Down, 3: Right");
	            else if(presentCol == noRooms-1)
	            	System.out.println("Player " + currentPlayer + ", choose: 0: Up, 1:Down, 2: Left");
	            else
	            	System.out.println("Player " + currentPlayer + ", choose 0: Up, 1: Down, 2: Left, 3: Right");
	            int nextMove = scanner.nextInt(); // 0: Up, 1: Down, 2: Left, 3: Right
	            
	        
	            
	            switch (nextMove) {
	                case 0:
	                    if (presentRow > 0) {
	                        presentRow--;
	                    }
	                    break;
	                case 1:
	                    if (presentRow < noFloors - 1) {
	                        presentRow++;
	                    }
	                    break;
	                case 2:
	                    if (presentCol > 0) {
	                        presentCol--;
	                    }
	                    break;
	                case 3:
	                    if (presentCol < noRooms - 1) {
	                        presentCol++;
	                    }
	                    break;
	            }

	            // Update player's position in the building matrix
	            if (currentPlayer == 1) 
	            {
	                
	                if(building[plr1Row][plr1Col] == 4) 
	                {
	                	building[plr2Row][plr2Col] = 2;
	                	plr1Row = presentRow;
	                	plr1Col = presentCol;
	                	building[plr1Row][plr1Col]=1;
	                }
	                else 
	                {
	                	building[plr1Row][plr1Col] = 0;
		                plr1Row = presentRow;
		                plr1Col = presentCol;
	                	if(building[plr1Row][plr1Col] == 2)
	                		building[plr1Row][plr1Col] = 4;
	                	else
	                		building[plr1Row][plr1Col]=1;
	                } 
	            }
	            else 
	            {
	                if(building[plr2Row][plr2Col] == 4) 
	                {
	                	building[plr1Row][plr1Col] = 1;
	                	plr2Row = presentRow;
	                	plr2Col = presentCol;
	                	building[plr2Row][plr2Col]=2;
	                }
	                else
	                {
	                	building[plr2Row][plr2Col] = 0;
		                plr2Row = presentRow;
		                plr2Col = presentCol;
		                if(building[plr2Row][plr2Col] == 1)
		                	building[plr2Row][plr2Col] = 4;
		                else
		                	building[plr2Row][plr2Col]=2;
	                }
	            }
	            
	         //print building after turn of each player
	            printBuilding(building);
	            
	         // check if puppy found
	            if (presentRow == puppyRow && presentCol == puppyCol) {
	                System.out.println("Player " + currentPlayer + " win the game!");
	                break;
	            }

	            // switch turn to other player
	            currentPlayer = (currentPlayer == 1) ? 2 : 1;
	        }

	        scanner.close();
	    }

	    // print 2D-array in building form
	    private static void printBuilding(int[][] building) {
	        System.out.println("Building State:");
	        for (int[] row : building) {
	            for (int room : row) {
	                if (room == 0) {
	                    System.out.print("- ");
	                } else if (room == 1) {
	                    System.out.print("P ");
	                } else if (room == 2) {
	                    System.out.print("Q ");
	                } else if (room == 3) {
	                    System.out.print("@ ");
	                }else if(room == 4)
	                	System.out.print("B ");
	            }
	            System.out.println();
	        }
	        System.out.println();
	    }	
}
