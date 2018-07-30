package com.example.glm9637.myapplication.database.daos;

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
    public LiveData<List<IngredientEntry>> loadIngredients(long recipeId);

    @Update()
    public void updateIngredient(IngredientEntry ingredient);

    @Insert
    public void insertIngredient(IngredientEntry ingredient);

    @Delete
    public void deleteIngredient(IngredientEntry ingredientEntry);

}
