package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.CutEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;

import java.util.List;

public class RubFragmentViewModel extends ViewModel {

    private RecipeDatabase database;

    private LiveData<List<RecipeEntry>> recipeList;

    public RubFragmentViewModel(RecipeDatabase database, long categoryId){
        this.database = database;
	    recipeList = this.database.getRecipeDao().loadRubForCategory(categoryId);
    }

    public LiveData<List<RecipeEntry>> getCutList() {
        return recipeList;
    }
}
