package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.CategoryEntry;
import com.example.glm9637.myapplication.database.entry.CutEntry;

public class CutViewModel extends ViewModel {

    private RecipeDatabase database;

    private LiveData<CutEntry> cut;

    public CutViewModel(RecipeDatabase database, long cutId){
        this.database = database;
        cut = this.database.getCutDao().loadCut(cutId);
    }

    public LiveData<CutEntry> getCut() {
        return cut;
    }
}
