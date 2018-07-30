package com.example.glm9637.myapplication.database.entry;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */

@Entity(tableName = "ingredient", foreignKeys = {
		@ForeignKey(entity = RecipeEntry.class,parentColumns = "id",childColumns = "recipe_id")
}, indices = {
		@Index(name = "IX_INGREDIENT_RECIPE_ID",value = "recipe_id")
})
public class IngredientEntry {
	
	@PrimaryKey()
	private long id;
	
	@ColumnInfo(name = "recipe_id")
	private int recipeId;
	
	private String name;
	private float amount;
	private String measure;

	public IngredientEntry(long id, int recipeId, String name, float amount, String measure) {
		this.id = id;
		this.recipeId = recipeId;
		this.name = name;
		this.amount = amount;
		this.measure = measure;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}
}
