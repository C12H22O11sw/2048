void setup() {
  size(532, 660);
  noStroke();
  for (int i = 0; i<10; i++)
    aktivGrotesk[i]=loadImage(i+"@3x.png");
  ellipseMode(CORNER);
}
PImage[] aktivGrotesk = new PImage[10];
int[][] values = new int[4][4];
boolean lastFrameKeyPressed;
boolean bla=false;
boolean thingything=false;
int blabla=1;
void displayInt(int Int, int x, int y) {
  String IntString = ""+Int;
  for (int i = 0; i<IntString.length(); i++) {
    image(aktivGrotesk[IntString.charAt(i)-48], x+i*25-(IntString.length()*15), y-20, 30, 40);
  }
}
void roundRect(int x, int y, int w, int h, int r) {
  rect(x+r, y, w-2*r, h);
  rect(x, y+r, w, h-2*r);
  ellipse(x, y, 2*r, 2*r);
  ellipse(x+w-2*r, y, 2*r, 2*r);
  ellipse(x, y+h-2*r, 2*r, 2*r);
  ellipse(x+w-2*r, y+h-2*r, 2*r, 2*r);
}
void draw() {
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
  int blabla=0;
  for (int x=0; x<4; x++)
    for (int y=0; y<4; y++) {
      blabla+=values[x][y];
      if (values[x][y]==0)
        fill(150, 150, 150);
      else if (values[x][y]==2)
        fill(230, 230, 230);
      else if (values[x][y]==4)
        fill(230, 230, 210);
      else if (values[x][y]==8)
        fill(230, 170, 100);
      else if (values[x][y]==16)
        fill(240, 150, 100);
      else if (values[x][y]==32)
        fill(240, 130, 100);
      else if (values[x][y]==64)
        fill(240, 100, 100);
      else if (values[x][y]==128)
        fill(230, 200, 120);
      else if (values[x][y]==256)
        fill(230, 200, 90);
      else if (values[x][y]==512)
        fill(230, 200, 60);
      else if (values[x][y]==1024)
        fill(230, 200, 30);
      else if (values[x][y]==2048)
        fill(230, 200, 0);
      else if (values[x][y]==4096)
        fill(240, 100, 100);
      else if (values[x][y]==8192)
        fill(240, 70, 70);
      roundRect(x*128+20, y*128+20, 108, 108, 5);
      fill(0, 0, 0);
      if (values[x][y]!=0)
        displayInt(values[x][y], x*128+76, y*128+74);
    }
  println(blabla);
  fill(26);
  roundRect(20, 532, 492, 108, 5);
  displayInt(blabla, width/2, height-72);
  lastFrameKeyPressed=keyPressed;
}