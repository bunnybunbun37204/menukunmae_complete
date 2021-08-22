package com.wfh.menukunmae.classes;

import java.util.List;

public class Ingredient {
    private List<String> ingredients;

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredients='" + ingredients +
                '}';
    }
}
