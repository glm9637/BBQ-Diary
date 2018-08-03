package com.example.glm9637.myapplication.ui.fragment.edit_recipe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.utils.Constants;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class EditRecipeDescriptionFragment extends Fragment {
	
	
	private EditText descriptionText;
	
	public static EditRecipeDescriptionFragment createFragment() {
		EditRecipeDescriptionFragment fragment = new EditRecipeDescriptionFragment();
		
		return fragment;
	}
	
	public static EditRecipeDescriptionFragment createFragment( String description) {
		EditRecipeDescriptionFragment fragment = new EditRecipeDescriptionFragment();
		Bundle bundle = new Bundle();
		bundle.putString(Constants.Arguments.DESCRIPTION, description);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_edit_description, container, false);
		descriptionText = rootView.findViewById(R.id.txt_description);
		if (getArguments() != null) {
			String description = getArguments().getString(Constants.Arguments.DESCRIPTION, "");
			descriptionText.setText(description);
		}
		return rootView;
	}
	
	public String getName() {
		return descriptionText.getText().toString();
	}
	
}
