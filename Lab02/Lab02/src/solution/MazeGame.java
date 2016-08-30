package skeleton;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;

/**
 * A maze game.
 * 
 * @author Dillingham, Jace
 * @version August 23, 2016
 *
 */
public class MazeGame
{
    /**
     * The size of each side of the game map.
     */
    private final static int HEIGHT = 19;
    private final static int WIDTH = 39;

    /**
     * The game map, as a 2D array of ints.
     */
    private boolean[][] blocked;
    
    /**
     * @return the moveScanner
     */
    public Scanner getMoveScanner()
    {
        return moveScanner;
    }

    /**
     * @param moveScanner the moveScanner to set
     */
    public void setMoveScanner(Scanner moveScanner)
    {
        this.moveScanner = moveScanner;
    }

    /**
     * @param userRow the userRow to set
     */
    public void setUserRow(int userRow)
    {
        this.userRow = userRow;
    }

    /**
     * @param userCol the userCol to set
     */
    public void setUserCol(int userCol)
    {
        this.userCol = userCol;
    }
    /**
     * The current location of the player vertically.
     */
    // TODO: add field here.
    private int userRow = 0;
    /**
     * The current location of the player horizontally.
     */
    // TODO: add field here.
    private int userCol = 0;
    /**
     * The scanner from which each move is read.
     */
    // TODO: add field here.
    private Scanner moveScanner;
    /**
     * The row and column of the goal.
     */
    // TODO: add fields here.
    
    /**
     * The row and column of the start.
     */
    // TODO: add fields here.

    /**
     * Constructor initializes the maze with the data in 'mazeFile'.
     * @param mazeFile the input file for the maze
     */
    public MazeGame(String mazeFile)
    {
        loadMaze(mazeFile);
        moveScanner = new Scanner(System.in);
    }

    /**
     * Constructor initializes the maze with the 'mazeFile' and the move 
     * scanner with 'moveScanner'.
     * @param mazeFile the input file for the maze
     * @param moveScanner the scanner object from which to read user moves
     */
    public MazeGame(String mazeFile, Scanner moveScanner)
    {
    	loadMaze(mazeFile);
    	this.moveScanner = moveScanner;
    	
    }

    /**
     * getMaze returns a copy of the current maze for testing purposes.
     * 
     * @return the grid
     */
    public boolean[][] getMaze()
    {
        if (blocked == null)
        {
            return null;
        }
        boolean[][] copy = new boolean[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                copy[i][j] = blocked[i][j];
            }
        }
        return copy;
    }

    /**
     * setMaze sets the current map for testing purposes.
     * 
     * @param maze
     *            another maze.
     */
    public void setMaze(boolean[][] maze)
    {
        this.blocked = maze;
    }
    
    /**
     * Function loads the data from the maze file and creates the 'blocked' 
     * 2D array.
     *  
     * @param mazeFile the input maze file.
     */
    private void loadMaze(String mazeFile)
    {
        blocked = new boolean[19][39];
        File file = new File(mazeFile);
        Scanner fileIn;
        try
        {          
            fileIn = new Scanner(file);
            while (fileIn.hasNext())
            {
                for (int i =0; i < 19; i++)
                {
                    for (int j = 0; j < 39; j++)
                    {
                        blocked[i][j] = fileIn.nextLine();
                    }
                }
            }
            
        }
        catch(FileNotFoundException exception)
        { 
            System.out.print("File not found.");
        }
        
    }

    /**
     * Actually plays the game.
     */
    public void playGame()
    {
        
    }

    /**
     * Checks to see if the player has won the game.
     * 
     * @return true if the player has won.
     */
    // TODO: 
    public boolean playerAtGoal()
    {
        
        return true;
    }

    /**
     * Makes a move based on the String.
     * 
     * @param move
     *            the direction to make a move in.
     * @return whether the move was valid.
     */
    public boolean makeMove(String move)
    {
        // TODO
        return false;
    }

    /**
     * Prints the map of the maze.
     */
    public void printMaze()
    {
        // TODO
    }

    /**
     * Creates a new game, using a command line argument file name, if one is
     * provided.
     * 
     * @param args the command line arguments
     */

    public static void main(String[] args)
    {
        String mapFile = "data/easy.txt";
        Scanner scan = new Scanner(System.in);
        MazeGame game = new MazeGame(mapFile, scan);
        game.playGame();
    }
    
    
}
