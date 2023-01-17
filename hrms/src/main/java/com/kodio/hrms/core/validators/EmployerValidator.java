package com.kodio.hrms.core.validators;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kodio.hrms.business.requests.EmployersRequest;

public class EmployerValidator {

    public static boolean isValidCompanyEmail(EmployersRequest addEmployersRequests) throws MalformedURLException {
        
        URL companyURL = new URL(addEmployersRequests.getWebsite());
        String newCompanyURL = companyURL.getHost();
		
        if(newCompanyURL.startsWith("www.")) {
        	newCompanyURL = newCompanyURL.substring(4);
        }
        
        String[] companyEmailPattern = newCompanyURL.split("[.]");
		
		String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@" + companyEmailPattern[0] + "\\." + companyEmailPattern[1] + "$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    	
    	Matcher matcher = pattern.matcher(addEmployersRequests.getEmail());
    	
        return matcher.matches();
        
    }
	
}
