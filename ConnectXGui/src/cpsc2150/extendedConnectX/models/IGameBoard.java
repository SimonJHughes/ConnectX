package cpsc2150.extendedConnectX.models;

/**
 * A row x column game board used to play ConnectX where 0,0 is the bottom left.
 * Initialization Ensures: grid is a rows x columns empty game board
 * @defines rows = # of rows
 *          columns = # of columns
 *          win = number in a row to win
 *          self = grid
 * @constraints [Board is rows by columns] AND
 *              [no space between two occupied cells of the same column] AND
 *              3 <= rows <= 100 AND
 *              3 <= columns <= 100
 *              3 <= win <= 25 AND
 *              win < rows AND win < columns
 */
public interface IGameBoard {
    /**
     * Checks if specified column is available
     * @pre 0 <= c < columns
     * @post self = #self AND [checkIfFree() = true if column c is not full, false otherwise]
     * @param c column to check
     * @return True if column is not full, false otherwise
     */
    default public boolean checkIfFree(int c)
    {
        return whatsAtPos(new BoardPosition(getNumRows() - 1, c)) == ' ';
    }
    /**
     * Places token in specified column
     * @pre board[rows][c] is empty AND 0 <= c < columns
     * @post [self = #self except the lowest row in column c is filled by p]
     * @param p Player who is placing token
     * @param c Column to place token
     */
    public void placeToken(char p, int c);
    /**
     * Checks if a player has won the game
     * @pre [c is last column played]
     * @post self = #self AND [checkForWin() = true if a player has win tokens in a row, false otherwise]
     * @param c Column to check for win
     * @return If player has won
     */
    default public boolean checkForWin(int c)
    {
        int row;
        for(row = getNumRows() - 1; row >= 0; row--)
        {
            if(whatsAtPos(new BoardPosition(row, c)) != ' ')
            {
                break;
            }
        }
        BoardPosition testPosition = new BoardPosition(row, c);
        if(checkHorizWin(testPosition, whatsAtPos(testPosition)))
        {
            return true;
        }
        else if(checkVertWin(testPosition, whatsAtPos(testPosition)))
        {
            return true;
        }
        else if(checkDiagWin(testPosition, whatsAtPos(testPosition)))
        {
            return true;
        }
        return false;
    }
    /**
     * Checks if the game has resulted in a tie
     * @post self = #self AND [checkTie() = true if board has no more empty cells, false otherwise]
     * @return If board is full
     */
    default public boolean checkTie()
    {
        int row = getNumRows() - 1;
        for(int column = 0; column < getNumColumns(); column++)
        {
            if(whatsAtPos(new BoardPosition(row, column)) == ' ')
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if player has 5 tokens horizontally
     * @pre 0 <= pos.getRow() < rows AND 0 <= pos.getColumn() < columns
     *      AND [pos is last position played]
     * @post self = #self AND [checkVertWin() = true if player has win tokens vertically, false otherwise]
     * @param pos Position to check from
     * @param p Player who is being checked
     * @return If player has won vertically
     */
    default public boolean checkVertWin(BoardPosition pos, char p)
    {
        int inRow = 1;
        int shift = 1;
        while(pos.getRow() + shift < getNumRows() &&
                isPlayerAtPos(new BoardPosition(pos.getRow() + shift, pos.getColumn()), p))
        {
            inRow++;
            shift++;
        }
        shift = 1;
        while(pos.getRow() - shift >= 0 &&
                isPlayerAtPos(new BoardPosition(pos.getRow() - shift, pos.getColumn()), p))
        {
            inRow++;
            shift++;
        }
        return inRow >= getNumToWin();
    }
    /**
     * Checks if player has win tokens horizontally
     * @pre 0 <= pos.getRow() < rows AND 0 <= pos.getColumn() < columns
     *      AND [pos is last position played]
     * @post self = #self AND [checkHorizWin() = true if player has win tokens horizontally, false otherwise]
     * @param pos Position to check from
     * @param p Player who is being checked
     * @return If player has won horizontally
     */
    default public boolean checkHorizWin(BoardPosition pos, char p)
    {
        int inRow = 1;
        int shift = 1;
        while(pos.getColumn() + shift < getNumColumns() &&
                isPlayerAtPos(new BoardPosition(pos.getRow(), pos.getColumn() + shift), p))
        {
            inRow++;
            shift++;
        }
        shift = 1;
        while(pos.getColumn() - shift >= 0 &&
                isPlayerAtPos(new BoardPosition(pos.getRow(), pos.getColumn() - shift), p))
        {
            inRow++;
            shift++;
        }
        return inRow >= getNumToWin();
    }
    /**
     * Checks if player has win tokens diagonally
     * @pre 0 <= pos.getRow() < rows AND 0 <= pos.getColumn() < columns
     *      AND [pos is last position played]
     * @post self = #self AND [checkDiagWin() = true if player has win tokens diagonally, false otherwise]
     * @param pos Position to check from
     * @param p Player who is being checked
     * @return If player has won diagonally
     */
    default public boolean checkDiagWin(BoardPosition pos, char p)
    {
        int inRow = 1;
        int shift = 1;
        while((pos.getRow() + shift < getNumRows() && pos.getColumn() + shift < getNumColumns())
                && isPlayerAtPos(new BoardPosition(pos.getRow() + shift, pos.getColumn() + shift), p))
        {
            inRow++;
            shift++;
        }
        shift = 1;
        while((pos.getRow() - shift >= 0 && pos.getColumn() - shift >= 0)
                && isPlayerAtPos(new BoardPosition(pos.getRow() - shift, pos.getColumn() - shift), p))
        {
            inRow++;
            shift++;
        }
        if(inRow >= getNumToWin())
        {
            return true;
        }
        inRow = 1;
        shift = 1;
        while((pos.getRow() + shift < getNumRows() && pos.getColumn() - shift >= 0)
                && isPlayerAtPos(new BoardPosition(pos.getRow() + shift, pos.getColumn() - shift), p))
        {
            inRow++;
            shift++;
        }
        shift = 1;
        while((pos.getRow() - shift >= 0 && pos.getColumn() + shift < getNumColumns())
                && isPlayerAtPos(new BoardPosition(pos.getRow() - shift, pos.getColumn() + shift), p))
        {
            inRow++;
            shift++;
        }
        return inRow >= getNumToWin();
    }
    /**
     * Checks if there is a token at a position and returns whose it is
     * @pre 0 <= pos.getRow() < rows AND 0 <= pos.getColumn() < columns
     * @post self = #self AND [whatsAtPos() = character at pos]
     * @param pos BoardPosition to check
     * @return Character at position pos
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * Checks if player is at BoardPosition pos
     * @pre 0 <= pos.getRow() < rows AND 0 <= pos.getColumn() < columns
     * @post self = #self AND [isPlayerAtPos() = true if player is at BoardPosition pos, false otherwise]
     * @param pos Position to check
     * @param p Player to check
     * @return If player is at pos
     */
    default public  boolean isPlayerAtPos(BoardPosition pos, char p)
    {
        return whatsAtPos(pos) == p;
    }

    /**
     * Getter method for number of rows
     * @pre none
     * @post self = #self AND [getNumRows() = rows]
     * @return rows
     */
    public int getNumRows();

    /**
     * Getter method for number of columns
     * @pre none
     * @post self = #self AND [getNumColumns() = columns]
     * @return columns
     */
    public int getNumColumns();

    /**
     * Getter method for number of tokens in a row to win
     * @pre none
     * @post self = #self AND [getNumToWin() = win]
     * @return win
     */
    public int getNumToWin();
}
