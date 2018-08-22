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
