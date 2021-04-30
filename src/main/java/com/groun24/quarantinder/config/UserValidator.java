package com.groun24.quarantinder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Services.UserService;

@Component
public class UserValidator implements Validator {
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phonenumber", "NotEmpty");
		
		try {
		if (Period.between(user.getDob().toLocalDate(), LocalDate.now()).getYears() < 18) {
			errors.rejectValue("dob", "Invalid.userForm.age");
			}
		}catch (Exception e) {
		//	errors.rejectValue("dob", "NotEmpty");
		}
		


		if (userService.findByUsername(user.getUsername()) != null) {
			User existing = userService.findByUsername(user.getUsername());
			if (!existing.getDob().equals(user.getDob())) {
				errors.rejectValue("username", "Duplicate.userForm.username");
			}
		}
		

		if (userService.findByEmail(user.getEmail()) != null) {
				errors.rejectValue("email", "Duplicate.userForm.email");
			
		}


		if (userService.findByPhonenumber(user.getPhonenumber()) != null) {
				errors.rejectValue("phonenumber", "Duplicate.userForm.phone");			
		}


		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		
		if (user.getPassword().length() < 1 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}

		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}


	}
}