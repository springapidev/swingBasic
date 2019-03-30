package com.coderbd;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageOptimizer {

    public static void optimizeImage(String UPLOADED_FOLDER, File file, float quality, int width, int height) throws IOException {

        Path path = Paths.get(UPLOADED_FOLDER + file.getName());
        System.out.println("Path999: " + path);
        Path pathOut = Paths.get(UPLOADED_FOLDER + "new-" + file.getAbsolutePath());
        //create instance of File
        File optimizedImage = new File(pathOut.toString());
        File inputImage = new File(path.toString());
        //BufferedImage bufferedImage = ImageIO.read(Files.newInputStream(Paths.get(basePath + imageSource)));
        BufferedImage bufferedImage = ImageIO.read(Files.newInputStream(path));
        BufferedImage resizedBufferedImage = resize(bufferedImage, height, width);
        ImageIO.write(resizedBufferedImage, "jpg", optimizedImage);
        //at the end of optimization , delete original file
        File fi = new File(path.toString());
        fi.createNewFile();
        fi.delete();
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        return resized;
    }
}
