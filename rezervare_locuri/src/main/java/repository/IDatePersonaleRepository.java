package repository;


import domain.DatePersonale;

public interface IDatePersonaleRepository  extends IRepository<Long, DatePersonale>{
    long getMaxId();
}
