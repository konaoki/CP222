/**
 * Created by Kona on 11/27/2017.
 */
public class IntPoint2D implements IIntPoint2D{
    int x;
    int y;

    public IntPoint2D(int X, int Y)
    {
        x=X;
        y=Y;
    }
    /**
     * @returns the X coordinate
     */
    public int getX()
    {
        return x;
    }
    /**
     * @returns the Y coordinate
     */
    public int getY()
    {
        return y;
    }

    /**
     * Computes the manhattan distance to another point.
     * formula: abs(x1-x2) + abs(y1-y2)
     * @param o the other point
     * @returns the manhattan distance
     */
    public int manhattanDistance(IIntPoint2D o)
    {
        return Math.abs(o.getX()-x)+Math.abs(o.getY()-y);
    }
    /**
     * Computes the euclidean distance to another point.
     * formula: sqrt((x1-x2)^2 + (y1-y2)^2)
     * @param o the other point
     * @returns the euclidean distance
     */
    public double distance(IIntPoint2D o)
    {
        return Math.sqrt(Math.pow(o.getX()-x,2)+Math.pow(o.getY()-y,2));
    }

    // The following methods make JAVA objects better behaved
    /**
     * Converts a point object to a string of the form (X,Y)
     * @returns human friendly point representation
     */
    public String toString()
    {
        return "("+x+","+y+")";
    }
    /**
     * Determines if this point is the same as another (i.e. x1=x2 and y1=y2)
     * @returns true if this point is the same as point o
     */
    public boolean equals(IIntPoint2D o)
    {
        return (o.getX()==x && o.getY()==y)?true:false;
    }
    /**
     * Hashcode to support some standard library data structures
     * formula: (x<<16)+y
     * @returns the hashcode for the point
     */
    public int hashcode()
    {
        return (x<<16)+y;
    }
}
