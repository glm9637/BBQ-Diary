package com.example.glm9637.myapplication.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.ui.adapter.recyclerView.IngredientAdapter;
import com.example.glm9637.myapplication.utils.AppExecutors;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.RecipeActivityViewModel;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

	public static final int RC_SIGN_IN = 1;
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
	private MenuItem uploadRecipeMenu;
	private MenuItem logInMenu;
	private MenuItem logOutMenu;
	private RecipeActivityViewModel viewModel;
	//Authentication fields
	private String username;
	private FirebaseAuth firebaseAuth;
	private FirebaseAuth.AuthStateListener authStateListener;
	private boolean upload = false;
	private TextView authorText;

	public static void reset() {
		mState = null;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe);
		Toolbar toolbar = findViewById(R.id.toolbar);
		descriptionText = findViewById(R.id.txt_description);
		durationText = findViewById(R.id.txt_duration);
		cookingStyleText = findViewById(R.id.txt_cooking_style);
		RecyclerView recyclerView = findViewById(R.id.recyclerview);
		authorText = findViewById(R.id.txt_author);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		recipeId = getIntent().getLongExtra(Constants.Arguments.RECIPE_ID, 0);
		categoryId = getIntent().getLongExtra(Constants.Arguments.CATEGORY_ID, 0);
		cutId = getIntent().getLongExtra(Constants.Arguments.CUT_ID, 0);
		firebaseReference = getIntent().getStringExtra(Constants.Arguments.FIREBASE_REFERENCE);
		firebaseAuth = FirebaseAuth.getInstance();

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
				} else {
					intent.putExtra(Constants.Arguments.FIREBASE_REFERENCE, firebaseReference);
				}
				startActivity(intent);
			}
		});

		authStateListener = new FirebaseAuth.AuthStateListener() {
			@Override
			public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
				FirebaseUser user = firebaseAuth.getCurrentUser();
				if (user != null) {
					// User is signed in
					username = user.getDisplayName();
					if (logInMenu != null) {
						logInMenu.setVisible(false);
						logOutMenu.setVisible(true);
					}
					if (upload) {
						upload = false;
						shareRecipe();
					}
				} else {
					// User is signed out
					if (logInMenu != null) {
						logInMenu.setVisible(true);
						logOutMenu.setVisible(false);
					}
					username = "";
				}
			}
		};


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (firebaseReference == null) {
			getMenuInflater().inflate(R.menu.menu_activity_recipe, menu);
			uploadRecipeMenu = menu.findItem(R.id.mnu_share);
			logInMenu = menu.findItem(R.id.mnu_log_in);
			logOutMenu = menu.findItem(R.id.mnu_log_out);
			if (recipeEntry != null) {
				uploadRecipeMenu.setVisible(!recipeEntry.isUploaded());
			}
			logInMenu.setVisible(username == null);
			logOutMenu.setVisible(username != null);

		} else {
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
			case R.id.mnu_log_in:
				logIn();
				return true;
			case R.id.mnu_log_out:
				logOut();
				return true;
			case R.id.mnu_save:
				saveRecipe();
			default:
				return super.onOptionsItemSelected(item);
		}
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

		firebaseAuth.addAuthStateListener(authStateListener);

		if (firebaseReference == null) {
			initDataFromRoom();
		} else {
			initDataFromFirebase();
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
		if (authStateListener != null) {
			firebaseAuth.removeAuthStateListener(authStateListener);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RC_SIGN_IN) {
			if (resultCode == RESULT_OK) {
				if (!username.isEmpty()) {
					shareRecipe();
				}
			}
		}

	}

	private void initDataFromRoom() {
		viewModel = new RecipeActivityViewModel(this, recipeId);

		viewModel.getRecipe().observe(this, new Observer<RecipeEntry>() {
			@Override
			public void onChanged(@Nullable RecipeEntry recipeEntry) {
				if (recipeEntry == null) {
					return;
				}
				RecipeActivity.this.recipeEntry = recipeEntry;
				if (uploadRecipeMenu != null) {
					uploadRecipeMenu.setVisible(!recipeEntry.isUploaded());
				}
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
					for (DataSnapshot snapshot : dataSnapshot.child("/ingredients").getChildren()) {
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

	private void loadDataIntoUi() {
		getSupportActionBar().setTitle(recipeEntry.getName());
		getSupportActionBar().setSubtitle(recipeEntry.getShortDescription());
		descriptionText.setText(recipeEntry.getDescription());
		durationText.setText(String.valueOf(recipeEntry.getDuration()));
		cookingStyleText.setText(recipeEntry.getCookingStyle());
		if (recipeEntry.getAuthor() == null) {
			authorText.setVisibility(View.GONE);
		} else {
			authorText.setVisibility(View.VISIBLE);
			authorText.setText(getString(R.string.author_text_template, recipeEntry.getAuthor()));
		}
	}

	private void detachDatabaseReadListener() {
		if (valueEventListener != null) {
			recipeDatabaseReference.removeEventListener(valueEventListener);
			valueEventListener = null;
		}
	}

	private void logIn() {
		// Choose authentication providers
		List<AuthUI.IdpConfig> providers = Arrays.asList(
				new AuthUI.IdpConfig.EmailBuilder().build(),
				new AuthUI.IdpConfig.GoogleBuilder().build());

		startActivityForResult(
				AuthUI.getInstance()
						.createSignInIntentBuilder()
						.setIsSmartLockEnabled(false)
						.setAvailableProviders(providers)
						.build(),
				RC_SIGN_IN);
	}

	private void logOut() {
		AuthUI.getInstance()
				.signOut(this)
				.addOnCompleteListener(new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						Toast.makeText(RecipeActivity.this, R.string.toast_logged_out, Toast.LENGTH_LONG).show();
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						Toast.makeText(RecipeActivity.this, R.string.toast_logged_out_error, Toast.LENGTH_LONG).show();
					}
				});
	}

	public void shareRecipe() {
		if (username.isEmpty()) {

			new AlertDialog.Builder(this)
					.setTitle(R.string.dlg_share_title)
					.setMessage(R.string.dlg_share_message)
					.setPositiveButton(R.string.dlg_share_positive, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							upload = true;
							logIn();
							dialog.dismiss();
						}
					})
					.setNegativeButton(R.string.dlg_share_negative, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					}).show();


			return;
		}
		String category = "category-" + recipeEntry.getCategoryId();
		String cut = "cut-" + recipeEntry.getCutId();
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("/recipes").child(category).child("cuts").child(cut).child("recipes").push();
		recipeEntry.setUploaded(true);
		recipeEntry.setAuthor(username);
		AppExecutors.getInstance().diskIO().execute(new Runnable() {
			@Override
			public void run() {
				RecipeDatabase.getInstance(RecipeActivity.this).getRecipeDao().updateRecipe(recipeEntry);
			}
		});
		ref.setValue(recipeEntry);
		shareIngredients(ref);
		shareSteps(ref);
		uploadRecipeMenu.setVisible(false);
		Toast.makeText(this, R.string.toast_recipe_uploaded, Toast.LENGTH_LONG).show();

	}

	private void shareSteps(final DatabaseReference recipeRef) {
		viewModel.getSteps().observe(this, new Observer<List<StepEntry>>() {
			@Override
			public void onChanged(@Nullable List<StepEntry> stepEntries) {
				viewModel.getSteps().removeObserver(this);
				for (StepEntry stepEntry : stepEntries) {
					DatabaseReference ingredientsRef = recipeRef.child("steps").push();
					ingredientsRef.setValue(stepEntry);
				}
			}
		});

	}

	private void shareIngredients(final DatabaseReference recipeRef) {
		viewModel.getIngredients().observe(this, new Observer<List<IngredientEntry>>() {
			@Override
			public void onChanged(@Nullable List<IngredientEntry> ingredientEntries) {
				viewModel.getIngredients().removeObserver(this);
				for (IngredientEntry ingredientEntry : ingredientEntries) {
					DatabaseReference ingredientsRef = recipeRef.child("ingredients").push();
					ingredientsRef.setValue(ingredientEntry);
				}
			}
		});
	}

	private void saveRecipe() {
		AppExecutors.getInstance().diskIO().execute(new Runnable() {
			@Override
			public void run() {
				final RecipeDatabase database = RecipeDatabase.getInstance(RecipeActivity.this);
				recipeEntry.setUploaded(true);
				final long id = database.getRecipeDao().insertRecipe(recipeEntry);
				for (IngredientEntry ingredientEntry : adapter.getData()) {
					ingredientEntry.setRecipeId(id);
					database.getIngredientDao().insertIngredient(ingredientEntry);
				}
				recipeDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
						recipeDatabaseReference.removeEventListener(this);
						AppExecutors.getInstance().diskIO().execute(new Runnable() {
							@Override
							public void run() {
								for (DataSnapshot snapshot : dataSnapshot.child("/steps").getChildren()) {
									StepEntry step = snapshot.getValue(StepEntry.class);
									step.setRecipeId(id);
									database.getStepDao().insertStep(step);
								}
							}
						});

					}

					@Override
					public void onCancelled(@NonNull DatabaseError databaseError) {
						recipeDatabaseReference.removeEventListener(this);
					}
				});
				Toast.makeText(RecipeActivity.this, R.string.recipe_saved, Toast.LENGTH_LONG).show();
			}
		});
	}
}
