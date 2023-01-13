import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CheckersLauncher
{
	public static void main(String args[])
	{
		// Create the frame
		Frame frame = new Frame("Checkers Frame");
		frame.setSize(800,875);
		frame.setResizable(false);
		frame.setVisible(true);
		
		// Set windowClosing listener
		frame.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent windowEvent)
				{
					System.exit(0);
				}
			}
		);
		
		// Create the CheckersPanel.
		CheckersPanel panel = new CheckersPanel();
		panel.setBounds(new Rectangle(0, 25, 800, 850));
		panel.setVisible(true);
		
		// Add the panel to the frame.
		frame.add(panel);
	}
}