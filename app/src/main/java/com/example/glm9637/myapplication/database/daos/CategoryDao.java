package com.example.glm9637.myapplication.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.glm9637.myapplication.database.entry.CategoryEntry;
import com.example.glm9637.myapplication.database.entry.CutEntry;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 08.06.2018.
 */
@Dao
public interface CategoryDao {
	
	@Query("SELECT * FROM Category")
	LiveData<List<CategoryEntry>> loadCategory();
	
	@Delete
	void deleteCategory(CategoryEntry category);
	
	@Insert
	void insertCategory(CategoryEntry category);

	@Query("SELECT * FROM Category WHERE id = :categoryId")
    LiveData<CutEntry> loadCategory(long categoryId);

	@Insert
	void insertAll(CategoryEntry... dataEntities);
}
