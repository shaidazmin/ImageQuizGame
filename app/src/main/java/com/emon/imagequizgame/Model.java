package com.emon.imagequizgame;

public class Model {
    String name;
    int image;

    public Model(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
