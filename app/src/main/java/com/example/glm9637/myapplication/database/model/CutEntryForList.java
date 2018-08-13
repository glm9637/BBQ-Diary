package com.example.glm9637.myapplication.database.model;

import android.arch.persistence.room.ColumnInfo;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */

public class CutEntryForList {

	private final long id;

	private final String name;
	private final String description;
	private final String origin;
	@ColumnInfo(name = "recipe_count")
	private final int recipeCount;
	@ColumnInfo(name = "category_id")
	private final long categoryId;


	public CutEntryForList(long id, long categoryId, String name, String description, String origin, int recipeCount) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.origin = origin;
		this.recipeCount = recipeCount;
		this.categoryId = categoryId;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getOrigin() {
		return origin;
	}

	public int getRecipeCount() {
		return recipeCount;
	}

	public long getCategoryId() {
		return categoryId;
	}
}
