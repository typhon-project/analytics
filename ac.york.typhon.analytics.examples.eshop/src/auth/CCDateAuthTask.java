package auth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.zone.ZoneOffsetTransitionRule;
import java.util.Date;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTask;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

public class CCDateAuthTask extends GenericAuthorisationTask {

	@Override
	public boolean checkCondition(Event event) {
		if (event.getQuery().toLowerCase().contains("order ")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean shouldIReject(Event event) {
		String query = event.getQuery();
		// this is specific to orders that only have new creditcards inserted
		String creditcard = query;
		creditcard = creditcard.substring(creditcard.indexOf("CreditCard") + 12);
		creditcard = creditcard.substring(0, creditcard.indexOf("}"));
		//System.out.println("CC: " + creditcard);
		String date = creditcard;
		date = date.substring(date.indexOf("expiryDate:") + 13);
		date = date.substring(0, date.indexOf("\""));
//		System.out.println("DATE: " + date);
		//
		// Instantiating the SimpleDateFormat class
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// Parsing the given String to Date object
		Date d = null;
		try {
			d = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//System.out.println("Date query value: " + date);
		//System.out.println("Date object value: " + d);
		//
		//System.out.println("epiry: " + d.getTime());
		//System.out.println("curre: " + LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(0)) * 1000);
		//
		return d.getTime() <= LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(0)) * 1000;
	}
}
