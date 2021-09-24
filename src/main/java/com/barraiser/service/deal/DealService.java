package com.barraiser.service.deal;

import com.barraiser.domainobject.DealDO;
import com.barraiser.service.BaseService;

public interface DealService extends BaseService<DealDO, Long> {
  
  public DealDO createDeal(DealDO dealDO);

  public DealDO modify(Long dealId, int plusHours, int plusQuantity);

  public void end(Long dealId);

  public void claim(Long dealId, Long userId);

}
