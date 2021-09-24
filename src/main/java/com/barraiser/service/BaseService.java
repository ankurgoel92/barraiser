package com.barraiser.service;

import java.io.Serializable;
import java.util.List;
import com.barraiser.domainobject.BaseDO;
import com.barraiser.exception.ConstraintsViolationException;
import com.barraiser.exception.EntityNotFoundException;

public interface BaseService<T extends BaseDO, ID extends Serializable> {

  T create(T entity) throws ConstraintsViolationException;

  T update(T entity) throws EntityNotFoundException;

  void delete(ID id) throws EntityNotFoundException;


  T find(ID id) throws EntityNotFoundException;


  List<T> findAll();

}
