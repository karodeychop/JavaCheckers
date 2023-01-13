import java.awt.Color;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class CheckersPanel extends Panel
{
	private final int CELL_SIZE = 100;
	private final int PIECE_SIZE = 80;
	private final int CELL_OFFSET = 25;
	private final Color COLOR_PIECE_BLACK = Color.BLACK;
	private final Color COLOR_PIECE_RED = Color.RED;
	private final Color COLOR_CELL_EVEN = new Color(255, 204, 153);
	private final Color COLOR_CELL_ODD = new Color(153, 102, 51);
	private final Color COLOR_HIGHLIGHT_SELECT = Color.YELLOW;
	private final Color COLOR_HIGHLIGHT_MOVE = new Color(0, 255, 0);
	private final Color COLOR_GRID = Color.BLACK;
	private final Color COLOR_TEXT = Color.BLACK;
	
	private enum HighlightType
	{
		NONE, SELECTED, MOVE
	}
	
	private HighlightType[][] highlights = new HighlightType[8][8];
	
	private String stateMessage = "";
	
	private CheckersModel gameModel;
	
	public CheckersPanel()
	{
		super();
		addMouseListener(new CheckersMouseListener(this));
		gameModel = new CheckersModel();
	}
	
	private void ResetHighlights()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				highlights[i][j] = HighlightType.NONE;
			}
		}
		
		for (int i = 0; i < 8; i++) for (int j = 0; j < 8; highlights[i][j] = HighlightType.NONE, j++) ;
		
		for (int i = 0, j = 0; i < 8 && j < 8; highlights[i][j] = HighlightType.NONE, j++, i = (j == 8 ? i + 1 : i), j = (j == 8 ? 0 : j)) ;
	}
	
	public void paint(Graphics g)
	{	
		for (int row = 1; row <= 8; row++)
		{
			for (int col = 1; col <= 8; col++)
			{
				drawCell(row, col, g);
			}
		}
		drawStatusMessage(g);
	}
	
	private void drawStatusMessage(Graphics g)
	{
		// Draw at bottom of screen.
		int startX = 200;
		int startY = 825;
		
		String outString = "";
		
		// Turn message
		switch (gameModel.GetTurn())
		{
			case REDPLAYER:
				outString += "Red to move. ";
				break;
			case BLACKPLAYER:
				outString += "Black to move. ";
				break;
		}
		
		// Add in state message.
		outString += stateMessage;
		
		g.setColor(COLOR_TEXT);
		g.drawString(outString, startX, startY);
	}
	
	private void drawCell(int row, int col, Graphics g)
	{
		boolean hasPiece;
		Color pieceColor = Color.WHITE;
		HighlightType highlight;
		int startX, startY, startPieceX, startPieceY;
		
		// Start locations for piece and cell.
		startX = CELL_SIZE * (col - 1);
		startY = CELL_SIZE * (row - 1);
		startPieceX = startX + (CELL_SIZE - PIECE_SIZE) / 2;
		startPieceY = startY + (CELL_SIZE - PIECE_SIZE) / 2;
		
		g.setColor(COLOR_GRID);
		g.drawRect(startX, startY, CELL_SIZE, CELL_SIZE);
		if ((row + col) % 2 == 0)
		{
			g.setColor(COLOR_CELL_EVEN);
		}
		else
		{
			g.setColor(COLOR_CELL_ODD);
		}
		
		highlight = highlights[row - 1][col - 1];
		if (highlight == HighlightType.MOVE)
		{
			g.setColor(COLOR_HIGHLIGHT_MOVE);
		}
		if (highlight == HighlightType.SELECTED)
		{
			g.setColor(COLOR_HIGHLIGHT_SELECT);
		}
		g.fillRect(startX, startY, CELL_SIZE, CELL_SIZE);
		switch (gameModel.GetPieceAt(row, col))
		{
			case RED:
			case REDKING:
				pieceColor = COLOR_PIECE_RED;
				hasPiece = true;
				break;
			case BLACK:
			case BLACKKING:
				pieceColor = COLOR_PIECE_BLACK;
				hasPiece = true;
				break;
			case EMPTY:
			default:
				hasPiece = false;
				break;
		}
		
		if (hasPiece)
		{
			g.setColor(COLOR_GRID);
			g.drawOval(startPieceX, startPieceY, PIECE_SIZE, PIECE_SIZE);
			g.setColor(pieceColor);
			g.fillOval(startPieceX, startPieceY, PIECE_SIZE, PIECE_SIZE);
		}
	}
	
	public void MouseClickHandler(MouseEvent e)
	{
		System.out.println("Mouse Event Happened");
	}
}