package com.alphabank.typhon.commons;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public abstract class Utils {

	public static long yearDifference(String date) {
		System.out.println(date);// 1920-01-01
//		date = "1920-12-01";
		long years = 0;
		try {
//			
//			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
//	        String dateInString = "7-Jun-2013";
//
//	        try {
//
//	            Date datet = formatter.parse(dateInString);
//	            System.out.println(datet);
//	            System.out.println(formatter.format(datet));
//
//	        } catch (ParseException e) {
//	            e.printStackTrace();
//	        }
//	        
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date myDate = sdf.parse(date);
//			
			
//			 Date paresedDate = new SimpleDateFormat().parse(date);
//			
//			   String sDate1="31/12/1998";  
//			    String sDate2 = "31-Dec-1998";  
//			    String sDate3 = "12 31, 1998";  
//			    String sDate4 = "Thu, Dec 31 1998";  
//			    String sDate5 = "Thu, Dec 31 1998 23:37:50";  
//			    String sDate6 = "31-Dec-1998 23:37:50";  
//			    SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");  
//			    SimpleDateFormat formatter2=new SimpleDateFormat("dd-MMM-yyyy");  
//			    SimpleDateFormat formatter3=new SimpleDateFormat("MM dd, yyyy");  
//			    SimpleDateFormat formatter4=new SimpleDateFormat("E, MMM dd yyyy");  
//			    SimpleDateFormat formatter5=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");  
//			    SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");  
//			    Date date1=formatter1.parse(sDate1);  
//			    Date date2=formatter2.parse(sDate2);  
//			    Date date3=formatter3.parse(sDate3);  
//			    Date date4=formatter4.parse(sDate4);  
//			    Date date5=formatter5.parse(sDate5);  
//			    Date date6=formatter6.parse(sDate6);  
//			    System.out.println(sDate1+"\t"+date1);  
//			    System.out.println(sDate2+"\t"+date2);  
//			    System.out.println(sDate3+"\t"+date3);  
//			    System.out.println(sDate4+"\t"+date4);  
//			    System.out.println(sDate5+"\t"+date5);  
//			    System.out.println(sDate6+"\t"+date6); 
			    
			    
//			String sDate1="31/12/1998";  
//		    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
//		    System.out.println(sDate1+"\t"+date1.);  
		    
			
			
			Date dateWithoutTime = new SimpleDateFormat("yyyy-MM-dd").parse(date);
//			dateWithoutTime = sdf.parse(sdf.format(dateWithoutTime));
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date dateWithoutTime = sdf.parse(sdf.format(date)); //sdf.format(new Date())
			
			System.out.println(dateWithoutTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateWithoutTime);

			System.out.println(calendar.get(Calendar.YEAR));
			System.out.println(calendar.get(Calendar.MONTH)+1);
			System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
			
			years = ChronoUnit.YEARS.between(
					LocalDate.of(calendar.get(Calendar.YEAR),
							calendar.get(Calendar.MONTH)+1,
							calendar.get(Calendar.DAY_OF_MONTH)),
					LocalDate.now(ZoneId.of("America/Montreal")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return years;
	}

}
