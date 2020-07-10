import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Font;

public class Board_Clussier extends JPanel
{
	
	public static int[][] matrix;
	public static int[][] nextPieceBox;
	public int NUM_ROWS;
	public int NUM_COLS;
	public static boolean rowCleared = false;
	public static int score = 0;
	static final int size = 30;
	public Board_Clussier(int r, int c)
	{	
		NUM_ROWS = r;
		NUM_COLS = c;
		matrix = new  int[NUM_ROWS][NUM_COLS];
		nextPieceBox = new  int[10][10];
		setPreferredSize(new Dimension((NUM_ROWS + 9)*size, (NUM_COLS)*size));
	}
	//Puts pieces in board
	public void setValue(int[][] piece, int color)
	{
		
		for(int i = 0; i < 4; i++)
		{
				int tempCol = piece[i][0];
				int tempRow = piece[i][1];
				matrix[tempCol][tempRow] = color;

		}
		
	}
	//Puts next Piece in next piece box
	public void setValueCurrent(int[][] piece, int color)
	{
		
		for(int i = 0; i < 4; i++)
		{
				int tempCol = piece[i][0];
				int tempRow = piece[i][1];
				nextPieceBox[tempCol][tempRow] = color;

		}
		
	}
	//Gets the value at r,c in the matrix
	public int getValue(int r, int c)
	{
		return matrix[r][c];
	}
	//Add scores
	public static void addScore(int bonus)
	{
		if(rowCleared)
		{
			score += 2;
		}
		else
		{
			score += 1;
		}
		
		if(score%50 == 0)
		{
			TetrisGame_Clussier.speedUpGame();
		}
	}
	//Clears row then falls
	public void clearRow()
	{
		int [][] tempMatrix = matrix;
		
		int bonus = 0;
		int pieceCounter = 0;
		boolean shouldShift = false;
		
		for(int y = 0; y < 21 ; y++)
		{
			
			for( int x = 0; x < NUM_ROWS; x++)
			{
				if(matrix[x][y] != 0)
				{
					pieceCounter++;	
				}
			}
			if(pieceCounter == 10)
			{
				shouldShift = true;
				rowCleared = true;
				addScore(0);
				
			}
			
			pieceCounter = 0;
			rowCleared = false;
			if (shouldShift)
			{
				for( int i = y; i > 0; i--)
				{
					for ( int x = 0; x < NUM_ROWS; x++)
					{
						matrix[x][i] = tempMatrix[x][i-1];
					}
				}
				shouldShift = false;
				tempMatrix = matrix;
				bonus++;
			}

		}
			addScore(bonus);	
	}
	

	public void paintComponent(Graphics g)
		{	
			super.paintComponents(g);
			setBackground(Color.BLACK);
			int maxWidth = NUM_COLS * size;
			int maxHeight = NUM_ROWS * size;
			
			//repaint black background
			for(int i = 0; i < NUM_ROWS; i++)
				for( int j = 0; j < NUM_COLS; j++)
				{
					if(matrix[i][j] == 0)
					{
						g.setColor(Color.BLACK);
						g.fillRect(i*size, j*size, size, size);
					}
				}
			//checks and colors in non zero entities in next Piece box
			for(int i = 0; i < 10; i++)
				for( int j = 0; j < 10; j++)
				{
				
					if(nextPieceBox[i][j] == 1)						
					{
						g.setColor(Color.ORANGE);
						g.fillRect((i + 10)*size, (j + 9)*size, size, size);
					
					}
					else if(nextPieceBox[i][j] == 2)					
					{
						g.setColor(Color.YELLOW);
						g.fillRect((i + 10)*size, (j + 9)*size, size, size);					
					}
					else if(nextPieceBox[i][j] == 3)					
					{
						g.setColor(Color.CYAN);
						g.fillRect((i + 10)*size, (j + 9)*size, size, size);					
					}
					else if(nextPieceBox[i][j] == 4)					
					{
						g.setColor(Color.PINK);
						g.fillRect((i + 10)*size, (j + 9)*size, size, size);					
					}
					else if(nextPieceBox[i][j] == 5)					
					{
						g.setColor(Color.MAGENTA);
						g.fillRect((i + 10)*size, (j + 9)*size, size, size);					
					}
					else if(nextPieceBox[i][j] == 6)					
					{
						g.setColor(Color.blue);
						g.fillRect((i + 10)*size, (j + 9)*size, size, size);					
					}
					else if(nextPieceBox[i][j] == 7)					
					{
						g.setColor(Color.RED);
						g.fillRect((i + 10)*size, (j + 9)*size, size, size);					
					}
					
				}
			//checks and colors in non zero entities
			for(int i = 0; i < NUM_ROWS; i++)
				for( int j = 0; j < NUM_COLS; j++)
				{

					if(matrix[i][j] == 1)						
					{
						g.setColor(Color.ORANGE);
						g.fillRect(i*size, j*size, size, size);
					
					}
					else if(matrix[i][j] == 2)					
					{
						g.setColor(Color.YELLOW);
						g.fillRect(i*size, j*size, size, size);
					
					}
					else if(matrix[i][j] == 3)					
					{
						g.setColor(Color.CYAN);
						g.fillRect(i*size, j*size, size, size);
					
					}
					else if(matrix[i][j] == 4)					
					{
						g.setColor(Color.PINK);
						g.fillRect(i*size, j*size, size, size);
					
					}
					else if(matrix[i][j] == 5)					
					{
						g.setColor(Color.MAGENTA);
						g.fillRect(i*size, j*size, size, size);
					
					}
					else if(matrix[i][j] == 6)					
					{
						g.setColor(Color.blue);
						g.fillRect(i*size, j*size, size, size);
					
					}
					else if(matrix[i][j] == 7)					
					{
						g.setColor(Color.RED);
						g.fillRect(i*size, j*size, size, size);
					
					}
					
				}
			
			//draw grids
			g.setColor(Color.green);
			g.drawRect(0, 0, maxHeight, maxWidth);
			for(int x = 0; x < maxHeight; x = x + size)
				for(int y = 0; y < maxWidth; y = y + size)
				{
					g.drawRect(x, y, size, size);
				}
			
			/*MENUE*/
			
			//ScoreBoard
			g.setColor(Color.GREEN);
			Font SCOREfont = new Font("Verdana", Font.PLAIN, 38 );
			g.setFont(SCOREfont);
			String scoreString = score + " ";
			g.drawString("Score: " , (maxHeight + 2*size +15), maxWidth/8);
			g.drawString(scoreString, (maxHeight + 3*size + 5), maxWidth/8+2*size);
			
			//NextPiece
			g.setColor(Color.GREEN);
			g.setFont(SCOREfont);
			g.drawString("Next Piece: " , (maxHeight + size), maxWidth/8+5*size);
			//"Game Over" screen
			if(Tetris_Clussier.gameOver)
			{
				for(int i = 0; i < NUM_ROWS; i++)
					for( int j = 0; j < NUM_COLS; j++)
					{
							g.setColor(Color.BLACK);
							g.fillRect(i*size, j*size, size, size);
					}
				//prints "Game Over"
				g.setColor(Color.RED);
				Font GAMEOVERfont = new Font("Verdana", Font.PLAIN, 42 );
				g.setFont(GAMEOVERfont);
				g.drawString("Game Over", (maxHeight*7/44), maxWidth/2);
			}
		}
}
