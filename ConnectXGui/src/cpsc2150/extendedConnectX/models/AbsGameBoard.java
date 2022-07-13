package cpsc2150.extendedConnectX.models;

/**
 * An abstract class for providing classes implementing the IGameBoard interface with
 * an appropriate and general toString method
 */
public abstract class AbsGameBoard implements IGameBoard {
    /**
     * Converts board into a string
     * @pre none
     * @post board = #board AND [toString() = string of board in the general format:]
     * |0|1|2|3|4|5|6|7|8|
     * | | | | | | | | | |
     * | | | | | | | | | |
     * | | | | | | | | | |
     * | | | | | | | | | |
     * | | | | | | | | | |
     * | | | | | | | | | |
     * @return String representation of GameBoard
     */
    @Override
    public String toString()
    {
        String returnString = "";
        for(int column = 0; column < this.getNumColumns(); column++)
        {
            if(column < 10)
            {
                returnString += "| " + column;
            }
            else
            {
                returnString += "|" + column;
            }
        }
        returnString += "|\n";
        for(int row = this.getNumRows() - 1; row >= 0; row--)
        {
            for(int column = 0; column < this.getNumColumns(); column++)
            {
                returnString += "|" + whatsAtPos(new BoardPosition(row, column)) + " ";
            }
            returnString += "|\n";
        }
        return returnString;
    }
}
