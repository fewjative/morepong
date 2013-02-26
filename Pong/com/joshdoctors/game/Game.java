package com.joshdoctors.game;

import javax.swing.*;

import com.joshdoctors.gfx.Screen;

import java.awt.*;
 
public class Game extends JFrame implements Runnable
{
  
   private static final long serialVersionUID = 1L;
	public static String title = "Pong Master";
   public static Dimension size = new Dimension(800, 800);
   public static KeyHandler k=new KeyHandler();
   public static Paddle myPaddle,comPaddle;
   public static Ball ball;
   public static Item item;
   public static int myScore,enemyScore;
   public static boolean pauseGame=false,resumeGame=false,reverseControls=false;
   public static int rotation=0;
   public static Thread gameloop;
   public static boolean inMenu=true;
   public static Menu menu;
 
    //public Thread thread = new Thread(this);
             
    public Game()
    {
   	
   	 Screen screen = new Screen(this);
       screen.setPreferredSize(size);
       setContentPane(screen);
     
       
       
   	  setTitle(title);
   	  pack();
   	  setResizable(false); // negates a lot of bugs
   	  setLocationRelativeTo(null); 
   	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.setFocusable(true);
        addKeyListener(k); 
        myPaddle =new Paddle(false);
     	  comPaddle =new Paddle(true);
     	  ball = new Ball(395,395);
     	  item = new Item();
     	  item.spawnItem();
     	  menu = new Menu();
         
       
    }
    
    public void start()
    {
 
   
 
    }
     

    public void init()
    {
       
       
    }
    
    public void run()
    {
   	 long frameStart,frameEnd=System.currentTimeMillis(),delta=0;
   	 
   	// long nsPerTick = 1000000000/60;
   	 long nsPerTick = 1000/60;
   	 
   	 while(true)
   	 {
   		 frameStart=System.currentTimeMillis();
   		 //----------------
   		 repaint(); 
   		 
   		 if(inMenu)
   		 {
   			 menu.update();
   		 }
   		 else
   		 {
	   		 ball.update();
	   		  if(k !=null)
	           {
	         	  k.update();
	         	  
	         	  
	           if(!reverseControls)
	           {
		           if(k.up)
		         	  myPaddle.moveUp();
		           
		           if(k.down)
		         	  myPaddle.moveDown();
	           }
	           else
	           {
	         	  if(k.up)
		         	  myPaddle.moveDown();
		           
		           if(k.down)
		         	  myPaddle.moveUp();  
	           }
	           
	           if(!k.up && !k.down)
	           {
	         	  myPaddle.isMovingUp=false;
	         	  myPaddle.isMovingDown=false;
	           }
	           
	           if(k.w)
	         	  ball.speedUp();
	           
	           if(k.s)
	         	  ball.slowDown();
	         	
	           if(k.p  && !pauseGame)
	           {
	         	  pauseGame = true;	
	         	  System.out.println("pause");
	         	  ball.pause();
	           }
	           
	           if(k.u && pauseGame)
	           {
	         	  pauseGame = false;	
	         	  System.out.println("unpause");
	         	  ball.unPause();
	           }
	           
	          
	           
	          if(k.e)
	         	 myPaddle.grow(1);
	          
	          if(k.q)
	         	 myPaddle.shrink(1);
	          
	        
	          
	           
	           }
	   		  
	   		  
	   		    		
	   		  
	   		  
	   		 comPaddle.moveAi();
   		 }
   		
   		 
   		

   		 
   		 
   		 //-------------------
   		 frameEnd = System.currentTimeMillis();
   		 delta = nsPerTick-(frameEnd-frameStart);
   		// System.out.println(delta);
   		 try
   		 {
   			 Thread.sleep(Math.abs(delta));
   		 }catch (InterruptedException e) {
				e.printStackTrace();
			}
   		 
   		 //System.out.println(delta);
   		setTitle("FPS: " + (1000/(delta))+" "+delta);
   	 }
    }
   	 
   	 

     			
                  
        
    
     
    public static void main(String args[])
    {
        //Game game = new Game();
   	 //    //public Thread thread = new Thread(this);
   	 gameloop = new Thread(new Game());
     	 gameloop.start();
    }
     
}