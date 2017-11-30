import java.util.Scanner;

public class Board
{
IIntGrid2D board;
public int status=0;
  public Board()
  {
    board = new IntGrid2D(0,0,6,5,' '); //making a grid of 7 x 6, changed 2Dgrid to be left to right and top to down
  }
  public void playPoint(int x,char mark) //mark is either o or x
  {
    int y=5;

    for(int j=0; j<6; j++)
    {
      if(y==5)
      {
        if(this.board.getPoint(new IntPoint2D(x,j))!=' ')
        {
          y=j-1;
        }
      }
    }
    this.board.setPoint(new IntPoint2D(x,y),mark);
    checkWin(mark);
  }

  public void printBoard()
  {
    for(int j=0; j<7; j++)
    {
      if(j<6)
      {
        System.out.println("|"+this.board.getPoint(new IntPoint2D(0,j))+"|"+this.board.getPoint(new IntPoint2D(1,j))+"|"+this.board.getPoint(new IntPoint2D(2,j))+"|"+this.board.getPoint(new IntPoint2D(3,j))+"|"+this.board.getPoint(new IntPoint2D(4,j))+"|"+this.board.getPoint(new IntPoint2D(5,j))+"|"+this.board.getPoint(new IntPoint2D(6,j))+"|");
        System.out.println("+-+-+-+-+-+-+-+");
      }
      else
      {
        System.out.println("|1|2|3|4|5|6|7|");
      }

    }
  }

  private boolean checkWin(char mark)
  {
    boolean isGameOver=false;
    //loop through grid and check for horizontal connections
    for(int j=0; j<6; j++)
    {
      for(int i=3; i<7; i++)
      {
        if(this.board.getPoint(new IntPoint2D(i,j))==mark)
        {
          if(this.board.getPoint(new IntPoint2D(i-1,j))==mark)
          {
            if(this.board.getPoint(new IntPoint2D(i-2,j))==mark)
            {
              if(this.board.getPoint(new IntPoint2D(i-3,j))==mark)
              {
                //if there are 4 "o"s or "x"s in a row
                isGameOver=true;
              }
            }
          }
        }
      }
    }
    //loop through grid and check for vertical connections
    for(int i=0; i<7; i++)
    {
      for(int j=3; j<6; j++)
      {
        if(this.board.getPoint(new IntPoint2D(i,j))==mark)
        {
          if(this.board.getPoint(new IntPoint2D(i,j-1))==mark)
          {
            if(this.board.getPoint(new IntPoint2D(i,j-2))==mark)
            {
              if(this.board.getPoint(new IntPoint2D(i,j-3))==mark)
              {
                //if there are 4 "o"s or "x"s in a row vertically
                isGameOver=true;
              }
            }
          }
        }
      }
    }
    //loop through grid and check for top left to bottom right diagnol connections
    for(int k=0; k<3; k++)
    {
      for(int i=3; i<6-k; i++)
      {
        if(this.board.getPoint(new IntPoint2D(i,i+k))==mark)
        {
          if(this.board.getPoint(new IntPoint2D(i-1,i-1+k))==mark)
          {
            if(this.board.getPoint(new IntPoint2D(i-2,i-2+k))==mark)
            {
              if(this.board.getPoint(new IntPoint2D(i-3,i-3+k))==mark)
              {
                isGameOver=true;
              }
            }
          }
        }
      }
    }
    for(int k=0; k<3; k++)
    {
      for(int i=3; i<6-k; i++)
      {
        if(this.board.getPoint(new IntPoint2D(6-i,5-i-k))==mark)
        {
          if(this.board.getPoint(new IntPoint2D(6-i+1,5-i-k+1))==mark)
          {
            if(this.board.getPoint(new IntPoint2D(6-i+2,5-i-k+2))==mark)
            {
              if(this.board.getPoint(new IntPoint2D(6-i+3,5-i-k+3))==mark)
              {
                isGameOver=true;
              }
            }
          }
        }
      }
    }

    //loop through grid and check for top right to bottom left diagnol connections
    for(int k=0; k<3; k++)
    {
      for(int i=3; i<6-k; i++)
      {
        if(this.board.getPoint(new IntPoint2D(i,5-i-k))==mark)
        {
          if(this.board.getPoint(new IntPoint2D(i-1,5-i-k+1))==mark)
          {
            if(this.board.getPoint(new IntPoint2D(i-2,5-i-k+2))==mark)
            {
              if(this.board.getPoint(new IntPoint2D(i-3,5-i-k+3))==mark)
              {
                isGameOver=true;
              }
            }
          }
        }
      }
    }
    for(int k=0; k<3; k++)
    {
      for(int i=3; i<6-k; i++)
      {
        if(this.board.getPoint(new IntPoint2D(6-i,i+k))==mark)
        {
          if(this.board.getPoint(new IntPoint2D(6-i+1,i+k-1))==mark)
          {
            if(this.board.getPoint(new IntPoint2D(6-i+2,i+k-2))==mark)
            {
              if(this.board.getPoint(new IntPoint2D(6-i+3,i+k-3))==mark)
              {
                isGameOver=true;
              }
            }
          }
        }
      }
    }
    if(isGameOver)
    {
      status=1;
    }
    return isGameOver;

  }

  public static void main(String[] args)
  {
    Board b = new Board();
    char p = 'x';
    b.printBoard();
    while(b.status==0)
    {
      p=(p=='x')?'o':'x';
      Scanner reader = new Scanner(System.in);
      System.out.println("");
      System.out.println("Player "+p+" enter your x coordinate: ");
      int inp= reader.nextInt()-1;
      b.playPoint(inp,p);
      System.out.println("");
      b.printBoard();
    }
    if(b.status==1)
    {
      System.out.println("-------------------------");
      System.out.println("Game Over. Player "+p+" wins.");
      System.out.println("-------------------------");
    }
  }
}
