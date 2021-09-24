package com.barraiser.dataaccessobject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.barraiser.domainobject.DealDO;

@Repository
public interface DealRepository extends JpaRepository<DealDO, Long> {

}
