import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class TetrisGame_Clussier extends JFrame
{	
	public static Board_Clussier myBoard;
	public static Tetris_Clussier tetrisPiece;
	public static Tetris_Clussier nextPiece;
	
	private static int timeStep;
	private static int normalTimeStep = 500;
	private static int speedUpTimeStep = 5;
	
	public TetrisGame_Clussier() throws InterruptedException
	{	
		super("Tetris_Clussier");

			myBoard = new Board_Clussier(10, 21);
			tetrisPiece = new Tetris_Clussier(0 ,4 ,myBoard);
			
			setLayout(new BorderLayout());
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(true);
			add(myBoard, BorderLayout.CENTER);
			setBackground(Color.BLACK);
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
			
			keyListener();
			
			timeStep = normalTimeStep;
			Thread.sleep(timeStep);
			
			runGame();
			
			myBoard.repaint();
	
	}
	
	public void keyListener()
	{
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e) 
			{
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_LEFT:
						 tetrisPiece.dir = 3; //need better object name
						 if(Tetris_Clussier.canMoveL && tetrisPiece.checkCollisions())
						 tetrisPiece.move(3); 
						 myBoard.repaint();
						 break;
					
					case KeyEvent.VK_RIGHT:
						 tetrisPiece.dir = 1;
						 if(Tetris_Clussier.canMoveR && tetrisPiece.checkCollisions())
						 tetrisPiece.move(1);
						 myBoard.repaint();
						 break; 

					case KeyEvent.VK_UP:				//rotate
						 if(tetrisPiece.checkRotate(0))
						 tetrisPiece.rotateClockwise();
						 myBoard.repaint();
						 break;
						 
					case KeyEvent.VK_DOWN:
						 if(tetrisPiece.checkCollisions())
						 timeStep = speedUpTimeStep;
						 myBoard.repaint();
						 break;
				}
			}
		});
	}

	public static void speedUpGame()
	{
		if(speedUpTimeStep > 300)
		speedUpTimeStep -= 25;
	}
	public void runGame() throws InterruptedException
	{	
		while (Tetris_Clussier.gameOver == false)
		{			
			if(tetrisPiece.checkCollisions() == false)
			{	
				//
				myBoard.setValue(Tetris_Clussier.myPiece, Tetris_Clussier.color);
				myBoard.clearRow();
				myBoard.repaint();
				
				int[][] tempPiece = tetrisPiece.nextPiece;
				int tempColor = tetrisPiece.nextColor;
				
				myBoard.setValueCurrent(Tetris_Clussier.nextPiece, 0);
				Tetris_Clussier tetrisPiece = new Tetris_Clussier(0 ,4 ,myBoard);
				myBoard.setValue(Tetris_Clussier.myPiece, 0);
				tetrisPiece.myPiece = tempPiece;
				tetrisPiece.color = tempColor;
				myBoard.setValue(Tetris_Clussier.myPiece, Tetris_Clussier.color);

				myBoard.setValueCurrent(Tetris_Clussier.nextPiece, Tetris_Clussier.nextColor);
				
				myBoard.repaint();
				
				timeStep = normalTimeStep;
				Thread.sleep(timeStep);
			}

			tetrisPiece.checkCollisions();
			tetrisPiece.moveDown();
			myBoard.repaint();
			Thread.sleep(timeStep);
		}	
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		new TetrisGame_Clussier();
	}	
}
