package solution;
//formated with APPSTATE formatter

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//removed unused import -- jwalker

/**
 * A maze game.
 * 
 * @author Dillingham, Jace
 * @version August 23, 2016
 *
 */
public class MazeGame
{
    //moved fields to the beginning
    /**
     * The current location of the player vertically.
     */
    // DONE: add field here.
    private int userRow = 0;
    /**
     * The current location of the player horizontally.
     */
    // DONE: add field here.
    private int userCol = 0;
    /**
     * The scanner from which each move is read.
     */
    // DONE: add field here.
    private Scanner moveScanner;
    /**
     * The row and column of the goal.
     */
    // DONE: add fields here.
    private int exitCol;
    private int exitRow;

    /**
     * The row and column of the start.
     */
    private int startRow;
    private int startCol;
    /**
     * The size of each side of the game map.
     */

    private static final int HEIGHT = 19;// reordered keywords because Java
                                         // cares
    private static final int WIDTH = 39;

    /**
     * The game map, as a 2D array of ints.
     */
    // used bools instead of ints, because I was in a hurry
    private boolean[][] blocked;
    // made an array to hold bools for wheter or not I had visited a space
    // before.
    private boolean[][] crumbs;

    //moved constructors to beginning per style recommendation
    /**
     * Constructor initializes the maze with the data in 'mazeFile'.
     * 
     * @param mazeFile
     *            the input file for the maze
     */
    public MazeGame(String mazeFile)
    {
        loadMaze(mazeFile);
        moveScanner = new Scanner(System.in);
        initCrumbs();// added a call to initialize the crumbs field
    }

    /**
     * Constructor initializes the maze with the 'mazeFile' and the move scanner
     * with 'moveScanner'.
     * 
     * @param mazeFile
     *            the input file for the maze
     * @param moveScanner
     *            the scanner object from which to read user moves
     */
    public MazeGame(String mazeFile, Scanner moveScanner)
    {
        loadMaze(mazeFile);
        this.moveScanner = moveScanner;
        initCrumbs();// added a call to initialize the crumbs field
    }
    
    /**
     * @return the moveScanner
     */
    public Scanner getMoveScanner()
    {
        return moveScanner;
    }

    /**
     * @param moveScanner
     *            the moveScanner to set
     */
    public void setMoveScanner(Scanner moveScanner)
    {
        this.moveScanner = moveScanner;
    }

    /**
     * @param userRow
     *            the userRow to set
     */
    public void setUserRow(int userRow)
    {
        this.userRow = userRow;
    }

    /**
     * @param userCol
     *            the userCol to set
     */
    public void setUserCol(int userCol)
    {
        this.userCol = userCol;
    }

    public int getUserCol()
    {
        return this.userCol;
    }

    public int getUserRow()
    {
        return this.userRow;
    }

    

    /**
     * initializes the crumbs array - you have visited no spaces yet.
     */
    public void initCrumbs()
    {
        crumbs = new boolean[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int c = 0; c < WIDTH; c++)
            {
                crumbs[i][c] = false;
            }
        }
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
            //instead of "return null;" returning empty array, per style recommendation
            return new boolean[HEIGHT][WIDTH];
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
     * Function loads the data from the maze file and creates the 'blocked' 2D
     * array.
     * 
     * @param mazeFile
     *            the input maze file.
     */
    private void loadMaze(String mazeFile)
    {
        blocked = new boolean[HEIGHT][WIDTH];
        // removed commented code.
        Scanner fileIn;
        try
        {
            fileIn = new Scanner(new File(mazeFile));
            while (fileIn.hasNext())
            {
                fillMazeArray(fileIn);
            }
            fileIn.close();
        }
        catch (FileNotFoundException exception)
        {
            System.err.print("File not found. " + exception);// always log the
                                                             // actual exception
                                                             // so you can find
                                                             // the code that
                                                             // caused it.
            // changed the output stderr
        }

    }

    private void fillMazeArray(Scanner fileIn)
    {
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
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
                    blocked[i][j] = false;
                }
                else if (temp == exit)
                {
                    exitRow = i;
                    exitCol = j;
                    blocked[i][j] = false;

                }
                else if (temp == open)
                {
                    blocked[i][j] = false;
                }
            }
        }
    }

    /**
     * Actually plays the game.
     */
    public void playGame()
    {
        boolean playing = true;
        this.printMaze();
        for (int a = 0; a < crumbs.length; a++)
        {
            for (int b = 0; b < crumbs[a].length; b++)
            {
                crumbs[a][b] = false;
            }
        }
        int moves = 0;
        while (playing)
        {
            String move = "";

            try
            {
                move = this.moveScanner.nextLine();
            }
            catch (java.util.NoSuchElementException e)
            {
                //removed breaks, they're often a bad practice
                //do this in the case we are at the end of the file that is inputting characters
                playing = false;
            }
            if (move.length() > 0 && move.toLowerCase().charAt(0) == 'q')
            {
                playing = false;
            }

            if (makeMove(move))
            {
                moves++;
            }
            this.printMaze();
            playing = playing && !playerAtGoal();//if we're still playing, and the player is at the goal, stop
        }
        if (this.playerAtGoal())
        {
            System.out.println("You won in " + moves + " moves.");
        }
        else
        {
            System.out.println("You lost in " + moves + " moves.");
        }
    }

    /**
     * Checks to see if the player has won the game.
     * 
     * @return true if the player has won.
     */
    // DONE:
    public boolean playerAtGoal()
    {   //simplified boolean statement
        return userRow == exitRow && userCol == exitCol;
        
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
        boolean rval = false;
        crumbs[userRow][userCol] = true;
        if (move.length() < 1)
        {
            return rval;
        }
        
        char mv = move.toLowerCase().charAt(0);
        //moved some logic out to another method for simplicity
        rval = validMoves(rval, mv);
        System.err.println("INVALID MOVE");
        return rval;
    }

    private boolean validMoves(boolean rval, char mv)
    {
      //findbugs does not like comparing to boolean literals
        //so just invert the existing boolean instead
        if (userCol < (WIDTH - 1) && mv == 'r'
            && !blocked[userRow][userCol + 1] )
        {
            userCol = userCol + 1;
            rval = true;
        }
        else if (userCol > 0 && mv == 'l'
            && !blocked[userRow][userCol - 1] )
        {
            userCol = userCol - 1;
            rval =  true;
        }
        else if (userRow > 0 && mv == 'u'
            && !blocked[userRow - 1][userCol])
        {
            crumbs[userRow][userCol] = true;
            userRow = userRow - 1;
            rval = true;;
        }
        else if (userRow < (HEIGHT - 1) && mv == 'd'
            && !blocked[userRow + 1][userCol] )
        {
            userRow = userRow + 1;
            rval = true;
        }
        return rval;
    }

    /**
     * Prints the map of the maze.
     */
    public void printMaze()
    {
        System.out.println("*---------------------------------------*");
        for (int r = 0; r < blocked.length; r++)
        {
            char out = '|';
            System.out.print(out);
            for (int c = 0; c < blocked[r].length; c++)
            {
                //moved the code for getoutputchar to a separate method, per findbugs complexity rules
                out = getOutputChar(r, c);
                System.out.print(out);
            }
            System.out.println("|");
        }
        
        
        System.out.println("*---------------------------------------*");
    }

    private char getOutputChar(int r, int c)
    {
        char out;
        if (r == this.getUserRow() && c == this.getUserCol())
        {
            out = '@';
        }
        else if (r == this.startRow && c == this.startCol)
        {
            out = 'S';
        }
        else if (r == this.exitRow && c == this.exitCol)
        {
            out = 'G';
        }
        else if (blocked[r][c])
        {
            out = 'X';
        }
        else if (crumbs[r][c])
        {
            out = '.';
        }
        else
        {
            out = ' ';
        }
        return out;
    }

    /**
     * Creates a new game, using a command line argument file name, if one is
     * provided.
     * 
     * @param args
     *            the command line arguments
     */

    public static void main(String[] args)
    {
        // removed commented code

        String mapFile = args[0];
        Scanner scan = new Scanner(System.in);
        MazeGame game = new MazeGame(mapFile, scan);

        game.playGame();
    }

}
