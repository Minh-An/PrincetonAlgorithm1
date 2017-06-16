import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;


public class FastCollinearPoints {

    private final ArrayList<LineSegment> segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points)
    {
        if (points == null) throw new NullPointerException();

        int n = points.length;
        Point[] tmp = new Point[n];
        for (int i = 0; i < n; i++)
        {
            tmp[i] = points[i];
        }
        Arrays.sort(tmp);

        for (int i = 0; i < n; i++)
        {
            if (tmp[i] == null) throw new NullPointerException();
            if (i != 0 && tmp[i].compareTo(tmp[i-1]) == 0) throw new IllegalArgumentException();
        }

        segments = new ArrayList<>();

        if (tmp.length < 4)
        {
            return;
        }


        for (int i = 0; i < n; i++)
        {
            Point p = points[i];
            Comparator<Point> pointComparator = p.slopeOrder();
            Arrays.sort(tmp);
            Arrays.sort(tmp, pointComparator);

            for (int j = 0; j < tmp.length-2; j++) {
                double slope = p.slopeTo(tmp[j]);
                //System.out.printf("%f%n", slope);
                if (slope == p.slopeTo(tmp[j+1]) && p.slopeTo(tmp[j+1]) == p.slopeTo(tmp[j+2]))
                {
                    ArrayList<Point> collinearPoints = new ArrayList<>(Arrays.asList(p, tmp[j], tmp[j+1], tmp[j+2]));
                    int k = j+3;
                    while (k < tmp.length && p.slopeTo(tmp[k]) == slope)
                    {
                        collinearPoints.add(tmp[k]);
                        k++;
                    }
                    //System.out.println(collinearPoints.toString());
                    if(isSorted(collinearPoints))
                    {
                        segments.add(new LineSegment(p, tmp[k-1]));
                    }
                    j = k-1;
                }
            }

        }

    }

    private boolean isSorted(ArrayList<Point> list)
    {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1).compareTo(list.get(i)) > 0) return false;
        }

        return true;
    }

    // the number of line segments
    public int numberOfSegments()
    {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments()
    {
        return segments.toArray(new LineSegment[segments.size()]);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        System.out.println(collinear.numberOfSegments());
    }
}