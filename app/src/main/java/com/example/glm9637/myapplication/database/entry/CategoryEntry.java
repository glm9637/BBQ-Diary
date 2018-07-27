package com.example.glm9637.myapplication.database.entry;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Erzeugt von M. Fengels am 08.06.2018.
 */

@Entity(tableName = "category")
public class CategoryEntry {
	
	@PrimaryKey()
	private long  id;
	private String  bezeichnung;
	
}
