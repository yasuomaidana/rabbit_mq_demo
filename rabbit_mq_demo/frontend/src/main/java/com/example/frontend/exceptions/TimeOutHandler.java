package com.example.frontend.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.amqp.AmqpException;
@ControllerAdvice
public class TimeOutHandler {
    @ExceptionHandler(value = AmqpException.class)
    public String timeOut(AmqpException e, Model model){
        model.addAttribute("cause",e.getMessage());
        return "timeout";
    }
}
