package com.example.glm9637.myapplication.database.entry;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
@Entity(tableName = "note", foreignKeys = {
		@ForeignKey(entity = RecipeEntry.class, parentColumns = "id", childColumns = "recipe_id")
}, indices = {
		@Index(name = "IX_NOTE_RECIPE_ID", value = "recipe_id")
})
public class NoteEntry {
	
	@PrimaryKey(autoGenerate = true)
	private long id;
	
	@ColumnInfo(name = "recipe_id")
	private final long recipeId;
	
	private String name;
	private String text;
	private Date date;
	
	public NoteEntry(long id, long recipeId, String name, String text, Date date) {
		this.id = id;
		this.recipeId = recipeId;
		this.name = name;
		this.text = text;
		this.date = date;
	}
	
	public NoteEntry(long recipeId, String name, String description) {
		this.recipeId=recipeId;
		this.name = name;
		this.text = description;
		this.date = new Date();
	}
	
	public long getId() {
		return id;
	}

	public long getRecipeId() {
		return recipeId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public static NoteEntry[] populateData(){
		return new NoteEntry[]{
				new NoteEntry(1,1,"Test Note","Just to see if this note thing works",new Date())
		};
	}
}

