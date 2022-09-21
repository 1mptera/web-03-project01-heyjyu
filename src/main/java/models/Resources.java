package models;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Resources {
    public static final Image BANKNOTE_IMAGE;
    public static final Image HEART_IMAGE;
    public static final Image MEDAL_IMAGE;
    public static final Image FILE_IMAGE;
    public static final Image X_IMAGE;
    public static final Image STAR_FILLED_IMAGE;
    public static final Image STAR_LINED_IMAGE;
    public static final Image STAR_IMAGE;

    static {
        try {
            BANKNOTE_IMAGE = ImageIO.read(new File("src/main/resources/images/iconmonstr-banknote-8-24.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            HEART_IMAGE = ImageIO.read(new File("src/main/resources/images/iconmonstr-favorite-4-24.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            MEDAL_IMAGE = ImageIO.read(new File("src/main/resources/images/iconmonstr-medal-3-24.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            STAR_FILLED_IMAGE = ImageIO.read(new File("src/main/resources/images/iconmonstr-star-filled-32.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            STAR_LINED_IMAGE = ImageIO.read(new File("src/main/resources/images/iconmonstr-star-lined-32.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            X_IMAGE = ImageIO.read(new File("src/main/resources/images/iconmonstr-x-mark-lined-32.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FILE_IMAGE = ImageIO.read(new File("src/main/resources/images/iconmonstr-file-20-24.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            STAR_IMAGE = ImageIO.read(new File("src/main/resources/images/iconmonstr-star-5-24.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
