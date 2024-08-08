package com.books.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TypeConverter {
	// LocalDate.parse() 메소드: convert from string to localdate
	public static LocalDate stringToLocalDate(String localDateString) {
		return LocalDate.parse(localDateString);
	}
	public static LocalDate timeStampToLocalDate(Timestamp timestamp) {
		return timestamp.toLocalDateTime().toLocalDate();
	}
	public static Timestamp localDateToTimestamp(LocalDate localDate) {        
        // Convert LocalDate to LocalDateTime
        LocalDateTime localDateTime = localDate.atStartOfDay();
        
        // Convert LocalDateTime to Instant
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        
        // Convert Instant to Timestamp
        return Timestamp.from(instant); 
	}
	
}
