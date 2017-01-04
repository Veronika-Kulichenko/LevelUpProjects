package com.levelup.dao;

import java.util.List;

/**
 * Created by Alexandr Shegeda on 30.09.16.
 */
public interface HibernateDao<T> {

    void save(T t);
    void saveAll(List<T> list);

    void delete(T t);
    void deleteAll(List<T> list);

    void update(T t);
    void updateAll(List<T> list);

    T getOneById(long id);
    List<T> getAll();
}
