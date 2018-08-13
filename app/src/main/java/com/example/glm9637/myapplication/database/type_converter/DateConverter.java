package com.example.glm9637.myapplication.database.type_converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Erzeugt von M. Fengels am 02.08.2018.
 */
public class DateConverter {
	@TypeConverter
	public static Date toDate(Long timestamp) {
		return timestamp == null ? null : new Date(timestamp);
	}

	@TypeConverter
	public static Long toTimestamp(Date date) {
		return date == null ? null : date.getTime();
	}
}
