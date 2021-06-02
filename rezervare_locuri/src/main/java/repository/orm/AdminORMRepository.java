package repository.orm;

import domain.Admin;
import domain.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.IAdminRepository;

import java.util.ArrayList;
import java.util.List;

public class AdminORMRepository implements IAdminRepository {
    SessionFactoryInit sessionFactory;

    public AdminORMRepository(SessionFactoryInit sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Admin loginAdmin(String username, String password) {
        sessionFactory.initialize();
        Admin admin =null;
        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                try {
                    admin =
                            (Admin)session.createQuery("from Admin where username=:fn and password=:ln")
                                    .setParameter("fn", username)
                                    .setParameter("ln", password)
                                    .uniqueResult();
                }catch(Exception ex){
                    ex.printStackTrace();
                }


                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }catch (Exception ex){

            ex.printStackTrace();
        }finally
        {
            sessionFactory.close();
        }
        return admin;

    }
    @Override
    public void update(Admin entity,Admin newEntity) {

    }

    @Override
    public void delete(Long id){

    }

    @Override
    public void save(Admin entity) {

    }
    @Override
    public Admin findOne(Long id_param) {
        return null;
    }
    @Override
    public Iterable<Admin> findAll() {
        sessionFactory.initialize();

        List<Admin> admini = new ArrayList<>();
        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                try {
                    admini =
                            session.createQuery("from Admin").list();
                }catch(Exception ex){
                    ex.printStackTrace();
                }


                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }catch (Exception ex){

            ex.printStackTrace();
        } finally
        {
            sessionFactory.close();
        }
        return admini;

    }



}
