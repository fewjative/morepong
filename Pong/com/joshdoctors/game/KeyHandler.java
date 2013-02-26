package com.joshdoctors.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	private boolean[] keys = new boolean[65536];
	public boolean up, down, left, right,esc,e,q,u,p,w,s,b,ent;
	
	public void update() {
		
		/*up = keys[KeyEvent.VK_UP]|| keys[KeyEvent.VK_W]; 
		down = keys[KeyEvent.VK_DOWN]|| keys[KeyEvent.VK_S]; 
		left = keys[KeyEvent.VK_LEFT]|| keys[KeyEvent.VK_A]; 
		right = keys[KeyEvent.VK_RIGHT]|| keys[KeyEvent.VK_D]; */
		
		up = keys[KeyEvent.VK_UP];
		w = keys[KeyEvent.VK_W]; 
		down = keys[KeyEvent.VK_DOWN];
		s =keys[KeyEvent.VK_S]; 
		left = keys[KeyEvent.VK_LEFT]|| keys[KeyEvent.VK_A]; 
		right = keys[KeyEvent.VK_RIGHT]|| keys[KeyEvent.VK_D]; 
		esc = keys[KeyEvent.VK_ESCAPE];
		e=keys[KeyEvent.VK_E];//grow
		q=keys[KeyEvent.VK_Q];//shrink
		u=keys[KeyEvent.VK_U];//unpause
		p=keys[KeyEvent.VK_P];//pause
		b=keys[KeyEvent.VK_B];//spawn ball
		ent=keys[KeyEvent.VK_ENTER];//enter
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		System.out.print("\nKeypressed");
	}

	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}

}