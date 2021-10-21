package com.sda.project.validator;

import com.sda.project.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class UserValidator {


    public void validate(UserDto userDto, BindingResult bindingResult) {
        if (userDto.getFirstName().isEmpty()) {
            FieldError fieldError = new FieldError("userDto", "firstName", "This field cannot be empty.");
            bindingResult.addError(fieldError);
        }

        if (userDto.getLastName().isEmpty()) {
            FieldError fieldError = new FieldError("userDto", "lastName", "This field cannot be empty.");
            bindingResult.addError(fieldError);
        }

        if (!userDto.getEmail().contains("@")) {
            FieldError fieldError = new FieldError("userDto", "email", "Your e-mail address must contain '@'.");
            bindingResult.addError(fieldError);
        }

        if (userDto.getPassword().length() < 5) {
            FieldError fieldError = new FieldError("userDto", "password", "Your password must have at least 5 characters.");
            bindingResult.addError(fieldError);
        }


        if (!userDto.getPassword().toLowerCase().contains("[a-z]") && userDto.getPassword().contains("[0-9]")) {
            FieldError fieldError = new FieldError("userDto", "password", "Your password must contain at least 1 letter and 1 digit.");
            bindingResult.addError(fieldError);
        }


    }
}
