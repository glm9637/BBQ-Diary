package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.CutEntry;

public class CategoryViewModel extends ViewModel {

    private RecipeDatabase database;

    private LiveData<CutEntry> cut;

    public CategoryViewModel(RecipeDatabase database, long categoryId){
        this.database = database;
        cut = this.database.getCategoryDao().loadCategory(categoryId);
    }

    public LiveData<CutEntry> getCut() {
        return cut;
    }
}
