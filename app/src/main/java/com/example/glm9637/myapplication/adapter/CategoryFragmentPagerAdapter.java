package com.example.glm9637.myapplication.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.glm9637.myapplication.fragments.category.CutsFragment;

/**
 * Erzeugt von M. Fengels am 23.07.2018.
 */
public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {

	private long cutId;

	public CategoryFragmentPagerAdapter(FragmentManager fm, long cutId) {
		super(fm);
		this.cutId = cutId;
	}
	
	@Override
	public Fragment getItem(int position) {
		return CutsFragment.createFragment(cutId);
	}
	
	@Override
	public int getCount() {
		return 2;
	}
	
	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position){
			case 0:
				return "Cuts";
			case 1:
				return "Rubs";
		}
		return "";
	}
}
