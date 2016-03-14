import java.util.Random;

class MineField{

	private boolean[][] mines,visible;
	private boolean boom;
	private final int rowMax = 5;
	private final int colMax = 10;
	
	MineField(){
		
		mines=new boolean[rowMax][colMax];
		visible=new boolean[rowMax][colMax];
		boom=false;
		
		initMap();
		
		int counter2=15;
		int randomRow,randomCol;
		Random RGenerator=new Random();
		
		while(counter2>0){
			
			randomRow=Math.abs(RGenerator.nextInt()%rowMax);
			randomCol=Math.abs(RGenerator.nextInt()%colMax);
			
			if(!mines[randomRow][randomCol]){
				mines[randomRow][randomCol]=true;
				counter2--;
			}
		}
	}	

	/**
	 * make a new 5X10 size mine map.
	 * initiate boolean array to 'false' values.
	*/
	private void initMap(){
		for(int row=0;row<rowMax;row++){
			for(int col=0;col<colMax;col++){
				mines[row][col]=false;
				visible[row][col]=false;
			}
		}
	}
	
	/**
	 * Check if the area in row and col coordinates is mine.
	 * If it is mine, the area become visible and boolean boom become 'true'. 
	*/
	private void boom() {
		for(int row=0;row<rowMax;row++){
			for(int col=0;col<colMax;col++){
				if(mines[row][col]){
					visible[row][col]=true;
				}
			}
		}
		boom=true;
		show();
	}

	/**
	 * receive row and col values and check state of the area.
	 * @param row value.
	 * @param col value.
	 * @return '*' if the area is mine, if else return other chars. 
	*/	
	private char drawChar(int row, int col) {
		int count=0;
		if(visible[row][col]){
			if(mines[row][col]) return '*';
			count = countMines(row, col);
		}
		else{
			if(boom){
				return '-';
			}
			else{
				return '?';
			}
		}
		count += 48;
		return (char) count;
	}
	
	/**
	 * count the number of mines around the specific area.
	 * @param row value.
	 * @param col value.
	 * @return int, the number of mines. 
	*/	
	private int countMines(int row, int col){
		int count = 0;
		for(int irow=row-1;irow<=row+1;irow++){
			for(int icol=col-1;icol<=col+1;icol++){
				if(icol>=0&&icol<colMax&&irow>=0&&irow<rowMax){
					if(mines[irow][icol]) count++;
				}
			}
		}
		return count;
	}
	
	public boolean getBoom(){
		
		return boom;
	}

	/**
	 * check if the input is legal form.
	 * @param string input.
	 * @return boolean. 
	*/	
	public boolean legalMoveString(String input) {
		String[] separated=input.split(" ");
		int row;
		int col;

		try{
			row=Integer.parseInt(separated[0]);
			col=Integer.parseInt(separated[1]);
			if(row<0||col<0||row>=rowMax||col>=colMax){
				throw new java.io.IOException();
			}
		}
		catch(Exception e){
			System.out.println("\nInvalid Input!");
			return false;
		}
		
		if(legalMoveValue(row,col)){
			return true;
		}
		else{
			return false;
		}
	}

	
	/**
	 * check if the specific area is already stepped, and if the area is mine.
	 * @param row value.
	 * @param col value.
	 * @return boolean.
	*/	
	private boolean legalMoveValue(int row, int col) {
		
		if(visible[row][col]){
			System.out.println("You stepped in already revealed area!");
			return false;
		}
		else{
			visible[row][col]=true;
		}
		
		if(mines[row][col]){
			boom();
			return false;
		}
		
		return true;
	}
	
	/**
	 * print out the mine map.
	*/	
	public void show() {
		System.out.println("\n    0 1 2 3 4 5 6 7 8 9 ");
		System.out.println("   ---------------------");
		
		for(int row=0;row<rowMax;row++){
			System.out.print(row+" |");
			for(int col=0;col<colMax;col++){
				System.out.print(" "+drawChar(row,col));				
			}
			System.out.println(" |");
		}
		System.out.println("   ---------------------");
	}
	
}
