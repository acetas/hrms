package com.kodio.hrms.core.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kodio.hrms.business.requests.UserRequest;

public class UserValidator {
	
    public static boolean isValidEmail(UserRequest addUserRequest) {
    	
    	String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        
        Matcher matcher = pattern.matcher(addUserRequest.getEmail());
        return matcher.matches();
    }

	
}
