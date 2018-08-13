package com.example.glm9637.myapplication.utils;

import android.content.Context;
import android.content.Intent;

import com.example.glm9637.myapplication.TimerService;
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.ui.widget.WidgetHelper;

/**
 * Erzeugt von M. Fengels am 03.08.2018.
 */
public class StepUtils {

	public static void setStepTimer(Context context, StepEntry stepEntry) {
		WidgetHelper.saveToFile(stepEntry, context);
		WidgetHelper.sendRefreshBroadcast(context);

		context.stopService(new Intent(context, TimerService.class));
		Intent intent = new Intent(context, TimerService.class);
		intent.putExtra(Constants.Arguments.TIMER_DURATION, stepEntry.getDuration());
		context.startService(intent);
	}
}
