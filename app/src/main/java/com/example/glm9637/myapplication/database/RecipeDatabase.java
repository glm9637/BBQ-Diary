package com.example.glm9637.myapplication.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.glm9637.myapplication.database.dao.CategoryDao;
import com.example.glm9637.myapplication.database.dao.CutDao;
import com.example.glm9637.myapplication.database.dao.IngredientDao;
import com.example.glm9637.myapplication.database.dao.NoteDao;
import com.example.glm9637.myapplication.database.dao.RecipeDao;
import com.example.glm9637.myapplication.database.dao.StepDao;
import com.example.glm9637.myapplication.database.entry.CategoryEntry;
import com.example.glm9637.myapplication.database.entry.CutEntry;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.database.entry.NoteEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.database.type_converter.DateConverter;

import java.util.concurrent.Executors;


@Database(entities = {CategoryEntry.class,
		CutEntry.class,
		IngredientEntry.class,
		RecipeEntry.class,
		StepEntry.class,
		NoteEntry.class},
		version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class RecipeDatabase extends RoomDatabase {
	private static final String DATABASE_NAME = "Recipe.db";
	private static RecipeDatabase sInstance;
	
	public static RecipeDatabase getInstance(Context context) {
		if (sInstance == null) {
			sInstance = buildDatabase(context);
		}
		
		return sInstance;
	}
	
	private static RecipeDatabase buildDatabase(final Context context) {
		return Room.databaseBuilder(context.getApplicationContext(),
				RecipeDatabase.class, RecipeDatabase.DATABASE_NAME)
				.addCallback(new Callback() {
					@Override
					public void onCreate(@NonNull SupportSQLiteDatabase db) {
						super.onCreate(db);
						Executors.newSingleThreadExecutor().execute(new Runnable() {
							@Override
							public void run() {
								RecipeDatabase instance = getInstance(context);
								instance .getCategoryDao().insertAll(CategoryEntry.populateData(context));
								instance .getCutDao().insertAll(CutEntry.populateData());
								instance .getRecipeDao().insertAll(RecipeEntry.populateData());
								instance .getIngredientDao().insertAll(IngredientEntry.populateData());
								instance .getStepDao().insertAll(StepEntry.populateData());
								instance.getNoteDao().insertAll(NoteEntry.populateData());
							}
						});
					}
				})
				.build();
	}
	
	public abstract CategoryDao getCategoryDao();
	
	public abstract CutDao getCutDao();
	
	public abstract RecipeDao getRecipeDao();
	
	public abstract IngredientDao getIngredientDao();
	
	public abstract StepDao getStepDao();
	
	public abstract NoteDao getNoteDao();
	
	
}
