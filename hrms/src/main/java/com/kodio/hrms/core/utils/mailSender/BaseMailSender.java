package com.kodio.hrms.core.utils.mailSender;

import java.io.UnsupportedEncodingException;

import com.kodio.hrms.entities.concretes.User;

import jakarta.mail.MessagingException;

public interface BaseMailSender {

	void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;
	
}
