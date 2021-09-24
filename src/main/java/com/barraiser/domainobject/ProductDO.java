package com.barraiser.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductDO extends BaseDO {

  @Column(nullable = false)
  @NotNull(message = "product name can not be null!")
  private String name;

  public ProductDO() {

  }

  public ProductDO(Long id) {
    super.setId(id);
  }

}
