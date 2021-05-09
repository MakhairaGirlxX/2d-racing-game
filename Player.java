import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player {
	int position;
	int playerNum;
	int startingPos;
	
	Response type;
	int responseLife;
	PositionInfo handler;
	Random generator;
	
	private BufferedImage rImage;
	private BufferedImage bImage;
	private BufferedImage yImage;
	private BufferedImage gImage;
	private BufferedImage oImage;
	
	
	public Player(int playerNum, PositionInfo handler){
		position = 0;
		startingPos = 0;
		this.playerNum = playerNum;
		generator = new Random();
		this.handler = handler;
		resetResponseType();
	}
	
	public void resetResponseType(){
		type = ResponseTypeFactory.getResponseType();
		responseLife = 2 + generator.nextInt(4);
	}
	
	public void makeMove(){
		int dieThrow = 1 + generator.nextInt(6);
		if( responseLife > 0 ){
			position += type.changeInPosition(dieThrow, position, handler); 
			responseLife--;
		}
		else resetResponseType();
	}
	
	public String playerColor()
	{
		if(playerNum == 0)
		{
			return "Red";
		}
		if(playerNum == 1)
		{
			return "Blue";
		}
		if(playerNum == 2)
		{
			return "Yellow";
		}
		if(playerNum == 3)
		{
			return "Green";
		}
		if(playerNum == 4)
		{
			return "Orange";
		}
		return "None";
	}
	
	public int startingPosition()
	{		
		int[] startingPos = {0, 150, 300, 450, 600};
		int temp = startingPos[playerNum];

		return temp;
	}
	
	public void draw(Graphics g)
	{ 
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(generateImage(), position * 20, startingPosition(), null);		
	}
	
	public BufferedImage generateImage()
	{		
		 try
	        {
	        	rImage = ImageIO.read(new File("C:\\\\Users\\\\prosi\\\\OneDrive\\\\Pictures\\\\RedCar_001.png"));
	        	bImage = ImageIO.read(new File("C:\\\\Users\\\\prosi\\\\OneDrive\\\\Pictures\\\\BlueCar_001.png"));
	        	yImage = ImageIO.read(new File("C:\\\\Users\\\\prosi\\\\OneDrive\\\\Pictures\\\\YellowCar_001.png"));
	        	gImage = ImageIO.read(new File("C:\\\\Users\\\\prosi\\\\OneDrive\\\\Pictures\\\\GreenCar_001.png"));
	        	oImage = ImageIO.read(new File("C:\\\\Users\\\\prosi\\\\OneDrive\\\\Pictures\\\\OrangeCar_001.png"));
	        }
	        catch(IOException e)
	        {
	        	e.printStackTrace();
	        }
		 
		 BufferedImage[] images = {rImage, bImage, yImage, gImage, oImage};
		 BufferedImage temp = images[playerNum];

		// Arrays.fill(images, null);
		 System.out.println(images);
		 return temp;
		 
	}

	public String toString()
	{
		return "Player " + playerColor() + "!";
	}
}
