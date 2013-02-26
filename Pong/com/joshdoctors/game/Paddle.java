package com.joshdoctors.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Paddle {
	
	private int width=20;
	private int height=100;
	private int d_width=20;
	private int d_height=100;
	private int gameWidth=810;
	private int gameHeight=810;
	private int x;
	private int y;
	private boolean rotation;
	public static boolean isMovingUp,isMovingDown;
	Rectangle pdl;
	public boolean ai;
	public int growth;
	public boolean test;
	
	Rectangle testRect,testRect2;
	public boolean hasHitBot;

	
	public Paddle(boolean ai)
	{
		this.ai = ai;
		
		if(!ai)
		{
			x=5;
			y=(810/2)-(10);
		}
		else
		{
			x=785;
			y=(810/2)-(10);
		}
		
		pdl = new Rectangle(this.x,this.y,width,height);		
		testRect = new Rectangle(this.x+30,this.y,width,height);
		//testRect2 = new Rectangle(35,785,0,20);
	}
	
	public void reset()
	{
		if(!ai)
		{
			this.x=5;
			this.y=(810/2)-(10);
			height=d_height;
			width=d_width;
			pdl.setBounds(x,y,d_width,d_height);
			
			testRect.x=35;
			testRect.y=(810/2)-(10);
			testRect.setBounds(testRect.x,testRect.y,testRect.width,testRect.height);
			System.out.println("reset");
		}
		else
		{
			height=d_height;
			width=d_width;
			
			this.x=785;
			this.y=(810/2)-(10);
			pdl.setBounds(x,y,d_width,d_height);
		}
		
	}
	
	public void grow(int amt)
	{
		this.y-=amt;
		height+=amt*2;
		pdl.setBounds(this.x,this.y,width,height);
		growth+=amt;
	}
	
	public void shrink(int amt)
	{
		this.y+=amt;
		height-=amt*2;
		pdl.setBounds(this.x,this.y,width,height);
		growth-=amt;
		}
	
	public void moveAi()
	{
//		if(Game.ball.dx > 0)
//		{
//			if(Game.ball.y > this.y)
//				moveDown();
//			
//			if(Game.ball.y < this.y)
//				moveUp();
//		}
		pdl.y = Game.ball.y-50;
		
		if(pdl.y < 0)
			pdl.setBounds(pdl.x, 0, pdl.width, pdl.height);
		else
		if(pdl.y > 710)
			pdl.setBounds(pdl.x, 710, pdl.width, pdl.height);
		else
		pdl.setBounds(pdl.x, pdl.y, pdl.width, pdl.height);
		
	}
	
	public void moveUp()
	{
		if(pdl.y > 4)
		{
			pdl.setBounds(pdl.x, pdl.y-4, pdl.width, pdl.height);
			isMovingUp=true;
			this.y-=4;
		}
		else
		{
			pdl.setBounds(pdl.x,0, pdl.width, pdl.height);
			this.y=0;
			isMovingUp=false;
		}
	}
	
	public void moveDown()
	{
		if(pdl.y+height<806)
		{
			pdl.setBounds(pdl.x, pdl.y+4, pdl.width, pdl.height);
			isMovingDown=true;
			this.y+=4;
		}
		else
		{
			pdl.setBounds(pdl.x, 810-height, pdl.width, pdl.height);
			isMovingDown=false;
			this.y=810-height;
		}
	}
	
	
	public void draw(Graphics g)
	{
		//System.out.println(g.getClipBounds().width + " " + g.getClipBounds().height);
		
		g.setColor(new Color(255,0,0));
		
		if(!rotation)
			g.fillRect(pdl.x,pdl.y,pdl.width,pdl.height);
		else
			g.fillRect(pdl.x,pdl.y,pdl.height,pdl.width);
		
		if(Game.ball.hasHitMy && !ai)
		{
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x+5,pdl.y,pdl.width-5,pdl.height);
			
			g.setColor(new Color(255,0,0));
			g.fillRect(pdl.x,pdl.y,pdl.width,pdl.height);
			
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x+5,pdl.y,pdl.width-5,pdl.height);
			
			g.setColor(new Color(255,0,0));
			g.fillRect(pdl.x,pdl.y,pdl.width,pdl.height);
			
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x+5,pdl.y,pdl.width-5,pdl.height);
			
			g.setColor(new Color(255,0,0));
			g.fillRect(pdl.x,pdl.y,pdl.width,pdl.height);
			
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x+5,pdl.y,pdl.width-5,pdl.height);
			
			g.setColor(new Color(255,0,0));
			g.fillRect(pdl.x,pdl.y,pdl.width,pdl.height);
			
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x+5,pdl.y,pdl.width-5,pdl.height);
			
			g.setColor(new Color(255,0,0));
			g.fillRect(pdl.x,pdl.y,pdl.width,pdl.height);
			
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x+5,pdl.y,pdl.width-5,pdl.height);
			
			g.setColor(new Color(255,0,0));
			g.fillRect(pdl.x,pdl.y,pdl.width,pdl.height);
			
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x+5,pdl.y,pdl.width-5,pdl.height);
		}
		
		if(Game.ball.hasHitCom && ai)
		{
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x,pdl.y,pdl.width-5,pdl.height);
			
			g.setColor(new Color(255,0,0));
			g.fillRect(pdl.x,pdl.y,pdl.width,pdl.height);
			
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x,pdl.y,pdl.width-5,pdl.height);
			
			g.setColor(new Color(255,0,0));
			g.fillRect(pdl.x,pdl.y,pdl.width,pdl.height);
			
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x,pdl.y,pdl.width-5,pdl.height);
			
			g.setColor(new Color(255,0,0));
			g.fillRect(pdl.x,pdl.y,pdl.width,pdl.height);
			
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x,pdl.y,pdl.width-5,pdl.height);
			
			g.setColor(new Color(255,0,0));
			g.fillRect(pdl.x,pdl.y,pdl.width,pdl.height);
			
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x,pdl.y,pdl.width-5,pdl.height);
			
			g.setColor(new Color(255,0,0));
			g.fillRect(pdl.x,pdl.y,pdl.width,pdl.height);
			
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x,pdl.y,pdl.width-5,pdl.height);
			
			g.setColor(new Color(255,0,0));
			g.fillRect(pdl.x,pdl.y,pdl.width,pdl.height);
			
			g.setColor(new Color(255,255,255));
			g.fillRect(pdl.x,pdl.y,pdl.width-5,pdl.height);
		}
		
	}
	
	public void drawRotate(Graphics g)
	{
		
		g.fillRect(testRect.x,testRect.y,testRect.width,testRect.height);
		
		if(!hasHitBot)
		{
			if(testRect.y+testRect.height<801)
			{
				testRect.y+=4;
				testRect.setBounds(testRect.x,testRect.y,testRect.width,testRect.height);
		
			}
			else
			{
				testRect.y=805-testRect.height;
				testRect.setBounds(testRect.x,805-testRect.height,testRect.width,testRect.height);
				hasHitBot=true;
			}
		}
		
		if(hasHitBot)
		{
			testRect.height-=4;
			
			if(testRect.height<0)
			{
				testRect.height=0;
			}
			
			testRect.setBounds(testRect.x,805-testRect.height,testRect.width,testRect.height);
			
		
			testRect2.width+=4;
			
			if(testRect2.width>100)
			{
				testRect2.width=100;
				pdl.setBounds(testRect2.x,testRect2.y,testRect2.width,testRect2.height);
			}
			
			if(testRect2.width!=100)
				g.fillRect(testRect2.x,testRect2.y,testRect2.width,testRect2.height);
				
			testRect2.setBounds(testRect2.x,testRect2.y,testRect2.width,testRect2.height);
			
			
			
			System.out.println(testRect2.width + " " + testRect2.height);
		}
	}

}
