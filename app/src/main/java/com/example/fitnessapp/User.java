package com.example.fitnessapp;

import java.util.ArrayList;

public class User {

    boolean flag;
    String name;
    String email,password;
    long height,weight,bmi,age;
    String gender;
    long breakfast_calories,evening_snacks_calories,lunch_calories,dinner_calories,total_calories;
    long total_duration_of_exercises,total_no_of_exercise,total_calories_burned;
    long no_of_glasses_water_intake;
    ArrayList<String> exercises_done;

    public long getAge() {
        return age;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public ArrayList<String> getExercises_done() {
        return exercises_done;
    }

    public void setExercises_done(ArrayList<String> exercises_done) {
        this.exercises_done = exercises_done;
    }

    public long getTotal_calories() {
        return total_calories;
    }

    public void setTotal_calories(long total_calories) {
        this.total_calories = total_calories;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getBmi() {
        return bmi;
    }

    public void setBmi(long bmi) {
        this.bmi = bmi;
    }

    public long getBreakfast_calories() {
        return breakfast_calories;
    }

    public void setBreakfast_calories(long breakfast_calories) {
        this.breakfast_calories = breakfast_calories;
    }

    public long getEvening_snacks_calories() {
        return evening_snacks_calories;
    }

    public void setEvening_snacks_calories(long evening_snacks_calories) {
        this.evening_snacks_calories = evening_snacks_calories;
    }

    public long getLunch_calories() {
        return lunch_calories;
    }

    public void setLunch_calories(long lunch_calories) {
        this.lunch_calories = lunch_calories;
    }

    public long getDinner_calories() {
        return dinner_calories;
    }

    public void setDinner_calories(long dinner_calories) {
        this.dinner_calories = dinner_calories;
    }

    public long getTotal_duration_of_exercises() {
        return total_duration_of_exercises;
    }

    public void setTotal_duration_of_exercises(long total_duration_of_exercises) {
        this.total_duration_of_exercises = total_duration_of_exercises;
    }

    public long getTotal_no_of_exercise() {
        return total_no_of_exercise;
    }

    public void setTotal_no_of_exercise(long total_no_of_exercise) {
        this.total_no_of_exercise = total_no_of_exercise;
    }

    public long getTotal_calories_burned() {
        return total_calories_burned;
    }

    public void setTotal_calories_burned(long total_calories_burned) {
        this.total_calories_burned = total_calories_burned;
    }

    public long getNo_of_glasses_water_intake() {
        return no_of_glasses_water_intake;
    }

    public void setNo_of_glasses_water_intake(long no_of_glasses_water_intake) {
        this.no_of_glasses_water_intake = no_of_glasses_water_intake;
    }
}
