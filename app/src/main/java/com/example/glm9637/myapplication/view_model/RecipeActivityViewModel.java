package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;

import java.util.List;

public class RecipeActivityViewModel extends ViewModel{

    private final LiveData<RecipeEntry> recipe;
    private final LiveData<List<IngredientEntry>> ingredients;

    public RecipeActivityViewModel(Context context, long recipeId){
        RecipeDatabase database = RecipeDatabase.getInstance(context);
        recipe = database.getRecipeDao().loadRecipe(recipeId);
        ingredients = database.getIngredientDao().loadIngredients(recipeId);
    }

    public LiveData<RecipeEntry> getRecipe() {
        return recipe;
    }

    public LiveData<List<IngredientEntry>> getIngredients() {
        return ingredients;
    }
}
