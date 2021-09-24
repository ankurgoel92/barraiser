package com.barraiser.service.deal;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.barraiser.dataaccessobject.DealRepository;
import com.barraiser.dataaccessobject.ProductRepository;
import com.barraiser.domainobject.DealDO;
import com.barraiser.domainobject.ProductDO;
import com.barraiser.exception.EntityNotFoundException;
import com.barraiser.service.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultDealService extends BaseServiceImpl<DealDO, Long> implements DealService {

  private final DealRepository dealRepository;
  private final ProductRepository productRepository;
  private Map<Long, Set<Long>> userDeals = new ConcurrentHashMap<>();

  @Override
  protected JpaRepository<DealDO, Long> getRepository() {
    return dealRepository;
  }

  @Override
  public DealDO createDeal(DealDO dealDO) {
    log.info("creating a deal {}", dealDO);
    ProductDO product = productRepository.findById(dealDO.getProduct().getId())
        .orElseThrow(() -> new EntityNotFoundException("Invalid Product"));
    dealDO.setProduct(product);
    return create(dealDO);
  }

  @Override
  public DealDO modify(Long dealId, int plusHours, int plusQuantity) {
    log.info("modifying deal deal {}", dealId);
    DealDO deal = find(dealId);
    if (plusQuantity != 0) {
      deal.setMaxQuantity(deal.getMaxQuantity() + plusQuantity);
    }
    if (plusHours != 0) {
      deal.setExpireAt(deal.getExpireAt().plusHours(plusHours));
    }
    return create(deal);
  }

  @Override
  public void end(Long dealId) {
    log.info("ending deal {}", dealId);
    DealDO deal = find(dealId);
    deal.setExpireAt(ZonedDateTime.now());
    deal.setDeleted(true);
    create(deal);
  }

  @Override
  public void claim(Long dealId, Long userId) {
    if (userDeals.containsKey(userId)) {
      Set<Long> dealIds = userDeals.get(userId);
      if (dealIds.contains(dealId)) {
        log.error("user {} is claiming deal again", userId);
        throw new RuntimeException("cannot apply for same deal more than once.");
      }
    }
    log.info("Deal {} is claimed by user {}", dealId, userId);
    modify(dealId, 0, -1);
    userDeals.putIfAbsent(userId, new HashSet<>());
    userDeals.get(userId).add(dealId);
  }
}
