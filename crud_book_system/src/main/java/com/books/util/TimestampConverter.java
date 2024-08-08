package com.books.util;

import java.sql.Timestamp;
import java.time.LocalDate;

public class TimestampConverter {
	public static LocalDate toLocalDate(Timestamp timestamp) {
		return timestamp.toLocalDateTime().toLocalDate();
	}
}
