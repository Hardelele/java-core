package io;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameField {

    public BufferedImage image;
    public BufferedImage cardsImage;
    public List<Card> cards = new ArrayList<>();

    public GameField(BufferedImage image) {
        this.image = image;
        this.cardsImage = image.getSubimage(80, 585, 370, 89);
        this.buildCardsList();
    }

    private void buildCardsList() {
        cards.add(new Card(cardsImage));
        cards.add(new Card(getSubImage(0)));
        cards.add(new Card(getSubImage(1)));
    }

    private BufferedImage getSubImage(int index) {
        int x = cards.get(index).endPoint;
        int y = 0;
        int width = cards.get(index).image.getWidth() - x;
        int height = cards.get(index).image.getHeight() - y;
        return cards.get(index).image.getSubimage(x, y, width, height);
    }
}