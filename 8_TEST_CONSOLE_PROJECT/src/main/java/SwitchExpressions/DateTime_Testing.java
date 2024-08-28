package SwitchExpressions;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DateTime_Testing {

	public static void main(String[] args) {
		
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusMonths(2);

		// INFO: Following next two lines are the same
        // List<LocalDate> listOfDates = startDate.datesUntil(endDate).collect(Collectors.toList());
		List<LocalDate> listOfDates = startDate.datesUntil(endDate).toList();

		System.out.println(listOfDates.size());     // 61
	}

}
