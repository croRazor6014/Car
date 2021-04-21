package org.example.car.controller;

import static org.example.car.config.RestURIConstants.CAR;
import static org.example.car.config.RestURIConstants.USER;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lovro.vrlec on Apr,2021
 */

@Slf4j
@Tag(name = "Car Controller", description = "Car Controller")
@RestController
@CrossOrigin
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
}
