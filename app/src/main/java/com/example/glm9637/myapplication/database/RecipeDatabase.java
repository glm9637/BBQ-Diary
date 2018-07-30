package com.example.glm9637.myapplication.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.glm9637.myapplication.database.daos.CategoryDao;
import com.example.glm9637.myapplication.database.daos.CutDao;
import com.example.glm9637.myapplication.database.daos.IngredientDao;
import com.example.glm9637.myapplication.database.daos.RecipeDao;
import com.example.glm9637.myapplication.database.daos.StepDao;
import com.example.glm9637.myapplication.database.entry.CategoryEntry;
import com.example.glm9637.myapplication.database.entry.CutEntry;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.utils.Constants;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@Database(entities = {CategoryEntry.class,
        CutEntry.class,
        IngredientEntry.class,
        RecipeEntry.class,
        StepEntry.class},
        version = 1, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "Recipe.db";
    private static RecipeDatabase sInstance;

    public static RecipeDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = buildDatabase(context);
        }

        return sInstance;
    }

    private static RecipeDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                RecipeDatabase.class, RecipeDatabase.DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).getCategoryDao().insertAll(CategoryEntry.populateData(context));
                            }
                        });
                    }
                })
                .build();
    }

    public abstract CategoryDao getCategoryDao();

    public abstract CutDao getCutDao();

    public abstract RecipeDao getRecipeDao();

    public abstract IngredientDao getIngredientDao();

    public abstract StepDao getStepDao();


}
