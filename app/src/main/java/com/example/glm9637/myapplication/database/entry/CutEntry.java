package com.example.glm9637.myapplication.database.entry;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */
@Entity(tableName = "cut", foreignKeys = {
		@ForeignKey(entity = CategoryEntry.class, parentColumns = "id",childColumns = "category_id")
})
public class CutEntry {
	
	@PrimaryKey()
	private long  id;
	
	@ColumnInfo(name = "category_id")
	private int categoryid;
	
	private String name;
	private String img;

}
