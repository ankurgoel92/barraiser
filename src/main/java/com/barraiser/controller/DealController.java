package com.barraiser.controller;

import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.barraiser.datatransferobject.DealDTO;
import com.barraiser.domainobject.DealDO;
import com.barraiser.domainobject.ProductDO;
import com.barraiser.service.deal.DealService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/deals")
public class DealController {

  private final DealService dealService;

  @GetMapping
  public List<DealDO> getDeals() {
    return dealService.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public DealDO newDeal(@RequestBody DealDTO dealDTO) {
    return dealService.create(toDO(dealDTO));
  }

  @DeleteMapping("{dealId}")
  public void end(@PathVariable("dealId") Long dealId) {
    dealService.end(dealId);
  }

  @PutMapping("{dealId}")
  public void modifyDeal(@PathVariable("dealId") Long dealId,
      @RequestParam("plusHours") int plusHours, @RequestParam("plusQuantity") int plusQuantity) {
    dealService.modify(dealId, plusHours, plusQuantity);
  }

  @PutMapping("{dealId}/{userId}")
  public void claim(@PathVariable("dealId") Long dealId, @PathVariable("userId") Long userId) {
    dealService.claim(dealId, userId);
  }

  private DealDO toDO(DealDTO dealDTO) {
    DealDO dealDO = new DealDO();
    ZonedDateTime now = ZonedDateTime.now();
    dealDO.setName("newDeal");
    dealDO.setDateCreated(now);
    dealDO.setDeleted(false);
    dealDO.setExpireAt(now.plusHours(2));
    dealDO.setMaxQuantity(dealDTO.getMaxQuantity());
    dealDO.setPrice(dealDTO.getPrice());
    ProductDO product = new ProductDO();
    product.setId(dealDTO.getProductId());
    dealDO.setProduct(product);
    return dealDO;
  }
}
