package com.encore.petandbe.utils.validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.encore.petandbe.exception.WrongTimeException;
import com.encore.petandbe.model.accommodation.reservation.Reservation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateTimeValidator {

	private static LocalDateTimeValidator localDateTimeValidator = null;

	public static LocalDateTimeValidator of() {
		if (localDateTimeValidator == null) {
			localDateTimeValidator = new LocalDateTimeValidator();
		}
		return localDateTimeValidator;
	}

	public String convertLocalDateTimeToString(LocalDateTime localDateTime) {
		return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public LocalDateTime convertStringToLocalDateTime(String dateTime) {
		LocalDateTime localDateTime;
		try {
			localDateTime = LocalDateTime.parse(dateTime,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			throw new WrongTimeException("Wrong time request : " + dateTime);
		}
		return localDateTime;
	}

	public void checkCheckInDateIsBeforeCheckOutDateTime(String checkInDate, String checkOutDate) {
		LocalDateTime checkInLocalDateTime = convertStringToLocalDateTime(checkInDate);

		if (checkInLocalDateTime.isBefore(LocalDateTime.now())) {
			throw new WrongTimeException("Check in date is before then now");
		}

		LocalDateTime checkOutLocalDateTime = convertStringToLocalDateTime(checkOutDate);

		if (checkInLocalDateTime.isAfter(checkOutLocalDateTime)) {
			throw new WrongTimeException("Check in date is after then Check out date");
		}
	}

	public Boolean isReservationAlreadyPassed(Reservation reservation) {
		return reservation.getCheckInDate().isBefore(LocalDateTime.now());
	}
}
