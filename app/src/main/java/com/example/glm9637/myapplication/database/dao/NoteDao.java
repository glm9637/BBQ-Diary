package com.example.glm9637.myapplication.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.glm9637.myapplication.database.entry.NoteEntry;
import com.example.glm9637.myapplication.database.model.NoteEntryForList;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
@Dao
public interface NoteDao {

	@Query("SELECT id, recipe_id, name, date FROM note WHERE recipe_id = :recipeId")
	LiveData<List<NoteEntryForList>> loadNoteListForRecipe(long recipeId);

	@Query("SELECT * FROM note WHERE id = :noteId")
	LiveData<NoteEntry> loadNote(long noteId);

	@Update
	void updateNote(NoteEntry noteEntry);

	@Insert
	void insertNote(NoteEntry noteEntry);

	@Delete
	void deleteNote(NoteEntry noteEntry);

	@Insert
	void insertAll(NoteEntry[] cutEntries);
}
