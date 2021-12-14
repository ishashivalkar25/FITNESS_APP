package com.example.fitnessapp;

public class FoodCategoryList {

    private String food_category ;
    double calculated_cal,total_cal;

    public FoodCategoryList(String food_category, double calculated_cal, double total_cal) {
        this.food_category = food_category;
        this.calculated_cal = calculated_cal;
        this.total_cal = total_cal;
    }

    public String getFood_category() {
        return food_category;
    }

    public void setFood_category(String food_category) {
        this.food_category = food_category;
    }

    public double getCalculated_cal() {
        return calculated_cal;
    }

    public void setCalculated_cal(float calculated_cal) {
        this.calculated_cal = calculated_cal;
    }

    public double getTotal_cal() {
        return total_cal;
    }

    public void setTotal_cal(float total_cal) {
        this.total_cal = total_cal;
    }
}

