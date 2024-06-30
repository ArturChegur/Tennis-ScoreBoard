package main.dao;

public interface Dao<T> {
    void add(T entity);

    T findById(Integer id);
}
