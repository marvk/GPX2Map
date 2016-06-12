import model.Mapper;
import model.Parser;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marvin on 11.06.2016.
 */
public class Main {
    private static final String path = "data";

    public static void main(String[] args) throws IOException {
        List<List<Point2D.Double>> points = Parser
                .getFiles(path)
                .stream()
                .map(Parser::getPoints)
                .filter(e -> !e.isEmpty())
                .collect(Collectors.toList());

        Mapper mapper = new Mapper(points);
        BufferedImage map = mapper.map(5000);

        ImageIO.write(map, "png", new File("out.png"));
    }
}
