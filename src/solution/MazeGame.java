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
    private int exitCol;
    private int exitRow;
    
    /**
     * The row and column of the start.
     */
    private int startRow;
    private int startCol;

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
        //File file = new File(mazeFile);
        Scanner fileIn;
        try
        {          
            fileIn = new Scanner(new File(mazeFile));
            while (fileIn.hasNext())
            {                
                for (int i =0; i < 19; i++)
                {
                    for (int j = 0; j < 39; j++)
                    {
                        char temp = fileIn.next(".").charAt(0);
                        char wall = '1';
                        char start = 'S';
                        char exit = 'G';
                        char open = ' ';
                        
                        if (temp == wall)
                        {
                            blocked[i][j] = true;
                        }
                        else if (temp == start) 
                        {
                            startRow = i;
                            startCol = j;
                        }
                        else if (temp == exit)
                        {
                            exitRow = i;
                            exitCol = j;
                        }
                        else if (temp = open)
                        {
                            blocked [i][j] = false;
                        }
                    }
                }
            }
            fileIn.close();
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
        loadMaze(mazeFile);
        makeMove(move);
        playerAtGoal();
    }

    /**
     * Checks to see if the player has won the game.
     * 
     * @return true if the player has won.
     */
    // TODO: 
    public boolean playerAtGoal()
    {
        if (userRow == exitRow && userCol == exitCol)
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }

    /**
     * Makes a move based on the String.
     * 
     * @param move the direction to make a move in.
     * @return whether the move was valid.
     */
    public boolean makeMove(String move)
    {
        if (move.toLowerCase().charAt(0) = "r" && blocked [userRow][userCol + 1] == false && userCol < 39)
        {
            userCol = userCol + 1;
            return false;
        }
        else if (move.toLowerCase().charAt(0) = "l" && blocked [userRow][userCol - 1] == false && userCol > 0)
        {
            userCol = userCol - 1;
            return false;
        }
        else if (move.toLowerCase().charAt(0) = "u" && blocked [userRow + 1][userCol] == false && userRow < 0)
        {
            userRow = userRow - 1;
        }
        else if (move.toLowerCase().charAt(0) = "d" && blocked [userRow + 1][userCol] == false && userRow < 19)
        {
            userRow = userRow + 1;
        }
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
