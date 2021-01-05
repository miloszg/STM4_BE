package pl.milosz.demo.web.parameter;

import org.springframework.core.io.ClassPathResource;
import pl.milosz.demo.Point;
import sun.misc.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ParameterService {

    private static final String IMAGE_SOURCE = "map.jpg";
    private static final String BASE_PATH = "resources/";
    private static final String JPG = "jpg";

    public String getMapText(Point pLeftUpper, Point pRightLower) {
        return pLeftUpper.toString() + pRightLower.toString();
    }

    public byte[] getMapFull() throws IOException, URISyntaxException {
        File file = getFile();
        if (file == null) return null;

        FileInputStream image = new FileInputStream(file);

        return IOUtils.readFully(image, image.available(), true);
    }

    private File getFile() throws URISyntaxException, IOException {
        URL resource = getResource();

        File file = new File(resource.toURI());
        if (!file.exists()) {
            throw new MapNotFoundException("Failed to open resources.");
        }
        return file;
    }

    private URL getResource() throws IOException {
        URL resource = new ClassPathResource(IMAGE_SOURCE).getURL();
        if (resource == null) {
            throw new MapNotFoundException("Map not found in resources.");
        }
        return resource;
    }

    public byte[] getMapCropped(Point pLeftUpper, Point pRightLower) throws IOException {
        URL resource = getResource();
        BufferedImage image = ImageIO.read(resource);
        BufferedImage cropImage = cropImage(image, pLeftUpper, pRightLower);
        ByteArrayOutputStream oStream = new ByteArrayOutputStream();
        ImageIO.write(cropImage, JPG, oStream);
        return oStream.toByteArray();
    }

    private BufferedImage cropImage(BufferedImage src, Point pLU, Point pRL) {
        pLU.limit(0.0, 0.0, src.getWidth(), src.getHeight());
        pRL.limit(0.0, 0.0, src.getWidth(), src.getHeight());
        return src.getSubimage((int) pLU.getX(), (int) pLU.getY(),
                (int) (pRL.getX() - pLU.getX()), (int) (pRL.getY() - pLU.getY()));
    }

    private class MapNotFoundException extends RuntimeException {
        MapNotFoundException(String msg) {
            super(msg);
        }
    }
}
