package com.example.glm9637.myapplication.ui.adapter.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.ui.fragment.cut.DiscoverFragment;
import com.example.glm9637.myapplication.ui.fragment.cut.RecipeFragment;

/**
 * Erzeugt von M. Fengels am 23.07.2018.
 */
public class CutFragmentAdapter extends FragmentPagerAdapter {

	private final long cutId;
	private long categoryId;
	private Context context;

	public CutFragmentAdapter(Context context, FragmentManager fm, long cutId, long categoryId) {
		super(fm);
		this.cutId = cutId;
		this.categoryId = categoryId;
		this.context = context;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return RecipeFragment.createFragment(cutId);
			default:
				return DiscoverFragment.createFragment(cutId, categoryId);
		}

	}

	@Override
	public int getCount() {
		return 2;
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 0:
				return context.getString(R.string.title_recipes);
			case 1:
				return context.getString(R.string.title_discover);
		}
		return "";
	}
}
