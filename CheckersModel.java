public class CheckersModel
{	
	// Size of the board.
	public static final int BOARD_SIZE = 8;

	// What piece is currently in the slot (EMPTY if none).
	public enum CellType
	{
		EMPTY, RED, REDKING, BLACK, BLACKKING
	}
	
	// Used to enumerate turn state.
	public enum Turn
	{
		REDPLAYER, BLACKPLAYER
	}
	
	// Who is next to move.
	private Turn currentPlayer;
	
	// What pieces are on the board.
	private CellType[][] board = new CellType[8][8];
	
	// Constructor. Calls ResetState since it was just created.
	public CheckersModel()
	{
		ResetState();
	}
	
	// Reset the state to the starting state of the game.
	// Red will always be the first player to move.
	public void ResetState()
	{
		boolean hasPiece;
		CellType piece;
		
		for (int row = 0; row < BOARD_SIZE; row++)
		{
			// Rows 0 through 2 = Black
			// Rows 5 through 7 = Red
			switch (row)
			{
				case 0:
				case 1:
				case 2:
					piece = CellType.BLACK;
					break;
				case 5:
				case 6:
				case 7:
					piece = CellType.RED;
					break;
				default:
					piece = CellType.EMPTY;
					break;
			}
			
			for (int col = 0; col < BOARD_SIZE; col++)
			{
				// Even Column & Odd Row = Piece
				// Odd Column & Even Row = Piece
				hasPiece = (row % 2 != col % 2);
				board[row][col] = hasPiece ? piece : CellType.EMPTY;
			}
		}
		
		// Red is first player.
		currentPlayer = Turn.REDPLAYER;
	}
	
	// Get a piece at the given position.
	// Calls GetPieceAt(row, col) for consistency.
	// Upper Left = row 1 col 1
	// Lower Right = row 8 col 8
	public CellType GetPieceAt(Position position)
	{
		int row = position.GetRow();
		int col = position.GetColumn();
		return GetPieceAt(row, col);
	}
	
	// Get a piece at the given row and column.
	// Upper Left = row 1 col 1
	// Lower Right = row 8 col 8
	public CellType GetPieceAt(int row, int col)
	{
		if (row < 1 || row > BOARD_SIZE)
		{
			return null;
		}
		else if (col < 1 || col > BOARD_SIZE)
		{
			return null;
		}
		return board[row - 1][col - 1];
	}
	
	// Determine which player's turn it is.
	public Turn GetTurn()
	{
		return currentPlayer;
	}
	
	public static void main(String args[])
	{
		CheckersModel model = new CheckersModel();
		model.ResetState();
		model.PrintState();
	}
	
	public void PrintState()
	{
		String outString;
		
		// Print the board's state in grid form.
		System.out.println("Board State");
		for (int row = 1; row <= BOARD_SIZE; row++)
		{
			outString = "| ";
			for (int col = 1; col <= BOARD_SIZE; col++)
			{
				switch (GetPieceAt(row, col))
				{
					case EMPTY:
						outString += "   |";
						break;
					case BLACK:
						outString += "B  |";
						break;
					case BLACKKING:
						outString += "BK |";
						break;
					case RED:
						outString += "R  |";
						break;
					case REDKING:
						outString += "RK |";
						break;
					default:
						outString += "?? |";
						break;
				}
			}
			System.out.println(outString);
		}
		
		// Print the turn state.
		switch (GetTurn())
		{
			case REDPLAYER:
				outString = "Red's Turn";
				break;
			case BLACKPLAYER:
				outString = "Black's Turn";
				break;
			default:
				outString = "???'s Turn";
				break;
		}
		System.out.println(outString);
	}
}