public class IntGrid2D implements IIntGrid2D
{
  int ulx;
  int uly;
  int lrx;
  int lry;
  char[] values;
  int size;
  public IntGrid2D(int upperLeftX, int upperLeftY, int lowerRightX, int lowerRightY, char value)
  {
    ulx=upperLeftX;
    uly=upperLeftY;
    lrx=lowerRightX;
    lry=lowerRightY;
    size= (lowerRightX-upperLeftX+1)*(lowerRightY-upperLeftY+1);
    values = new char[size];
    for(int i=0;i<size;i++)
    {
      values[i]=value;
    }
  }
  /**
   * Sets the value at a point on the grid, replacing the previous value if any.
   * @param p The coordinate to set the value of
   * @param v The value to set at the coordinate
   */
  public void setPoint(IIntPoint2D p, char v)
  {
    int convX = 1+p.getX()-ulx;
    int convY = 1+p.getY()-uly;
    int convMaxX = 1+lrx-ulx;
    values[(convY-1)*convMaxX +convX -1] = v;//converts 2d to 1d and assigns value at 1d index
  }

  /**
   * Gets the value at a point on the grid
   * @param p The coordinate to get the value of
   * @returns the stored value
   */
  public char getPoint(IIntPoint2D p)
  {
    int convX = 1+p.getX()-ulx;
    int convY = 1+p.getY()-uly;
    int convMaxX = 1+lrx-ulx;
    return values[(convY-1)*convMaxX +convX -1]; //returns value at 1d index converted from 2d point
  }

  /**
   * Gets the coordinate for the upper left most location
   * @returns an IIntPoint that is the coordinate of the upper left corner
   */
  public IIntPoint2D getUpperLeftCorner()
  {
    return new IntPoint2D(ulx,uly);
  }

  /**
   * Gets the coordinate for the lower right most location
   * @returns an IIntPoint that is the lower right corner
   */
  public IIntPoint2D getLowerRightCorner()
  {
    return new IntPoint2D(lrx,lry);
  }
}
