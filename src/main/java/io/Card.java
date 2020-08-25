package io;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Card {
    public BufferedImage image;
    public int[] startPoint;
    public int endPoint = 0;
    public int[] signature;
    public Entry entry;

    public Card(BufferedImage image) {
        this.image = image;
        this.startPoint = findStartPoint();
        this.signature = getSignature();
        for (int x : signature) {
            if (x > endPoint) {
                this.endPoint = x;
            }
        }
        int exitPoint = ((endPoint - startPoint[0]) / 3) + startPoint[0];
        entry = new Entry(signature, exitPoint);
        debug();
    }

    @SneakyThrows
    private void debug() {
        AtomicInteger y = new AtomicInteger();
        Arrays.stream(entry.suitSignature).forEach(x -> {
            image.setRGB(x, y.get(), Color.red.getRGB());
            y.getAndIncrement();
        });
        ImageIO.write(image, "png", new File("C:/Users/harde/IdeaProjects/training-laboratory/src/main/java/io/results/" + entry.suit + "result" + this.hashCode() + ".png"));
        System.out.println(entry.suit);
    }

    private int[] findStartPoint() {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));
                if (color.getRGB() == Color.white.getRGB()) {
                    return new int[]{x, y};
                }
            }
        }
        return new int[]{image.getWidth(), image.getHeight()};
    }

    private int[] getSignature() {
        int length = image.getHeight() - startPoint[1];
        int[] signature = new int[length];
        for (int counter = 0; counter < length; counter++) {
            int y = counter + startPoint[1];
            for (int x = startPoint[0]; x < image.getWidth(); x++) {
                if (image.getRGB(x, y) != Color.white.getRGB()) {
                    signature[counter] = x;
                    break;
                }
            }
        }
        return signature;
    }
}
