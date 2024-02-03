package game;
import java.util.Scanner;
import java.util.Random;
public class TicTacToe {

	public static void main(String[] args) {
		Random rand = new Random();
		Scanner input = new Scanner(System.in);
		
		int playAgain = 1;
		String playerChar = " ";
		
		String[] rowA = {" "," "," "};
		String[] rowB = {" "," "," "};
		String[] rowC = {" "," "," "};
		boolean empty = true;
		int turn = 0;
		
		String [][] board = {rowA,rowB,rowC};
		

		while(playAgain == 1) {
			
			System.out.print("Welcome to Tic Tac Toe\nPick x or o to begin: ");
			playerChar = input.next().toLowerCase();
			System.out.println(playerChar);
			while(playerChar.charAt(0)!='o'&&playerChar.charAt(0)!='x') {
				System.out.print("Invalid input, please try again.\nPick x or o to begin: ");
				playerChar = input.next().toLowerCase();
			}

			display(board);
			turn=0;
			while(winCondition(board).compareTo("false")==0&&turn<5) {
				System.out.print("\nEnter Column: ");
				int collumn = (Character.getNumericValue(input.next().charAt(0))-10);
				while(collumn<0||collumn>2) {
					System.out.print("Invalid input, enter a letter a through c.\nEnter Column: ");
					collumn = (Character.getNumericValue(input.next().charAt(0))-10);
				}
				System.out.print("Enter Row: ");
				int row = input.nextInt()-1; 
				while(row<0||row>2) {
					System.out.print("Invalid input, enter a number 1 through 3.\nEnter Row: ");
					row = input.nextInt()-1; 
				}

				while(board[row][collumn].compareToIgnoreCase(" ")!=0)	{
					
					System.out.print("Space already taken, please try again.\nEnter Column: ");
					collumn = (Character.getNumericValue(input.next().charAt(0))-10);
					while(collumn<0||collumn>2) {
						System.out.print("Invalid input, enter a letter a through c.\nEnter Column: ");
						collumn = (Character.getNumericValue(input.next().charAt(0))-10);
					}
					System.out.print("Enter Row: ");
					row = input.nextInt()-1; 
					while(row<0||row>2) {
						System.out.print("Invalid input, enter a number 1 through 3.\nEnter Row: ");
						row = input.nextInt()-1; 
					}
				}
				board[row][collumn] = playerChar;;
				while(empty==true&&turn<4&&winCondition(board).compareTo("false")==0) {
					int a =rand.nextInt(3);
					int b =rand.nextInt(3);
					if(board[a][b].compareTo(" ")==0) {
						if(playerChar.compareTo("x")==0)board[a][b]="o";
						if(playerChar.compareTo("o")==0)board[a][b]="x";
						empty=false;
					}
				}
				empty = true;
				display(board);
				turn++;
			}
			
			if(winCondition(board).charAt(0)==playerChar.charAt(0)) {
				System.out.print("\n\nYou Win!!!");
			}
			else if(turn>=5) {
				System.out.print("\n\nIt's a Draw!");
			}
			else if(winCondition(board).charAt(0)!=playerChar.charAt(0)) {
				System.out.print("\n\nYou Lose :(");
			}

			
			for(int i = 0; i <3;i++) {
				for(int j = 0;j<3;j++) {
					board[i][j] = " ";
				}
			}
			
			System.out.print("\nPlay again? 1 for yes 2 for no: ");
			playAgain = input.nextInt();
			while(playAgain <1 || playAgain >2) {
				System.out.print("Invalid input, please try again\nPlay again? 1 for yes 2 for no: ");
				playAgain = input.nextInt();
			}
		}
		
		System.out.println("Thanks for playing!");
		input.close();
	}
	
	public static void display(String[][]board) {
		System.out.print("\n\n\n a b c \n _ _ _");
		for(int i = 0; i <3;i++) {
			System.out.print("\n|");
			for(int j = 0;j<3;j++) {
				System.out.print(board[i][j]+ "|");
			}
			System.out.print(" " + (i+1)+ "\n - - -"	);
		}
		
	}
	
	public static String winCondition(String[][]board) {
		
		String win = "false";
		String line = "";
		String lineDiagLR = "";
		String lineDiagRL = "";
		String lineVert = "";
		
		int counter = 2;
		
		for(int i = 0; i <3;i++) {
			for(int j = 0;j<3;j++) {
				line += board[i][j];
				lineVert += board[j][i];
				if(i==j)lineDiagLR+=board[i][j];
				if(j==counter) {
					lineDiagRL+=board[i][j];
					counter-=1;
				}
			}
			if(line.compareTo("xxx")==0|lineDiagLR.compareTo("xxx")==0|lineDiagRL.compareTo("xxx")==0|lineVert.compareTo("xxx")==0) {
				win="xWinner";
			}
			else if(line.compareTo("ooo")==0|lineDiagLR.compareTo("ooo")==0|lineDiagRL.compareTo("ooo")==0|lineVert.compareTo("ooo")==0) {
				win="oWinner";
			}
			line ="";
			lineVert="";
		}
		lineDiagLR = "";
		lineDiagRL = "";
		return win;
	}
}
