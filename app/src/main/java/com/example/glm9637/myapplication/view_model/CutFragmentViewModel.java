package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.CutEntry;

import java.util.List;

public class CutFragmentViewModel extends ViewModel {

    private RecipeDatabase database;

    private LiveData<List<CutEntry>> cutList;

    public CutFragmentViewModel(RecipeDatabase database, long categoryId){
        this.database = database;
        cutList = this.database.getCutDao().loadCutList(categoryId);
    }

    public LiveData<List<CutEntry>> getCutList() {
        return cutList;
    }
}
