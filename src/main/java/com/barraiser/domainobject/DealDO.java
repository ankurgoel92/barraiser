package com.barraiser.domainobject;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "deal")
public class DealDO extends BaseDO {

  @Column(nullable = false)
  @NotNull(message = "name can not be null!")
  private String name;

  @Column(nullable = false)
  @NotNull(message = "price can not be null!")
  private Double price;

  @Column(nullable = false)
  @NotNull(message = "maxQuantity can not be null!")
  private int maxQuantity;

  @Column(nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime expireAt;

  @ManyToOne
  private ProductDO product;

  public DealDO() {

  }

}
