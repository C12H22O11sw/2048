import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_2048_2 extends PApplet {

public void setup() {
  
  noStroke();
  for (int i = 0; i<10; i++)
    aktivGrotesk[i]=loadImage(i+"-8.png");
  ellipseMode(CORNER);
  colors[0]=  color(150, 150, 150);
  colors[1]=  color(230, 230, 230);
  colors[2]=  color(230, 230, 210);
  colors[3]=  color(230, 170, 100);
  colors[4]=  color(240, 150, 100);
  colors[5]=  color(240, 130, 100);
  colors[6]=  color(240, 100, 100);
  colors[7]=  color(230, 200, 120);
  colors[8]=  color(230, 200, 90);
  colors[9]=  color(230, 200, 60);
  colors[10]=  color(230, 200, 30);
  colors[12]=  color(230, 200, 0);
  colors[13]=  color(240, 100, 100);
  colors[14]=  color(240, 70, 70);
  gameOver=loadImage("GameOverDisplay.gif");
}
int[] colors = new int[15];
PImage[] aktivGrotesk = new PImage[10];
PImage gameOver;
int[][] values = new int[4][4];
boolean lastFrameKeyPressed;
boolean bla=false;
boolean thingything=false;
int score=1;
int highScore=0;
public boolean canMakeAMove() {
  boolean returnValue=false;
  boolean scorebla[][] = new boolean[4][4];
  boolean holder;
  for (int x=0; x<4; x++)
    for (int y=0; y<4; y++) {
      holder=returnValue;
      if (x>0) {
        if (values[x-1][y]==values[x][y]||values[x-1][y]==0)
          returnValue=true;
      }      
      if (x<3) {
        if (values[x+1][y]==values[x][y]||values[x+1][y]==0)
          returnValue=true;
      }        
      if (y>0) {
        if (values[x][y-1]==values[x][y]||values[x][y-1]==0)
          returnValue=true;
      }      
      if (y<3) {
        if (values[x][y+1]==values[x][y]||values[x][y+1]==0)
          returnValue=true;
      }
      if (holder!=returnValue)
        scorebla[x][y]=true;
      else
        scorebla[x][y]=false;
    }

  return returnValue;
}
public void gameOver() {
  fill(255);
  rect(0,0,width,height);
  image(gameOver,0,0);
  displayInt(score,150+(""+score).length()*30,352);
}
public void displayInt(int Int, int x, int y) {
  String IntString = ""+Int;
  for (int i = 0; i<IntString.length(); i++) {
    image(aktivGrotesk[IntString.charAt(i)-48], x+i*25-(IntString.length()*15), y-20, 30, 40);
  }
}
public void roundRect(int x, int y, int w, int h, int r) {
  rect(x+r, y, w-2*r, h);
  rect(x, y+r, w, h-2*r);
  ellipse(x, y, 2*r, 2*r);
  ellipse(x+w-2*r, y, 2*r, 2*r);
  ellipse(x, y+h-2*r, 2*r, 2*r);
  ellipse(x+w-2*r, y+h-2*r, 2*r, 2*r);
}
public void draw() {
  if (canMakeAMove()) {
    fill(128, 128, 128);
    rect(0, 0, width, height);
    if (keyPressed) {
      bla = false;
      for (int i=0; i<4; i++) {
        if (key=='w') {
          for (int x=0; x<4; x++)
            for (int y=3; y>=0; y--) {
              if (y!=0) {
                if (values[x][y-1]==0) {
                  values[x][y-1]=values[x][y];
                  values[x][y]=0;
                } else if (values[x][y-1]==values[x][y]) {
                  values[x][y-1]=2*values[x][y];
                  values[x][y]=0;
                }
              }
            }
        } else if (key=='s') {
          for (int x=0; x<4; x++)
            for (int y=0; y<=3; y++) {
              if (y!=3) {
                if (values[x][y+1]==0) {
                  values[x][y+1]=values[x][y];
                  values[x][y]=0;
                } else if (values[x][y+1]==values[x][y]) {
                  values[x][y+1]=2*values[x][y];
                  values[x][y]=0;
                }
              }
            }
        }    
        if (key=='a') {
          for (int y=0; y<4; y++)
            for (int x=3; x>=0; x--) {
              if (x!=0) {
                if (values[x-1][y]==0) {
                  values[x-1][y]=values[x][y];
                  values[x][y]=0;
                } else if (values[x-1][y]==values[x][y]) {
                  values[x-1][y]=2*values[x][y];
                  values[x][y]=0;
                }
              }
            }
        } else if (key=='d') {
          for (int y=0; y<4; y++)
            for (int x=0; x<=3; x++) {
              if (x!=3) {
                if (values[x+1][y]==0) {
                  values[x+1][y]=values[x][y];
                  values[x][y]=0;
                } else if (values[x+1][y]==values[x][y]) {
                  values[x+1][y]=2*values[x][y];
                  values[x][y]=0;
                }
              }
            }
        }
      }
      thingything=false;
      boolean hasCheckedValue[][]=new boolean[4][4];
      while (!thingything&&!lastFrameKeyPressed) {
        int rand=(int)random(4);
        if (key=='w') {
          hasCheckedValue[rand][3]=true;
          if (values[rand][3]==0) {
            thingything=true;
            values[rand][3]=2;
          }
        } else if (key=='a') {
          hasCheckedValue[3][rand]=true;
          if (values[3][rand]==0) {
            thingything=true;
            values[3][rand]=2;
          }
        } else if (key=='s') {
          hasCheckedValue[rand][0]=true;
          if (values[rand][0]==0) {
            thingything=true;
            values[rand][0]=2;
          }
        } else if (key=='d') {
          hasCheckedValue[0][rand]=true;
          if (values[0][rand]==0) {
            thingything=true;
            values[0][rand]=2;
          }
        }
        boolean thing = true;
        for (int x=0; x<4; x++)
          for (int y=0; y<4; y++)
            if (values[x][y]==0)
              thing=false;
        thingything=thingything||thing;
      }
    }
    score=0;
    for (int x=0; x<4; x++)
      for (int y=0; y<4; y++) {
        score+=values[x][y];
        if (values[x][y]==0)
          fill(colors[0]);
        else
          fill(colors[(int)(log(values[x][y])/log(2))]);
        roundRect(x*128+20, y*128+20, 108, 108, 5);
        fill(0, 0, 0);
        if (values[x][y]!=0)
          displayInt(values[x][y], x*128+76, y*128+74);
      }

    fill(26);
    roundRect(20, 532, 492, 108, 5);
    displayInt(score, width/2, height-72);
    lastFrameKeyPressed=keyPressed;
  } else {
    gameOver();
  }
}
  public void settings() {  size(532, 660); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_2048_2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
