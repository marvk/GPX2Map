package model;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marvin on 11.06.2016.
 */
public class Parser {
    private static final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

    private Parser() {
        //no instance
    }

    public static List<Point2D.Double> getPoints(Path path) {
        if (!Files.isRegularFile(path))
            return Collections.emptyList();

       try ( InputStream inputStream = new FileInputStream(path.toString())) {
           SAXParser parser = saxParserFactory.newSAXParser();
           PointHandler handler = new PointHandler();
           parser.parse(inputStream, handler);

           return handler.getPoints();
       } catch (IOException | SAXException | ParserConfigurationException e) {
           e.printStackTrace();
           return Collections.emptyList();
       }
    }

    public static List<Path> getFiles(String path) throws IOException {
        return Files.walk(Paths.get(path)).collect(Collectors.toList());
    }
}
