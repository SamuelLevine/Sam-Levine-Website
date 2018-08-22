private Ball b;
private Paddle p;
private Paddle2 p2;
private int score;
private boolean stage1, stage2, isPaused;
private PImage pongTitle;

public void setup()
{
  size(1000, 720);
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
      if ((mouseX >= 162.5) && (mouseX <= 812.5) && (mouseY >= 222.5) && (mouseY <= 497.5))
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
        if ((mouseX >= 400) && (mouseX <= 600) && (mouseY >= 247.5) && (mouseY <= 322.5))
        {
          isPaused = false;
        }
        if ((mouseX >= 400) && (mouseX <= 600) && (mouseY >= 332.5) && (mouseY <= 407.5))
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
