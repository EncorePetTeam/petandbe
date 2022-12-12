package com.encore.petandbe.utils.validator;

import static java.lang.Character.*;

import java.time.LocalTime;

import com.encore.petandbe.exception.WrongTimeException;

public class TimeValidator {

	private static final int TIME_LENGTH = 8;
	private static final int startTimeLength = TIME_LENGTH / 2;
	private static final int startTimeDivide = startTimeLength / 2;

	public static String convertLocalTimeToString(LocalTime startTime, LocalTime endTime) {
		String time = "";
		time += String.valueOf(startTime.getHour()) + String.valueOf(startTime.getMinute());
		time += String.valueOf(endTime.getHour()) + String.valueOf(endTime.getMinute());
		return time;
	}

	public static LocalTime parseStartTime(String time) {
		String startTimeHour = time.substring(0, startTimeLength / 2);
		String startTimeMinute = time.substring(startTimeDivide, startTimeLength);
		return LocalTime.of(Integer.parseInt(startTimeHour), Integer.parseInt(startTimeMinute));
	}

	public static LocalTime parseEndTime(String time) {
		String endTimeHour = time.substring(startTimeLength, TIME_LENGTH - startTimeDivide);
		String endTimeMinute = time.substring(TIME_LENGTH - startTimeDivide, TIME_LENGTH);
		return LocalTime.of(Integer.parseInt(endTimeHour), Integer.parseInt(endTimeMinute));
	}

	public static void validateTime(String time) {
		if (time.length() != TIME_LENGTH) {
			throw new WrongTimeException(time);
		}

		if (isDigitTime(time)) {
			throw new WrongTimeException(time);
		}

		String startTimeHour = time.substring(0, startTimeLength / 2);
		String startTimeMinute = time.substring(startTimeDivide, startTimeLength);
		String endTimeHour = time.substring(startTimeLength, TIME_LENGTH - startTimeDivide);
		String endTimeMinute = time.substring(TIME_LENGTH - startTimeDivide, TIME_LENGTH);

		if (validateHour(startTimeHour) || validateHour(endTimeHour) || validateMinute(startTimeMinute)
			|| validateMinute(endTimeMinute)) {
			throw new WrongTimeException(time);
		}

		if (Integer.parseInt(startTimeHour + startTimeMinute) >= Integer.parseInt(endTimeHour + endTimeMinute)) {
			throw new WrongTimeException(time);
		}
	}

	private static boolean validateHour(String time) {
		return 0 > Integer.parseInt(time) || Integer.parseInt(time) > 23;
	}

	private static boolean validateMinute(String time) {
		return 0 > Integer.parseInt(time) || Integer.parseInt(time) > 60;
	}

	private static boolean isDigitTime(String time) {
		for (int i = 0; i < TIME_LENGTH; i++) {
			if (!isDigit(time.charAt(i))) {
				return true;
			}
		}
		return false;
	}
}
