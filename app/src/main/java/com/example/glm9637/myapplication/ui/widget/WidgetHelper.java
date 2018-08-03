package com.example.glm9637.myapplication.ui.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.example.glm9637.myapplication.database.entry.StepEntry;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void sendRefreshBroadcast(Context context) {
		Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		intent.setComponent(new ComponentName(context,StepWidgetProvider.class));
		context.sendBroadcast(intent);
	}
	
}
