package com.example.glm9637.myapplication.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.glm9637.myapplication.database.entry.CutEntry;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */
@Dao
public interface CutDao {
	
	@Query("SELECT * FROM cut WHERE category_id = :categoryId")
	public LiveData<List<CutEntry>> loadCutList(long categoryId);
	
	@Delete
	public void deleteCut(CutEntry cut);
	
	@Update
	public void updateCut(CutEntry cut);
	
	@Insert
	public void insertCut(CutEntry cut);
}
