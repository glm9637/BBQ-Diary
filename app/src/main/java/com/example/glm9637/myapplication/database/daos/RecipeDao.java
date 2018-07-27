package com.example.glm9637.myapplication.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.glm9637.myapplication.database.entry.RecipeEntry;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */

@Dao
public interface RecipeDao {

	@Query("SELECT * FROM recipe WHERE cut_id = :cutId")
	public LiveData<List<RecipeEntry>> loadRecipeForCut(int cutId);
	
	@Query("SELECT * FROM recipe WHERE category_id = :categoryId")
	public LiveData<List<RecipeEntry>> loadRecipeForCategory(int categoryId);
	
	@Update
	public void updateRecipe(RecipeEntry recipe);
	
	@Delete
	public void deleteRecipe(RecipeEntry recipe);
	
	@Insert
	public void insertRecipe(RecipeEntry recipe);
}