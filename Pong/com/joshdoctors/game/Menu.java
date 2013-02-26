package com.joshdoctors.game;

import java.awt.*;

public class Menu
{
	private boolean hoverOnPlay=true;
	private boolean hoverOnControls=false;
	private boolean inControl = false;
	private int buttonWidth = 200;
	private int buttonHeight = 50;
	
	private Rectangle playButton,backButton,controlButton;
	
	public static boolean enterGame=false,enterControls=false;
	private KeyHandler key=new KeyHandler();
	
	public Menu()
	{
		playButton = new Rectangle(305,405,buttonWidth,buttonHeight);
		controlButton = new Rectangle(305,405+(buttonHeight)+10,buttonWidth,buttonHeight);
		backButton = new Rectangle(305,705,buttonWidth,buttonHeight);
		
	}
	
	public void update()
	{
		System.out.println("ply "+hoverOnPlay);
		System.out.println("ctrl "+hoverOnControls);
		System.out.println("men:"+Game.inMenu);
		
		 if(Game.k !=null)
       {
			 Game.k.update();
		
			if(Game.k.up || Game.k.down)
			{
				System.out.println("got key");
				hoverOnPlay=!hoverOnPlay;
				hoverOnControls=!hoverOnControls;
			}
		
			if(Game.k.ent)
			{
				if(hoverOnPlay)
				{
					System.out.println("enter games aDDWDWAADW");
					Game.inMenu=false;

				}
				
				
				if(hoverOnControls && !inControl)
				{
					inControl=true;
				}
				else
				{
					inControl=false;
				}
				
				
			}
      }
	}
	
	public void draw(Graphics g)
	{
	
		
		if(!inControl)
		{
		   g.setFont(new Font("tahoma",Font.BOLD,72));
	        g.setColor(new Color(255,255,255));
	        g.drawString("Re_Pong",345, 100);
	        
			if(hoverOnPlay)
				g.setColor(new Color(0,255,0));
			else
				g.setColor(new Color(255,0,0));
		g.fillRect(playButton.x, playButton.y, playButton.width, playButton.height);
		
		if(!hoverOnPlay)
			g.setColor(new Color(0,255,0));
		else
			g.setColor(new Color(255,0,0));
		
		g.fillRect(controlButton.x, controlButton.y, controlButton.width, controlButton.height);
		}
		else
		{
			g.setColor(new Color(0,0,255));
			g.fillRect(backButton.x, backButton.y, backButton.width, backButton.height);
		}
		
		
	}

}
