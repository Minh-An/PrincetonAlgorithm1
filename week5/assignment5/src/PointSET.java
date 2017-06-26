import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Stack;

public class PointSET {

    private RedBlackBST<Point2D, Integer> points;
    private int n;

    // construct an empty set of points
    public PointSET()
    {
        points = new RedBlackBST();
        n = 0;
    }

    // is the set empty?
    public boolean isEmpty()
    {
        return n == 0;
    }
    // number of points in the set
    public int size()
    {
        return n;
    }
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p)
    {
        points.put(p, ++n);
    }
    // does the set contain point p?
    public boolean contains(Point2D p)
    {
        if(p == null) throw new IllegalArgumentException();
        return points.contains(p);
    }
    // draw all points to standard draw
    public void draw()
    {
        for(Point2D p: points.keys())
        {
            p.draw();
        }
    }
    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect)
    {
        if(rect == null) throw new IllegalArgumentException();
        Stack<Point2D> insideRect = new Stack<>();
        for (Point2D point: points.keys())
        {
            if (rect.contains(point))
            {
                insideRect.push(point);
            }
        }
        return insideRect;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p)
    {
        if(p == null) throw new IllegalArgumentException();
        Point2D nearest = null;
        for (Point2D point: points.keys())
        {
            if (nearest == null || point.distanceTo(p) < nearest.distanceTo(p))
            {
                nearest = point;
            }
        }
        return nearest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args)
    {

    }
}