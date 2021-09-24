package com.barraiser.dataaccessobject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.barraiser.domainobject.ProductDO;

@Repository
public interface ProductRepository extends JpaRepository<ProductDO, Long> {

}
