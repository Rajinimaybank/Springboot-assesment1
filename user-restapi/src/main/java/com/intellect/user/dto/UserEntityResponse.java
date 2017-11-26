package com.intellect.user.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

import com.intellect.common.CommonConstants;

public class UserEntityResponse extends UserDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserEntityResponse() {
		super();
	}

	public void setBirthDate(DateTime birthDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
		Date date=birthDate.toDate();
		super.birthDate = formatter.format(date);
	}
}
