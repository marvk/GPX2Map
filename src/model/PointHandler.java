package model;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marvin on 11.06.2016.
 */
class PointHandler extends DefaultHandler {
    private static final String
            trackpointKey = "trkpt",
            latKey = "lat",
            lonKey = "lon";

    private final List<Point2D.Double> points;

    public PointHandler() {
        points = new ArrayList<>();
    }

    public List<Point2D.Double> getPoints() {
        return points;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (trackpointKey.equals(qName)) {
            double lat = Double.parseDouble(attributes.getValue(latKey));
            double lon = Double.parseDouble(attributes.getValue(lonKey));

            points.add(new Point2D.Double(lon, lat));
        }
    }
}
