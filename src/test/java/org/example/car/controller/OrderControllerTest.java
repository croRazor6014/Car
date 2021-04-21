package org.example.car.controller;

import static org.junit.Assert.*;

import org.example.car.config.RestURIConstants;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by lovro.vrlec on Apr,2021
 */

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {
    RestURIConstants.class}, initializers =
                          ConfigFileApplicationContextInitializer.class)
@WebAppConfiguration
public class OrderControllerTest {

}