package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.StepEntry;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class RecipeStepsViewModel {

	
	LiveData<List<StepEntry>> steps;
	
	public RecipeStepsViewModel(Context context, long recipeId){
		RecipeDatabase database = RecipeDatabase.getInstance(context);
		steps = database.getStepDao().loadStepList(recipeId);
	}
	
	public LiveData<List<StepEntry>> getSteps() {
		return steps;
	}
}
