package com.example.glm9637.myapplication.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.IngredientAdapter;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.RecipeActivityViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RecipeActivity extends AppCompatActivity {

	private static Bundle mState;
	private TextView descriptionText;
	private TextView durationText;
	private TextView cookingStyleText;
	private IngredientAdapter adapter;
	private RecipeEntry recipeEntry;

	private FirebaseDatabase firebaseDatabase;
	private DatabaseReference recipeDatabaseReference;
	private ValueEventListener valueEventListener;

	private long recipeId;
	private long categoryId;
	private long cutId;
	private String firebaseReference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe);
		Toolbar toolbar = findViewById(R.id.toolbar);
		descriptionText = findViewById(R.id.txt_description);
		durationText = findViewById(R.id.txt_duration);
		cookingStyleText = findViewById(R.id.txt_cooking_style);
		RecyclerView recyclerView = findViewById(R.id.recyclerview);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		recipeId = getIntent().getLongExtra(Constants.Arguments.RECIPE_ID, 0);
		categoryId = getIntent().getLongExtra(Constants.Arguments.CATEGORY_ID, 0);
		cutId = getIntent().getLongExtra(Constants.Arguments.CUT_ID, 0);
		firebaseReference = getIntent().getStringExtra(Constants.Arguments.FIREBASE_REFERENCE);


		adapter = new IngredientAdapter(this);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);


		FloatingActionButton btn = findViewById(R.id.btn_cook);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RecipeActivity.this, RecipeStepActivity.class);
				if (firebaseReference == null) {
					intent.putExtra(Constants.Arguments.RECIPE_ID, recipeId);
				}else {
					intent.putExtra(Constants.Arguments.FIREBASE_REFERENCE,firebaseReference);
				}
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if(firebaseReference==null) {
			getMenuInflater().inflate(R.menu.menu_activity_recipe, menu);
		}else {
			getMenuInflater().inflate(R.menu.menu_activity_recipe_firebase, menu);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finishAfterTransition();
				return true;
			case R.id.mnu_notes:
				Intent intent = new Intent(this, NoteListActivity.class);
				intent.putExtra(Constants.Arguments.RECIPE_ID, recipeId);
				startActivity(intent);
				return true;
			case R.id.mnu_edit:
				intent = new Intent(this, EditRecipeActivity.class);
				intent.putExtra(Constants.Arguments.RECIPE_ID, recipeId);
				intent.putExtra(Constants.Arguments.CUT_ID, recipeEntry.getCutId());
				intent.putExtra(Constants.Arguments.CATEGORY_ID, recipeEntry.getCategoryId());
				startActivity(intent);
			case R.id.mnu_share:
				shareRecipe();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}


	@Override
	protected void onPause() {
		super.onPause();
		mState = new Bundle();
		mState.putLong(Constants.Arguments.RECIPE_ID, recipeId);
		mState.putLong(Constants.Arguments.CATEGORY_ID, categoryId);
		mState.putLong(Constants.Arguments.CUT_ID, cutId);
		mState.putString(Constants.Arguments.FIREBASE_REFERENCE, firebaseReference);
		detachDatabaseReadListener();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mState != null && recipeId == 0) {
			recipeId = mState.getLong(Constants.Arguments.RECIPE_ID, 0);
			categoryId = mState.getLong(Constants.Arguments.CATEGORY_ID, 0);
			cutId = mState.getLong(Constants.Arguments.CUT_ID, 0);
			firebaseReference = mState.getString(Constants.Arguments.FIREBASE_REFERENCE);
		} else {
			mState = null;
		}

		if(firebaseReference==null){
			initDataFromRoom();
		}else {
			initDataFromFirebase();
		}

	}

	private void initDataFromRoom(){
		RecipeActivityViewModel viewModel = new RecipeActivityViewModel(this, recipeId);

		viewModel.getRecipe().observe(this, new Observer<RecipeEntry>() {
			@Override
			public void onChanged(@Nullable RecipeEntry recipeEntry) {
				if (recipeEntry == null) {
					return;
				}
				RecipeActivity.this.recipeEntry = recipeEntry;
				loadDataIntoUi();
			}
		});
		viewModel.getIngredients().observe(this, new Observer<List<IngredientEntry>>() {
			@Override
			public void onChanged(@Nullable List<IngredientEntry> ingredientEntries) {
				adapter.setData(ingredientEntries);
			}
		});
	}

	private void initDataFromFirebase() {
		firebaseDatabase = FirebaseDatabase.getInstance();
		recipeDatabaseReference = firebaseDatabase.getReference().child(firebaseReference);

		if (valueEventListener == null) {
			valueEventListener = new ValueEventListener() {
				@Override
				public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
					RecipeActivity.this.recipeEntry = dataSnapshot.getValue(RecipeEntry.class);
					recipeEntry.setDatabaseReference(firebaseReference);
					recipeEntry.setCategoryId(categoryId);
					recipeEntry.setCutId(cutId);
					for(DataSnapshot snapshot: dataSnapshot.child("/ingredients").getChildren()){
						IngredientEntry ingredientEntry = snapshot.getValue(IngredientEntry.class);
						adapter.addData(ingredientEntry);
					}
					loadDataIntoUi();
				}

				public void onCancelled(@NonNull DatabaseError databaseError) {
				}
			};
			recipeDatabaseReference.addValueEventListener(valueEventListener);

		}
	}

	private void detachDatabaseReadListener() {
		if (valueEventListener != null) {
			recipeDatabaseReference.removeEventListener(valueEventListener);
			valueEventListener = null;
		}
	}

	private void loadDataIntoUi(){
		getSupportActionBar().setTitle(recipeEntry.getName());
		getSupportActionBar().setSubtitle(recipeEntry.getShortDescription());
		descriptionText.setText(recipeEntry.getDescription());
		durationText.setText(String.valueOf(recipeEntry.getDuration()));
		cookingStyleText.setText(recipeEntry.getCookingStyle());
	}

	public static void reset(){
		mState=null;
	}

	public void shareRecipe(){
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference insertReference;
		String category = "category-"+recipeEntry.getCategoryId();
		String cut = "cut-"+recipeEntry.getCutId();
		String reference = String.format("/recipes/%s/%s",category,cut);
		insertReference = database.getReference(reference);

		String recipeId = insertReference.push().getKey();
		recipeDatabaseReference.child(recipeId).setValue(recipeEntry);

	}



}
