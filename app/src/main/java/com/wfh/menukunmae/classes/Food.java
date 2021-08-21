package com.wfh.menukunmae.classes;
import java.util.List;

public class Food {

    private String food_name;
    private List<String> food_ingredients;
    private List<String> cooking_method;
    private float food_cals;
    private String food_tutorial;
    private String food_img;

    public void setFood_name(String food_name){
        this.food_name = food_name;
    }

    public String getFood_name(){
        return food_name;
    }

    public void setFood_ingredients(List<String> ingredients){
        this.food_ingredients = ingredients;
    }

    public List<String> getFood_ingredients(){
        return food_ingredients;
    }

    public void setCooking_method(List<String> cooking_method) {
        this.cooking_method = cooking_method;
    }

    public List<String> getCooking_method() {
        return cooking_method;
    }

    public void setFood_cals(float food_cals) {
        this.food_cals = food_cals;
    }

    public float getFood_cals() {
        return food_cals;
    }

    public void setFood_img(String food_img) {
        this.food_img = food_img;
    }

    public String getFood_img() {
        return food_img;
    }

    public void setFood_tutorial(String food_tutorial) {
        this.food_tutorial = food_tutorial;
    }

    public String getFood_tutorial() {
        return food_tutorial;
    }

    @Override
    public String toString() {
        return "Food{" +
                "food_name='" + food_name + '\'' +
                ", food_ingredients=" + food_ingredients +
                ", cooking_method=" + cooking_method +
                ", food_cals=" + food_cals +
                ", food_tutorial=" + food_tutorial +
                ", food_img=" + food_img +
                '}';
    }
}
