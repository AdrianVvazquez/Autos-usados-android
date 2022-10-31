package com.example.contextmenu;

public class Auto {
    private int id;
    private String brand;
    private String name;
    private String model;
    private int cylinders;
    private int price;
    private int image;

    public Auto(int id, String brand, String name, String model, int cylinders, int price, int image) {
        super();
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.model = model;
        this.cylinders = cylinders;
        this.price = price;
        this.image = image;
    }

    public Auto() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBrand() { return brand; }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getCylinders() {
        return cylinders;
    }
    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getImage() { return image; }
    public void setImage(int image) {
        this.image = image;
    }

}