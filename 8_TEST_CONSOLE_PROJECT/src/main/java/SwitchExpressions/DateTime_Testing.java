package SwitchExpressions;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DateTime_Testing {

	public static void main(String[] args) {
		
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusMonths(2);
		List<LocalDate> listOfDates = startDate.datesUntil(endDate).collect(Collectors.toList());
		System.out.println(listOfDates.size());     // 61
	}

}
