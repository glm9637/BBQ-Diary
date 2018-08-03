package com.example.glm9637.myapplication.database.entry;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.utils.Constants;

/**
 * Erzeugt von M. Fengels am 08.06.2018.
 */

@Entity(tableName = "category")
public class CategoryEntry {
	
	@PrimaryKey()
	private final long id;
	private final String name;
	
	public CategoryEntry(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static CategoryEntry[] populateData(Context context) {
		return new CategoryEntry[]{
				new CategoryEntry(Constants.Ids.CATEGORY_BEEF, context.getString(R.string.cut_beef_title)),
				new CategoryEntry(Constants.Ids.CATEGORY_PORK, context.getString(R.string.cut_pork_title)),
				new CategoryEntry(Constants.Ids.CATEGORY_POULTRY, context.getString(R.string.cut_poultry_title)),
				new CategoryEntry(Constants.Ids.CATEGORY_FISH, context.getString(R.string.cut_fish_title)),
				new CategoryEntry(Constants.Ids.CATEGORY_VEGETABLE, context.getString(R.string.cut_vegetable_title)),
				new CategoryEntry(Constants.Ids.CATEGORY_OTHER, context.getString(R.string.cut_other_title))
			
		};
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getIconId() {
		return Constants.getIconForCategory(id);
	}
}
