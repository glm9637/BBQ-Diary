package com.example.glm9637.myapplication.ui.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.CategoryEntry;
import com.example.glm9637.myapplication.ui.adapter.fragment.CategoryFragmentAdapter;
import com.example.glm9637.myapplication.ui.fragment.category.CutsFragment;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.CategoryViewModel;

public class CategoryActivity extends AppCompatActivity {

	private static Bundle mState;
	private TextView toolbarText;
	private ImageView toolbarIcon;
	private CategoryViewModel viewModel;
	private long categoryId;
	private ViewPager pager;
	private TabLayout tabLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		toolbarText = findViewById(R.id.category_title);
		toolbarIcon = findViewById(R.id.toolbar_icon);
		categoryId = getIntent().getLongExtra(Constants.Arguments.CATEGORY_ID, 0);
		if (savedInstanceState != null && categoryId == 0) {
			categoryId = savedInstanceState.getLong(Constants.Arguments.CATEGORY_ID);
		}

		tabLayout = findViewById(R.id.tab_layout);
		pager = findViewById(R.id.view_pager);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mState = new Bundle();
		mState.putLong(Constants.Arguments.CATEGORY_ID, categoryId);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mState != null && categoryId == 0) {
			categoryId = mState.getLong(Constants.Arguments.CATEGORY_ID);
		} else {
			CutsFragment.reset();
		}

		viewModel = new CategoryViewModel(RecipeDatabase.getInstance(this), categoryId);
		viewModel.getCategory().observe(this, new Observer<CategoryEntry>() {
			@Override
			public void onChanged(@Nullable CategoryEntry categoryEntry) {
				viewModel.getCategory().removeObserver(this);
				Log.w("Tag", categoryId + "");
				if (categoryEntry == null) {
					return;
				}
				toolbarText.setText(categoryEntry.getName());
				toolbarIcon.setImageResource(categoryEntry.getIconId());
			}
		});

		if (pager.getAdapter() == null) {
			CategoryFragmentAdapter adapter = new CategoryFragmentAdapter(getSupportFragmentManager(), categoryId);
			pager.setAdapter(adapter);
			tabLayout.setupWithViewPager(pager);
		}

	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finishAfterTransition();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}


}
