package com.example.glm9637.myapplication.ui.fragment.edit_recipe;

import android.os.Binder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.utils.Constants;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class EditRecipeNameFragment extends Fragment {
	
	private EditText nameText;
	private EditText descriptionText;
	private CheckBox rubCheck;
	
	public static EditRecipeNameFragment createFragment() {
		EditRecipeNameFragment fragment = new EditRecipeNameFragment();
		
		return fragment;
	}
	
	public static EditRecipeNameFragment createFragment(boolean isRub) {
		EditRecipeNameFragment fragment = new EditRecipeNameFragment();
		Bundle bundle = new Bundle();
		bundle.putBoolean(Constants.Arguments.IS_RUB, isRub);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	public static EditRecipeNameFragment createFragment(String name, String shortDescription, boolean isRub) {
		EditRecipeNameFragment fragment = new EditRecipeNameFragment();
		Bundle bundle = new Bundle();
		bundle.putBoolean(Constants.Arguments.IS_RUB, isRub);
		bundle.putString(Constants.Arguments.NAME, name);
		bundle.putString(Constants.Arguments.DESCRIPTION, shortDescription);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_edit_name, container, false);
		nameText = rootView.findViewById(R.id.txt_name);
		descriptionText = rootView.findViewById(R.id.txt_description_short);
		rubCheck = rootView.findViewById(R.id.chk_rub);
		if (getArguments() != null) {
			boolean isRub = getArguments().getBoolean(Constants.Arguments.IS_RUB, false);
			String name = getArguments().getString(Constants.Arguments.NAME, "");
			String shortDescription = getArguments().getString(Constants.Arguments.DESCRIPTION, "");
			
			nameText.setText(name);
			descriptionText.setText(shortDescription);
			rubCheck.setChecked(isRub);
			if (isRub) {
				rubCheck.setEnabled(false);
			}
			
		}
		return rootView;
	}
	
	
	public boolean isRub() {
		return rubCheck.isChecked();
	}
	
	public String getName() {
		return nameText.getText().toString();
	}
	
	public String getShortDescription() {
		return descriptionText.getText().toString();
	}
}
