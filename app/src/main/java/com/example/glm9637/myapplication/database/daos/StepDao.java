package com.example.glm9637.myapplication.database.daos;

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

    @Insert()
    public void insertStep(StepEntry step);

    @Update
    public void updateStep(StepEntry step);

    @Delete
    public void deleteStep(StepEntry stepEntry);

}
