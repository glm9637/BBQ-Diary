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
		@ForeignKey(entity = RecipeEntry.class, parentColumns = "id", childColumns = "recipe_id")
}, indices = {
		@Index(name = "IX_STEP_RECIPE_ID", value = "recipe_id"),
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
	private long duration;
	
	public StepEntry(long id, long recipeId, int order, String name, String description, long duration) {
		this.id = id;
		this.recipeId = recipeId;
		this.order = order;
		this.name = name;
		this.description = description;
		this.duration = duration;
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
	
	public long getDuration() {
		return duration;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public static StepEntry[] populateData() {
		return new StepEntry[]{
				new StepEntry(1, 1, 1, "Trimming", "Trim down the fat on the outside", 5),
				new StepEntry(2, 1, 1, "Searing", "Sear the Steak from both sides", 4),
				new StepEntry(3, 1, 1, "Seasoning", "Get the meat off the grill and season it from both sides", 1),
				new StepEntry(4, 1, 1, "Finishing", "Put the steak in indirekt heat of your grill, till ich reaches 57°C", 10)
		};
	}
}
