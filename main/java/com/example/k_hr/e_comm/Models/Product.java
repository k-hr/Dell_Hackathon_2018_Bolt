package com.example.k_hr.e_comm.Models;

public class Product {
    private String Name;
    private String Image;
//    private String Image1;
//    private String Image2;
//    private String Name1;
//    private String Name2;
//    private String Price;
//    private String Review;


    public Product() {
    }

    public Product(String name, String image) {
        Name = name;
        Image = image;

    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}