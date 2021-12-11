package com.example.fitnessapp;

public class FoodItem {

    String name , description ;
    float cal_present;

    public FoodItem(String name, String description, float cal_present) {
        this.name = name;
        this.description = description;
        this.cal_present = cal_present;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCal_present() {
        return cal_present;
    }

    public void setCal_present(float cal_present) {
        this.cal_present = cal_present;
    }

}
