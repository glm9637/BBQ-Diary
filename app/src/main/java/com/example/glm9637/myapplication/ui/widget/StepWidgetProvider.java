package com.example.glm9637.myapplication.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.glm9637.myapplication.R;
import com.example.glm9637.myapplication.database.entry.StepEntry;

/**
 * Erzeugt von M. Fengels am 03.08.2018.
 */
public class StepWidgetProvider extends AppWidgetProvider {
	
	private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
										int appWidgetId) {
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_step);
		//Intent intent = new Intent(context,WidgetRemoteViewsService.class);
		StepEntry stepEntry = WidgetHelper.loadFromFile(context);
		if (stepEntry == null) {
			views.setTextViewText(R.id.txt_name, "No step selected");
		} else {
			views.setTextViewText(R.id.txt_name, stepEntry.getName());
			views.setTextViewText(R.id.txt_step_number,stepEntry.getOrder()+"");
			views.setTextViewText(R.id.txt_description,stepEntry.getDescription());
			views.setTextViewText(R.id.txt_duration,stepEntry.getDuration()+"");
		}
		
		// Instruct the widget manager to update the widget
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// There may be multiple widgets active, so update all of them
		for (int appWidgetId : appWidgetIds) {
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}
		
	}
}
