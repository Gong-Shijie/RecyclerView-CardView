package com.example.recyclev;

public class Fruit {
    private int f_image;
    private String f_name;

    public Fruit(int f_image, String f_name) {
        this.f_image = f_image;
        this.f_name = f_name;
    }

    public int getF_image() {
        return f_image;
    }

    public void setF_image(int f_image) {
        this.f_image = f_image;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }
}
