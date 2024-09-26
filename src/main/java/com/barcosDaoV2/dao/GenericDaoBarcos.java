package com.barcosDaoV2.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Flix
 * @param <T>
 * @param <ID>
 */
public interface GenericDaoBarcos<T,ID extends Serializable> {
    void crear(T entity);
    void saveorupdate(T entity);
    T obtener (ID id);
    void delete(ID id);
    List<T> findAll();
}
