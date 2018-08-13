package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.CutEntry;

public class CutViewModel extends ViewModel {

	private final LiveData<CutEntry> cut;

	public CutViewModel(RecipeDatabase database, long cutId) {
		cut = database.getCutDao().loadCut(cutId);
	}

	public LiveData<CutEntry> getCut() {
		return cut;
	}
}
