package com.example.glm9637.myapplication.database.entry;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */

@Entity(tableName = "step", foreignKeys = {
		@ForeignKey(entity = RecipeEntry.class,parentColumns = "id",childColumns = "recipe_id")
})
public class StepEntry {

	@PrimaryKey()
	private long id;
	
	@ColumnInfo(name = "recipe_id")
	private long recipeId;
	private int order;
	private String name;
	private String description;
	
	
}
