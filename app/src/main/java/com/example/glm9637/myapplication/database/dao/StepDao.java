package com.example.glm9637.myapplication.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.glm9637.myapplication.database.entry.StepEntry;

import java.util.List;

@Dao
public interface StepDao {

    @Query("SELECT * FROM step WHERE recipe_id = :recipeId ORDER BY step_order")
    public LiveData<List<StepEntry>> loadStepList(long recipeId);

    @Query("SELECT id FROM step WHERE recipe_id= :recipeId")
    public LiveData<List<Integer>> loadStepIdList(long recipeId);
    @Insert()
    void insertStep(StepEntry step);

    @Update
    void updateStep(StepEntry step);

    @Delete
    void deleteStep(StepEntry stepEntry);
	
	@Insert
	void insertAll(StepEntry[] cutEntries);

}
