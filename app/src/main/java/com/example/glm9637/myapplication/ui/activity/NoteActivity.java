package com.example.glm9637.myapplication.ui.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.NoteEntry;
import com.example.glm9637.myapplication.utils.AppExecutors;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.NoteViewModel;

import java.util.Date;

public class NoteActivity extends AppCompatActivity {
	
	long noteId;
	long recipeId;
	NoteViewModel viewModel;
	EditText txtName;
	EditText txtDescription;
	NoteEntry noteEntry;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		txtName = findViewById(R.id.txt_name);
		txtDescription = findViewById(R.id.txt_description);
		
		noteId = getIntent().getLongExtra(Constants.Arguments.NOTE_ID, 0);
		recipeId = getIntent().getLongExtra(Constants.Arguments.RECIPE_ID, 0);
		if (noteId != 0) {
			viewModel = new NoteViewModel(this, noteId);
			viewModel.getNote().observe(this, new Observer<NoteEntry>() {
				@Override
				public void onChanged(@Nullable NoteEntry noteEntry) {
					viewModel.getNote().removeObserver(this);
					NoteActivity.this.noteEntry = noteEntry;
					txtName.setText(noteEntry.getName());
					txtDescription.setText(noteEntry.getText());
				}
			});
		} else {
			viewModel = new NoteViewModel(this);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (noteId != 0) {
			getMenuInflater().inflate(R.menu.menu_activity_note, menu);
		}
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.mnu_delete:
				deleteNote();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		AppExecutors.getInstance().diskIO().execute(new Runnable() {
			@Override
			public void run() {
				if (noteEntry == null) {
					noteEntry = new NoteEntry(recipeId, txtName.getText().toString(), txtDescription.getText().toString());
					viewModel.saveNote(noteEntry);
				} else {
					noteEntry.setDate(new Date());
					noteEntry.setName(txtName.getText().toString());
					noteEntry.setText(txtDescription.getText().toString());
					viewModel.updateNote(noteEntry);
				}
			}
		});
	}
	
	private void deleteNote() {
		AppExecutors.getInstance().diskIO().execute(new Runnable() {
			@Override
			public void run() {
				viewModel.deleteNote(noteEntry);
			}
		});
		finish();
	}
}
