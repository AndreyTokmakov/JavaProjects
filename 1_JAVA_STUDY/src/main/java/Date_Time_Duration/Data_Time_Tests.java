package Date_Time_Duration;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Data_Time_Tests {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	
	private void LocalDateTime_Tests() {
		final LocalDateTime endTimestamp = LocalDateTime.now();
		System.out.println(endTimestamp);


		
		final Timestamp timestamp = Timestamp.valueOf(endTimestamp);
		System.out.println(timestamp);
		
		
		final LocalDateTime endTimestamp_NULL = LocalDateTime.parse("2018-01-01T00:00:00");
		System.out.println(endTimestamp_NULL);
		
		final Timestamp timestamp_NULL = Timestamp.valueOf("2009-00-00 00:00:00");
		System.out.println(timestamp_NULL);
	}
	
	public void timeToString() {
		final LocalDateTime timestamp = LocalDateTime.now();
		String dateStr = timestamp.format(formatter);
		System.out.println(dateStr);
	}
	
	public void Str2LocalDateTime() {
		final String dateTimeStr = "2016-03-04 11:22:33";
		try {
			LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
			System.out.println("Result: " + dateTime);
		} catch (DateTimeParseException parseException) {
			System.err.println(parseException);
		}
	}
	
	public void Str2LocalDatee() {
		String date = "2020-02-05";
		final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
		    LocalDate datetime = LocalDate.parse(date, pattern);
		    System.out.println(datetime); 
		} catch (final DateTimeParseException exc) {
		    // DateTimeParseException - Text '2019-nov-12' could not be parsed at index 5
		    // Exception handling message/mechanism/logging as per company standard
			System.out.println(exc);
		}
	}
	
	public void GetWeekDay_Spesific() {
		LocalDate localDate = LocalDate.of(2020, 5,4);
		System.out.println(localDate.getDayOfWeek());
	}
	
	public void GetCurrentTime_Calendar() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println( sdf.format(cal.getTime()) );
	}
	
	public void GetCurrentTime() {
		{
			final LocalDateTime timestamp = LocalDateTime.now();
			System.out.println(timestamp);
		}
		{
			final LocalDateTime timestamp = LocalDateTime.now();
			System.out.println(formatter.format(timestamp));
		}
		{
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss.SSS] ");
			final LocalDateTime timestamp = LocalDateTime.now();
			System.out.println(formatter1.format(timestamp));
		}
	}
		
	public void CurrentTime_Date() {
		Date date = new Date(); // This object contains the current date value
		System.out.println(dateFormatter.format(date));
	}
	
	public void GetAvailableTimeZones() {
		System.out.println(ZoneId.getAvailableZoneIds());
	}
	
	public void CurrentTime_Zone(final String zoneName) {
		LocalDateTime date = LocalDateTime.now(ZoneId.of(zoneName)); 
		System.out.println(String.format("%s current time: %s", zoneName, formatter.format(date)));
	}
	
	
	public static void Get_All_Dates_Between_Two_Dates() {
		
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusMonths(2);
		
		{
			System.out.println("Test1: Days between '" + startDate + "' and '" + endDate + "'");
			
			List<LocalDate> listOfDates = startDate.datesUntil(endDate).collect(Collectors.toList());
			System.out.println(listOfDates.size());     // 61
		}
		
		{
			System.out.println("\nTest2: Days between '" + startDate + "' and '" + endDate + "'");
			
			long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
			List<LocalDate> listOfDates = Stream.iterate(startDate, date -> date.plusDays(1)).limit(numOfDays).collect(Collectors.toList());
			System.out.println(listOfDates.size());
			System.out.println(listOfDates);
		}
		
		{
			System.out.println("\nTest3: Days between '" + startDate + "' and '" + endDate + "'");
			long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
	          
			List<LocalDate> listOfDates = LongStream.range(0, numOfDays).mapToObj(startDate::plusDays).collect(Collectors.toList());
			System.out.println(listOfDates.size());     // 61
		}
	}

	public static void FromString_To_LocalDateTime_AndBack()
	{
		DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC+04:00"));

		final String originalTimeString = "2025-03-05 17:12:52";
		try {
			LocalDateTime dateTime = LocalDateTime.parse(originalTimeString, formatter);
			System.out.println(dateTime);

			final String newTimeString = dateTime.format(formatter);

			System.out.println(newTimeString);
			System.out.println(originalTimeString);
		} catch (DateTimeParseException parseException) {
			System.err.println(parseException);
		}
	}

	public static void main(String[] args) throws ParseException
	{
		Data_Time_Tests tests = new Data_Time_Tests();
		
		// tests.GetCurrentTime();
		// tests.GetCurrentTime_Calendar();
		// tests.CurrentTime_Date();
		
		// tests.GetWeekDay_Spesific();
		// tests.LocalDateTime_Tests();
		
		// tests.timeToString();
		
		// tests.Str2LocalDateTime();
		// Str2LocalDatee();
		
		// -------------- Time zones ----------------:
		
		// tests.GetAvailableTimeZones();
		// tests.CurrentTime_Zone("Europe/Paris");
		// tests.CurrentTime_Zone("Cuba");
		
		
		// tests.Get_All_Dates_Between_Two_Dates();

		// System.out.println(LocalDateTime.now());

		FromString_To_LocalDateTime_AndBack();
	}
}
