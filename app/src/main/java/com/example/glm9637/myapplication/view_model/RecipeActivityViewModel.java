package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.CutEntry;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.database.entry.StepEntry;

import java.util.List;

public class RecipeActivityViewModel extends ViewModel{

    private RecipeDatabase database;

    private LiveData<RecipeEntry> recipe;
    private LiveData<List<IngredientEntry>> ingredients;
    private LiveData<List<StepEntry>> steps;

    public RecipeActivityViewModel(Context context, long recipeId){
        this.database = RecipeDatabase.getInstance(context);
        recipe = this.database.getRecipeDao().loadRecipe(recipeId);
        ingredients = this.database.getIngredientDao().loadIngredients(recipeId);
        steps = this.database.getStepDao().loadStepList(recipeId);
    }

    public LiveData<RecipeEntry> getRecipe() {
        return recipe;
    }

    public LiveData<List<IngredientEntry>> getIngredients() {
        return ingredients;
    }

    public LiveData<List<StepEntry>> getSteps() {
        return steps;
    }
}
