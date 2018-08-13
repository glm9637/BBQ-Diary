package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 03.08.2018.
 */
public class IngredientFragmentViewModel {

	private final RecipeDatabase database;
	private LiveData<List<IngredientEntry>> data;

	public IngredientFragmentViewModel(Context context) {
		database = RecipeDatabase.getInstance(context);
	}

	public void setRecipeId(long recipeId) {
		data = database.getIngredientDao().loadIngredients(recipeId);
	}

	public LiveData<List<IngredientEntry>> getData() {
		return data;
	}
}
