package com.joshdoctors.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.joshdoctors.gfx.Screen;

public class Item {
	public int x;
	public int y;
	public Rectangle bounds=new Rectangle();
	//0=grow,1=shrink,2=slowdown,3=speedup,4=reversecontrols,5=rotateboard,6=spawnball
	public int attribute;
	Random r = new Random();
	public boolean isAlive=false;
	
	public Item()
	{
		isAlive=true;
	}
	
	public void spawnItem()
	{
		x=r.nextInt(300)+300;
		y=r.nextInt(300)+300;
		
		bounds.setBounds(x, y, 50, 50);
		
		attribute=r.nextInt(7);
	}
	
	public void reset()
	{
		isAlive = false;
		r.setSeed(System.currentTimeMillis());
		x=r.nextInt(300)+300;
		y=r.nextInt(300)+300;
		
		bounds.setBounds(x, y, 50, 50);
		
		attribute=r.nextInt(7);
		isAlive = true;
	}
	
	
	
	public void draw(Graphics g)
	{
		if(isAlive)
		{
		
			g.drawImage(Screen.item_icons[attribute], bounds.x, bounds.y, bounds.width, bounds.height,null);
			
			if(attribute==5)
			{
				 g.setFont(new Font("tahoma",Font.BOLD,30));
					g.setColor(new Color(255,255,255));
					g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
					g.setColor(new Color(255,0,0));
					g.drawString(attribute+"",bounds.x+13,bounds.y+33);
			}
		}
	}

}
