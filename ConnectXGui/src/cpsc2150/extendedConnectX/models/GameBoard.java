package cpsc2150.extendedConnectX.models;

/**
 * A ConnectX game board with a fast implementation;
 * @invariants [Board has numRows rows and numColumns columns] AND
 *             [No space between two occupied cells of the same column] AND
 *             3 <= numRows <= 100 AND
 *             3 <= numColumns <= 100
 *             3 <= numToWin <= 25 AND
 *             numToWin < numRows AND numToWin < numColumns
 * @correspondences rows = numRows AND
 *                  columns = numColumns AND
 *                  win = numToWin AND
 *                  grid = board
 */
public class GameBoard extends AbsGameBoard implements IGameBoard {
    private char[][] board;
    private int numRows;
    private int numColumns;
    private int numToWin;

    /**
     * Constructs instance of GameBoard object with a numRows x numColumns empty board
     * @pre 3 <= rows <= 100 AND
     *      3 <= columns <= 100 AND
     *      3 <= numToWin <= 25 AND
     *      numToWin < columns AND numToWin < rows
     * @post [Object created with empty board[numRows][numColumns]] AND
     *       numRows = rows AND
     *       numColumns = columns AND
     *       this.numToWin = numToWin
     * @param rows number of rows the game board will have
     * @param columns number of columns the game board will have
     * @param numToWin number of pieces in a row needed to win
     */
    public GameBoard(int rows, int columns, int numToWin)
    {
        numRows = rows;
        numColumns = columns;
        this.numToWin = numToWin;
        board = new char[numRows][numColumns];
        for(int row = 0; row < numRows; row++)
        {
            for(int column = 0; column < numColumns; column++)
            {
                board[row][column] = ' ';
            }
        }
    }


    public void placeToken(char p, int c)
    {
        for(int row = 0; row < numRows; row++)
        {
            if(board[row][c] == ' ')
            {
                board[row][c] = p;
                return;
            }
        }
    }
    @Override
    //Overridden because default must make many BoardPosition objects
    public boolean checkTie()
    {
        for(int column = 0; column < getNumColumns(); column++)
        {
            if(board[numRows - 1][column] == ' ')
            {
                return false;
            }
        }
        return true;
    }

    @Override
    //Overridden because default must make many BoardPosition objects
    public boolean checkHorizWin(BoardPosition pos, char p)
    {
        int inRow = 1;
        int shift = 1;
        while(pos.getColumn() + shift < numColumns && board[pos.getRow()][pos.getColumn() + shift] == p)
        {
            inRow++;
            shift++;
        }
        shift = 1;
        while(pos.getColumn() - shift >= 0 && board[pos.getRow()][pos.getColumn() - shift] == p)
        {
            inRow++;
            shift++;
        }
        return inRow >= numToWin;
    }

    @Override
    //Overridden because default must make many BoardPosition objects
    public boolean checkVertWin(BoardPosition pos, char p)
    {
        int inRow = 1;
        int shift = 1;
        while(pos.getRow() + shift < numRows && board[pos.getRow() + shift][pos.getColumn()] == p)
        {
            inRow++;
            shift++;
        }
        shift = 1;
        while(pos.getRow() - shift >= 0 && board[pos.getRow() - shift][pos.getColumn()] == p)
        {
            inRow++;
            shift++;
        }
        return inRow >= numToWin;
    }

    @Override
    //Overridden because default must make many BoardPosition objects
    public boolean checkDiagWin(BoardPosition pos, char p)
    {
        int inRow = 1;
        int shift = 1;
        while((pos.getRow() + shift < numRows && pos.getColumn() + shift < numColumns)
                && board[pos.getRow() + shift][pos.getColumn() + shift] == p)
        {
            inRow++;
            shift++;
        }
        shift = 1;
        while((pos.getRow() - shift >= 0 && pos.getColumn() - shift >= 0)
                && board[pos.getRow() - shift][pos.getColumn() - shift] == p)
        {
            inRow++;
            shift++;
        }
        if(inRow >= numToWin)
        {
            return true;
        }
        inRow = 1;
        shift = 1;
        while((pos.getRow() + shift < numRows && pos.getColumn() - shift >= 0)
                && board[pos.getRow() + shift][pos.getColumn() - shift] == p)
        {
            inRow++;
            shift++;
        }
        shift = 1;
        while((pos.getRow() - shift >= 0 && pos.getColumn() + shift < numColumns)
                && board[pos.getRow() - shift][pos.getColumn() + shift] == p)
        {
            inRow++;
            shift++;
        }
        return inRow >= numToWin;
    }


    public char whatsAtPos(BoardPosition pos)
    {
        return board[pos.getRow()][pos.getColumn()];
    }

    public int getNumRows()
    {
        return numRows;
    }

    public int getNumColumns()
    {
        return numColumns;
    }

    public int getNumToWin()
    {
        return numToWin;
    }
}
