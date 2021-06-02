package repository;

public interface IRepository<ID, T> {

    void save(T entity);
    T findOne(ID id);
    Iterable<T> findAll();
    void update(T entity,T newEntity);
    void delete(ID id);
}
