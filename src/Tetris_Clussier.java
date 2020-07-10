public class Tetris_Clussier
{
	private static Board_Clussier myBoard;
	
	private int posR = 0;//white space
	private int posC = 0;
	
	public static int[][] myPiece;
	public static int[][] nextPiece;
	
	public static int dir = 2;
	private static int rotateDir = 0;
	public static int color;
	public static int nextColor;
	public static int boom;
	
	public static boolean canMoveL = true;
	public static boolean canMoveR = true;
	private static boolean canRotate = true;
	public static boolean gameOver = false;
	
	
	
	public Tetris_Clussier(int r, int c, Board_Clussier  b)
	{	
		myBoard = b;
		
		color = (int) (Math.random()* 7 +1);
		nextColor = (int) (Math.random()* 7 +1);
		
		posC = c;
		posR = r;
		
		myPiece = chooseRandomPiece(color);
		nextPiece = chooseRandomPiece(nextColor);
		
		for(int i = 0; i < 4; i++)
		{
			myPiece[i][0] = myPiece[i][0] + posC;
			myPiece[i][1] = myPiece[i][1] + posR;
		}
			
		if(checkCollisions() == false)
		gameOver = true;
			
		if (gameOver != true)
		{
			myBoard.setValue(myPiece, color);
		}			
		
		for(int i = 0; i < 4; i++)
		{
			nextPiece[i][0] = nextPiece[i][0] + 4;
			nextPiece[i][1] = nextPiece[i][1] + posR;
		}	
		myBoard.setValueCurrent(nextPiece, nextColor);
	}

	public static int[][] chooseRandomPiece(int color)
	{
		
		switch(color)
		{
		   //L-piece
		   case 1: int[][] pieceL = {{0, 0}, {-1, 1}, {1, 0}, { - 1, 0}};
				return pieceL;
		   //square
	       case 2: int[][] pieceSq = {{0,0} , {1,0}, {1,1} ,{0,1}};
	       		return pieceSq;
	       // I-piece
	       case 3: int[][] pieceI = {{0,0} , {-1,0}, {1,0} ,{2,0}};
	       		return pieceI;
	       //Seven-piece
	       case 4: int[][] pieceSe = {{0,0} , {-1,0}, {1,0} ,{1,1}};
      			return pieceSe;
	       //T-piece
	       case 5: int[][] pieceT = {{0,0} , {1,0}, {-1,0} ,{0,1}};
      			return pieceT;	
	       //Z-piece
	       case 6: int[][] pieceZ = {{0,0} , {-1,0}, {1,1} ,{0,1}};
      			return pieceZ;
	       //S-piece 
	       case 7: int[][]  pieceS = {{0,0} , {1,0}, {-1,1} ,{0,1}};
      			return pieceS;
		}		
		
		return null;
	}
	
	public boolean checkCollisions()
	{
		myBoard.setValue(myPiece, 0);
		int newC = 0;
		int newR = 0;
		canMoveL =true;
		canMoveR =true;
		//finds next spot based on direction
		switch (dir)
		{
		
			//left
			case 3:	
					myBoard.setValue(myPiece, 0);
					for(int i = 0; i < 4; i++)
					{
						newC = myPiece[i][0] - 1;
						newR = myPiece[i][1];
						if((newC < 0) || (newC >= myBoard.NUM_ROWS))
						{
							canMoveL = false;
							myBoard.repaint();
						}
						else if(((myBoard.getValue(newC, newR)) != 0))
						{		
							canMoveL = false;
							myBoard.repaint();

						}
					}
					myBoard.repaint();
					break;
			//right
			case 1: 
					myBoard.setValue(myPiece, 0);
					for(int i = 0; i < 4; i++)
					{
							newC = myPiece[i][0] + 1;
							newR = myPiece[i][1];
							if((newC < 0) || (newC >= myBoard.NUM_ROWS))
							{
							canMoveR = false;
							myBoard.repaint();

							}
							else if(((myBoard.getValue(newC, newR)) != 0))
							{		
							canMoveR = false;
							myBoard.repaint();
							}	
						}
					
					break;

		}
		
		//check down
		for(int i = 0; i < 4; i++)
		{
				newR = myPiece[i][1] + 1;
				newC = myPiece[i][0];
				if((newR > myBoard.NUM_COLS -1))
				{
					return false;
				}
				
				if(((myBoard.getValue(newC, newR)) != 0))
				{		
					return false;
				}
		}
		
		return true;
	}
	
	public boolean checkRotate(int dir)
	{	
		myBoard.setValue(myPiece, 0);
		
		int[][] tempPiece = myPiece;
		double[][] newCords = new double[1][2];
		
		int newC = 0;
		int newR = 0;
		int rotX = 0;
		int rotY = 0;
		
		canRotate = true;
		rotateDir = dir;
		switch(rotateDir)//if statement
		{
			//clock-wise
			case 0: rotX = tempPiece[0][0];
					rotY = tempPiece[0][1];
					for(int i = 0; i < 4; i++)
					{		
							newCords = rotPointOff(tempPiece[i][0], tempPiece[i][1], rotX, rotY, 90);
							newC = (int)newCords[0][0];
							newR = (int)newCords[0][1];	
							if((newC < 0) || (newC >= myBoard.NUM_ROWS))
							{
								canRotate = false;
							}
							
							else if((newR > myBoard.NUM_COLS -1) || newR <0)
							{
								canRotate = false;
							}
							else if(((myBoard.getValue(newC, newR)) != 0))
							{		
								canRotate = false;
							}
					}
		}
		return true;
	}
	
	public void move(int d)
	{
		dir = d;
			switch (dir)
			{
				//left
				case 3: if (canMoveL)
						{
							myBoard.setValue(myPiece, 0);
							for(int i = 0; i < 4; i++)
							{
									myPiece[i][0] -= 1;
							}
							myBoard.setValue(myPiece, color);
							myBoard.repaint();
						}
						break;
	
				//right
				case 1: if(canMoveR)
						{
							myBoard.setValue(myPiece, 0);
							for(int i = 0; i < 4; i++)
							{
									myPiece[i][0] += 1;
							}
							myBoard.setValue(myPiece, color);
							myBoard.repaint();
						}
						break;
			}
		
	}
	
	public void moveDown()
	{
		myBoard.setValue(myPiece, 0);
		for(int i = 0; i < 4; i++)
		{
				myPiece[i][0] += 0;
				myPiece[i][1] += 1;
		}
		myBoard.setValue(myPiece, color);
		myBoard.repaint();
	}

	public static double[][] rotPoint(int x, int y, int ang)
	{
		double[][] array = new double[1][2];
		array[0][0] = y;
		array[0][1] = -x;
		return array;
	}
	
	public static double[][] rotPointOff(int x, int y, int a, int b, int ang)
	{
		double[][] array = new double[1][2];
		int rotX = x-a;
		int rotY = y-b;
		array = rotPoint(rotX, rotY, ang);
		array[0][0] = array[0][0] + a;
		array[0][1] = array[0][1] + b;
		return array;
	}
	public void rotateClockwise()
	{
		checkRotate(0);
		if (canRotate)
		{
			double[][] newCords = new double[1][2];
			int rotX = myPiece[0][0];
			int rotY = myPiece[0][1];
			for(int i = 0; i < 4; i++)
			{		
					newCords = rotPointOff(myPiece[i][0], myPiece[i][1], rotX, rotY, 90);
					myPiece[i][0] = (int)newCords[0][0];
					myPiece[i][1] = (int)newCords[0][1];	
			}
			myBoard.setValue(myPiece, color);
			myBoard.repaint();
		}
		
	}
}