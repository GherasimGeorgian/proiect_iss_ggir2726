package repository;


import domain.Client;

public interface IClientRepository extends IRepository<Long, Client>{
    Client loginClient(String username, String password);
}