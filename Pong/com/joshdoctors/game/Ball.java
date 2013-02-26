package com.joshdoctors.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Vector;

import com.joshdoctors.sound.Sound;

public class Ball {
	
	public int x, y;
	public int amtHit;
	Random rnd = new Random();
	public float dx=9,dy=4;//dx=9,dy=4
	public float pdx=9,pdy=4;
//	public int dx=-rnd.nextInt(40)+20,dy=1;//-(rnd.nextInt(5)+3);
	public boolean hasHitMy=false;
	public boolean hasHitCom=false;
	public int[] xVal =  new int[255];
	public int [] yVal = new int[255];
	
	public int[] xVal2 =  new int[75];//testing the a smaller size tail
	public int [] yVal2 = new int[75];
	
	public int val=0;
	public int val2=0;
	
	public int pauseTime=-1,pauseFrame=1;
	public boolean start=true;
	
	
	private Rectangle bounds  = new Rectangle();
	
	public Ball(int x, int y)
	{
		this.x = x;
		this.y = y;
		bounds.setBounds(x,y,10,10);
		
		if(rnd.nextBoolean())
			this.dx*=-1;
		
		if(rnd.nextBoolean())
			this.dy*=-1;
		
		System.out.println("Dx: " + dx + " DY: " + dy);
	}
	
	public void setPos(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void reset()
	{
		x=395;
		y=395;
		//dx=-rnd.nextInt(5)+3;
		//dy=-(rnd.nextInt(5)+1);
		dx=9;
		dy=4;
		
		if(rnd.nextBoolean())
			this.dx*=-1;
		
		if(rnd.nextBoolean())
			this.dy*=-1;
		
		if(this.dx<0)
		{
			//this.dx*=.7;
			this.dy*=.3;
		}
		
		pdx=dx;
		pdy=dy;
		
		for(int i=0;i<xVal.length;i++)
		{
			xVal[i]=0;
			yVal[i]=0;
		}
		
		for(int i=0;i<xVal2.length;i++)
		{
			xVal2[i]=0;
			yVal2[i]=0;
		}
		
		amtHit=0;
		val2=0;//do I nee this?
		Game.reverseControls=false;
		this.pause();
		this.pauseTime=1;
		System.out.println("Dx: " + dx + " DY: " + dy);
		start=true;
		
	}
	
	public void update()
	{
		
		System.out.println("In update");
		System.out.println("Dx: " + dx + " DY: " + dy);
		
		if(this.pauseTime==1 && !(Game.myScore==0 && Game.enemyScore==0))
		{
			if(this.pauseFrame % 125==0)
			{
				this.unPause();
				pauseTime=0;
				pauseFrame=1;
			}
			else
				this.pauseFrame++;
		}
		

		
		
		hasHitMy=false;
		hasHitCom = false;
		
		
		if(this.y < 0 || this.y >800)
		{
			this.dy=this.dy*-1;
			Sound.BOUNCE.playOnce();
			System.out.println("hit top/bot");
		}
		
		if(Game.myPaddle.pdl.intersects(bounds))//
		{
			this.dx=this.dx*-1.01f;
			hasHitMy=true;
			amtHit++;
			
			if(Game.myPaddle.isMovingUp)
				this.dy=this.dy*1.2f;
			
			if(Game.myPaddle.isMovingDown)
				this.dy=this.dy*1.2f;
			
			Sound.HIT.playOnce();
			
			System.out.println("Hit game paddle");
		}
		
		if(Game.comPaddle.pdl.intersects(bounds))
		{
			this.dx=this.dx*-1.01f;
			hasHitCom=true;
			Sound.HIT.playOnce();
			amtHit++;
			
			System.out.println("Hit com paddle");
		}
		
		if(this.x <0)
		{
			Game.enemyScore++;
			Game.myPaddle.reset();
			//Game.comPaddle.reset();
			reset();
			Game.item.reset();
		}
		
		if(this.x > 810)
		{
			Game.myScore++;
			Game.myPaddle.reset();
			//Game.comPaddle.reset();
			reset();
			Game.item.reset();
		}
		
		
		if(Game.item.bounds.intersects(bounds) && Game.item.isAlive)
		{
			
			//0=grow,1=shrink,2=slowdown,3=speedup,4=reversecontrols,5=rotateboard,6=spawnball
			if(Game.item.attribute==0)
			{
				if(this.dx <0)
					Game.myPaddle.grow(3);
				else
					Game.comPaddle.grow(3);
			}
			else
				if(Game.item.attribute==1)
				{
					if(this.dx <0)
						Game.myPaddle.shrink(6);
					else
						Game.comPaddle.shrink(6);
					
				}
				else
					if(Game.item.attribute==2)
					{
						this.dx*=.5;
						this.dy*=.5;
					}
					else
						if(Game.item.attribute==3)
						{
							this.dx*=1.5;
							this.dy*=1.5;
						}
						else
							if(Game.item.attribute==4)
							{
								if(this.dx<0)
									Game.reverseControls=true;
							}
							else
								if(Game.item.attribute==5)
								{
									
								}
								else
									if(Game.item.attribute==6)
									{
										
									}
			Game.item.reset();
			
		}
		
		setPos(this.x +(int)dx,this.y+(int)dy);
		bounds.setBounds(this.x,this.y,10,10);
		
		System.out.println("Dx: " + dx + " DY: " + dy);
		
		if(!Game.pauseGame)
			getTailData();
		
		if(hasHitMy)
			System.out.println("hasHit Left");
		
		if(hasHitCom)
			System.out.println("hasHit right");
		
		
		

	}
	
	public void getTailData()
	{
		if(val<255)
		{
		xVal[val]=this.x;
		yVal[val]=this.y;
		val++;
		}
		else
		{
			for(int i=0;i<xVal.length-1;i++)
			{
				xVal[i]=xVal[i+1];
				yVal[i]=yVal[i+1];
			}
			xVal[254]=this.x;
			yVal[254]=this.y;
		}
		
		
		if(val2<75)
		{
		xVal2[val2]=this.x;
		yVal2[val2]=this.y;
		val2++;
		}
		else
		{
			for(int i=0;i<xVal2.length-1;i++)
			{
				xVal2[i]=xVal2[i+1];
				yVal2[i]=yVal2[i+1];
			}
			xVal2[74]=this.x;
			yVal2[74]=this.y;
		}
	}
	
	public void pause()
	{
		pdx=dx;
		pdy=dy;
		
		dx=0;
		dy=0;
	}
	
	public void unPause()
	{
	
		dx=pdx;
		dy=pdy;
	}
	
	public void slowDown()
	{
		//float convert;
		//if(dx>dy)
		if(dx*.9>1 && dy*.9>1)
		{
		dx*=.9;
		dy*=.9;
		}
		
		System.out.println("dx: " + dx + " dy: "  +dy);
	}
	
	public void speedUp()
	{
		dx*=1.1;
		dy*=1.1;
	}
	
	
	public void draw(Graphics g)
	{
		g.setColor(new Color(0,255,0));
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		//g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	public void drawTail(Graphics g)
	{
		
//		for(int i=0;i<xVal.length;i++)
//		{
//			if(xVal[i]!=0)
//			{
////				if(i<40)
////					g.setColor(new Color(255,0,0,i));
////				else
////					if(i<80)
////						g.setColor(new Color(0,255,0,i));
////					else
////						if(i<120)
////							g.setColor(new Color(0,0,255,i));
////						else
////							if(i<160)
////								g.setColor(new Color(255,255,0,i));
////							else
////								if(i<200)
////									g.setColor(new Color(0,255,255,i));
////								else
////									if(i<240)
////										g.setColor(new Color(255,0,255,i));
////									else
////										g.setColor(new Color(130,175,130,i));
//
//				g.setColor(new Color(0,255,0,i));
//				
//				
//				//g.fillRect(xVal[i],yVal[i],1,1);
//				
//				if(i>0 && xVal[i-1]!=0)
//					g.drawLine(xVal[i], yVal[i], xVal[i-1], yVal[i-1]);
//				else
//				{
//					g.drawLine(xVal[i], yVal[i], 395, 395);
//				}
//			}
//		}
		
		for(int i=0;i<xVal2.length;i++)
		{
			if(xVal2[i]!=0)
			{

				g.setColor(new Color(0,255,0,i*3));
				
				
				if(i>0 && xVal2[i-1]!=0)
					g.drawLine(xVal2[i], yVal2[i], xVal2[i-1], yVal2[i-1]);
				else
				{
					g.drawLine(xVal2[i], yVal2[i], 395, 395);
				}
			}
		}
		
	}
	

}
