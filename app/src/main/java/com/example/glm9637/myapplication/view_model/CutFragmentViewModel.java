package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.model.CutEntryForList;

import java.util.List;

public class CutFragmentViewModel extends ViewModel {

	private final LiveData<List<CutEntryForList>> cutList;

	public CutFragmentViewModel(RecipeDatabase database, long categoryId) {
		cutList = database.getCutDao().loadCutList(categoryId);
	}

	public LiveData<List<CutEntryForList>> getCutList() {
		return cutList;
	}
}
