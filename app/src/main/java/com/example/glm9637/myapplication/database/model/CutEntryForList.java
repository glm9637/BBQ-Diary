package com.example.glm9637.myapplication.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */

public class CutEntryForList {
	
	private long id;
	
	private String name;
	private String description;
	private String origin;
	@ColumnInfo(name = "recipe_count")
	private int recipeCount;
	
	
	public CutEntryForList(long id, String name, String description, String origin, int recipeCount) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.origin = origin;
		this.recipeCount = recipeCount;
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
}
