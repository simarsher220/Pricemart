package com.example.simasingh.pricemart;

/**
 * Created by Sima Singh on 09-11-2016.
 */

public class MenShoe {
    private String name;
    private String image;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public MenShoe(){

    }

    public MenShoe(String name, String image, int price) {

        this.name = name;
        this.image = image;
        this.price = price;
    }
}
