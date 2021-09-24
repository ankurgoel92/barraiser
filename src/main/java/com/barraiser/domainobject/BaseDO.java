package com.barraiser.domainobject;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseDO {

  @Id
  @GeneratedValue
  private Long id;

  @CreatedDate
  @Column(nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime dateCreated;

  @Column(nullable = false)
  private Boolean deleted = false;


  public BaseDO() {

  }
}
