package controller;

import javax.swing.*;
import java.awt.*;

/**
 * @author Leith Ahmad
 */
public class ImageResizer {
    /**
     * Method used to resize pictures
     * Then returns an ImageIcon
     * @param path path to image file
     * @param width width of image
     * @param height height of image
     * @return an ImageIcon
     */
    public static ImageIcon resize(String path, int width, int height) {
        ImageIcon backgroundPicture = new ImageIcon(path);
        Image image = backgroundPicture.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
