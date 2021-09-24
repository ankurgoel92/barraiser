package com.barraiser.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.barraiser.service.deal.DealService;

@RunWith(SpringRunner.class)
@WebMvcTest(DealController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DealControllerUnitTest {

  private static final String BAD_REQUEST = "BAD_REQUEST_BODY";
  private static final String DEAL_JSON = "";
  private static final String BASE_URL = "/v1/deals/";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DealService carService;


  @Test
  public void testGetCar() throws Exception {

    when(carService.find(1l)).thenReturn(null);

    mockMvc.perform(get(BASE_URL + 1l)).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(DEAL_JSON));

    verify(carService, times(1)).find(1L);
  }
}
