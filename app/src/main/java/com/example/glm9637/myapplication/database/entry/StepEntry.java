package com.example.glm9637.myapplication.database.entry;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */

@Entity(tableName = "step", foreignKeys = {
		@ForeignKey(entity = RecipeEntry.class,parentColumns = "id",childColumns = "recipe_id")
}, indices = {
		@Index(name = "IX_STEP_RECIPE_ID",value = "recipe_id"),
})
public class StepEntry {

	@PrimaryKey()
	private long id;
	
	@ColumnInfo(name = "recipe_id")
	private long recipeId;
	@ColumnInfo(name = "step_order")
	private int order;
	private String name;
	private String description;

	public StepEntry(long id, long recipeId, int order, String name, String description) {
		this.id = id;
		this.recipeId = recipeId;
		this.order = order;
		this.name = name;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
