package com.example.urbanlegendexplorer.ui;

import java.util.Random;

public class FloatingSymbol {

    float x;
    float y;
    float speed;
    int size;
    int alpha;
    int imageIndex;

    public FloatingSymbol(float x, float y, float speed, int size, int alpha, int imageIndex) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
        this.alpha = alpha;
        this.imageIndex = imageIndex;
    }

    public void update(int width, int height, Random random, int imageCount) {
        y -= speed;
        x += Math.sin(y * 0.01f) * 0.4f;

        if (y < -size) {
            y = height + random.nextInt(300);
            x = random.nextInt(Math.max(width, 1));
            speed = 0.5f + random.nextFloat() * 1.5f;
            size = 45 + random.nextInt(45);
            alpha = 45 + random.nextInt(80);
            imageIndex = random.nextInt(imageCount);
        }
    }
}