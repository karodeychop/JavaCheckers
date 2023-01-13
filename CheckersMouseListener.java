import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckersMouseListener extends MouseAdapter
{
	private CheckersPanel listeningPanel;

	// Must have reference to the source panel.
	public CheckersMouseListener(CheckersPanel listeningPanel)
	{
		this.listeningPanel = listeningPanel;
	}
	
	// Tell panel about the click.
	public void mouseClicked(MouseEvent e)
	{
		listeningPanel.MouseClickHandler(e);
	}
}