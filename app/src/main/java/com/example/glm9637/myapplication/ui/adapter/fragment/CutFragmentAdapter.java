package com.example.glm9637.myapplication.ui.adapter.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.glm9637.myapplication.ui.fragment.cut.RecipeFragment;

/**
 * Erzeugt von M. Fengels am 23.07.2018.
 */
public class CutFragmentAdapter extends FragmentPagerAdapter {
	
	private final long cutId;
	
	public CutFragmentAdapter(FragmentManager fm, long cutId) {
		super(fm);
		this.cutId = cutId;
	}
	
	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return RecipeFragment.createFragment(cutId);
			default:
				return RecipeFragment.createFragment(cutId);
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
				return "Recipes";
			case 1:
				return "Discover";
		}
		return "";
	}
}
