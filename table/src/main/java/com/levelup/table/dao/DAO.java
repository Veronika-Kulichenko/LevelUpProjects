package com.levelup.table.dao;

import java.util.ArrayList;

public interface DAO<T> {

    void Create(T t);

    ArrayList<T> Read();

    void Update(T t);

    void Delete(T t);

    T getOneById(long id);
}
