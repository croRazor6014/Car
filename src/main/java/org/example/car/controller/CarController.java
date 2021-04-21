package org.example.car.controller;

/**
 * Created by lovro.vrlec on Apr,2021
 */

import static org.example.car.config.RestURIConstants.CAR;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BookingController is Rest API controller for Booking object.
 */
@Slf4j
@Tag(name = "Car Controller", description = "Car Controller")
@RestController
@CrossOrigin
@RequestMapping(CAR)
@RequiredArgsConstructor
public class CarController {
}
