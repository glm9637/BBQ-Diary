package com.example.glm9637.myapplication;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.glm9637.myapplication.adapter.RecyclerView.IngredientAdapter;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.RecipeActivityViewModel;

import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    RecipeActivityViewModel viewModel;
    TextView descriptionText;
    TextView durationText;
    TextView cookingStyleText;
    RecyclerView recyclerView;
    IngredientAdapter adapter;

    long recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = findViewById(R.id.toolbar);
        descriptionText = findViewById(R.id.txt_description);
        durationText = findViewById(R.id.txt_duration);
        cookingStyleText = findViewById(R.id.txt_cooking_style);
        recyclerView = findViewById(R.id.recyclerview);
        setSupportActionBar(toolbar);

        recipeId = getIntent().getLongExtra(Constants.Arguments.RECIPE_ID, 0);
        if (recipeId == 0) {
            finish();
        }

        adapter = new IngredientAdapter(this);
        recyclerView.setAdapter(adapter);
        viewModel = new RecipeActivityViewModel(this, recipeId);

        viewModel.getRecipe().observe(this, new Observer<RecipeEntry>() {
            @Override
            public void onChanged(@Nullable RecipeEntry recipeEntry) {
                if (recipeEntry == null) {
                    return;
                }
                getSupportActionBar().setTitle(recipeEntry.getName());
                getSupportActionBar().setSubtitle(recipeEntry.getShortDescription());
                descriptionText.setText(recipeEntry.getDescription());
                durationText.setText(recipeEntry.getDuration() + "");
                cookingStyleText.setText(recipeEntry.getCookingStyle());
            }
        });
        viewModel.getIngredients().observe(this, new Observer<List<IngredientEntry>>() {
            @Override
            public void onChanged(@Nullable List<IngredientEntry> ingredientEntries) {
                adapter.setData(ingredientEntries);
            }
        });
    }
}
