package com.example.glm9637.myapplication.database.entry;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */

@Entity(tableName = "recipe", foreignKeys = {
		@ForeignKey(entity = CategoryEntry.class, parentColumns = "id",childColumns = "category_id"),
		@ForeignKey(entity = CutEntry.class, parentColumns = "id",childColumns = "cut_id")
}, indices = {
		@Index(name = "IX_RECIPE_CATEGORY_ID",value = "category_id"),
		@Index(name = "IX_RECIPE_CUT_ID",value = "cut_id")
})
public class RecipeEntry {
	
	@PrimaryKey(autoGenerate = true)
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


	public RecipeEntry(int id, int categoryid, int cutId, String name, String shortDescription, String description, String cookingStyle, long duration, boolean isSeasoning) {
		this.id = id;
		this.categoryid = categoryid;
		this.cutId = cutId;
		this.name = name;
		this.shortDescription = shortDescription;
		this.description = description;
		this.cookingStyle = cookingStyle;
		this.duration = duration;
		this.isSeasoning = isSeasoning;
	}
	
	@Ignore
	public RecipeEntry(int categoryid, int cutId, String name, String shortDescription, String description, String cookingStyle, long duration, boolean isSeasoning) {
		this.categoryid = categoryid;
		this.cutId = cutId;
		this.name = name;
		this.shortDescription = shortDescription;
		this.description = description;
		this.cookingStyle = cookingStyle;
		this.duration = duration;
		this.isSeasoning = isSeasoning;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public int getCutId() {
		return cutId;
	}

	public void setCutId(int cutId) {
		this.cutId = cutId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCookingStyle() {
		return cookingStyle;
	}

	public void setCookingStyle(String cookingStyle) {
		this.cookingStyle = cookingStyle;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public boolean isSeasoning() {
		return isSeasoning;
	}

	public void setSeasoning(boolean seasoning) {
		isSeasoning = seasoning;
	}
	
	public static RecipeEntry[] populateData() {
		return  new RecipeEntry[]{
				new RecipeEntry(1,1,"Tasty Stuff","Some Seriously Tasty Stuff","How often have i to repeat myself?","smoking",5000,false)
		};
	}
}
