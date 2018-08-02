package com.example.glm9637.myapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.NoteEntry;
import com.example.glm9637.myapplication.database.model.NoteEntryForList;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.StepListAdapter;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class NoteViewModel extends ViewModel {
	
	private LiveData<NoteEntry> note;
	private RecipeDatabase database;
	
	public NoteViewModel(Context context, long noteId){
		database = RecipeDatabase.getInstance(context);
		note = database.getNoteDao().loadNote(noteId);
	}
	
	public NoteViewModel(Context context){
		database = RecipeDatabase.getInstance(context);
	}
	
	public LiveData<NoteEntry> getNote() {
		return note;
	}
	
	public LiveData<NoteEntry> getNote(long noteId) {
		note = database.getNoteDao().loadNote(noteId);
		return note;
	}
	
	public void saveNote(NoteEntry noteEntry) {
		database.getNoteDao().insertNote(noteEntry);
	}
	
	public void updateNote(NoteEntry noteEntry){
		database.getNoteDao().updateNote(noteEntry);
	}
	
	public void deleteNote(NoteEntry noteEntry) {
		database.getNoteDao().deleteNote(noteEntry);
	}
}