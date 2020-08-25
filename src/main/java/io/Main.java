package io;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
//        File imageFile = new File("C:/Users/harde/IdeaProjects/training-laboratory/src/main/java/io/img.png");
//        Card card = new Card(ImageIO.read(imageFile));
        File[] files = new File("C:/Users/harde/IdeaProjects/training-laboratory/src/main/java/io/images/").listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName());
                GameField gameField = new GameField(ImageIO.read(file));
            }
        }
    }
}
