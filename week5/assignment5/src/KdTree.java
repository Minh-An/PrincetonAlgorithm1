import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

public class KdTree {

    private static class Node {
        private Point2D p;      // the point
        private double cmp;    // the x or y value to compare with
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
    }

    private Node root;
    private int n;

    // construct an empty set of points
    public KdTree()
    {
        n = 0;
    }

    // is the set empty?
    public boolean isEmpty()
    {
        return root == null;
    }

    // number of points in the set
    public int size()
    {
        return n;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p)
    {
        if(p == null) throw new IllegalArgumentException();
        root = insert(root, p, 0);
        n++;
    }

    private Node insert(Node x, Point2D p, int level)
    {
        if(x == null)
        {
            Node node = new Node();
            node.p = p;
            if(level%2 == 0) { node.cmp = p.x();}
            else { node.cmp = p.y();}
            return node;
        }
        else if (!x.p.equals(p))
        {
            double cmp;
            if(level%2 == 0) { cmp = p.x() - x.cmp;}
            else { cmp = p.y() - x.cmp; }
            if(cmp < 0) x.lb = insert(x.lb, p, level+1);
            else x.rt = insert(x.rt, p, level+1);
        }
        else
        {
            n--;
        }
        return x;
    }

    // does the set contain point p?
    public boolean contains(Point2D p)
    {
        int level = 0;
        if(p == null) throw new IllegalArgumentException();
        Node x = root;
        while(x != null)
        {
            double cmp;
            if(level%2 == 0) { cmp = p.x() - x.cmp;}
            else { cmp = p.y() - x.cmp; }
            if(cmp < 0)
            {
                x = x.lb;
                level++;
            }
            else if (cmp > 0)
            {
                x = x.rt;
                level++;
            }
            else return true;
        }
        return false;
    }

    // draw all points to standard draw
    public void draw()
    {
        Node x = root;
        while(x != null)
        {
            double cmp;
            if(level%2 == 0) { cmp = p.x() - x.cmp;}
            else { cmp = p.y() - x.cmp; }
            if(cmp < 0)
            {
                x = x.lb;
                level++;
            }
            else if (cmp > 0)
            {
                x = x.rt;
                level++;
            }
            else return true;
        }
        return false;
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect)
    {
        if(rect == null) throw new IllegalArgumentException();
        Stack<Point2D> insideRect = new Stack<>();
        root = range(rect, root, insideRect, 0);
        return insideRect;
    }

    public Node range(RectHV rect, Node x, Stack<Point2D> stack, int level)
    {
        if(x != null) {
            if (rect.contains(x.p)) {
                stack.push(x.p);
            }
            double min;
            double max;
            if (level % 2 == 0) {
                max = rect.xmax();
                min = rect.xmin();
            } else {
                max = rect.ymax();
                min = rect.ymin();
            }
            if (max >= x.cmp) {
                x.rt = range(rect, x.rt, stack, level + 1);
            }
            if (min <= x.cmp) {
                x.lb = range(rect, x.lb, stack, level + 1);
            }
        }
        return x;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p)
    {
        if(p == null) throw new IllegalArgumentException();
        Point2D nearest = nearest(null, p, root, 0);
        return nearest;
    }

    public Point2D nearest(Point2D nearest, Point2D goal, Node x, int level)
    {
        if(x != null) {
            if (nearest == null || goal.distanceSquaredTo(nearest) > goal.distanceSquaredTo(x.p)) {
                nearest = x.p;
            }
            double cmp;
            if (level % 2 == 0) cmp = goal.x();
            else cmp = goal.y();

            Point2D oldNearest = new Point2D(nearest.x(), nearest.y());
            if (cmp >= x.cmp) {
                nearest = nearest(nearest, goal, x.rt, level + 1);
                if(nearest == null || oldNearest.equals(nearest))
                {
                    nearest = nearest(nearest, goal, x.lb, level + 1);
                }
            }
            else {
                nearest = nearest(nearest, goal, x.lb, level + 1);
                if(nearest == null || oldNearest.equals(nearest))
                {
                    nearest = nearest(nearest, goal, x.rt, level + 1);
                }
            }
        }
        return nearest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args)
    {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(0.1, 0.4));
        tree.insert(new Point2D(0.8, 0.6));
        tree.insert(new Point2D(0.1, 0.0));
        tree.insert(new Point2D(0.6, 0.5));
        tree.insert(new Point2D(0.6, 0.4));
        System.out.println(tree.nearest(new Point2D(0.6,0.3)));
        //for (Point2D p: tree.range(new RectHV(0.4,0.3,0.8,0.6))) {
          //  System.out.println(p);
        //}
    }
}
