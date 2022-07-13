package cpsc2150.extendedConnectX.models;
/**
 * An object to represent a position on the ConnectX board
 * @invariant (0 <= row <= 6) AND (0 <= column < = 9)
 */
public class BoardPosition {
    private int row;
    private int column;
    /**
     * Creates a BoardPosition object which holds a chip's column and row
     * @param row the row of the piece
     * @param column the column of the piece
     * @pre (0 <= row <= 6) AND (0 <= column < = 9)
     * @post this.row = row AND this.column = column
     */
    public BoardPosition(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the row of a placed chip
     * @post row = #row AND column = #column AND [getRow() = row]
     * @return row
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Returns the column of a placed chip
     * @post row = #row AND column = #column AND [getColumn() = column]
     * @return column
     */
    public int getColumn()
    {
        return column;
    }

    /**
     * Tests equality between two BoardPosition objects.
     * @param obj BoardPosition being compared to this.BoardPosition
     * @post row = #row AND column = #column AND equals() = (this.row == boardPos.row && this.column == boardPos.column)
     * @return If this BoardPosition contains the same values as boardPos
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof BoardPosition)
        {
            final BoardPosition boardPos = (BoardPosition) obj;
            return (this.row == boardPos.row && this.column == boardPos.column);
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns a string representation of the object in the form <row>,<column>
     * @post row = #row AND column = #column AND [toString() = row and column in
     * the form <row>,<columns>]
     * @return objectToString
     */
    @Override
    public String toString()
    {
        return row + "," + column;
    }
}
