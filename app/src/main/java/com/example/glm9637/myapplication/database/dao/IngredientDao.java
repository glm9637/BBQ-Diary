package com.example.glm9637.myapplication.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.glm9637.myapplication.database.entry.IngredientEntry;

import java.util.List;

@Dao
public interface IngredientDao {

    @Query("SELECT * FROM ingredient WHERE recipe_id = :recipeId")
    LiveData<List<IngredientEntry>> loadIngredients(long recipeId);

    @Update()
    void updateIngredient(IngredientEntry ingredient);

    @Insert
    void insertIngredient(IngredientEntry ingredient);

    @Delete
    void deleteIngredient(IngredientEntry ingredientEntry);
	
	@Insert
	void insertAll(IngredientEntry[] cutEntries);

}
