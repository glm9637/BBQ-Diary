package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.CategoryEntry;

public class CategoryViewModel extends ViewModel {

    private RecipeDatabase database;

    private LiveData<CategoryEntry> category;

    public CategoryViewModel(RecipeDatabase database, long categoryId){
        this.database = database;
        category = this.database.getCategoryDao().loadCategory(categoryId);
    }

    public LiveData<CategoryEntry> getCategory() {
        return category;
    }
}
