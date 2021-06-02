package repository;


import domain.Admin;

public interface IAdminRepository extends IRepository<Long, Admin>{
    Admin loginAdmin(String username, String password);
}