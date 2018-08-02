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
	private long amount;
	private String unit;

	public IngredientEntry(long id, int recipeId, String name, long amount, String unit) {
		this.id = id;
		this.recipeId = recipeId;
		this.name = name;
		this.amount = amount;
		this.unit = unit;
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

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public static IngredientEntry[] populateData(){
		return new IngredientEntry[]{
				new IngredientEntry(1,1,"Flat Iron Steak",500,"g"),
				new IngredientEntry(2,1,"Salt",20,"g"),
				new IngredientEntry(3,1,"Pepper",20,"g")
		};
	}
}
