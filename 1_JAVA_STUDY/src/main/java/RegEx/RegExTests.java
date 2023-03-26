/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* RegExTests.java class
*
* @name    : RegExTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 17, 2020
****************************************************************************/

package RegEx;

import java.util.regex.Pattern;

class RomanNumerals {
	private static final Pattern ROMAN = 
			Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
	
	public boolean isRomanNumeral(String s) {
		return ROMAN.matcher(s).matches();
	}
}

final class IPValidator {
	public static final String IP_PATTERN
			= "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";

	public boolean isIpValid(String address) {
		return address.matches(IP_PATTERN);
	}
}

public class RegExTests {

	public static void main(String[] args)
	{
		/*
		RomanNumerals R = new RomanNumerals();
		
		System.out.println(R.isRomanNumeral("V"));
		System.out.println(R.isRomanNumeral("X"));
		System.out.println(R.isRomanNumeral("11"));
		*/

		IPValidator validator = new IPValidator();
		System.out.println(validator.isIpValid("121.234.12.12"));
		System.out.println(validator.isIpValid("121.234.12.1222"));
	}
}
