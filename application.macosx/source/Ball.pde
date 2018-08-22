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
    return ((x > (p.getX() - 7.5) && x < (p.getX() + 77.5) && y > (p.getY() - 7.5) && y < (p.getY() + 22.5)));
  }
  
  public boolean insidePaddle2(Paddle2 p2)
  {
    return (x > (p2.getX() - 7.5) && x < (p2.getX() + 77.5) && y > (p2.getY() - 7.5) && y < (p2.getY() + 22.5));
  }

  public float getX()
  {
    return x;
  }

  public boolean outsideGame()
  {
    return ((y < 7.5) || (y > height - 7.5));
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
