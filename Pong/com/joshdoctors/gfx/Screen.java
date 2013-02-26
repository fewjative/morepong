package com.joshdoctors.gfx;

import java.awt.*;
import java.awt.image.*;
import java.io.File;

import javax.swing.*;

import com.joshdoctors.game.Game;
import com.joshdoctors.game.KeyHandler;
import com.joshdoctors.game.Paddle;


public class Screen extends JPanel
{
	private static final long serialVersionUID = 1L;

	public int myWidth=810;
	public int myHeight=810;
	
	
	Paddle myPaddle,comPaddle;
	
     
    public static Image[] item_icons = new Image[100]; // 100 images

     
    
    public boolean isFirst=true;
    
    public static Point mse = new Point(0,0);
     
    public Game game;
    
    
    public Screen(Game game)
    {
   	 this.game = game;	
    }
     
     
    public void define()
    {
        
        ///projectile[1] = new ImageIcon("res/projecttilecannon.png").getImage();
   	 item_icons[0] = new ImageIcon("res/img/pong_item_growpaddle.png").getImage();
   	 item_icons[1] = new ImageIcon("res/img/pong_item_shrinkpaddle.png").getImage();
   	 item_icons[2] = new ImageIcon("res/img/pong_item_slowball.png").getImage();
   	 item_icons[3] = new ImageIcon("res/img/pong_item_speedball.png").getImage();
   	 item_icons[4] = new ImageIcon("res/img/pong_item_reverse.png").getImage();
   	 item_icons[6] = new ImageIcon("res/img/pong_item_moreballs.png").getImage();
 
    }
    
   
    
    public void paintComponent(Graphics g)
    {
   	
   	 super.paintComponent(g);
   	 
		
        if(isFirst)
        {
      	  
            define();
          
            isFirst = false;
        }
        
        
        
       
        g.setColor(new Color(0,0,0));
  		  g.fillRect(0,0,g.getClipBounds().width,g.getClipBounds().height);
  		  if(Game.inMenu)
  		  {
  		  Game.menu.draw(g);
  		  }
  		  else
  		  {
	  		  g.setColor(new Color(255,255,255));
	  		  if(Game.rotation==0)
	  			  g.drawLine(405, 0, 405, 810);
	  		  
	        Game.myPaddle.draw(g);
	        Game.comPaddle.draw(g);
	       // Game.myPaddle.drawRotate(g);
	        Game.ball.draw(g);
	        Game.ball.drawTail(g);
	        
	        if(Game.item.isAlive)
	      	  Game.item.draw(g);
	        
	        g.setFont(new Font("tahoma",Font.BOLD,72));
	        g.setColor(new Color(255,255,255));
	        g.drawString(Game.myScore+"",345, 100);
	        g.drawString(Game.enemyScore+"",415, 100);
  		  }
        
        
        
    }
       
    
}
