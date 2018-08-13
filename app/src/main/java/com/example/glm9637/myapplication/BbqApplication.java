package com.example.glm9637.myapplication;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Erzeugt von M. Fengels am 31.07.2018.
 */
public class BbqApplication extends Application {
	public void onCreate() {
		super.onCreate();
		Stetho.initializeWithDefaults(this);
	}
}
