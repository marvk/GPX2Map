package model;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Marvin on 12.06.2016.
 */
class Lines {
    private Lines() {
        //no instance
    }

    static List<Line2D.Double> getLines(List<Point2D.Double> points) {
        if (points.size() <= 1)
            return Collections.emptyList();

        ArrayList<Line2D.Double> result = new ArrayList<>(points.size() - 1);

        Point2D.Double last = points.get(0);

        for (int i = 1; i < points.size(); i++) {
            Point2D.Double cur = points.get(i);
            result.add(new Line2D.Double(last, cur));
            last = cur;
        }

        return result;
    }
}
