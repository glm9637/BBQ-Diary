package com.example.glm9637.myapplication.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.glm9637.myapplication.database.entry.CategoryEntry;

/**
 * Erzeugt von M. Fengels am 08.06.2018.
 */
@Dao
public interface CategoryDao {

	@Query("SELECT * FROM Category WHERE id = :categoryId")
	LiveData<CategoryEntry> loadCategory(long categoryId);

	@Insert
	void insertAll(CategoryEntry... dataEntities);
}
