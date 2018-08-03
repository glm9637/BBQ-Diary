package com.example.glm9637.myapplication.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.glm9637.myapplication.database.entry.CutEntry;
import com.example.glm9637.myapplication.database.model.CutEntryForList;
import com.example.glm9637.myapplication.utils.Constants;

import java.util.List;

/**
 * Erzeugt von M. Fengels am 27.07.2018.
 */
@Dao
public interface CutDao {

	@Query("SELECT id, category_id ,name,description,origin, (SELECT COUNT(*) FROM recipe WHERE cut_id = cut.id) AS recipe_count FROM cut WHERE category_id = :categoryId AND name <> '" + Constants.CUT_NAME_RUB + "' order by name")
	LiveData<List<CutEntryForList>> loadCutList(long categoryId);

	@Insert
	void insertAll(CutEntry[] cutEntries);

	@Query("SELECT * FROM cut WHERE id = :cutId")
	LiveData<CutEntry> loadCut(long cutId);
}
