package com.example.glm9637.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.glm9637.myapplication.ui.activity.RecipeStepActivity;
import com.example.glm9637.myapplication.utils.Constants;

/**
 * Erzeugt von M. Fengels am 03.08.2018.
 */
public class TimerService extends Service {

	private NotificationCompat.Builder notification;
	private NotificationManager manager;
	private CountDownTimer timer;

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		notification = new NotificationCompat.Builder(this, Constants.NOTIFICATION_CHANNEL_ID);
		notification.setAutoCancel(true);
		notification.setSmallIcon(R.drawable.ic_access_time_black_24dp);
		notification.setContentTitle("Timer Notification");
		notification.setVibrate(new long[]{-1});
		notification.setOngoing(true);
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(intent== null){
			return START_NOT_STICKY;
		}
		long duration = intent.getLongExtra(Constants.Arguments.TIMER_DURATION, 0);
		final String title = intent.getStringExtra(Constants.Arguments.TIMER_TEXT);
		notification.setContentTitle(title);
		Intent notifyIntent = new Intent(this, RecipeStepActivity.class);
		notifyIntent.putExtras(intent.getExtras());
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setContentIntent(contentIntent);
		timer = new CountDownTimer(duration * 60000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				int numMessages = 0;
				int seconds = (int) Math.floor(Math.floor(millisUntilFinished / 1000));
				int minutes = seconds / 60;
				int hours = minutes / 60;
				seconds = seconds % 60;
				minutes = minutes % 60;
				notification.setContentText(getString(R.string.timer_content, hours, minutes, seconds))
						.setNumber(++numMessages);
				manager.notify(
						Constants.Ids.TIMER_NOTIFICATION_ID,
						notification.build());
			}

			@Override
			public void onFinish() {
				notification.setContentText("Step finished");
				notification.setVibrate(new long[]{50, 50});
				notification.setOngoing(false);
				Log.d("Service", "Notification updated");
				manager.notify(
						Constants.Ids.TIMER_NOTIFICATION_ID,
						notification.build());
				Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				if (v != null) {
					// Vibrate for 500 milliseconds
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
						v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
					} else {
						//deprecated in API 26
						v.vibrate(500);
					}
				}
			}
		};
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel channel = new NotificationChannel(Constants.NOTIFICATION_CHANNEL_ID,
					"Timer Notifications",
					NotificationManager.IMPORTANCE_LOW);
			channel.enableVibration(false);
			channel.enableLights(false);
			manager.createNotificationChannel(channel);
		}
		timer.start();
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (timer != null)
			timer.cancel();
	}
}
