package com.example.glm9637.myapplication.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.ui.adapter.fragment.CutFragmentAdapter;
import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.CutEntry;
import com.example.glm9637.myapplication.ui.fragment.cut.RecipeFragment;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.CutViewModel;

public class CutActivity extends AppCompatActivity {

	private ImageView titleImage;
	private CollapsingToolbarLayout collapsingToolbarLayout;
	private FloatingActionButton fab;
	private TabLayout tabLayout;
	private ViewPager pager;
	private long cutId;
	private long categoryId;

	private static Bundle mState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cut);
		collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
		cutId = getIntent().getLongExtra(Constants.Arguments.CUT_ID, 0);
		categoryId = getIntent().getLongExtra(Constants.Arguments.CATEGORY_ID,0);
		fab = findViewById(R.id.btn_add);
		Toolbar toolbar = findViewById(R.id.toolbar);
		titleImage = findViewById(R.id.title_image);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		tabLayout = findViewById(R.id.tab_layout);
		pager = findViewById(R.id.view_pager);

		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				if(tab.getPosition()==0){
					fab.setVisibility(View.VISIBLE);
				}else {
					fab.setVisibility(View.GONE);
				}
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
				if(tab.getPosition()==0){
					fab.setVisibility(View.VISIBLE);
				}else {
					fab.setVisibility(View.GONE);
				}
			}
		});

		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(CutActivity.this, EditRecipeActivity.class);
				intent.putExtra(Constants.Arguments.CUT_ID,cutId);
				intent.putExtra(Constants.Arguments.CATEGORY_ID,categoryId);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		mState = new Bundle();
		mState.putLong(Constants.Arguments.CATEGORY_ID, cutId);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mState != null && cutId == 0) {
			cutId = mState.getLong(Constants.Arguments.CATEGORY_ID);
		} else {
			RecipeFragment.reset();
		}

		CutViewModel viewModel = new CutViewModel(RecipeDatabase.getInstance(this), cutId);
		viewModel.getCut().observe(this, new Observer<CutEntry>() {
			@Override
			public void onChanged(@Nullable CutEntry cutEntry) {
				if (cutEntry == null) {
					return;
				}

				//ToDo: Load Images from Firebase
				collapsingToolbarLayout.setTitle(cutEntry.getName());
				if (cutEntry.getCategoryId() == Constants.Ids.CATEGORY_BEEF) {
					titleImage.setImageResource(R.drawable.ic_beef);
				} else if (cutEntry.getCategoryId() == Constants.Ids.CATEGORY_PORK) {
					titleImage.setImageResource(R.drawable.ic_pork);
				} else if (cutEntry.getCategoryId() == Constants.Ids.CATEGORY_POULTRY) {
					titleImage.setImageResource(R.drawable.ic_poultry);
				} else if (cutEntry.getCategoryId() == Constants.Ids.CATEGORY_FISH) {
					titleImage.setImageResource(R.drawable.ic_fish);
				} else if (cutEntry.getCategoryId() == Constants.Ids.CATEGORY_VEGETABLE) {
					titleImage.setImageResource(R.drawable.ic_beef);
				} else if (cutEntry.getCategoryId() == Constants.Ids.CATEGORY_OTHER) {
					titleImage.setImageResource(R.drawable.ic_beef);
				}

			}
		});



		CutFragmentAdapter adapter = new CutFragmentAdapter(getSupportFragmentManager(), cutId);
		pager.setAdapter(adapter);
		tabLayout.setupWithViewPager(pager);
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
