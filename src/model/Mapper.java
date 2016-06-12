package model;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marvin on 12.06.2016.
 */
public class Mapper {
    private final List<Line2D.Double> lines;

    private final double
            minX,
            maxX,
            minY,
            maxY;

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Mapper(List<List<Point2D.Double>> pointsList) {
        if (pointsList.isEmpty())
            throw new IllegalArgumentException("Input cannot be empty");


        lines = pointsList
                .stream()
                .map(Lines::getLines)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<Point2D.Double> points = pointsList
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        minX = points.stream()
                     .map(Point2D.Double::getX)
                     .reduce(Math::min)
                     .get();

        maxX = points.stream()
                     .map(Point2D.Double::getX)
                     .reduce(Math::max)
                     .get();

        minY = points.stream()
                     .map(Point2D.Double::getY)
                     .reduce(Math::min)
                     .get();

        maxY = points.stream()
                     .map(Point2D.Double::getY)
                     .reduce(Math::max)
                     .get();
    }

    public BufferedImage map(int scale) {
        int offset = scale / 10;
        final int width = (int) ((maxX - minX) * scale) + offset * 2;
        final int height = (int) ((maxY - minY) * scale) + offset * 2;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = (Graphics2D) image.getGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setColor(new Color(0, 0, 0, 64));

        for (Line2D.Double l : lines) {
            int x1 = (int) ((l.getX1() - minX) * scale) + offset;
            int y1 = height - (int) ((l.getY1() - minY) * scale) - offset;
            int x2 = (int) ((l.getX2() - minX) * scale) + offset;
            int y2 = height - (int) ((l.getY2() - minY) * scale) - offset;

            g.drawLine(x1, y1, x2, y2);
        }

        return image;
    }

}
