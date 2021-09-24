package com.barraiser.service;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.barraiser.domainobject.BaseDO;
import com.barraiser.exception.ConstraintsViolationException;
import com.barraiser.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * Encapsulates common logic
 */
@Slf4j
@Service
public abstract class BaseServiceImpl<T extends BaseDO, ID extends Serializable>
    implements BaseService<T, ID> {

  @PersistenceContext
  protected EntityManager entityManager;

  protected abstract JpaRepository<T, ID> getRepository();

  @Override
  public T create(T entity) throws ConstraintsViolationException {
    try {
      entity = getRepository().save(entity);
    } catch (DataIntegrityViolationException e) {
      log.warn("Some constraints are violated due to Entity creation: " + entity.getClass(), e);
      throw new ConstraintsViolationException(e.getMessage());
    }
    return entity;
  }

  @Override
  public T update(T entity) throws ConstraintsViolationException {
    return create(entity);
  }

  @Override
  @Transactional
  public void delete(ID id) throws EntityNotFoundException {
    T entity = find(id);
    entity.setDeleted(true);
  }


  @Override
  public T find(ID id) throws EntityNotFoundException {
    return getRepository().findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + id));
  }

  @Override
  public List<T> findAll() {
    return getRepository().findAll();
  }

}
