package com.example.glm9637.myapplication.ui.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.glm9637.myapplication.database.entry.StepEntry;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Erzeugt von M. Fengels am 03.08.2018.
 */
public class WidgetHelper {

	public static void saveToFile(StepEntry stepEntry, Context context) {
		try {
			FileOutputStream outputStream = context.openFileOutput("widget.data", Context.MODE_PRIVATE);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(stepEntry);
			objectOutputStream.close();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static StepEntry loadFromFile(Context context) {
		try {
			FileInputStream fileInputStream = context.openFileInput("widget.data");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			StepEntry stepEntry = (StepEntry) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
			return stepEntry;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void sendRefreshBroadcast(Context context) {

		AppWidgetManager man = AppWidgetManager.getInstance(context);
		int[] ids = man.getAppWidgetIds(
				new ComponentName(context,StepWidgetProvider.class));
		Intent updateIntent = new Intent();
		updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		updateIntent.putExtra(StepWidgetProvider.WIDGET_ID_KEY, ids);
		//updateIntent.putExtra(MyWidgetProvider.WIDGET_DATA_KEY, data);
		context.sendBroadcast(updateIntent);
	}

}
