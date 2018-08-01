package com.example.glm9637.myapplication;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.glm9637.myapplication.adapter.CategoryFragmentPagerAdapter;
import com.example.glm9637.myapplication.adapter.CutFragmentPagerAdapter;
import com.example.glm9637.myapplication.database.RecipeDatabase;
import com.example.glm9637.myapplication.database.entry.CutEntry;
import com.example.glm9637.myapplication.utils.Constants;
import com.example.glm9637.myapplication.view_model.CutViewModel;

public class CutActivity extends AppCompatActivity {

	private ImageView titleImage;
	private CutViewModel viewModel;
	private CollapsingToolbarLayout collapsingToolbarLayout;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cut);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        final long cutId = getIntent().getLongExtra(Constants.Arguments.CUT_ID,0);
	
	    Toolbar toolbar = findViewById(R.id.toolbar);
	    titleImage = findViewById(R.id.title_image);
	    setSupportActionBar(toolbar);
	    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	    viewModel = new CutViewModel(RecipeDatabase.getInstance(this),cutId);
	    viewModel.getCut().observe(this, new Observer<CutEntry>() {
		    @Override
		    public void onChanged(@Nullable CutEntry cutEntry) {
		    	if(cutEntry==null){
		    		return;
			    }
			    
			    //ToDo: Load Images from Firebase
		    	collapsingToolbarLayout.setTitle(cutEntry.getName());
		    	if(cutEntry.getCategoryid()==Constants.Ids.CATEGORY_BEEF){
		    	    titleImage.setImageResource(R.drawable.ic_beef);
			    }else if(cutEntry.getCategoryid()==Constants.Ids.CATEGORY_PORK){
				    titleImage.setImageResource(R.drawable.ic_pork);
			    }else if(cutEntry.getCategoryid()==Constants.Ids.CATEGORY_POULTY){
				    titleImage.setImageResource(R.drawable.ic_poultry);
			    }else if(cutEntry.getCategoryid()==Constants.Ids.CATEGORY_FISH){
				    titleImage.setImageResource(R.drawable.ic_fish);
			    }else if(cutEntry.getCategoryid()==Constants.Ids.CATEGORY_VEGETABLE){
				    titleImage.setImageResource(R.drawable.ic_beef);
			    }else if(cutEntry.getCategoryid()==Constants.Ids.CATEGORY_OTHER){
				    titleImage.setImageResource(R.drawable.ic_beef);
			    }
		    	
		    }
	    });
	
	    TabLayout tabLayout = findViewById(R.id.tab_layout);
	    ViewPager pager = findViewById(R.id.view_pager);
	    CutFragmentPagerAdapter adapter = new CutFragmentPagerAdapter(getSupportFragmentManager(),cutId);
	    pager.setAdapter(adapter);
	    tabLayout.setupWithViewPager(pager);
	    
    }
}
