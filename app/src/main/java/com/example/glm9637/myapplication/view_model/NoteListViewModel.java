package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.model.NoteEntryForList;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class NoteListViewModel extends ViewModel {
	
	private final LiveData<List<NoteEntryForList>> noteList;
	
	public NoteListViewModel(Context context, long recipeId) {
		RecipeDatabase database = RecipeDatabase.getInstance(context);
		noteList = database.getNoteDao().loadNoteListForRecipe(recipeId);
	}
	
	public LiveData<List<NoteEntryForList>> getNoteList() {
		return noteList;
	}
}
