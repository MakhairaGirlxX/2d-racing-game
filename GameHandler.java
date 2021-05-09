import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class GameHandler implements PositionInfo {
	Player[] players;
	int numPlayers;
	final int WINNING_MARKER = 50;
	Thread animator;
	RacingFrame frame;
	RacingTask task;
	
	public GameHandler(int numPlayersAllowed){
		players = new Player[numPlayersAllowed];
		numPlayers = 0;
		task = new RacingTask();
		animator = new Thread(task);
		frame = new RacingFrame(players, animator);
	}
	
	public void addPlayer(){
		players[numPlayers] = new Player(numPlayers, this);
		numPlayers++;
	}
	
	public void runAnimation()
	{		
		try 
		{
			TimeUnit.SECONDS.sleep(2);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		animator.start();
	}
	
	public int getLeadingPlayerPosition(){
		int leadingPos = players[0].position;
		for(int i = 1; i < numPlayers; i++){
			if(players[i].position > leadingPos) leadingPos = players[i].position;
		}
		return leadingPos; 
	}
	
	public int getTrailingPlayerPosition(){
		int trailingPos = players[0].position;
		for(int i = 1; i < numPlayers; i++){
			if(players[i].position < trailingPos) trailingPos = players[i].position;
		}
		return trailingPos; 
	}
	
	private boolean isSomeoneWinning(){
		for(int i = 1; i < numPlayers; i++){
			if(players[i].position >= WINNING_MARKER)
			{
				frame.winner(players[i].toString());
				return true;
			}
		}
		return false;
	}
	
	class RacingTask implements Runnable
	{
		int playerTurn = 0;

		@Override
		public void run() {
			while(!isSomeoneWinning())
			{
				players[playerTurn].makeMove();
				System.out.println(players[playerTurn].toString());
				playerTurn++;
				if( playerTurn > numPlayers - 1 ) playerTurn = 0; 
				
				try
				{
					Thread.sleep(30);
				}
				catch(InterruptedException e)
				{
					System.out.println(e.getMessage());
				}
				frame.repaint();
				
			}			
			isSomeoneWinning();			
		}
		
	}
}



class RacingFrame extends JFrame
{
	Player[] players;
	RacingPnl pnl;
	Thread animator;	
	JLabel winner;
	JFrame editorFrame;
	
	public RacingFrame(Player[] players, Thread anim)
	{
		animator = anim;
		this.players = players;
		
		editorFrame = new JFrame("Racing Game");
	    editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    editorFrame.setResizable(false);
	    editorFrame.setLocation(100, 100);
	    editorFrame.setSize(1280, 800);
	    editorFrame.setLayout(new BorderLayout());
	    
	    winner = new JLabel(" ", SwingConstants.CENTER);
	        
	    pnl = new RacingPnl();

	    editorFrame.add(pnl);
		
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				animator.interrupt();
				e.getWindow().dispose();
				System.exit(0);
			}
		});
		editorFrame.setVisible(true);
	}
	

	public void repaint()
	{		
		super.repaint();
		pnl.repaint();
		pnl.revalidate();
		System.out.println("repainted");
	}
	
	public void winner(String win)
	{
		winner.setText("Winner is: " + win);
		winner.setFont(new Font("Time New Roman", Font.BOLD, 30));

		editorFrame.add(winner, BorderLayout.SOUTH);
		editorFrame.revalidate();
		
	}
	
	class RacingPnl extends JPanel
	{		
		
		ImageIcon startingLine;
		ImageIcon finishLine;
		
		Image sImage;
		Image fImage;
		
		public RacingPnl()
		{
			this.setBackground(Color.white);
			this.setBorder(BorderFactory.createTitledBorder("Panel"));
			
			 startingLine = new ImageIcon("C:\\\\Users\\\\prosi\\\\OneDrive\\\\Pictures\\\\StartingLine_001.png");
			 finishLine = new ImageIcon("C:\\\\Users\\\\prosi\\\\OneDrive\\\\Pictures\\\\StartingLine_001.png");
			 
			 sImage = startingLine.getImage();
			 fImage = finishLine.getImage();		
		}
		
		public void paintComponent(Graphics g)
		{
			
			super.paintComponents(g);
			
			g.drawImage(sImage, 125, 0, this);
			g.drawImage(fImage, 1100, 0, this);
			
			for(int i = 0; i < players.length; i++){			
				players[i].draw(g);
				
			}			
		}
	}		
		
}


interface PositionInfo{
	int getLeadingPlayerPosition();
	int getTrailingPlayerPosition();
	void runAnimation();
}



