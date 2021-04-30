package com.groun24.quarantinder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.sql.Date;
import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Services.UserService;

@Component
public class PasswordValidator implements Validator {
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

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}
		
		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}

		if (user.getOldPasswordConfirm() != null) {
			if (!bCryptPasswordEncoder.matches(user.getOldPasswordConfirm(), userService.get(user.getId()).getPassword())) {
				user.setOldPasswordConfirm(null);
				errors.rejectValue("oldPasswordConfirm", "Diff.userForm.oldPasswordConfirm");
			}
		}

	}
}