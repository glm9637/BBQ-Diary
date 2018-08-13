package com.example.glm9637.myapplication.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class NoteEntryForList {

	@PrimaryKey(autoGenerate = true)
	private final long id;

	@ColumnInfo(name = "recipe_id")
	private final long recipeId;

	private final String name;
	private final long date;

	public NoteEntryForList(long id, long recipeId, String name, long date) {
		this.id = id;
		this.recipeId = recipeId;
		this.name = name;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getDate() {
		return date;
	}

	public long getRecipeId() {
		return recipeId;
	}
}
