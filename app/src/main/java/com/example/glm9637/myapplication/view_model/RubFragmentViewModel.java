package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;

import java.util.List;

public class RubFragmentViewModel extends ViewModel {

	private final LiveData<List<RecipeEntry>> recipeList;

	public RubFragmentViewModel(RecipeDatabase database, long categoryId) {
		recipeList = database.getRecipeDao().loadRubForCategory(categoryId);
	}

	public LiveData<List<RecipeEntry>> getRubList() {
		return recipeList;
	}
}
