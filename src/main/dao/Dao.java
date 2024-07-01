package main.dao;

import java.util.List;

public interface Dao<T> {
    void add(T entity);

    List<T> findAll();
}
