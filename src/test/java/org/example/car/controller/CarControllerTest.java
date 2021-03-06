package org.example.car.controller;

import static org.example.car.config.RestURIConstants.CAR;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.car.config.RestURIConstants;
import org.example.car.model.Car;
import org.example.car.model.dto.CarDto;
import org.example.car.model.jsonviews.View;
import org.example.car.service.CarService;
import org.example.car.util.ModelConverterHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by lovro.vrlec on Apr,2021
 */

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {
    RestURIConstants.class}, initializers =
                          ConfigFileApplicationContextInitializer.class)
@WebAppConfiguration
public class CarControllerTest {

  public static final String UUID = "/123e4567-e89b-12d3-a456-426655440000";

  private MockMvc mockMvc;

  @Mock
  private CarService mockCarService;

  @Spy
  private ModelMapper modelMapper =new ModelMapper();

  @InjectMocks
  private CarController carControllerUnderTest;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(carControllerUnderTest).dispatchOptions(true).build();
  }

  @Test
  public void findCarByUUID() throws Exception {
    mockMvc.perform(get(CAR + UUID).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void updateCar() throws Exception {
    final CarDto testCarDto = new CarDto();
    mockMvc.perform(put(CAR + UUID).contentType(MediaType.APPLICATION_JSON).
        content(ModelConverterHelper.toJson(View.Basic.class, testCarDto))).andExpect(status().isNotFound());
  }

  @Test
  public void addCar() throws Exception {
    final CarDto testCarDto = new CarDto();
    mockMvc.perform(post(CAR).
        contentType(MediaType.APPLICATION_JSON).content(ModelConverterHelper.toJson(View.Basic.class, testCarDto)))
        .andExpect(status().isOk());
  }


}