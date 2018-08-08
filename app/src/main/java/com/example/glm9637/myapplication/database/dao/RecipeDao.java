package com.example.glm9637.myapplication.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.glm9637.myapplication.database.entry.RecipeEntry;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */

@Dao
public abstract class RecipeDao {

	@Query("SELECT * FROM recipe WHERE cut_id = :cutId")
 	public abstract LiveData<List<RecipeEntry>> loadRecipeForCut(long cutId);

	@Query("SELECT * FROM recipe WHERE category_id = :categoryId and is_seasoning = 1")
	public abstract LiveData<List<RecipeEntry>> loadRubForCategory(long categoryId);

	@Update
	public abstract void updateRecipe(RecipeEntry recipe);
	
	@Insert
	public abstract long insertRecipe(RecipeEntry recipe);
	
	@Insert
	public abstract void insertAll(RecipeEntry[] recipeEntries);

	@Query("SELECT * FROM recipe WHERE id = :recipeId")
	public abstract LiveData<RecipeEntry> loadRecipe(long recipeId);

	@Query("SELECT * FROM recipe WHERE id = :recipeId")
	public abstract RecipeEntry loadRecipe_sync(long recipeId);


	public void deleteRecipe(long id){
		deleteRecipeSteps(id);
		deleteRecipeIngredients(id);
		deleteRecipeInternal(id);
	}

	@Query("DELETE FROM recipe WHERE id = :id")
	abstract void deleteRecipeInternal(long id);

	@Query("DELETE FROM ingredient WHERE recipe_id = :id")
	abstract void deleteRecipeIngredients(long id);

	@Query("DELETE FROM step WHERE recipe_id = :id")
	abstract void deleteRecipeSteps(long id);
}
