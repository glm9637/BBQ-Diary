package com.example.glm9637.myapplication.ui.fragment.edit_recipe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.utils.Constants;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class EditRecipeDescriptionFragment extends Fragment {


	private static Bundle instanceState;
	private TextInputEditText descriptionText;

	public static EditRecipeDescriptionFragment createFragment() {

		return new EditRecipeDescriptionFragment();
	}

	public static EditRecipeDescriptionFragment createFragment(String description) {
		EditRecipeDescriptionFragment fragment = new EditRecipeDescriptionFragment();
		Bundle bundle = new Bundle();
		bundle.putString(Constants.Arguments.DESCRIPTION, description);
		fragment.setArguments(bundle);
		return fragment;
	}

	public static void reset() {
		instanceState = null;
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

	@Override
	public void onPause() {
		super.onPause();
		instanceState = new Bundle();
		instanceState.putString(Constants.Arguments.DESCRIPTION, descriptionText.getText().toString());
	}

	public String getDescription() {
		if (descriptionText == null) {
			return instanceState.getString(Constants.Arguments.DESCRIPTION);
		}
		return descriptionText.getText().toString();
	}
}
