package com.mws.examtwo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mws.examtwo.models.User;

@Component
public class UserValidator implements Validator{
	
	//this allows the validator to operate on an instance of the User domain model
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	//creates a custom validation. errors are added with .rejectValue
	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;
		
		if(!user.getPasswordConfirmation().equals(user.getPassword())) {
			//the first argument here is a member variable of the model we are validating.
			//the second argument is a code to fetch an error message that we will define in the messages.properties file
			errors.rejectValue("passwordConfirmation", "Match");
		}
	}

}