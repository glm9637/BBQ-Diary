package com.example.glm9637.myapplication.ui.adapter.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import com.example.glm9637.myapplication.database.entry.IngredientEntry;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeDescriptionFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeFinishFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeIngredientFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeNameFragment;
import com.example.glm9637.myapplication.ui.fragment.edit_recipe.EditRecipeStepsFragment;
import com.example.glm9637.myapplication.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class EditRecipeFragmentAdapter extends FragmentPagerAdapter {

	private RecipeEntry recipe;
	private boolean isRub;

	private EditRecipeNameFragment nameFragment;
	private EditRecipeDescriptionFragment descriptionFragment;
	private EditRecipeIngredientFragment ingredientFragment;
	private EditRecipeStepsFragment stepsFragment;
	private EditRecipeFinishFragment finishFragment;
	private FragmentManager fragmentManager;


	private FragmentTransaction mCurTransaction = null;
	private ArrayList<Fragment.SavedState> mSavedState = new ArrayList<>();
	private ArrayList<Fragment> mFragments = new ArrayList<>();

	public EditRecipeFragmentAdapter(FragmentManager fm, RecipeEntry recipe) {
		super(fm);
		this.recipe = recipe;
		this.fragmentManager = fm;
	}

	public EditRecipeFragmentAdapter(FragmentManager fm, boolean isRub) {
		super(fm);
		this.isRub = isRub;
		this.fragmentManager = fm;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				if (nameFragment == null) {
					if (recipe == null) {
						nameFragment = EditRecipeNameFragment.createFragment(isRub);
					} else {
						nameFragment = EditRecipeNameFragment.createFragment(recipe.getName(), recipe.getShortDescription(), recipe.isSeasoning(), recipe.getCookingStyle(), recipe.getDuration());
					}
				}
				return nameFragment;
			case 1:
				if (descriptionFragment == null) {
					if (recipe == null) {
						descriptionFragment = EditRecipeDescriptionFragment.createFragment();
					} else {
						descriptionFragment = EditRecipeDescriptionFragment.createFragment(recipe.getDescription());
					}
				}
				return descriptionFragment;
			case 2:
				if (ingredientFragment == null) {
					if (recipe == null) {
						ingredientFragment = EditRecipeIngredientFragment.createFragment();
					} else {
						ingredientFragment = EditRecipeIngredientFragment.createFragment(recipe.getId());
					}
				}
				return ingredientFragment;
			case 3:
				if (stepsFragment == null) {
					if (recipe == null) {
						stepsFragment = EditRecipeStepsFragment.createFragment();
					} else {
						stepsFragment = EditRecipeStepsFragment.createFragment(recipe.getId());
					}
				}
				return stepsFragment;
			default:
				if (finishFragment == null) {
					finishFragment = EditRecipeFinishFragment.createFragment();
				}
				return finishFragment;
		}

	}

	@Override
	public int getCount() {
		return 5;
	}

	public String getName() {
		if(nameFragment==null){
			nameFragment = (EditRecipeNameFragment) instantiateItem(null,0);
		}
		return nameFragment.getName();
	}

	public String getShortDescription() {
		if(nameFragment==null){
			nameFragment = (EditRecipeNameFragment) instantiateItem(null,0);
		}
		return nameFragment.getShortDescription();
	}

	public String getCookingStyle() {
		if(nameFragment==null){
			nameFragment = (EditRecipeNameFragment)instantiateItem(null,0);
		}
		return nameFragment.getCookingStyle();
	}

	public long getDuration() {
		if(nameFragment==null){
			nameFragment = (EditRecipeNameFragment)instantiateItem(null,0);
		}
		return nameFragment.getDuration();
	}

	public String getDescription() {
		if(descriptionFragment==null){
			descriptionFragment = (EditRecipeDescriptionFragment) instantiateItem(null,1);
		}
		return descriptionFragment.getDescription();
	}

	public List<IngredientEntry> getIngredients() {
		if(ingredientFragment==null){
			ingredientFragment = (EditRecipeIngredientFragment) instantiateItem(null,2);
		}
		return ingredientFragment.getIngredients();
	}

	public List<StepEntry> getSteps() {
		if(stepsFragment==null){
			stepsFragment = (EditRecipeStepsFragment) instantiateItem(null,3);
		}
		return stepsFragment.getSteps();
	}

	public boolean getIsRub() {
		if(nameFragment==null){
			nameFragment = (EditRecipeNameFragment) instantiateItem(null,0);
		}
		return nameFragment.isRub();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		if (mFragments.size() > position) {
			Fragment f = mFragments.get(position);
			if (f != null) {
				return f;
			}
		}

		if (mCurTransaction == null) {
			mCurTransaction = fragmentManager.beginTransaction();
		}

		Fragment fragment = getItem(position);
		if (mSavedState.size() > position) {
			Fragment.SavedState fss = mSavedState.get(position);
			if (fss != null) {
				fragment.setInitialSavedState(fss);
			}
		}
		while (mFragments.size() <= position) {
			mFragments.add(null);
		}
		fragment.setMenuVisibility(false);
		fragment.setUserVisibleHint(false);
		mFragments.set(position, fragment);
		// Lacy: Add call to getItemTag()
		mCurTransaction.add(container.getId(), fragment, getItemTag(position));

		return fragment;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		Fragment fragment = (Fragment)object;

		if (mCurTransaction == null) {
			mCurTransaction = fragmentManager.beginTransaction();
		}
		while (mSavedState.size() <= position) {
			mSavedState.add(null);
		}
		mSavedState.set(position, fragmentManager.saveFragmentInstanceState(fragment));
		mFragments.set(position, null);

		mCurTransaction.remove(fragment);
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		if (mCurTransaction != null) {
			mCurTransaction.commitAllowingStateLoss();
			mCurTransaction = null;
			fragmentManager.executePendingTransactions();
		}
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return ((Fragment)object).getView() == view;
	}

	@Override
	public Parcelable saveState() {
		Bundle state = null;
		if (mSavedState.size() > 0) {
			state = new Bundle();
			Fragment.SavedState[] fss = new Fragment.SavedState[mSavedState.size()];
			mSavedState.toArray(fss);
			state.putParcelableArray("states", fss);
		}
		for (int i=0; i<mFragments.size(); i++) {
			Fragment f = mFragments.get(i);
			if (f != null) {
				if (state == null) {
					state = new Bundle();
				}
				String key = "f" + i;
				fragmentManager.putFragment(state, key, f);
			}
		}
		return state;
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
		if (state != null) {
			Bundle bundle = (Bundle)state;
			bundle.setClassLoader(loader);
			Parcelable[] fss = bundle.getParcelableArray("states");
			mSavedState.clear();
			mFragments.clear();
			if (fss != null) {
				for (int i=0; i<fss.length; i++) {
					mSavedState.add((Fragment.SavedState)fss[i]);
				}
			}
			Iterable<String> keys = bundle.keySet();
			for (String key: keys) {
				if (key.startsWith("f")) {
					int index = Integer.parseInt(key.substring(1));
					Fragment f = fragmentManager.getFragment(bundle, key);
					if (f != null) {
						while (mFragments.size() <= index) {
							mFragments.add(null);
						}
						f.setMenuVisibility(false);
						mFragments.set(index, f);
					}
				}
			}
		}
	}

	String getItemTag(int position) {
		switch (position) {
			case 0:
				return Constants.Tags.NAME_FRAGMENT;
			case 1:
				return Constants.Tags.DESCRIPTION_FRAGMENT;
			case 2:
				return Constants.Tags.INGREDIENT_FRAGMENT;
			case 3:
				return Constants.Tags.STEP_FRAGMENT;
			case 4:
				return Constants.Tags.FINISH_FRAGMENT;
		}
		return "";
	}
}
