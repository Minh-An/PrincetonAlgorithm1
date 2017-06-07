import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import javax.sound.sampled.Line;

public final class BruteCollinearPoints {

    private final ArrayList<LineSegment> segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)
    {
        if (points == null) throw new NullPointerException();

        int n = points.length;
        Point[] tmp = new Point[n];
        for (int i = 0; i < n; i++)
        {
            tmp[i] = points[i];
        }
        Arrays.sort(tmp);

        segments = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            if (tmp[i] == null) throw new NullPointerException();
            if (i != 0 && tmp[i].compareTo(tmp[i-1]) == 0) throw new IllegalArgumentException();
        }

        if (points.length < 4)
        {
            return;
        }

        for(int i = 0; i < n; i++)
        {
            for(int j = i+1; j<n; j++)
            {
                for(int k = j +1; k<n; k++)
                {
                    if (tmp[i].slopeTo(tmp[k]) ==  tmp[i].slopeTo(tmp[j]))
                    {
                        for (int l = k + 1; l < n; l++) {
                            if (tmp[i].slopeTo(tmp[j]) == tmp[i].slopeTo(tmp[l])) {
                                segments.add(new LineSegment(tmp[i], tmp[l]));
                            }
                        }
                    }
                }
            }
        }

    }

    // the number of line segments
    public  int numberOfSegments()
    {
        return segments.size();
    }

    // the line segments
    public  LineSegment[] segments()
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
