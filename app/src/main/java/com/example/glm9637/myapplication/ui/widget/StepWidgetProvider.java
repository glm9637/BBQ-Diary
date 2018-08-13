package com.example.glm9637.myapplication.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.StepEntry;
import com.example.glm9637.myapplication.ui.activity.RecipeStepActivity;

/**
 * Erzeugt von M. Fengels am 03.08.2018.
 */
public class StepWidgetProvider extends AppWidgetProvider {

	public static final String WIDGET_ID_KEY = "widget id";
	public static final String WIDGET_DATA_KEY = "widget data";

	private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
										int appWidgetId, PendingIntent pendingIntent) {

		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_step);
		//Intent intent = new Intent(context,WidgetRemoteViewsService.class);
		StepEntry stepEntry = WidgetHelper.loadFromFile(context);
		if (stepEntry == null) {
			views.setTextViewText(R.id.txt_name, "No step selected");
		} else {
			views.setTextViewText(R.id.txt_name, stepEntry.getName());
			views.setTextViewText(R.id.txt_step_number, stepEntry.getOrder() + "");
			views.setTextViewText(R.id.txt_description, stepEntry.getDescription());
			views.setTextViewText(R.id.txt_duration, stepEntry.getDuration() + "");
		}
		views.setOnClickPendingIntent(R.id.root_view,pendingIntent);
		// Instruct the widget manager to update the widget
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// There may be multiple widgets active, so update all of them
		for (int appWidgetId : appWidgetIds) {
			updateAppWidget(context, appWidgetManager, appWidgetId, null);
		}
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.hasExtra(WIDGET_ID_KEY)) {
			int[] ids = intent.getExtras().getIntArray(WIDGET_ID_KEY);
			if (intent.hasExtra(WIDGET_DATA_KEY)) {
				Bundle data = intent.getExtras().getBundle(WIDGET_DATA_KEY);
				this.update(context, AppWidgetManager.getInstance(context), ids, data);
			} else {
				this.onUpdate(context, AppWidgetManager.getInstance(context), ids);
			}
		} else super.onReceive(context, intent);
	}

	private void update(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, Bundle data) {
		Intent notifyIntent = new Intent(context, RecipeStepActivity.class);
		notifyIntent.putExtras(data);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notifyIntent, 0);
		for (int appWidgetId : appWidgetIds) {
			updateAppWidget(context, appWidgetManager, appWidgetId, contentIntent);
		}
	}
}
