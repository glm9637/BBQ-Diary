package com.example.glm9637.myapplication.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.glm9637.myapplication.fragments.category.CutsFragment;
import com.example.glm9637.myapplication.fragments.category.RubFragment;

/**
 * Erzeugt von M. Fengels am 23.07.2018.
 */
public class CutFragmentPagerAdapter extends FragmentPagerAdapter {
	
	private long cutId;
	
	public CutFragmentPagerAdapter(FragmentManager fm, long cutId) {
		super(fm);
		this.cutId = cutId;
	}
	
	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return CutsFragment.createFragment(cutId);
			default:
				return RubFragment.createFragment(cutId);
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
				return "Cuts";
			case 1:
				return "Rubs";
		}
		return "";
	}
}
