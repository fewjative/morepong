package com.joshdoctors.sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public enum Sound {

	HIT("/res/sounds/hit.wav"),BOUNCE("/res/sounds/bounce.wav");

	


  private Sequence sequence;
  private Sequencer sequencer;
  private Thread thread;
  private Clip clip;
  private boolean isMidi = false;
  private int soundTime=1000,soundFrame=0,soundRun=0;
  private float newVol=0.0f;

  private Sound(String path) {
    URL url = Sound.class.getResource(path);
   
    try {
   	 System.out.println("in try");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
        DataLine.Info info = new DataLine.Info(Clip.class, audioIn.getFormat());
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(audioIn);
        audioIn.close();
      }
     catch (IOException e) {
      e.printStackTrace();
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    } catch (UnsupportedAudioFileException e) {
      e.printStackTrace();
    }
  }
  
  public void setVolume(float db)
  {
	  FloatControl gainControl = 
 		    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
 		gainControl.setValue(db); 
  }
  
  public float getVolume()
  {
	  FloatControl gainControl = 
 		    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
 		return gainControl.getValue();
  }

  public void play() {
    this.thread = new Thread() {
      public void run() {
       
          clip.start();
          int timer = 5;
          while (clip != null && clip.isActive()) {
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }    
      	}
	    };
	    thread.start();
   }
  
  public void fade()
  {
//	  while(soundRun <30)
//	  {
//		  
//			if(soundFrame >=soundTime)
//			{
//				setVolume(-50.0f);
//				soundFrame=0;
//			}
//			else
//			{
//				soundFrame+=1;
//				 soundRun++;
//			}
//	  
//		 
//	  }
	  while(getVolume() >-60.0f)
	  {
		 
		  if(soundFrame >=soundTime)
				{
					setVolume(getVolume()-0.1f);
					soundFrame=0;
				}
				else
				{
					soundFrame+=1;
					 soundRun++;
				}
	  		}
	//setVolume(-50.0f);
	  soundRun=0;
	 
  }

  
  
  public void playOnce() 
  {
	  play();
	  reset();
  }
  
  public boolean isActive()
  {
	  if(clip.isActive())
		  return true;
	  else return false;
  }

  public void loop() {
    if (this.thread == null) {
      reset();
      this.thread = new Thread() {
        public void run() {
          
            clip.loop(Clip.LOOP_CONTINUOUSLY);
          
        }
      };
      thread.start();
    }
  }

  public void stop() {
 
      clip.stop();

  }

  public void reset() {
    stop();
      clip.setMicrosecondPosition(0);
  }
}