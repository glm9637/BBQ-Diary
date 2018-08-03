package com.example.glm9637.myapplication.ui.fragment.edit_recipe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.glm9637.myapplication.R;

/**
 * Erzeugt von M. Fengels am 03.08.2018.
 */
public class EditRecipeFinishFragment extends Fragment {
	
	public static EditRecipeFinishFragment createFragment(boolean b) {
		return new EditRecipeFinishFragment();
	}
	
	public interface RecipeFinishedListener{
		void onRecipeFinished();
	}
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_edit_finish, container, false);
		//rootView.findViewById(R.id.btn)
		
		return rootView;
	}
	
}
