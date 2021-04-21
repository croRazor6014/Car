package org.example.car.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lovro.vrlec on Apr,2021
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NonExistentOrderException extends Exception{
}
