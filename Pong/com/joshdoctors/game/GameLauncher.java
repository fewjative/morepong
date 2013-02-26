package com.joshdoctors.game;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JApplet;
import javax.swing.JFrame;

import com.joshdoctors.gfx.Screen;

public class GameLauncher extends Applet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Game game= new Game();
	public static String title = "Pong Master";
   public static Dimension size = new Dimension(800, 800);
   public static KeyHandler k=new KeyHandler();
   public static Screen screen;
   public static Frame frame;
   
   @Override
   public void init()
   {
   	add(game,BorderLayout.CENTER);
   }
   
   @Override
   public void start()
   {
   	game.start();
   }

   public void run()
   {
   	game.run();
  
   }
   
   @Override
   public void stop()
   {
   	
   }
	  public static void main(String args[])
	    {
		
        
   
        
		  game.setTitle(title);
		  game.pack();
		  game.setResizable(false); // negates a lot of bugs
		  game.setLocationRelativeTo(null); 
		  game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  game.setVisible(true);
		  game.setFocusable(true);
		  game.addKeyListener(k); 
		  game.myPaddle =new Paddle(false);
		  game.comPaddle =new Paddle(true);
		  game.ball = new Ball(395,395);
		  game.item = new Item();
		  game.item.spawnItem();
		  
		  screen = new Screen(game);
        screen.setPreferredSize(size);
        
        game.setContentPane(screen);
         
     	  
	   	// Thread gameloop = new Thread(new Game());
	   	 game.start();

	   	 
	    }
}
