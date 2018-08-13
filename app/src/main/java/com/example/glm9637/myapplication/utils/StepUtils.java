package com.example.glm9637.myapplication.utils;

import android.content.Context;
import android.content.Intent;

import com.example.glm9637.myapplication.TimerService;
import com.example.glm9637.myapplication.database.entry.RecipeEntry;
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.ui.widget.WidgetHelper;

/**
 * Erzeugt von M. Fengels am 03.08.2018.
 */
public class StepUtils {
	public static void setStepTimer(Context context, StepEntry stepEntry, long categoryId, long cutId) {
		WidgetHelper.saveToFile(stepEntry, context);
		context.stopService(new Intent(context, TimerService.class));
		Intent intent = new Intent(context, TimerService.class);
		intent.putExtra(Constants.Arguments.TIMER_DURATION, stepEntry.getDuration());
		intent.putExtra(Constants.Arguments.TIMER_TEXT, stepEntry.getName());
		intent.putExtra(Constants.Arguments.CATEGORY_ID, categoryId);
		intent.putExtra(Constants.Arguments.CUT_ID, cutId);
		intent.putExtra(Constants.Arguments.RECIPE_ID, stepEntry.getRecipeId());
		intent.putExtra(Constants.Arguments.STEP_ID, stepEntry.getId());
		context.startService(intent);
		WidgetHelper.sendRefreshBroadcast(context, intent.getExtras());
	}
}
