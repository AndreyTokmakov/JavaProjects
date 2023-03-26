/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* DateFormatting class
*
* @name    : DateFormatting.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 20, 2020
****************************************************************************/ 

package Date_Time_Duration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatting 
{
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss a"; 
    public static final DateTimeFormatter LDT_FOMATTER  = DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN);
    
    public static final String DATE_PATTERN = "yyyy-MM-dd"; 
    public static final DateTimeFormatter LD_FOMATTER  = DateTimeFormatter.ofPattern(DATE_PATTERN);
    
    public static final SimpleDateFormat SIMPLE_FORMATER = new SimpleDateFormat(TIMESTAMP_PATTERN);
	
    public static void main(String[] args) 
    {
    	formatLocalDateTime();
    	formatLocalDate();
    	Simple_Format_Test();
    }

    public static void Simple_Format_Test() 
    {
        Date date = new Date();
        String formattedDate = SIMPLE_FORMATER.format(date);
        System.out.println(formattedDate);      //2020-05-09 00:32:28 AM
    }
 
    private static void formatLocalDateTime()
    {
    	 String dateTimeString = LDT_FOMATTER.format(LocalDateTime.now()); 
         System.out.println(dateTimeString);  
    }
     
    private static void formatLocalDate()
    {
    	String dateString = LD_FOMATTER.format(LocalDate.now()); 
        System.out.println(dateString);  //2020-05-08
    }
}
