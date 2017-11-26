package com.intellect.user.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.intellect.common.CommonConstants;
import com.intellect.common.ValidationError;
import com.intellect.user.dto.UserDTO;

@Component
public class UserValidation {

	public List<ValidationError> userValidation(UserDTO request) {
		List<ValidationError> errors = new ArrayList<>();
		String fName = request.getfName().trim();
		if (StringUtils.isEmpty(fName)) {
			errors.add(new ValidationError(CommonConstants.ERROR_CODE, CommonConstants.F_NAME,
					CommonConstants.EMPTY_OR_NULL));
		} else if (!isValidString(fName)) {
			errors.add(new ValidationError(CommonConstants.ERROR_CODE, CommonConstants.F_NAME,
					CommonConstants.INVALID_CHARACTERS));
		}
		String lName = request.getlName().trim();
		if (StringUtils.isEmpty(lName)) {
			errors.add(new ValidationError(CommonConstants.ERROR_CODE, CommonConstants.L_NAME,
					CommonConstants.EMPTY_OR_NULL));
		} else if (!isValidString(lName)) {
			errors.add(new ValidationError(CommonConstants.ERROR_CODE, CommonConstants.L_NAME,
					CommonConstants.INVALID_CHARACTERS));
		}

		String birthDate = request.getBirthDate().trim();
		if (StringUtils.isEmpty(birthDate)) {
			errors.add(new ValidationError(CommonConstants.ERROR_CODE, CommonConstants.DOB,
					CommonConstants.EMPTY_OR_NULL));
		} else if (!isValidDate(birthDate)) {
			errors.add(
					new ValidationError(CommonConstants.ERROR_CODE, CommonConstants.DOB, CommonConstants.INVALID_DATE));
		} else{
			org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern(CommonConstants.DATE_FORMAT);
			DateTime dateTime= formatter.parseDateTime(birthDate);
			if(dateTime.isAfterNow()){
				errors.add(
						new ValidationError(CommonConstants.ERROR_CODE, CommonConstants.DOB, CommonConstants.DOB_ERROR));
			}
		}
		Integer pinCode = request.getPinCode();
		if (StringUtils.isEmpty(pinCode)) {
			errors.add(new ValidationError(CommonConstants.ERROR_CODE, CommonConstants.PIN_CODE,
					CommonConstants.EMPTY_OR_NULL));
		} else if (!isValidNumber(pinCode)) {
			errors.add(new ValidationError(CommonConstants.ERROR_CODE, CommonConstants.PIN_CODE,
					CommonConstants.INVALID_NUMBER));
		}

		String email = request.getEmail().trim();
		if (StringUtils.isEmpty(email)) {
			errors.add(new ValidationError(CommonConstants.ERROR_CODE, CommonConstants.E_MAIL,
					CommonConstants.EMPTY_OR_NULL));
		} else if (!isValidEmail(email)) {

			errors.add(new ValidationError(CommonConstants.ERROR_CODE, CommonConstants.E_MAIL,
					CommonConstants.INVALID_MAIL));
		}

		return errors;

	}

	private boolean isValidString(String value) {
		return value.matches("[a-zA-Z]+");

	}

	private boolean isValidDate(String value) {
		
		return value.matches("[0-9]{1,2}-[a-zA-Z]{3}-[0-9]{4}");

	}

	private boolean isValidNumber(Integer value) {
		if (value.intValue() > 0) {
			return true;
		}
		return false;

	}

	private boolean isValidEmail(String email) {
		final Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}
}
