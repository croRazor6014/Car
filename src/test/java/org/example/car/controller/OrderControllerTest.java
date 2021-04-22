package org.example.car.controller;

import static org.example.car.config.RestURIConstants.ORDER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.example.car.config.FilterConfig;
import org.example.car.config.RestURIConstants;
import org.example.car.model.Order;
import org.example.car.model.dto.OrderDto;
import org.example.car.model.jsonviews.View;
import org.example.car.service.OrderService;
import org.example.car.util.ModelConverterHelper;
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
import org.springframework.data.jpa.domain.Specification;
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
@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {
    RestURIConstants.class}, initializers =
                          ConfigFileApplicationContextInitializer.class)
@WebAppConfiguration
public class OrderControllerTest {

  public static final String UUID = "/123e4567-e89b-12d3-a456-426655440000";

  private MockMvc mockMvc;

  @Mock
  private OrderService mockOrderService;

  @Spy
  private ModelMapper modelMapper =new ModelMapper();

  @InjectMocks
  private OrderController orderControllerUnderTest;


  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(orderControllerUnderTest).dispatchOptions(true).build();

  }

  @Test
  public void findOrderByUUID() throws Exception {
    mockMvc.perform(get(ORDER + UUID).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void updateOrder() throws Exception {
    final OrderDto testOrderDto = new OrderDto();
    mockMvc.perform(put(ORDER + UUID).contentType(MediaType.APPLICATION_JSON).
        content(ModelConverterHelper.toJson(View.Basic.class, testOrderDto))).andExpect(status().isBadRequest());
  }

  @Test
  public void addOrder() throws Exception {
    final OrderDto testOrderDto = new OrderDto();
    mockMvc.perform(post(ORDER ).
        contentType(MediaType.APPLICATION_JSON).content(ModelConverterHelper.toJson(View.Basic.class, testOrderDto)))
        .andExpect(status().isOk());
  }


}