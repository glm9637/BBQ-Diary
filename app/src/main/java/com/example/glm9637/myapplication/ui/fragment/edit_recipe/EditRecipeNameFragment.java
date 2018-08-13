package com.example.glm9637.myapplication.ui.fragment.edit_recipe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.utils.Constants;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class EditRecipeNameFragment extends Fragment {

	private TextInputEditText nameText;
	private TextInputEditText descriptionText;
	private TextInputEditText cookingStyleText;
	private TextInputEditText durationText;
	private CheckBox rubCheck;
	private Button btnNext;

	private boolean swipeEnabled = false;

	public interface EnableSwipeListener {
		void onEnableSwipe();

		void onDisableSwipe();

		void onSwipeToNextPage();
	}

	public static EditRecipeNameFragment createFragment(boolean isRub) {
		EditRecipeNameFragment fragment = new EditRecipeNameFragment();
		Bundle bundle = new Bundle();
		bundle.putBoolean(Constants.Arguments.IS_RUB, isRub);
		fragment.setArguments(bundle);
		return fragment;
	}

	public static EditRecipeNameFragment createFragment(String name, String shortDescription, boolean isRub, String cookingStyle, long duration) {
		EditRecipeNameFragment fragment = new EditRecipeNameFragment();
		Bundle bundle = new Bundle();
		bundle.putBoolean(Constants.Arguments.IS_RUB, isRub);
		bundle.putString(Constants.Arguments.NAME, name);
		bundle.putString(Constants.Arguments.DESCRIPTION, shortDescription);
		bundle.putString(Constants.Arguments.COOKING_STYLE, cookingStyle);
		bundle.putLong(Constants.Arguments.DURATION, duration);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_edit_name, container, false);
		btnNext = rootView.findViewById(R.id.btn_next);
		btnNext.setEnabled(false);
		nameText = rootView.findViewById(R.id.txt_name);
		nameText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				if (charSequence.length() > 3) {
					if (!swipeEnabled) {
						swipeEnabled = true;
						((EnableSwipeListener)getActivity()).onEnableSwipe();
						btnNext.setEnabled(true);
					}
				} else {
					if (swipeEnabled) {
						((EnableSwipeListener)getActivity()).onDisableSwipe();
						btnNext.setEnabled(false);
						swipeEnabled = false;
					}
				}
			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		});
		descriptionText = rootView.findViewById(R.id.txt_description_short);
		cookingStyleText = rootView.findViewById(R.id.txt_cooking_style);
		durationText = rootView.findViewById(R.id.txt_duration);

		rubCheck = rootView.findViewById(R.id.chk_rub);
		if (getArguments() != null) {
			boolean isRub = getArguments().getBoolean(Constants.Arguments.IS_RUB, false);
			String name = getArguments().getString(Constants.Arguments.NAME, "");
			String shortDescription = getArguments().getString(Constants.Arguments.DESCRIPTION, "");
			String cookingStyle = getArguments().getString(Constants.Arguments.COOKING_STYLE, "");
			long duration = getArguments().getLong(Constants.Arguments.DURATION, 0);

			nameText.setText(name);
			descriptionText.setText(shortDescription);
			rubCheck.setChecked(isRub);
			cookingStyleText.setText(cookingStyle);
			if(duration==0) {
				durationText.setText("");
			}else {
				durationText.setText(String.valueOf(duration));
			}
			if (isRub) {
				rubCheck.setEnabled(false);
			}

		}

		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				((EnableSwipeListener)getActivity()).onSwipeToNextPage();
			}
		});

		((EnableSwipeListener)getActivity()).onDisableSwipe();
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

	public String getCookingStyle() {
		return cookingStyleText.getText().toString();
	}

	public long getDuration() {
		return Long.parseLong(durationText.getText().toString());
	}
}
