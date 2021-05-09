import javax.swing.SwingUtilities;

public class RacingGame {

	public static void main(String[] args) {
		GameHandler handler = new GameHandler(5);
		for(int i = 0; i < 5; i++) handler.addPlayer();
		handler.runAnimation();
		
	//test if thread is running on EDT
			if(SwingUtilities.isEventDispatchThread())
			{
				System.out.println("Run");
			}
			else
			{
				System.out.println("is not");
			}
	
	}
}
