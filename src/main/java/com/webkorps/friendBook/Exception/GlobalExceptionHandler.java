package com.webkorps.friendBook.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice 
public class GlobalExceptionHandler {

	@ExceptionHandler
	public String handleUserNotFoundException(UserNotFoundException ex,Model m)
	{
		
		m.addAttribute("error1", "No userFound");
		return "SearchedList";
	}
	@ExceptionHandler
	public String handleInvalidException(InvalidCredentialsException ex, Model m)
	{
        m.addAttribute("error1", "Invalid email or password");
		return "redirect:/users";
	}
}
