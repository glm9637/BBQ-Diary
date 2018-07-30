package com.example.glm9637.myapplication.database.entry;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.example.glm9637.myapplication.utils.Constants;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */
@Entity(tableName = "cut", foreignKeys = {
		@ForeignKey(entity = CategoryEntry.class, parentColumns = "id",childColumns = "category_id")
}, indices = {
		@Index(name = "IX_CUT_CATEGORY_ID",value = "category_id")
})
public class CutEntry {
	
	@PrimaryKey()
	private long  id;
	
	@ColumnInfo(name = "category_id")
	private int categoryid;
	
	private String name;
	private int img;
	private String description;

	public CutEntry(long id, int categoryid, String name, int img, String description) {
		this.id = id;
		this.categoryid = categoryid;
		this.name = name;
		this.img = img;
		this.description = description;
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

}
