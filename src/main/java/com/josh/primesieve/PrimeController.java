package com.josh.primesieve;

import java.util.ArrayList;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@Validated
public class PrimeController {
    @Autowired private PrimeService primeService;

	@GetMapping("/primes")
    public ArrayList<Integer> listPrimes(@Positive @NonNull @RequestParam(name = "value") int maxNumber) {
        return primeService.listPrimes(maxNumber);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleInvalidNumber(ConstraintViolationException e) {
        String message = e.getMessage();
        System.out.println(message);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public void handleInvalidParamType(MethodArgumentTypeMismatchException e) {
        String message = e.getMessage();
        System.out.println(message);
    }

}