package com.example.projectandroid2019.Model.M_Category;

public class Category_Model {
    private String Name;
    private String Image;

    public Category_Model(String name, String image) {
        Name = name;
        Image = image;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

}
