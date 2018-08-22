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

public class Pong extends PApplet {

private Ball b;
private Paddle p;
private Paddle2 p2;
private int score;
private boolean stage1, stage2, isPaused;
private PImage pongTitle;

public void setup()
{
  
  b = new Ball();
  p = new Paddle(670);
  p2 = new Paddle2(50);
  score = 0;
  stage1 = true;
  stage2 = isPaused = false;
  pongTitle = loadImage("PongTitle.png");
}

public void draw()
{
  background(150, 0, 150);
  if (stage1)
  {
    imageMode(CENTER);
    image(pongTitle, width/2, height/2);
    rectMode(CENTER);
    noFill();
    strokeWeight(5);
    rect(width/2, height/2, 650, 275);
    rectMode(CORNER);
    strokeWeight(1);
    textAlign(CENTER);
    textSize(30);
    fill(0);
    text("click to play", width/2, (height/2) + 125);
    fill(255);
    text("Press SpaceBar to Pause", width/2, (height/2) + 170);
    textAlign(CORNER);
    if (mousePressed)
    {
      if ((mouseX >= 162.5f) && (mouseX <= 812.5f) && (mouseY >= 222.5f) && (mouseY <= 497.5f))
      {
        stage1 = false;
        stage2 = true;
      }
    }
  } else if (stage2)
  {
    if (isPaused == false)
    {
      if (b.outsideGame())
      {
        score = 0;
        b.setX((width/2) + 1);
        b.setY(height/2);
      }
      b.move(p, p2, score);
      p.move();
      p2.move(b);
      if (b.insidePaddle(p))
      {
        score++;
      }
    }
    p2.display();
    b.display();
    p.display();
    textSize(32);
    fill(255);
    text("Score: " + score, 10, 30);
    if (isPaused)
    {
      rectMode(CENTER);
      noFill();
      strokeWeight(4);
      rect(width/2, (height/2) - 75, 200, 75);
      fill(175, 0, 175);
      rect(width/2, (height/2) - 75, 200, 75); 
      rect(width/2, (height/2) + 10, 200, 75);
      fill(175, 0, 175);
      rect(width/2, (height/2) + 10, 200, 75); 
      rectMode(CORNER);
      strokeWeight(1);
      fill(255);
      textAlign(CENTER);
      textSize(33);
      text("RESUME", width/2, (height/2) - 65);
      text("MAIN MENU", width/2, (height/2) + 20);
      textAlign(CORNER);
      if (mousePressed)
      {
        if ((mouseX >= 400) && (mouseX <= 600) && (mouseY >= 247.5f) && (mouseY <= 322.5f))
        {
          isPaused = false;
        }
        if ((mouseX >= 400) && (mouseX <= 600) && (mouseY >= 332.5f) && (mouseY <= 407.5f))
        {
          isPaused = false; 
          stage1 = true;
          stage2 = false;
          delay(100); //To stop the mouse from clicking the play button
        }
      }
    }
  }
}

public void keyPressed()
{
  if ((key == ' ') && (stage1 == false))
  {
    if (isPaused)
    {
      isPaused = false;
    } else if (isPaused == false)
    {
      isPaused = true;
    }
  }
}
public class Ball
{
  private float x, y;
  private float xVelocity, yVelocity;
  private int size;
  float score;
  
  public Ball()
  {
    x = (width / 2) + 1;
    y = height / 2;
    xVelocity = 5f;
    yVelocity = 5f;
    size = 15;
  }

  public void move(Paddle p, Paddle2 p2, float score)
  {
    x += xVelocity;
    y += yVelocity;
    if (x > (width - (size / 2)) || x < (0 + (size / 2)))
    {
      xVelocity *=-1;
    }
    if (y > (height - (size / 2)) || y < (0 + (size / 2)))
    {
      yVelocity *=-1;
    }

    if (insidePaddle(p))
    {
      yVelocity *= -1;
    }

    if (insidePaddle2(p2))
    {
      yVelocity *= -1;
    }
  }

  public void display()
  {
    fill(250);
    ellipse(x, y, size, size);
  }
  
  public boolean insidePaddle(Paddle p)
  {
    return ((x > (p.getX() - 7.5f) && x < (p.getX() + 77.5f) && y > (p.getY() - 7.5f) && y < (p.getY() + 22.5f)));
  }
  
  public boolean insidePaddle2(Paddle2 p2)
  {
    return (x > (p2.getX() - 7.5f) && x < (p2.getX() + 77.5f) && y > (p2.getY() - 7.5f) && y < (p2.getY() + 22.5f));
  }

  public float getX()
  {
    return x;
  }

  public boolean outsideGame()
  {
    return ((y < 7.5f) || (y > height - 7.5f));
  }
  
  public void setX(float x)
  {
    this.x = x;
  }
  
  public void setY(float y)
  {
    this.y = y;
  }
}
public class Paddle
{
  float x, y;
  int paddleLength;
  int paddleHeight;
  public Paddle(float y_)
  {
    paddleLength = 70;
    paddleHeight = 15;
    x = width / 2;
    y = y_;
  }

  public void display()
  {
    fill(255);
    rect(x, y, paddleLength, paddleHeight);
  }

  public void move()
  {
    if (keyPressed)
    {
      if (key == 'a')
      {
        x -= 7;
      }
      if (key == 'd')
      {
        x += 7;
      }
    }
    if (x < 0)
    {
      x = 0;
    }
    if ((x + 70) > width)
    {
      x = (width - 70);
    }
  }

  public float getX()
  {
    return x;
  }

  public float getY()
  {
    return y;
  }
}
public class Paddle2
{
  float x, y;
  int paddleLength;
  int paddleHeight;
  
  public Paddle2(float y_)
  { 
    paddleLength = 70;
    paddleHeight = 15;
    x = width / 2;
    y = y_;
  }
  
  public void display()
  {
    fill(255);
    rect(x, y, paddleLength, paddleHeight);
  }
  
  public void move(Ball b)
  {
    x = (b.getX() - 36);
    if (x < 0)
    {
      x = 0;
    }
    if ((x + 70) > width)
    {
      x = (width - 70);
    }
  }
  
  public float getX()
  {
    return x;
  }
  
  public float getY()
  {
    return y;
  }
}
  public void settings() {  size(1000, 720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Pong" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
