package cpsc2150.extendedConnectX.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A ConnectX game board with a memory efficient implementation;
 * @invariants [Board has numRows rows and numColumns columns] AND
 *             [No space between two occupied cells of the same column]
 *             3 <= rows <= 100 AND
 *             3 <= columns <= 100
 *             3 <= win <= 25 AND
 *             win < rows AND win < columns
 * @correspondences rows = numRows AND
 *                  columns = numColumns AND
 *                  win = numToWin
 *                  grid = boardMap
 */

public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    private HashMap<Character, ArrayList<BoardPosition>> boardMap;
    private int numRows;
    private int numColumns;
    private int numToWin;

    /**
     * Constructs instance of GameBoardMem object with an empty HashMap
     * @pre 3 <= rows <= 100 AND
     *      3 <= columns <= 100 AND
     *      3 <= numToWin <= 25 AND
     *      numToWin <= columns AND numToWin <= rows
     * @post [Object created with an empty HashMap] AND
     *      numRows = row AND
     *      numColumns = column AND
     *      this.numToWin = numToWin
     * @param row number of rows the game board will have
     * @param column number of columns the game board will have
     * @param numToWin number of pieces in a row needed to win
     */
    public GameBoardMem(int row, int column, int numToWin)
    {
        numRows = row;
        numColumns = column;
        this.numToWin = numToWin;
        boardMap = new HashMap<Character, ArrayList<BoardPosition>>();
    }

    public void placeToken(char p, int c)
    {
        //If hashmap does not contain key p, add it
        if(!boardMap.containsKey(p))
        {
            boardMap.put(p, new ArrayList<BoardPosition>());
        }
        //Loop through all possible positions in column c
        for(int row = 0; row < numRows; row++)
        {
            BoardPosition tempPos = new BoardPosition(row, c);
            boolean posEmpty = true;
            for(ArrayList<BoardPosition> players : boardMap.values())
            {
                for (int listIndex = 0; listIndex < players.size(); listIndex++)
                {
                    //If there is a player with a position at tempPos, set posEmpty to false
                    //and check row above
                    if (players.get(listIndex).equals(tempPos))
                    {
                        posEmpty = false;
                    }
                }
            }
            //If the position is empty, add the position to player p's hashmap and return
            if(posEmpty)
            {
                boardMap.get(p).add(tempPos);
                return;
            }
        }
        return;
    }

    public char whatsAtPos(BoardPosition pos)
    {
        //Check
        for(HashMap.Entry<Character, ArrayList<BoardPosition>> entry : boardMap.entrySet())
        {
            for(int arrayIndex = 0; arrayIndex < entry.getValue().size(); arrayIndex++)
            {
                if(pos.equals(entry.getValue().get(arrayIndex)))
                {
                    return entry.getKey();
                }
            }
        }
        return ' ';
    }

    @Override
    //Overridden because HashMap provides a more efficient implementation
    public boolean isPlayerAtPos(BoardPosition pos, char p)
    {
        if(!boardMap.containsKey(p)) return false;
        for(int arrayIndex = 0; arrayIndex < boardMap.get(p).size(); arrayIndex++)
        {
            if(boardMap.get(p).get(arrayIndex).equals(pos))
            {
                return true;
            }
        }
        return false;
    }
    public int getNumRows() { return numRows; }

    public int getNumColumns() { return numColumns; }

    public int getNumToWin() { return numToWin; }
}
