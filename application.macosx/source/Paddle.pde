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
