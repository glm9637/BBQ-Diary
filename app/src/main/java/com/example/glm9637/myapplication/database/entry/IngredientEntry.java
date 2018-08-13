package com.example.glm9637.myapplication.database.entry;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */

@Entity(tableName = "ingredient", foreignKeys = {
		@ForeignKey(entity = RecipeEntry.class,parentColumns = "id",childColumns = "recipe_id")
}, indices = {
		@Index(name = "IX_INGREDIENT_RECIPE_ID",value = "recipe_id")
})
public class IngredientEntry implements Parcelable {

	@Exclude
	@PrimaryKey(autoGenerate = true)
	private long id;

	@Exclude
	@ColumnInfo(name = "recipe_id")
	private long recipeId;
	
	private String name;
	private long amount;
	private String unit;

	@Ignore
	private boolean deleted;

	public IngredientEntry(long id, long recipeId, String name, long amount, String unit) {
		this.id = id;
		this.recipeId = recipeId;
		this.name = name;
		this.amount = amount;
		this.unit = unit;
	}

	@Ignore
	public IngredientEntry(String name, long amount, String unit) {
		this.name = name;
		this.amount = amount;
		this.unit = unit;
	}

	@Ignore
	public IngredientEntry(){

	}

	protected IngredientEntry(Parcel in) {
		id = in.readLong();
		recipeId = in.readLong();
		name = in.readString();
		amount = in.readLong();
		unit = in.readString();
		deleted = in.readByte() != 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeLong(recipeId);
		dest.writeString(name);
		dest.writeLong(amount);
		dest.writeString(unit);
		dest.writeByte((byte) (deleted ? 1 : 0));
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<IngredientEntry> CREATOR = new Creator<IngredientEntry>() {
		@Override
		public IngredientEntry createFromParcel(Parcel in) {
			return new IngredientEntry(in);
		}

		@Override
		public IngredientEntry[] newArray(int size) {
			return new IngredientEntry[size];
		}
	};

	@Exclude
    public long getId() {
		return id;
	}
	@Exclude
	public long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public static IngredientEntry[] populateData(){
		return new IngredientEntry[]{
				new IngredientEntry(1,1,"Flat Iron Steak",500,"g"),
				new IngredientEntry(2,1,"Salt",20,"g"),
				new IngredientEntry(3,1,"Pepper",20,"g")
		};
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


}
