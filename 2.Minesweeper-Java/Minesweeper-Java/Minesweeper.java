import java.util.Scanner;

public class Minesweeper {

	private static MineField field;
	private static Ranking rank;	
	
	public static void main(String[] args) {
		rank=new Ranking();
		mainMessage();
		while(gameContinue());
		System.out.println("\nThank you for playing :) Have a nice day!");
	}	
	
	
	/**
	 * game starts. receive input from command line and execute the command.
	 * @return boolean, true if the game continues, or false.
	*/	
	@SuppressWarnings("resource")
	private static boolean gameContinue() {
		field = new MineField();
		int result = 0;
		Scanner in = new Scanner(System.in);
		
		while (true) {

			field.show();
			System.out.print("\nPlease enter your move(row col): ");
			
			String input = in.nextLine();

			if (input.equals("top")) {
				rank.show();
				continue;
			}
			else if (input.equals("restart")) {
				rank.recordName(result);
				return true;
			}
			else if (input.equals("exit")) {
				rank.recordName(result);
				in.close();
				return false;
			}else if (field.legalMoveString(input)) {
				result++;
				continue;
			}
			
			if(endGame(field, result)){
				return true;
			}
		}
	}
	
	/**
	 * check if the game ends.
	 * @param MineField class.
	 * @param int result.
	 * @return boolean. true if the game ends.
	*/	
	private static boolean endGame(MineField field, int result){
		if (result == 35){
			System.out.println("Congratulations you WON the game!");
			rank.recordName(35);
			return true;
		}else if (field.getBoom()){
			System.out.println("\nBooooooooooooooooooooooooooooom!You stepped on a mine!You survived " + result + " turns");
			rank.recordName(result);
			return true;
		}
		return false;
	}
	
	
	/**
	 * print out main messages.
	*/	
	private static void mainMessage(){
		System.out.println("Welcome to Minesweeper!");
		System.out.println("To play just input some coordinates and try not to step ont mine :)");
		System.out.println("Usefull commands:");
		System.out.println("restart- Starts a new game.");
		System.out.println("exit- Quits the game.");
		System.out.println("top- Reveals the top scoreboard.");
		System.out.println("Have Fun !");
	}
}
