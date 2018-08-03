package com.example.glm9637.myapplication.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class EditRecipeViewPager extends ViewPager {

	private boolean enabled;

	public EditRecipeViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.enabled = true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.enabled && super.onTouchEvent(event);
	}

	@Override
	public boolean performClick() {
		return super.performClick();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {

		return this.enabled && super.onInterceptTouchEvent(event);
	}

	public void setPagingEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}