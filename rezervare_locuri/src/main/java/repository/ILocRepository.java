package repository;

import domain.Loc;

public interface ILocRepository  extends IRepository<Long, Loc>{
    long getMaxId();
}
