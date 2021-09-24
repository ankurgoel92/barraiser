package com.barraiser.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DealDTO {

  private Long productId;

  private double price;

  private int maxQuantity;

}
