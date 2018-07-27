package com.example.glm9637.myapplication.database.entry;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */

@Entity(tableName = "recipe", foreignKeys = {
		@ForeignKey(entity = CategoryEntry.class, parentColumns = "id",childColumns = "category_id"),
		@ForeignKey(entity = CutEntry.class, parentColumns = "id",childColumns = "cut_id")
})
public class RecipeEntry {
	
	@PrimaryKey()
	@ColumnInfo(name = "recipe_id")
	private int id;
	
	@ColumnInfo(name = "category_id")
	private int categoryid;
	
	@ColumnInfo(name = "cut_id")
	private int cutId;
	
	private String name;
	@ColumnInfo(name = "short_description")
	private String shortDescription;
	private String description;
	@ColumnInfo(name = "cooking_style")
	private String cookingStyle;
	private long duration;
	
	@ColumnInfo(name = "is_seasoning")
	private boolean isSeasoning;
	
	
}
