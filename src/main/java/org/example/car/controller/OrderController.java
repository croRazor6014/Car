package org.example.car.controller;

import static org.example.car.config.RestURIConstants.ORDER;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lovro.vrlec on Apr,2021
 *
 * OrderController is Rest API controller for Order object.
 */

@Slf4j
@Tag(name = "Order Controller", description = "Order Controller")
@RestController
@CrossOrigin
@RequestMapping(ORDER)
@RequiredArgsConstructor
public class OrderController {
}
