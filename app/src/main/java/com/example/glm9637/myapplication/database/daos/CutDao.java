package com.example.glm9637.myapplication.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.glm9637.myapplication.database.entry.CutEntry;
import com.example.glm9637.myapplication.database.entry.CutEntryForList;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */
@Dao
public interface CutDao {
	
	@Query("SELECT id,category_id,name,img,description,origin, (SELECT COUNT(*) FROM recipe WHERE cut_id = cut.id) AS recipe_count FROM cut WHERE category_id = :categoryId order by origin, name")
	public LiveData<List<CutEntryForList>> loadCutList(long categoryId);
	
	@Delete
	public void deleteCut(CutEntry cut);
	
	@Update
	public void updateCut(CutEntry cut);
	
	@Insert
	public void insertCut(CutEntry cut);
	
	@Insert
	public void insertAll(CutEntry[] cutEntries);
}
