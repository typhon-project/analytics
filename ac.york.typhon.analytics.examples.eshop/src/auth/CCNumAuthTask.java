package auth;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTask;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

public class CCNumAuthTask extends GenericAuthorisationTask {

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
//		System.out.println("CC: " + creditcard);
		String number = creditcard;
		number = number.substring(number.indexOf("number:") + 9);
		number = number.substring(0, number.indexOf("\""));
		// System.out.println("NUMBER: " + number);
		boolean reject = true;
		try {
			reject = !new CreditCard().isValid(Long.parseLong(number.trim().replace("-", "")));
		} catch (Exception e) {
		}
		return reject;
	}

	// https://www.geeksforgeeks.org/program-credit-card-number-validation/
	private class CreditCard {

		// Return true if the card number is valid
		public boolean isValid(long number) {
			return (getSize(number) >= 13 && getSize(number) <= 16)
					&& (prefixMatched(number, 4) || prefixMatched(number, 5) || prefixMatched(number, 37)
							|| prefixMatched(number, 6))
					&& ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0);
		}

		// Get the result from Step 2
		public int sumOfDoubleEvenPlace(long number) {
			int sum = 0;
			String num = number + "";
			for (int i = getSize(number) - 2; i >= 0; i -= 2)
				sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

			return sum;
		}

		// Return this number if it is a single digit, otherwise,
		// return the sum of the two digits
		public int getDigit(int number) {
			if (number < 9)
				return number;
			return number / 10 + number % 10;
		}

		// Return sum of odd-place digits in number
		public int sumOfOddPlace(long number) {
			int sum = 0;
			String num = number + "";
			for (int i = getSize(number) - 1; i >= 0; i -= 2)
				sum += Integer.parseInt(num.charAt(i) + "");
			return sum;
		}

		// Return true if the digit d is a prefix for number
		public boolean prefixMatched(long number, int d) {
			return getPrefix(number, getSize(d)) == d;
		}

		// Return the number of digits in d
		public int getSize(long d) {
			String num = d + "";
			return num.length();
		}

		// Return the first k number of digits from
		// number. If the number of digits in number
		// is less than k, return number.
		public long getPrefix(long number, int k) {
			if (getSize(number) > k) {
				String num = number + "";
				return Long.parseLong(num.substring(0, k));
			}
			return number;
		}
	}

}
