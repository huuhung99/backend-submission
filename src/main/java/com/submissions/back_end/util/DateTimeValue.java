package com.submissions.back_end.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("exports")
public class DateTimeValue {
	public static List<Integer> listHour() {
		List<Integer> listHour = new ArrayList<>();
		for (int i = 0; i < 24; i++) {
			listHour.add(i);
		}
		return listHour;
	}

	public static List<Integer> listMinute() {
		List<Integer> listMinute = new ArrayList<>();
		for (int i = 0; i < 60; i++) {
			listMinute.add(i);
		}
		return listMinute;
	}

	// convert giờ, phút, giây sang epoch second
	public static long getSecond(Integer hour, Integer minute, Integer second) {
		return hour * 3600 + minute * 60 + second;
	}

	public static List<String> listHours() {
		List<String> listHours = new ArrayList<>();
		for (int i = 0; i < 24; i++) {
			listHours.add(String.format("%02d", i));
		}
		return listHours;
	}

	public static List<String> listMinutes() {
		List<String> listMinutes = new ArrayList<>();
		for (int i = 0; i < 60; i++) {
			listMinutes.add(String.format("%02d", i));
		}
		return listMinutes;
	}

	public static int setHourOrMinute(List<String> listTime, int time) {
		for (int i = 0; i < listTime.size(); i++) {
			if (Integer.parseInt(listTime.get(i)) == time) {
				return i;
			}
		}
		return 0;
	}

	public static String getTimeCall(long startTime, long finishTime) {
		String time = "";

		long diffSeconds = finishTime - startTime;
		if (diffSeconds < 0)
			return null;

		if (diffSeconds >= 60) {
			long diffMinutes = diffSeconds / (60);
			time += String.valueOf(diffMinutes) + " phút";
			diffSeconds = diffSeconds - (60 * diffMinutes);
			time += " - " + String.valueOf(diffSeconds) + " giây";
		} else {
			time += String.valueOf(diffSeconds) + " giây";
		}

		return time.trim();
	}

}