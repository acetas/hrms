package com.kodio.hrms.core.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kodio.hrms.business.requests.AddUserRequest;

public class UserValidator {
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValidEmail(final AddUserRequest addUserRequest) {
        Matcher matcher = pattern.matcher(addUserRequest.getEmail());
        return matcher.matches();
    }

	
}
