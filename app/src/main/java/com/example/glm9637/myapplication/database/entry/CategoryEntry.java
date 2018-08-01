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
	private long  id;
	private String  name;
	private int iconId;

	public CategoryEntry(long id, String name, int iconId) {
		this.id = id;
		this.name = name;
		this.iconId = iconId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIconId() {
		return iconId;
	}

	public static CategoryEntry[] populateData(Context context) {
		return new CategoryEntry[]{
				new CategoryEntry(Constants.Ids.CATEGORY_BEEF, context.getString(R.string.cut_beef_title), R.drawable.ic_beef),
				new CategoryEntry(Constants.Ids.CATEGORY_PORK, context.getString(R.string.cut_pork_title), R.drawable.ic_pork),
				new CategoryEntry(Constants.Ids.CATEGORY_POULTY, context.getString(R.string.cut_poultry_title), R.drawable.ic_poultry),
				new CategoryEntry(Constants.Ids.CATEGORY_FISH, context.getString(R.string.cut_fish_title), R.drawable.ic_fish),
				new CategoryEntry(Constants.Ids.CATEGORY_VEGETABLE, context.getString(R.string.cut_vegetable_title), R.drawable.ic_beef),
				new CategoryEntry(Constants.Ids.CATEGORY_OTHER, context.getString(R.string.cut_other_title), R.drawable.ic_beef)

		};
	}
}
