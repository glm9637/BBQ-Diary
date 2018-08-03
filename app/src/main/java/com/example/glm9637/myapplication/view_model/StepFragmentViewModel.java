package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.StepEntry;

/**
 * Erzeugt von M. Fengels am 03.08.2018.
 */
public class StepFragmentViewModel {
	
	private LiveData<StepEntry> step;
	private final RecipeDatabase database;
	
	public StepFragmentViewModel(Context context){
		database = RecipeDatabase.getInstance(context);
	}
	
	public void setStepId(long stepId){
		step = database.getStepDao().loadStep(stepId);
	}
	
	public LiveData<StepEntry> getStep() {
		return step;
	}
}
