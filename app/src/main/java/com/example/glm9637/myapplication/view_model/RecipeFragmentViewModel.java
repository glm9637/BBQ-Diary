package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 01.08.2018.
 */
public class RecipeFragmentViewModel extends ViewModel {
	
	private LiveData<List<RecipeEntry>> recipeList;
	
	public RecipeFragmentViewModel(Context context,long cutId){
		RecipeDatabase database = RecipeDatabase.getInstance(context);
		recipeList = database.getRecipeDao().loadRecipeForCut(cutId);
	}
	
	public LiveData<List<RecipeEntry>> getRecipeList() {
		return recipeList;
	}
}
