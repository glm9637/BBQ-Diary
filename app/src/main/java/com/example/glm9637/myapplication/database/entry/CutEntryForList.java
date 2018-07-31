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

public class CutEntryForList {
	
	private long id;
	
	@ColumnInfo(name = "category_id")
	private int categoryid;
	
	private String name;
	private int img;
	private String description;
	private String origin;
	@ColumnInfo(name = "recipe_count")
	private int recipeCount;
	
	
	public CutEntryForList(long id, int categoryid, String name, int img, String description, String origin, int recipeCount) {
		this.id = id;
		this.categoryid = categoryid;
		this.name = name;
		this.img = img;
		this.description = description;
		this.origin = origin;
		this.recipeCount = recipeCount;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public int getCategoryid() {
		return categoryid;
	}
	
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getImg() {
		return img;
	}
	
	public void setImg(int img) {
		this.img = img;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public int getRecipeCount() {
		return recipeCount;
	}
	
	public void setRecipeCount(int recipeCount) {
		this.recipeCount = recipeCount;
	}
}
