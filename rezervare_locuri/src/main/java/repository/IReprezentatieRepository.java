package repository;

import domain.Reprezentatie;

public interface IReprezentatieRepository  extends IRepository<Long, Reprezentatie>{
    long getMaxId();
}
