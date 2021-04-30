package com.groun24.quarantinder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Services.UserService;

@Component
public class AccountDetailsValidator implements Validator {
	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phonenumber", "NotEmpty");

		if (userService.findByEmail(user.getEmail()) != null) {
			if (userService.findByEmail(user.getEmail()).getId() != user.getId()) {
				errors.rejectValue("email", "Duplicate.userForm.email");

			}

		}

		if (userService.findByPhonenumber(user.getPhonenumber()) != null) {
			if (userService.findByPhonenumber(user.getPhonenumber()).getId() != user.getId()) {
				errors.rejectValue("phonenumber", "Duplicate.userForm.pgone");

			}
		}



	}
}